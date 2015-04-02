package com.pro.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.os.Environment;

public class SdUtils {

	private static boolean  isdubug=false;
	public static void write(String msg){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&isdubug){
			File path=Environment.getExternalStorageDirectory();
			File log=new File(path, "msglog.txt");
			try {
				FileOutputStream fos=new FileOutputStream(log,true);
				OutputStreamWriter osw=new OutputStreamWriter(fos);
				BufferedWriter bw=new BufferedWriter(osw);
				bw.write(msg+"\r\n");
				bw.close();
				osw.close();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void writetemp(String msg){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&isdubug){
			File path=Environment.getExternalStorageDirectory();
			File log=new File(path, "lclog.txt");
			try {
				FileOutputStream fos=new FileOutputStream(log,true);
				OutputStreamWriter osw=new OutputStreamWriter(fos);
				BufferedWriter bw=new BufferedWriter(osw);
				bw.write(msg+"\r\n");
				bw.close();
				osw.close();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
