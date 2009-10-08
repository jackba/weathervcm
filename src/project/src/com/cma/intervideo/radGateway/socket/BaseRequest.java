package com.cma.intervideo.radGateway.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.cma.intervideo.util.PropertiesHelper;

public abstract class BaseRequest {
	private static Log logger = LogFactory.getLog(BaseRequest.class);
	private String version = "";
	private String account = "";
	private String password = "";
	private BaseResponse response;
	private String requestId = "";
	private String xml;
	private static int seq = -1;
	Document doc = null;
	private static synchronized int incSeq(){
		seq++;
		if(seq<0)
			seq = 0;
		return seq;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public BaseResponse getResponse() {
		return response;
	}
	public void setResponse(BaseResponse response) {
		this.response = response;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String encode(){
		try{
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><MCU_XML_API/>";
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
			Element root = doc.getDocumentElement();
			add("Version",PropertiesHelper.getMcuProxyVersion(),root);
			add("Account",PropertiesHelper.getMcuProxyAccount(),root);
			add("Password",PropertiesHelper.getMcuProxyPassword(),root);
			requestId = String.valueOf(incSeq());
			doEncode(add("Request",root));
			this.xml = toString();
			return this.xml;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	public abstract void doEncode(Element request);
	protected void add(String name, String value, Element e){
		Element ne = doc.createElement(name);
		Text t = doc.createTextNode(value);
		ne.appendChild(t);
		e.appendChild(ne);
	}
	protected Element add(String name, Element e){
		Element ne = doc.createElement(name);
		e.appendChild(ne);
		return ne;
	}
	public String toString(){
		try{
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(bos, "utf-8");
			StreamResult result = new StreamResult(osw);
			transformer.transform(source, result);
			return bos.toString("utf-8");
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
}
