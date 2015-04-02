package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CaijiMain {

	private static HttpClient client=HttpsClient.getInstance();
	private static String baseurl="https://forum-en.guildwars2.com";
	private List<String> urllist=new ArrayList<String>();
	private File file=new File("d:/mm.txt");
	/**
	 * @param args
	 */
	private JTextArea textarea;
	private JComboBox comboBox;
	public CaijiMain(JTextArea textArea,JComboBox comboBox){
		this.textarea=textArea;
		this.comboBox=comboBox;
		textarea.append("开始");
	}
	public void GetUrl(){
		try {
			textarea.append("正在获取URL地址\r\n");
			HttpGet hg=new HttpGet(baseurl+"/forum");
			HttpResponse hr=client.execute(hg);
			if(hr.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				String html=EntityUtils.toString(hr.getEntity(),"UTF-8");
				Document doc=Jsoup.parse(html);
				Element ele=doc.getElementById("main");
				
				Elements es=ele.getElementsByClass("forum");
				Iterator<Element> ie=es.iterator();
				while(ie.hasNext()){
					Element a=ie.next();
					Element p=a.parent();
					textarea.append(p.attr("href")+"--一级\r\n");
					textarea.setCaretPosition(textarea.getText().length());
					urllist.add(p.attr("href"));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			comboBox.removeAllItems();
			comboBox.addItem("全部");
			for(int i=0;i<urllist.size();i++){
				comboBox.addItem(urllist.get(i));
			}
		}
	}
	public void GetPageurl(){
		textarea.append("开始采集数据\r\n");
		if("全部".equals(comboBox.getSelectedItem())){
			for(int i=0;i<urllist.size();i++){
				getDate(urllist.get(i));
			}
		}else{
			getDate(comboBox.getSelectedItem().toString());
		}
		
		textarea.append("结束");
		textarea.setCaretPosition(textarea.getText().length());
	}
	public void getDate(String url){
		try {
			if(url==null||"".equals(url)){
				return;
			}
			textarea.append(url+"--二级\r\n");
			HttpGet hg=new HttpGet(baseurl+url);
			HttpResponse hr=client.execute(hg);
			if(hr.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				String html=EntityUtils.toString(hr.getEntity(),"UTF-8");
				Document doc=Jsoup.parse(html);
				Elements es=doc.getElementsByClass("topic");
				Iterator<Element> ie=es.iterator();
				while(ie.hasNext()){
					Element e=ie.next();
					String href=e.attr("href");
					getUser(href);
				}
				Elements next_page=doc.getElementsByClass("next_page");
				if(next_page.size()>0){
					Element page=next_page.get(0);
					String href=page.attr("href");
					getDate(href);
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getUser(String url){
		try {
			if(url==null||"".equals(url)){
				return;
			}
			textarea.append(url+"--三级\r\n");
			textarea.setCaretPosition(textarea.getText().length());
			HttpGet hg=new HttpGet(baseurl+url);
			HttpResponse hr=client.execute(hg);
			if(hr.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				String html=EntityUtils.toString(hr.getEntity(),"UTF-8");
				Document doc=Jsoup.parse(html);
				Elements es=doc.getElementsByClass("post-header");
				Iterator<Element> ie=es.iterator();
				while(ie.hasNext()){
					Element e=ie.next();
					Elements me=e.getElementsByClass("member");
					Iterator<Element> ime=me.iterator();
					while(ime.hasNext()){
						Element m=ime.next();
						appendFile(m.text());
					}
				}
				Elements next_page=doc.getElementsByClass("next_page");
				if(next_page.size()>0){
					Element page=next_page.get(0);
					String href=page.attr("href");
					getUser(href);
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void appendFile(String b){
		b=b+"\r\n";
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(file,true);
			fos.write(b.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
