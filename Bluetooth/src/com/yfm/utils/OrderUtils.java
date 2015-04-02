package com.yfm.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class OrderUtils {

	private Context context;
	private BluetoothAdapter adapter;
	public BluetoothAdapter getAdapter() {
		return adapter;
	}

	public void setListBl(List<BluetoothDevice> lbd){
		if(lbd!=null){
			lbd.clear();
			Set<BluetoothDevice> sbd=adapter.getBondedDevices();
			for(BluetoothDevice bd:sbd){
				lbd.add(bd);
			}
		}
	}
	private static UUID myuuid = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private BluetoothSocket socket = null;
	private Handler handler = null;

	public OrderUtils(Context context, Handler handlder) {
		this.context = context;
		adapter = BluetoothAdapter.getDefaultAdapter();
		this.handler = handlder;
	}

	public boolean openBluetooth() {
		if (!adapter.isEnabled()) {
			Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			context.startActivity(enabler);
			return false;
		} else {
			return true;
		}
	}

	public void founddevice() {
		if (adapter.isDiscovering()) {
			adapter.cancelDiscovery();
		}
		adapter.startDiscovery();

	}

	public void socketclose() {
		if (socket != null) {
			try {
				Log.i("关闭蓝牙连接", "关闭");
				socket.close();
				adapter.disable();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void conncetDevice(final BluetoothDevice device) {
		Thread th = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					if (device != null) {
						socket = device.createRfcommSocketToServiceRecord(myuuid);
						socket.connect();
						readData rd = new readData();
						rd.start();
						if (handler != null) {
							Message msg = handler.obtainMessage();
							msg.what = 1;
							msg.arg1 = 1;
							msg.obj = "已连接" + device.getName();
							handler.sendMessage(msg);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if (handler != null) {
						Message msg = handler.obtainMessage();
						msg.what = 1;
						msg.arg1 = 2;
						msg.obj = "连接失败,请重新连接";
						handler.sendMessage(msg);
					}
					socketclose();
					e.printStackTrace();
				}
			}

		};
		th.start();

	}

	class readData extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				StringBuffer sb = new StringBuffer();
				InputStream is = socket.getInputStream();
				byte[] b = new byte[1];
				LogUtils("开始读取数据", "-----");
				while (is.read(b)!=-1) {
					String o=byte2hex(b);
					Log.i("读取到的数据", o);
					if("FC".equals(o)){
						sb=new StringBuffer();
					}
					sb.append(o);
					LogUtils("读取到的数据", sb.toString());
					if(sb.length()>=8){
						if (handler != null) {
							Message msg = handler.obtainMessage();
							msg.what = 2;
							msg.arg1 = 1;
							msg.obj = sb.toString();
							handler.sendMessage(msg);
							LogUtils("接收", sb.toString());
							LogUtils("接收完成", "-----------------------------"+ "\r\n");
							sb=new StringBuffer();
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if (handler != null) {
					Message msg = handler.obtainMessage();
					msg.what = 1;
					msg.arg1 = 2;
					msg.obj = "蓝牙连接失败,请重新连接";
					handler.sendMessage(msg);
				}
				socketclose();
				e.printStackTrace();
			}

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
				hs = hs + "0" + tmp;
			} else {
				hs = hs + tmp;
			}
		}
		tmp = "";
		return hs.toUpperCase(); // 转成大写

	}

	public void SendOrder(final String order) {
		Thread th = new Thread() {

			@Override
			public void run() {
				try {
					if(order!=null&&!"".equals(order)){
						LogUtils("开始发送", "-----------------------------");
						OutputStream os = socket.getOutputStream();
						byte[] b = hexStringToBytes(order);
						if (b != null) {
							os.write(b);
							os.flush();
							LogUtils("发送", order.toUpperCase());
						}
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					if (handler != null) {
						Message msg = handler.obtainMessage();
						msg.what = 1;
						msg.arg1 = 2;
						msg.obj = "蓝牙连接失败,请重新连接";
						handler.sendMessage(msg);
					}
					socketclose();
					e.printStackTrace();
				}
			}

		};
		th.start();
	}

	public static byte[] hexStringToBytes(String hexString) {

		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase().replaceAll(" ", "");
		System.out.println(hexString);
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			int temp = (charToByte(hexChars[pos]) << 4)
					+ charToByte(hexChars[pos + 1]);
			d[i] = (byte) (temp & 0xff);
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static void LogUtils(String type, String order) {
		try {
			File sdcardDir = Environment.getExternalStorageDirectory();
			File save = new File(sdcardDir, "logbl.txt");
			FileWriter fw = new FileWriter(save, true);
			fw.write(type + ":" + order + "\r\n");
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
