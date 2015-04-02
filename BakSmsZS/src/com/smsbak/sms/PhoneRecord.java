package com.smsbak.sms;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;

public class PhoneRecord {
	private static String clientdir = "smsclient";
	private static SimpleDateFormat smf = new SimpleDateFormat(
			"yyyy-MM-dd_HH:mm:ss");
	private MediaRecorder PhoneRecorder;
	private File file;
	private Context context;
	public String getFileName(){
		if(file.exists()){
			return file.getName();
		}
		return "";
	}
	public PhoneRecord(Context context){
		this.context=context;
	}
	public void startRecord(String phonenum){
		File dir=getFiledir();
		if(dir!=null){
			try {
				PhoneRecorder=new MediaRecorder();
				PhoneRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
				PhoneRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				PhoneRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
				file=new File(dir, smf.format(new Date())+"_"+phonenum+"record.mp3");
				PhoneRecorder.setOutputFile(file.getAbsolutePath());
				PhoneRecorder.prepare();
				PhoneRecorder.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				SdCardUtils.WriteLog("PhoneRecord", "通话录音出现异常"+e.getMessage());
				e.printStackTrace();
				try {
					if(PhoneRecorder!=null){
						PhoneRecorder.release();
						PhoneRecorder=null;
					}
					if(file.exists()){
						file.delete();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void StopRecord(){
		if(PhoneRecorder!=null){
			try {
				PhoneRecorder.stop();
				PhoneRecorder.release();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PhoneRecorder=null;
		}
	}
	
	
	public File getFiledir() {
		File file = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			file = new File(context.getExternalCacheDir(),
					clientdir);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		return file;
	}
	
}
