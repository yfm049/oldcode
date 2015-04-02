package com.iptv.net;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.iptv.db.SqlUtils;
import com.iptv.model.Category;
import com.iptv.season.HomeActivity;
import com.iptv.season_new.R;
import com.iptv.utils.HttpClientUtils;
import com.iptv.utils.ToolsUtils;
import com.iptv.xml.XmlUtils;

public class LoginAsyncTask extends AsyncTask<Object, Integer, String> {

	private static String TAG = LoginAsyncTask.class.getName();

	private ProgressDialog dialog;
	private Context context;

	public LoginAsyncTask(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		dialog = ToolsUtils.getProgressDialog(context);
		dialog.setTitle(R.string.login_dialog_title);
		dialog.show();
	}

	@Override
	protected String doInBackground(Object... params) {
		// TODO Auto-generated method stub
		String loginurl = String.format("live.jsp?active=%s&pass=%s&mac=%s",
				params);
		String xml = HttpClientUtils.Request(loginurl);
		if (xml == null) {
			return "netfail";
		} else {
			xml = ToolsUtils.DecodeBase64(xml);
			Log.i(TAG, "DecodeBase64  " + xml);
			List<Category> lcategory = XmlUtils.loginXml(xml);
			Log.i(TAG, "lcategory  " + lcategory);
			if (lcategory == null) {
				return "false";
			} else {
				SqlUtils su = new SqlUtils();
				su.initTvData(lcategory);
				return "true";
			}
		}

	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		dialog.dismiss();
		if ("true".equals(result)) {
			ToolsUtils.showToast(R.string.loginsuc);
			ToHome();
		} else if ("false".equals(result)) {
			ToolsUtils.showToast(R.string.loginfail);
		} else if ("netfail".equals(result)) {
			ToolsUtils.showToast(R.string.netfail);
		}
	}
	private void ToHome(){
		Intent home=new Intent(context,HomeActivity.class);
		context.startActivity(home);
	}

}
