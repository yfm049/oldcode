package com.pro.utils;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebserviceUtils {

	private static String wsdl="http://se.waoowa.com/CardService.asmx?wsdl";
	private static String namespace="http://www.waoowa.com/";
	public static List<Info> GetWebXml(){
		List<Info> linfo = new ArrayList<Info>();
		try {
			SoapObject so=new SoapObject(namespace, "GetUserList");
			so.addProperty("SiteID", "0779xyy");
			SoapSerializationEnvelope sste=new SoapSerializationEnvelope(SoapEnvelope.VER12);
			sste.dotNet=true;
			sste.setOutputSoapObject(so);
			HttpTransportSE htse=new HttpTransportSE(wsdl);
			htse.call(null, sste);
			SoapObject st=(SoapObject)sste.getResponse();
			st=(SoapObject)st.getProperty("diffgram");
			st=(SoapObject)st.getProperty("NewDataSet");
			for(int i=0;i<st.getPropertyCount();i++){
				Info info = new Info();
				SoapObject pr=(SoapObject)st.getProperty(i);
				info.setPareCard(pr.getPropertyAsString("PareCard"));
				info.setChildName(pr.getPropertyAsString("ChildName"));
				info.setClassName(pr.getPropertyAsString("ClassName"));
				info.setParePhone(pr.getPropertyAsString("ParePhone"));
				info.setChildBirth(pr.getPropertyAsString("ChildBirth"));
				info.setPareName(pr.getPropertyAsString("PareName"));
				linfo.add(info);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return linfo;
	}
}
