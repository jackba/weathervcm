package com.cma.intervideo;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.util.RuntimeInfo;
import com.radvision.icm.service.vcm.ICMService;

public class StatusMonitorServlet extends VcmServlet {
	
	private static Log logger = LogFactory.getLog(StatusMonitorServlet.class);
	long interval = PropertiesHelper.getStatusMonitorInterval();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	public void init() throws ServletException {
		/**
		 * 启动定时测试iCM Service, 每testPeriod秒钟进行一次测试
		 */
		Timer timer = new Timer();
		TestTask4IcmService tt = new TestTask4IcmService();
		timer.schedule(tt, Calendar.getInstance().getTime(), interval);
		logger.info("Started checking iCM Service connection per " + interval + " milliseconds.");
		
		/**
		 * 启动服务器状态定时检测
		 */
		StatusMonitorTask tt2 = new StatusMonitorTask(this);
		timer.schedule(tt2, Calendar.getInstance().getTime(), interval);
		
	}
	
	class TestTask4IcmService extends TimerTask {
		public void run(){
			boolean connected = ICMService.isConnected();
			RuntimeInfo.setIcmServiceConnected(connected);
			if (!connected)
				logger.warn("Test RADVISION iCM Service connection failed, please check the connnection and configuration!");
			else
				logger.warn("Test RADVISION iCM Service connection successfully!");
		}
	}
	
}

class StatusMonitorTask extends TimerTask {
	
	private static Log logger = LogFactory.getLog(StatusMonitorTask.class);
	final private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private static long startupTime = 0L;
	private static long countsResetTime = 0L;
	private static String jvmVersion = null;
	private static String osVersion = null;
	private static long lastCpuTime = 0l;
	private static long lastUpdatedTime = 0l;
	
	private static VcmServlet vcmServlet = null; 
	
	private static String sline = 
		"---------------------------------------------------------------------------";
	
	StatusMonitorTask(VcmServlet servlet) {
		init();
		vcmServlet = servlet;
	}
	
	private void init() {
		if (startupTime == 0L)
		    startupTime = System.currentTimeMillis();
		if (countsResetTime == 0L)
			countsResetTime = startupTime;
	}
	
	public void run(){
		ArrayList<String> statusLines = getStatusLines();
		for(String line : statusLines)		
			logger.warn(line);		
	}
		
	ArrayList<String> getStatusLines() {
		ArrayList<String> statusLines = new ArrayList<String>(50);
		
		statusLines.add(sline);
		uptimeStatus(statusLines);
		versionStatus(statusLines);
		iviewStatus(statusLines);
		conferenceStatus(statusLines);
		runtimeCacheStatus(statusLines);
		statusLines.add(sline);
		
		return statusLines;
	}
	
	private void uptimeStatus(ArrayList<String> statusLines)
	{
		long runtime = System.currentTimeMillis() - startupTime;
		long days = runtime / (24*3600000L);
		runtime = runtime % (24*3600000L);
		long hours = runtime / 3600000L;
		runtime = runtime % 3600000L;
		long minutes = runtime / 60000L;
		runtime = runtime % 60000L;
		long seconds = runtime / 1000L;
		statusLines.add("--- server uptime  : " + days + " days, " 
				+ hours + " hours, " + minutes + " minutes, " + seconds + " seconds");
		if (countsResetTime != startupTime)
		    statusLines.add("--- counts reset   : " + formatDate(countsResetTime));
	}
	
