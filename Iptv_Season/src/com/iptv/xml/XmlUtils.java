package com.iptv.xml;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.iptv.model.Category;
import com.iptv.model.Tvinfo;


public class XmlUtils {

	public static List<Category> loginXml(String xml){
		List<Category> lcategory=new ArrayList<Category>();
		if(xml!=null){
			try {
				XmlPullParserFactory Parserfactory=XmlPullParserFactory.newInstance();
				XmlPullParser parser=Parserfactory.newPullParser();
				parser.setInput(new StringReader(xml));
				int eventType=parser.getEventType();
				Category category=null;
				Tvinfo tvinfo = null;
				while (eventType != XmlPullParser.END_DOCUMENT) {
					if(eventType == XmlPullParser.START_TAG){
						String name=parser.getName();
						if("ret".equals(name)){
							return null;
						}
						if("Category".equals(name)){
							category=new Category();
							category.setId(parser.getAttributeValue(null,"id"));
							category.setName(parser.getAttributeValue(null,"name"));
							lcategory.add(category);
						}else if("file".equals(name)){
							tvinfo=new Tvinfo();
							category.getTvlist().add(tvinfo);
						}else if("id".equals(name)){
							tvinfo.setId(parser.nextText());
						}else if("name".equals(name)){
							tvinfo.setName(parser.nextText());
						}else if("httpurl".equals(name)){
							tvinfo.setHttpurl(parser.nextText());
						}else if("hotlink".equals(name)){
							tvinfo.setHotlink(parser.nextText());
						}else if("isflag".equals(name)){
							tvinfo.setIsflag(parser.nextText());
						}else if("logo".equals(name)){
							tvinfo.setLogo(parser.nextText());
						}else if("epg".equals(name)){
							tvinfo.setEpg(parser.nextText());
						}
						
					}
					eventType=parser.next();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lcategory;
	}
}
