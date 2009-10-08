package com.cma.intervideo.radGateway.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cma.intervideo.service.NotificationHandleService;
import com.cma.intervideo.util.PropertiesHelper;

public class SocketGateway extends HttpServlet {
	private static Log logger = LogFactory.getLog(SocketGateway.class);
	private static ApplicationContext ctx = null;
	private static Hashtable<String, BaseRequest> reqToRes = new Hashtable<String, BaseRequest>(1000);
	private static Object sendLock = new Object();
	private static SocketGateway instance;
	Socket mcuSocket = null;
	DataOutputStream os = null;
	PrintWriter pw = null;
	InputStream is = null;
	boolean flag = true;
	private LinkedBlockingQueue<BaseNotification>[] queue;
	private int queueNum = 20;
	/**
	 * 测试类,定期调用GetServices接口以测试连接是否正常,如果测试失败则重新建立连接
	 * @author lihw2
	 *
	 */
	class TestTask extends TimerTask{
		private SocketGateway sg = null;
		public TestTask(SocketGateway sg){
			this.sg = sg;
		}
		public void run(){
			GetServicesRequest req = new GetServicesRequest();
			GetServicesResponse rsp = (GetServicesResponse)sg.call(req);
			//logger.info(req.getXml());
			if(rsp == null){
				logger.info("test MCU connection failed, begin to reconnect...");
				sg.reconnect();
			}else{
				//logger.info(rsp.getXml());
				logger.debug("test MCU connection success");
			}
		}
	}
	/**
	 * 处理消息线程,从消息队列中取出消息进行处理,同一个会议的消息在同一个队列,因而由同一个线程进行处理
	 * @author lihw2
	 *
	 */
	class HandleThread extends Thread{
		private int tindex;
		public HandleThread(int tindex){
			this.tindex = tindex;
		}
		public void run(){
			logger.info("handle thread "+tindex+" begin to run...");
			//获取消息处理类
			NotificationHandleService service = (NotificationHandleService)ctx.getBean("notificationHandleService");
			
			while(true){
				BaseNotification notification = null;
				try{
					notification = queue[tindex].take();
					service.handle(notification);
				}catch(Exception e){
					logger.error("handle notification exception. confGID:" + notification.getConfGID(), e);
					continue;
				}
			}
		}
	}
	/**
	 * 从连接中读取消息,解析后放置到消息队列中等待处理
	 * @author lihw2
	 *
	 */
	class ReceiveThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//存放读取到的数据的数组
			byte[] bs = new byte[10240];
			// bs数组的偏移量，当一次从socket读取的消息不是完整的一条xml消息后，就需要从数组偏移位置开始继续读取
			int offset = 0;
			logger.info("began to receive...");
			while (flag) {
				try {
					// 从socket读到的字节数
					int rt;
					try {
						//保证读取的数据不会溢出接收数组
						rt = is.read(bs, offset, 10240 - offset);
						if (rt == -1) {
							// 没有读到任何数据
							throw new IOException("socket reader is -1");
						}
					} catch (InterruptedIOException eTimeout) {
						logger.warn(eTimeout.toString());
						continue;
					}
					//数组偏移增加
					offset = offset + rt;
					// 将字节数组转换成字符串
					String rr = new String(bs, 0, offset, "utf-8");
					logger.debug(rr);
					//消息同步
					int startIndex = rr.indexOf("<?xml version=\"1.0\" encoding=\"UTF-8\"?><MCU_XML_API>");
					if(startIndex >0){
						//消息失去同步
						logger.info("消息失去同步，重新同步");
						logger.info(rr);
						String errstr = rr.substring(0,startIndex);
						int errlen = errstr.getBytes("utf-8").length;
						//将前面失同步的字节删除，并调整字节数组偏移
						offset = offset - errlen;
						for(int i=0;i<offset;i++){
							bs[i] = bs[i+errlen];
						}
						rr = new String(bs, 0, offset, "utf-8");
						logger.info(rr);
					}
					if(startIndex <0){
						//消息不完整，继续读取
						if(offset >= 10240){
							logger.error("can't find <?xml version=\"1.0\" encoding=\"UTF-8\"?><MCU_XML_API>");
							logger.error(rr);
							offset = 0;
						}else{
							logger.info("receive intact message without start flag");
							logger.info(rr);
						}
						continue;
					}
					// len用来表示一条xml消息的长度
					int len = rr.indexOf("</MCU_XML_API>");
					if (len < 0) {
						// 未找到结束标志，此时可能发生两种情况，一是消息没有读完整，二是消息超过最大消息长度10240
						if (offset >= 10240) {
							// 消息超过最大长度，丢弃该消息，大于的情况不可能发生，但是在等于的时候说明已经超出接收数组能力，予以抛弃
							logger.error("can't find </MCU_XML_API>");
							offset = 0;
						} else {
							// 消息不完整，继续读取
							logger.info("receive intact message without end flag");
							logger.info(rr);
						}
						continue;
					}
					// 计算xml消息字符串的长度，注意此长度为字符串长度而不是字节长度
					len = len + 14;
					// rr中xml消息的起始位置，rr中可能包含数条xml消息，此位置为字符位置，而不是字节位置
					int i = 0;
					// bs数组中的字节位置
					int bi = 0;
					String aMessage = rr.substring(i, i + len);
					handleOneMessage(aMessage);
					i = i + len;
					bi = bi + aMessage.getBytes("utf-8").length;
					// 此时rr中包含超过一条消息
					while (bi < offset) {
						len = rr.indexOf("</MCU_XML_API>", i);
						if (len < 0)
							break;
						len = len + 14 - i;
						aMessage = rr.substring(i, i + len);
						handleOneMessage(aMessage);
						i = i + len;
						bi = bi + aMessage.getBytes("utf-8").length;
					}
					if (len < 0) {
						logger.info("receive intact message without end flag");
						logger.info(rr);
						//将前面已经处理的字节删除，并调整偏移
						offset = offset - bi;
						for (int j = 0; j < offset; j++) {
							bs[j] = bs[j+bi];
						}
						continue;
					}
					//此时说明是由于bi==offset导致的正常退出
					offset = 0;
				} catch (Exception e) {
					logger.error(e.toString());
					//等待一段时间,出现异常的原因可能是连接中断,等待重新连接
					try{
						Thread.sleep(1000);
					}catch(Exception ex){
						logger.error(ex.toString());
					}
				}
			}
		}

	}
	

	/**
	 * Constructor of the object.
	 */
	public SocketGateway() {
		super();
	}
	
	
	
	public static ApplicationContext getCtx() {
		return ctx;
	}



	public static void setCtx(ApplicationContext ctx) {
		SocketGateway.ctx = ctx;
	}



	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
		try{
			flag = false;
			os.close();
			is.close();
			mcuSocket.close();
			logger.info("disconnect from radvision proxy gateway");
		}catch(UnknownHostException e){
			logger.error(e.toString());
		}catch(IOException e){
			logger.error(e.toString());
		}
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
//		if (true) return;
		// Put your code here
		if(ctx==null){
			ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		}
		boolean startMcuProxySocket = PropertiesHelper.startMcuProxySocket();
		if(!startMcuProxySocket){
			logger.info("socket gateway are setuped to not start");
			return;
		}
		logger.info("begin to start socket gateway...");
		String mcuIp = PropertiesHelper.getMcuProxyIp();
		int mcuPort = PropertiesHelper.getMcuProxyPort();
		try{
			mcuSocket = new Socket();
			mcuSocket.connect(new InetSocketAddress(mcuIp,mcuPort),5*1000);
			logger.info("connected to radvision proxy gateway");
			os = new DataOutputStream(mcuSocket.getOutputStream());
			pw = new PrintWriter(os,true);
			is = mcuSocket.getInputStream();
			queueNum = PropertiesHelper.getMcuProxyQueueNum();
			queue = new LinkedBlockingQueue[queueNum];
			for(int i=0;i<queueNum;i++){
				queue[i] = new LinkedBlockingQueue();
				new HandleThread(i).start();
			}
			new ReceiveThread().start();
			instance = this;
			//启动定时测试
			Timer timer = new Timer();
			TestTask tt = new TestTask(this);
			//每testPeriod秒钟进行一次测试
			int testPeriod = PropertiesHelper.getMcuConnectionTestPeriod();
			timer.schedule(tt, Calendar.getInstance().getTime(), testPeriod);
			NotificationHandleService service = (NotificationHandleService)ctx.getBean("notificationHandleService");
			GetConferenceListRequest request = new GetConferenceListRequest();
			GetConferenceListResponse response = (GetConferenceListResponse)this.call(request);
			if(response == null){
				logger.error("GetConferenceListResponse响应为空！");
				return;
			}
			List<Conf> confs = response.getConfList();
			for(int i=0;i<confs.size();i++){
				Conf conf = confs.get(i);
				GetParticipantListRequest prequest = new GetParticipantListRequest();
				prequest.setConfGID(conf.getConfGID());
				GetParticipantListResponse presponse = (GetParticipantListResponse)this.call(prequest);
				if(presponse == null){
					logger.error("GetParticipantListReponse响应为空！");
					return;
				}
				conf.setParts(presponse.getPartList());
				conf.setDesktopPID(presponse.getDesktopPID());
			}
		}catch(UnknownHostException e){
			logger.error(e.toString());
		}catch(IOException e){
			logger.error(e.toString());
		}
	}
	/**
	 * 重新建立MCU连接
	 *
	 */
	public void reconnect(){
		//关闭原有连接
		try{
			is.close();
			pw.close();
			os.close();
			mcuSocket.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		//建立新的连接
		try{
			String mcuIp = PropertiesHelper.getMcuProxyIp();
			int mcuPort = PropertiesHelper.getMcuProxyPort();
			mcuSocket = new Socket();
			mcuSocket.connect(new InetSocketAddress(mcuIp,mcuPort),5*1000);
			logger.info("reconnected to radvision proxy gateway");
			os = new DataOutputStream(mcuSocket.getOutputStream());
			pw = new PrintWriter(os,true);
			is = mcuSocket.getInputStream();
		}catch(UnknownHostException e){
			logger.error(e.toString());
		}catch(IOException e){
			logger.error(e.toString());
		}
	}
	public BaseResponse call(BaseRequest request){
		request.encode();
		reqToRes.put(request.getRequestId(), request);
		try{
			synchronized(request){
				synchronized(sendLock){
					pw.print(request.getXml());
					pw.flush();
				}
				request.wait(10000);
			}
			return request.getResponse();
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}finally{
			reqToRes.remove(request.getRequestId());
		}
	}
	public static SocketGateway getInstance() {
		return instance;
	}
	public static void setInstance(SocketGateway instance) {
		SocketGateway.instance = instance;
	}
	//解析一条xml消息,如果是响应则解除调用阻塞,如果是通知则放入相应消息队列中
	private void handleOneMessage(String aMessage){
		try {
			if (aMessage
					.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?><MCU_XML_API><Version>"
							+ PropertiesHelper.getMcuProxyVersion()
							+ "</Version><Response>")) {
				BaseResponse response = BaseResponse.parse(aMessage);
				BaseRequest request = (BaseRequest) reqToRes.get(response
						.getRequestId());
				request.setResponse(response);
				synchronized (request) {
					request.notify();
				}
			} else {
				// 对消息进行解析
				BaseNotification n = BaseNotification.parse(aMessage);
				// 处理消息
				if (n != null) {
					int confGID = Integer.parseInt(n.getConfGID());
					queue[confGID % queueNum].put(n);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
}