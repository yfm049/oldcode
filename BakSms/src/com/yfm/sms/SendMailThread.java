package com.yfm.sms;


public class SendMailThread extends Thread {

	private SendMail sm;
	public SendMailThread(SendMail sm){
		this.sm=sm;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Loginfo.write("定时发送", "定时发送中");
		sm.SendMail();
	}

}
