package com.yfm.posetion;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebserviceUtils {

	
	private static String namespace="http://211.136.187.246:8080/location/services/locationService";
	private static String wsdl="http://211.136.187.246:8080/location/services/locationService?wsdl";
	public static String GetEncryptionKey(String userName,String imei,String imsi){
		SoapObject so=new SoapObject(namespace, "GetEncryptionKey");
		try {
			so.addProperty("userName",userName);
			so.addProperty("imei", imei);
			so.addProperty("imsi", imsi);
			SoapSerializationEnvelope sste=new SoapSerializationEnvelope(SoapEnvelope.VER11);
			sste.dotNet=true;
			sste.setOutputSoapObject(so);
			HttpTransportSE htse=new HttpTransportSE(wsdl);
			htse.call(null, sste);
			return sste.getResponse().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public static String ReportPosition(String userName,String password,String imei,String imsi,String longitude,String latitude){
		SoapObject so=new SoapObject(namespace, "reportPosition");
		try {
			so.addProperty("userName",userName);
			so.addProperty("password",password);
			so.addProperty("imei", imei);
			so.addProperty("imsi", imsi);
			so.addProperty("longitude", longitude);
			so.addProperty("latitude", latitude);
			SoapSerializationEnvelope sste=new SoapSerializationEnvelope(SoapEnvelope.VER11);
			sste.dotNet=true;
			sste.setOutputSoapObject(so);
			HttpTransportSE htse=new HttpTransportSE(wsdl);
			htse.call(null, sste);
			//System.out.println(so.toString());
			return sste.getResponse().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public static String ReportPosition(String userName,String password,String imei,String imsi,String identityCard){
		SoapObject so=new SoapObject(namespace, "ReportRFIDInfo");
		try {
			so.addProperty("userName",userName);
			so.addProperty("password",password);
			so.addProperty("imei", imei);
			so.addProperty("imsi", imsi);
			so.addProperty("identityCard", identityCard);
			SoapSerializationEnvelope sste=new SoapSerializationEnvelope(SoapEnvelope.VER11);
			sste.dotNet=true;
			sste.setOutputSoapObject(so);
			HttpTransportSE htse=new HttpTransportSE(wsdl);
			htse.call(null, sste);
			System.out.println(so.toString());
			return sste.getResponse().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public static String RFIDInfo(String uidNum,String password){
		SoapObject so=new SoapObject(namespace, "getRFIDInfo");
		try {
			so.addProperty("uidNum",uidNum);
			so.addProperty("password",password);
			SoapSerializationEnvelope sste=new SoapSerializationEnvelope(SoapEnvelope.VER11);
			sste.dotNet=true;
			sste.setOutputSoapObject(so);
			HttpTransportSE htse=new HttpTransportSE(wsdl);
			htse.call(null, sste);
			System.out.println("RFIDInfo--"+so.toString());
			return sste.getResponse().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
