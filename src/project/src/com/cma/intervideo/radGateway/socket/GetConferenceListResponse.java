package com.cma.intervideo.radGateway.socket;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetConferenceListResponse extends BaseResponse{
	private static Log logger = LogFactory.getLog(GetConferenceListResponse.class);
	private String returnValue;
	private List<Conf> confList = new ArrayList<Conf>();
	
	public List<Conf> getConfList() {
		return confList;
	}

	public void setConfList(List<Conf> confList) {
		this.confList = confList;
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
			Node confListNode = (Node)xpath.evaluate("Conf_List", cr, XPathConstants.NODE);
			if(confListNode!=null){
				NodeList confs = (NodeList)xpath.evaluate("Conf", confListNode, XPathConstants.NODESET);
				if(confs!=null){
					for(int i=0;i<confs.getLength();i++){
						Conf conf = new Conf();
						Node confNode = confs.item(i);
						conf.setConfGID(xpath.evaluate("ConfGID", confNode));
						conf.setServicePrefix(xpath.evaluate("ServicePrefix", confNode));
						conf.setName(xpath.evaluate("Name", confNode));
						conf.setNumName(xpath.evaluate("NumName", confNode));
						confList.add(conf);
					}
				}
			}
		}catch(Exception e){
			logger.error(e.toString());
			throw new MessageDecodeException("解析GetConferenceListResponse消息错误!");
		}
	}
}
