package com.smsbak.sms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;
import android.util.Log;

import com.smsbak.model.Call;
import com.smsbak.model.Location;
import com.smsbak.model.Sms;

public class SdCardUtils {
	private static SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void WriteSms(Sms sms){
		String con="\""+sms.getDates()+"\",\""+sms.getType()+"\",\""+sms.getAddress()+"\",\""+sms.getPhonename()+"\",\""+sms.getBody()+"\"\r\n";
		storeTosdcard(con,"sms.csv");
	}
	public static void WriteCall(Call call){
		String con="\""+call.getDates()+"\",\""+call.getType()+"\",\""+call.getAddress()+"\",\""+call.getPhonename()+"\",\""+call.getDuration()+"\"\r\n";
		storeTosdcard(con,"call.csv");
	}
	public static void WriteLoc(Location loc){
		String con="\""+loc.getTime()+"\",\""+loc.getAddr()+"\",\""+loc.getLatitude()+"\",\""+loc.getLongitude()+"\",\"http://api.map.baidu.com/staticimage?markers="+loc.getLongitude()+","+loc.getLatitude()+"&zoom=17&width=800&height=600\"\r\n";
		storeTosdcard(con,"loc.csv");
	}
	public static void WriteLog(String tag,String content){
		String time=smf.format(new Date());
		String con="\""+time+"\",\""+tag+"\",\""+content+"\"\r\n";
		storeTosdcard(con,"log.csv");
		Log.i(tag, content);
	}
	private synchronized static void storeTosdcard(String con,String filename) {
		if (BootServer.writeSDcard&&Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&BootServer.writeSDcard) {
			try {
				File f = new File(BootServer.file,filename);
				if (!f.exists()) {
					f.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(f,true);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				bw.write(con);
				bw.flush();
				fos.flush();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
}
