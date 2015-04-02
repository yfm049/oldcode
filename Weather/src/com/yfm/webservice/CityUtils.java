package com.yfm.webservice;

import com.aibang.open.client.AibangApi;
import com.aibang.open.client.exception.AibangException;


public class CityUtils {

	public String apiKey="ada81c4fcb55930abef9f923789941a7";
	public AibangApi aa=null;
	public CityUtils(){
		aa=new AibangApi(apiKey, "json");
		
	}
	public String getcitybus(String city,String line){
		String json="";
		try {
			json=aa.busLines(city, line, 0);
			//json=new String(json.getBytes(), "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
}
