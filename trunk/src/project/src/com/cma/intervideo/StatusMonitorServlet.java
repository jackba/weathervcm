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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.util.VcmProperties;
import com.radvision.icm.service.vcm.ICMService;

public class StatusMonitorServlet extends HttpServlet {
	
	private static Log logger = LogFactory.getLog(StatusMonitorServlet.class);
	long interval = VcmProperties.getPropertyByLong("vcm.statusMonitorInterval",300000L); //default 5 minutes
	
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
		int testPeriod = PropertiesHelper.getMcuConnectionTestPeriod();
		timer.schedule(tt, Calendar.getInstance().getTime(), testPeriod);
		
		/**
		 * 启动服务器状态定时检测
		 */
		Timer timer2 = new Timer();
		StatusMonitorTask tt2 = new StatusMonitorTask();
		timer2.schedule(tt2, Calendar.getInstance().getTime(), interval);
		
	}
	
	class TestTask4IcmService extends TimerTask {
		public void run(){
			boolean connected = ICMService.isConnected();
			PropertiesHelper.setIcmServiceConnected(connected);
			if (!connected)
				logger.warn("Test RADVISION iCM Service connection failed, please check the connnection and configuration!");
			else
				logger.info("Test RADVISION iCM Service connection successfully!");
		}
	}

	
}

class StatusMonitorTask extends TimerTask {
	
	private static Log logger = LogFactory.getLog(StatusMonitorTask.class);
	final private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private static long startupTime = 0L;
	private static long countsResetTime = 0L;
	private static long lastCpuTime = 0l;
	private static long lastUpdatedTime = 0l;
	
	private static String sline = 
		"---------------------------------------------------------------------------";
	
	StatusMonitorTask()
	{
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
	        statusLines.add("--- JVMLoad          : " + tabString(load/100.0+"%("+overallLoad/100.0+"%)"));
	        lastCpuTime = totalCpuTime;
	        lastUpdatedTime = curTime;
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