	private void runtimeCacheStatus(ArrayList<String> statusLines)
	{
		Runtime rt = Runtime.getRuntime();	    
		statusLines.add("--- Runtime/Cache Information...");	  
		long maxMemory = rt.maxMemory();
		statusLines.add("--- Maximum Memory : " + tabString(maxMemory/1048576 + "M") +"\t"
	        + "--- CPU Processors   : " + rt.availableProcessors());
		long totalMemory = rt.totalMemory();
		long freeMemory = rt.freeMemory();
		statusLines.add("--- Total Memory   : " + tabString(totalMemory/1048576 + "M") + "\t"
	        + "--- Used Memory      : " + (totalMemory-freeMemory)/1048576 + "M " + 
	        + (totalMemory-freeMemory)*100/maxMemory + "%");	       	    
	    
	    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();	    
        if (threadMXBean != null)
        {        	
    	List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
    	long peakUsedHeap = 0L;
    	for(MemoryPoolMXBean memoryPoolBean : memoryPoolMXBeans)
    	{
    		if (memoryPoolBean.getType().equals(MemoryType.HEAP))
    		{
    		    MemoryUsage peakUsage = memoryPoolBean.getPeakUsage();         		
    		    peakUsedHeap += peakUsage.getUsed();
    		}
    	}
    	statusLines.add("--- PeakUsedHeap   : " + tabString(peakUsedHeap/1048576 + "M") + "\t"
            + "--- Peak/StartedThd  : " + threadMXBean.getPeakThreadCount() + "/" 
            + threadMXBean.getTotalStartedThreadCount());
        }
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        OperatingSystemMXBean operatingMXBean = ManagementFactory.getOperatingSystemMXBean();
        if (threadMXBean != null && operatingMXBean != null){
        	long totalCpuTime = 0l;
        	long curTime = System.currentTimeMillis();
        	long[] threadIds = threadMXBean.getAllThreadIds();
        	for(long threadId : threadIds)
        		totalCpuTime += threadMXBean.getThreadCpuTime(threadId);
        	long load = -1;
        	long overallLoad = -1;
        	if (lastUpdatedTime != 0 && curTime - lastUpdatedTime > 0){
        		load = (totalCpuTime - lastCpuTime)/(curTime - lastUpdatedTime)/100;
        		if (load < 0) load = 0;
        	}
        	if (runtimeMXBean != null){
        		overallLoad = totalCpuTime/(curTime - runtimeMXBean.getStartTime())/100;
        	}
        	if (load > 0)
        		load = load/rt.availableProcessors();
        	if (overallLoad > 0)
        		overallLoad = overallLoad/rt.availableProcessors();
//        	double systemLoad = operatingMXBean.getSystemLoadAverage();
//	        statusLines.add("--- SystemLoad     : " + (systemLoad < 0 ? "N/A" :  String.valueOf(systemLoad)) + "\t"
//	        	+ "--- JVMLoad          : " + tabString(load/100.0+"%("+overallLoad/100.0+"%)"));
	        statusLines.add("--- JVMLoad        : " + tabString(load/100.0+"%("+overallLoad/100.0+"%)"));
	        lastCpuTime = totalCpuTime;
	        lastUpdatedTime = curTime;
        }
        
	    statusLines.add("---");
	}
	
	private void versionStatus(ArrayList<String> statusLines)
	{
		if (jvmVersion == null)
		{
	        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
	        if (runtimeMXBean != null)
	        {
	        	jvmVersion = runtimeMXBean.getVmName() + "-" + runtimeMXBean.getVmVendor() 
	        	    + "-" + runtimeMXBean.getVmVersion();
	        	if (jvmVersion.length() > 50)
	        		jvmVersion = "..." + jvmVersion.substring(jvmVersion.length()-50);
	        }
	        OperatingSystemMXBean operatingMXBean = ManagementFactory.getOperatingSystemMXBean();
	        if (operatingMXBean != null)
	        {
	        	osVersion = operatingMXBean.getArch() + "-" + operatingMXBean.getName()
	        	    + "-" + operatingMXBean.getVersion();	        	
	        }
		}
		statusLines.add("--- java version   : " + jvmVersion);
		statusLines.add("--- os   version   : " + osVersion);
		statusLines.add("---");
	}		
	
	private void iviewStatus(ArrayList<String> statusLines) {
		statusLines.add("--- iCM            : " + PropertiesHelper.getIcmIpPort());
		statusLines.add("--- iCM Service    : " + (RuntimeInfo.isIcmServiceConnected() ? "Connected" : "Disconnected"));
		statusLines.add("--- MCU Proxy      : " + (RuntimeInfo.isMcuProxyConnected() ? "Connected" : "Disconnected"));
		statusLines.add("---");
	}
	
	private void conferenceStatus(ArrayList<String> statusLines) {
		int toBeScheduleds = 0;
		int upcomings = 0;
		int ongoings = 0;
		int total = 0;
		
		// TODO
		IConfService confService = (IConfService)vcmServlet.getBean("confService");
		List<Conference> confList = confService.findNotFinishedConfs();
		for (Conference conf : confList) {
			Short status = conf.getStatus();
			switch (status) {
				case Conference.status_tobescheduled:
					toBeScheduleds++;
					break;
				case Conference.status_upcoming:
					upcomings++;
					break;
				case Conference.status_ongoing:
					ongoings++;
					break;
				default:
					break;
			}
		}
		total = toBeScheduleds + upcomings + ongoings;
		
		statusLines.add("--- t-confs/tobes/upcomings/ongoings  : " + total + "/" + toBeScheduleds
				+ "/" + upcomings + "/" + ongoings);		
		if (total > 0) {
			statusLines.add("---");		
			statusLines.add("--- confId\t radConfId\t name\t\tvirId\t status\t duration(m)");
		}
		
		statusLines.add("---");
	}
	
	/**
     * Format a time to a displayable string with format yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param time timestamp
     * @return displayable string.
     */
    public static String formatDate(long time)
    {
        if (time <= 0L)
            return "NULL";        
    	synchronized(DATE_FORMAT){
            return DATE_FORMAT.format(new Date(time));
    	}        
    }
    
    private String tabString(String msg)
	{		
		return tabString(msg,10);		
	}

    private String tabString(String msg,int len)
	{		
		if (msg.length() < len)
		{
			return msg + "               ".substring(0,len-msg.length());
		}else
		    return msg;
	}
    
}