package com.yfm.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import android.content.Context;

public class FileUtils {

	private static final String filename="";
	public static List<Category> GetProfile(Context context){
		List<Category> lc=new ArrayList<Category>();
		try {
			Properties properties=new Properties();
			properties.load(context.getAssets().open("category/file.properties"));
			Set<Entry<Object, Object>> seo=properties.entrySet();
			Iterator<Entry<Object, Object>> eo=seo.iterator();
			while(eo.hasNext()){
				Entry<Object, Object> eoo=eo.next();
				String type=eoo.getKey().toString();
				String value=eoo.getValue().toString();
				Category cate=new Category();
				cate.setType(type);
				String[] file=value.split(",");
				for(String f:file){
					cate.getNames().add(f);
				}
				lc.add(cate);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lc;
	}
	public static void Save(Context context){
		context.getSharedPreferences("", Context.MODE_PRIVATE);
	}
}
