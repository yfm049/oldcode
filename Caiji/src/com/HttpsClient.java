package com;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpsClient {

	public static HttpClient getInstance(){
		HttpClient client=new DefaultHttpClient();
		try {
			SSLContext ctx=SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[]{new TrustManagerImpl()}, null);
			SSLSocketFactory ssf=new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm=client.getConnectionManager();
			SchemeRegistry sr=ccm.getSchemeRegistry();
			sr.register(new Scheme("https",443,ssf));
			client=new DefaultHttpClient(ccm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return client;
	}
	
}
