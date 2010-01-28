package com.cma.intervideo.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.sendsms.Library;
import cn.sendsms.MessageEncodings;
import cn.sendsms.OutboundMessage;
import cn.sendsms.SerialModemGateway;
import cn.sendsms.Service;

import com.cma.intervideo.dao.IConfDao;
import com.cma.intervideo.pojo.SendMessage;

public class SMSUtil {
	private String comPort;
	private Service srv;
	private IConfDao confDao;
	private static Log logger = LogFactory.getLog(SMSUtil.class);
	public void setConfDao(IConfDao confDao) {
		this.confDao = confDao;
	}
	public void setComPort(String comPort) {
		this.comPort = comPort;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public SMSUtil(){
		
	}
	
	public void init(){
		logger.info("示例: 通过华腾通宇串口短信猫发送短信.");
		logger.info(Library.getLibraryDescription());
		logger.info("版本: " + Library.getLibraryVersion());
		try{
			srv = new Service();
			SerialModemGateway gateway = new SerialModemGateway("jindi", "COM1", 115200, "wavecom", "M1306B", srv.getLogger());
			gateway.setInbound(true);
			gateway.setOutbound(true);
			gateway.setSimPin("0000");
			srv.addGateway(gateway);
			srv.startService();
			System.out.println("GSM Modem信息:");
			System.out.println("  厂家: " + gateway.getManufacturer());
			System.out.println("  型号: " + gateway.getModel());
			System.out.println("  序列号: " + gateway.getSerialNo());
			System.out.println("  SIM IMSI: " + gateway.getImsi());
			System.out.println("  信号强度: " + gateway.getSignalLevel() + "%");
			System.out.println("  电池容量: " + gateway.getBatteryLevel() + "%");
		}catch(Exception e){
			logger.error(e.toString());
		}
	}
	public void cleanup(){
		try{
			srv.stopService();
		}catch(Exception e){
			logger.error(e.toString());
		}
	}
	public boolean sendMessage(SendMessage sendMessage){
		
		OutboundMessage msg;
		msg = new OutboundMessage(sendMessage.getMsisdn(), sendMessage.getMessage());
		msg.setEncoding(MessageEncodings.ENCUCS2);
		msg.setStatusReport(false);
		try{
			srv.sendMessage(msg);
			logger.info(msg);
			sendMessage.setStatus(sendMessage.status_success);
			sendMessage.setSendTime(new Date());
			confDao.saveSmsLog(sendMessage);
		}catch(Exception e){
			logger.error(e.toString());
			sendMessage.setStatus(sendMessage.status_failure);
			sendMessage.setErrorDesc(e.toString().substring(0,200));
			sendMessage.setSendTime(new Date());
			confDao.saveSmsLog(sendMessage);
			return false;
		}
		return true;
	}
}
