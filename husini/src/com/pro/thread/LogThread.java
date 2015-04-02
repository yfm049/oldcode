package com.pro.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

import com.pro.utils.SdUtils;

public class LogThread extends Thread {

	private boolean isrun = true;
	private boolean isfrist=true;
	private String currstate="";
	private Handler handler;
	public static final int DIALING=1,ALERTING=2,ACTIVE=3,START=4,RSTOP=5;
	public LogThread(Handler handler){
		this.handler=handler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		BufferedReader bufferedReader = null;
		try {
			Runtime run = Runtime.getRuntime();
			Process process = run.exec(getcmdline().toArray(
					new String[getcmdline().size()]));
			bufferedReader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			String str = null;
			SdUtils.write("-------------------------------------------------------------------");
			while ((str = bufferedReader.readLine()) != null&&isrun){
				SdUtils.writetemp(str+"------");
				if(str.toUpperCase().contains("DIALING")){
					SdUtils.write(currstate+"------DIALING");
					if(!currstate.equals("DIALING")){
						currstate="DIALING";
						sendmsg(DIALING,0);
					}
				}else if(str.toUpperCase().contains("ALERTING")){
					SdUtils.write(currstate+"------ALERTING");
					if(!currstate.equals("ALERTING")&&currstate=="DIALING"){
						currstate="ALERTING";
						sendmsg(ALERTING,500);
					}
				} else if(str.toUpperCase().contains("ACTIVE")){
					SdUtils.write(currstate+"------ACTIVE");
					if(!currstate.equals("ACTIVE")){
						currstate="ACTIVE";
						sendmsg(ACTIVE,100);
					}
				}else{
					if(isfrist){
						isfrist=false;
						sendmsg(START,0);
					}
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bufferedReader!=null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
	public void stoprun(){
		isrun=false;
	}
	public void sendmsg(int what,long delayMillis){
		if(handler!=null){
			handler.sendEmptyMessageDelayed(what, delayMillis);
		}
	}

	private List<String> getcmdline() {
		ArrayList<String> cmdLine = new ArrayList<String>(); // 设置命令 logcat -d
		cmdLine.add("logcat");
		cmdLine.add("-v");
		cmdLine.add("time");
		cmdLine.add("-b");
		cmdLine.add("radio");
		return cmdLine;
	}

	private List<String> getclearline() {
		ArrayList<String> clearLog = new ArrayList<String>(); // 设置命令 logcat -c
																// 清除日志
		clearLog.add("logcat");
		clearLog.add("-c");
		return clearLog;
	}

}
