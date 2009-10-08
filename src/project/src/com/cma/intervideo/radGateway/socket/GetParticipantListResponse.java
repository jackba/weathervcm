package com.cma.intervideo.radGateway.socket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetParticipantListResponse extends BaseResponse{
	private static Log logger = LogFactory.getLog(GetParticipantListResponse.class);
	private String returnValue;
	private String desktopPID = null;
	private List<Part> partList = new ArrayList<Part>();
	
	

	public String getDesktopPID() {
		return desktopPID;
	}

	public List<Part> getPartList() {
		return partList;
	}

	public void setPartList(List<Part> partList) {
		this.partList = partList;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public void decode(Node cr, XPath xpath) throws MessageDecodeException{
		try{
			setRequestId(xpath.evaluate("RequestID", cr));
			returnValue = xpath.evaluate("ReturnValue", cr);
			Node partListNode = (Node)xpath.evaluate("Participants_List", cr, XPathConstants.NODE);
			if(partListNode!=null){
				NodeList parts = (NodeList)xpath.evaluate("Part", partListNode, XPathConstants.NODESET);
				if(parts!=null){
					for(int i=0;i<parts.getLength();i++){
						Part part = new Part();
						Node partNode = parts.item(i);
						part.setPid(xpath.evaluate("PID", partNode));
						part.setDialStr(xpath.evaluate("DialStr", partNode));
						part.setPartName(xpath.evaluate("PartName", partNode));
						part.setIp(xpath.evaluate("IP", partNode));
						String str1 = xpath.evaluate("EPType", partNode);
						if(str1.equals("Gateway")){
							//如果终端是电话
							part.setEPType(1);
						}else{
							part.setEPType(0);
						}
						if(!part.getPartName().equals("Desktop Server")){
							if(!(part.getPartName().startsWith("RESERVED:")&&part.getPid().startsWith("RESERVED:"))){
								partList.add(part);
							}
						}else{
							desktopPID = part.getPid();
							logger.info("desktopPID = "+desktopPID);
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(e.toString());
			throw new MessageDecodeException("解析GetConferenceListResponse消息错误!");
		}
	}
}
