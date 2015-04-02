package com.yfm.posetion;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.R.integer;
import android.util.Log;

public class XMlUtils {

	public static Info ParseXml(String xml){
		
		Info info=new Info();
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc=builder.parse(new ByteArrayInputStream(xml.getBytes()));
			Element root=doc.getDocumentElement();
			NodeList nls=root.getChildNodes();
			System.out.println(xml);
			for(int i=0;i<nls.getLength();i++){
				CardInfo cardinfo = null;
				Node node=nls.item(i);
				NodeList inc=node.getChildNodes();
				Log.i("node", node.getNodeName());
				for(int j=0;j<inc.getLength();j++){
					Node nc=inc.item(j);
					
					if("content".equals(node.getNodeName())){
						if("ContentTypeID".equals(nc.getNodeName())){
							cardinfo=new CardInfo();
							cardinfo.setContentTypeID(Integer.parseInt(nc.getTextContent()));
						}
						if("ContentIndex".equals(nc.getNodeName())){
							cardinfo.setContentIndex(Integer.parseInt(nc.getTextContent()));
						}
						if("ContentAddr".equals(nc.getNodeName())){
							cardinfo.setContentAddr(Integer.parseInt(nc.getTextContent()));
						}
						if("ReadPassword".equals(nc.getNodeName())){
							cardinfo.setReadPassword(nc.getTextContent());
							info.getLci().add(cardinfo);
							cardinfo=null;
						}
					}
					
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	} 
}
