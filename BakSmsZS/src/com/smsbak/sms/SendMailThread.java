package com.smsbak.sms;



public class SendMailThread extends Thread {

	private SendMail sm;
	public SendMailThread(SendMail sm){
		this.sm=sm;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
			if(SendMail.key.equals(sm.getPublicKey())){
				sm.StartSend();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
