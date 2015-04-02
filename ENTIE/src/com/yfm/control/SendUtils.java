package com.yfm.control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SendUtils {

	private Context context;
	private static Handler handler;
	private static DatagramSocket socket = null;
	private static boolean istishi;
	private static ReciverMsg rm = null;
	private static Map<String,Timer> mst=new HashMap<String,Timer>();
	private byte[] data;
	private int port;
	private String ip;
	private SharedPreferences sp;
	private InetAddress address;

	public SendUtils(Context context, Handler handler) {
		try {
			istishi = false;
			this.context = context;
			SendUtils.handler = handler;
			sp = context.getSharedPreferences("set", Context.MODE_PRIVATE);
			port = sp.getInt("port", 20625);
			Log.i("port", port + "");
			ip = sp.getString("ip", "");
			Log.i("ip", ip);
			address = InetAddress.getByName(ip);
			if (socket == null || socket.isClosed()) {
				socket = new DatagramSocket(20265);
			}
			if (rm == null && socket != null) {
				rm = new ReciverMsg();
				rm.start();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("错误", e.getMessage());
			if (handler != null) {
				Message msg = handler.obtainMessage();
				msg.what = 1;
				msg.obj = "发送失败" + e.getMessage();
				handler.sendMessage(msg);
			}
			writeFiletosd(e.getMessage());
			e.printStackTrace();
		}
	}

	// 发送指令

	public void Sendmsg(byte[] data) {
		println("发送", data);
		this.data = data;
		try {
			
			Log.i("istishi", istishi + "发送");
			DatagramPacket packet = new DatagramPacket(data, data.length,
					address, port);
			socket.send(packet);
			addTimer(data);
		} catch (IOException e) {
			Log.i("错误", e.getMessage());
			if (handler != null) {
				Message msg = handler.obtainMessage();
				msg.what = 1;
				msg.obj = "执行失败";
				handler.sendMessage(msg);
			}
			writeFiletosd(e.getMessage());
			e.printStackTrace();
		}
	}
	public void addTimer(byte[] data){
		byte[] ba=new byte[4];
		ba[0]=data[19];
		ba[1]=data[24];
		ba[2]=data[25];
		ba[3]=data[26];
		String u=byte2hex(ba);
		if(u!=null&&u.startsWith("91")){
			return;
		}
		Timer t=mst.get(u);
		if(t!=null){
			t.cancel();
		}
		Timer timer=new Timer(true);
		timer.schedule(new TimerT(u,handler), 1000);
		Log.i("指令", u);
		mst.put(u, timer);
		Log.i("keyset", mst.keySet().toString());
	}
	class TimerT extends TimerTask{
		private String key=null;
		Handler hd = null;
		public TimerT(String key,Handler hd){
			this.key=key;
			this.hd = hd;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (hd != null) {
				Message msg = hd.obtainMessage();
				msg.what = 1;
				msg.obj = "执行超时";
				hd.sendMessage(msg);
			}
			Log.i("handler", hd+"");
			mst.remove(key);
		}
		
	}
	public void Recivedate(){
		byte[] d = null;
		try {
			byte[] recBuf = new byte[32];
			DatagramPacket recpacket = new DatagramPacket(recBuf,
					recBuf.length);
			socket.receive(recpacket);
			d = recpacket.getData();
			byte[] ba=new byte[4];
			ba[0]=data[19];
			ba[1]=data[24];
			ba[2]=data[25];
			ba[3]=data[26];
			String u=byte2hex(ba);
			Log.i("响应", u);
			Timer t=mst.get(u);
			if(t!=null){
				t.cancel();
			}
			mst.remove(u);
			Log.i("istishi", istishi + "接收");
			Log.i("接收指令", byte2hex(d));
			println("接收", d);

			if (handler != null) {
				Message msg = handler.obtainMessage();
				msg.what = 2;
				msg.obj = d;
				handler.sendMessage(msg);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (handler != null) {
				Message msg = handler.obtainMessage();
				msg.what = 1;
				msg.obj = "执行失败";
				handler.sendMessage(msg);

			}

		}
	}
	public void println(String type, byte[] receiveByte) {
		StringBuffer s = new StringBuffer();
		if (receiveByte == null) {
			return;
		}
		s.append(type);
		s.append(byte2hex(receiveByte));
		writeFiletosd(s.toString());
	}

	public void writeFiletosd(String p) {
		try {
			File sdCardDir = Environment.getExternalStorageDirectory();
			File saveFile = new File(sdCardDir, "log.txt");
			FileWriter fw = new FileWriter(saveFile, true);
			fw.write(p + "\r\n");
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String byte2hex(byte[] b) { // 一个字节的数，

		// 转成16进制字符串

		String hs = "";
		String tmp = "";
		for (int n = 0; n < b.length; n++) {
			// 整数转成十六进制表示

			tmp = (Integer.toHexString(b[n] & 0XFF));
			if (tmp.length() == 1) {
				hs = hs + "0" + tmp + ",";
			} else {
				hs = hs + tmp + ",";
			}
		}
		tmp = "";
		return hs.toUpperCase(); // 转成大写

	}

	class ReciverMsg extends Thread {

		
		Handler hd = null;

		public void setHd(Handler hd) {
			this.hd = hd;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				Recivedate();
			}
		}
	}

}
