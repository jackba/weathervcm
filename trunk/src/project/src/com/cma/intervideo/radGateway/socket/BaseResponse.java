package com.cma.intervideo.radGateway.socket;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public abstract class BaseResponse {
	private static Log logger = LogFactory.getLog(BaseResponse.class);
	private String requestId;
	private String version;
	protected Document document;
	private String xml;
	
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public static BaseResponse parse(String xml){
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node response = (Node)xpath.evaluate("/MCU_XML_API/Response", doc, XPathConstants.NODE);
			Node concreteResponse = response.getFirstChild();
			String responseType = concreteResponse.getNodeName();
			BaseResponse r = null;
			if(responseType.equals("Terminate_Conference_Response")){
				r = new TerminateConferenceResponse();
			}else if(responseType.equals("Drop_Participant_Response")){
				r = new DropParticipantResponse();
			}else if(responseType.equals("Get_Services_Response")){
				r = new GetServicesResponse();
			}else if(responseType.equals("Get_Conference_List_Response")){
				r = new GetConferenceListResponse();
			}else if(responseType.equals("Get_Participant_List_Response")){
				r = new GetParticipantListResponse();
			}else{
				logger.error("receive "+responseType+" message");
				return null;
			}
			if(r == null){
				return null;
			}
			r.setVersion(xpath.evaluate("/MCU_XML_API/Version", doc));
			r.xml = xml;
			r.document = doc;
			r.decode(concreteResponse, xpath);
			return r;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	public abstract void decode(Node cr, XPath xpath) throws MessageDecodeException;
}
