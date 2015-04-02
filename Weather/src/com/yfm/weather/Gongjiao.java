package com.yfm.weather;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yfm.webservice.CityUtils;

public class Gongjiao extends Activity {

	private EditText cityline;
	private EditText line;
	private ScrollView ll;
	private Button button;
	ProgressDialog pd = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.gongjiao);
		cityline = (EditText) super.findViewById(R.id.cityline);
		line = (EditText) super.findViewById(R.id.line);

		ll = (ScrollView) super.findViewById(R.id.gongjiaolist);
		button = (Button) super.findViewById(R.id.chaxunline);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pd = ProgressDialog.show(Gongjiao.this, "正在查询", "请稍后...");
				Thread td = new Thread(new chaxun());
				td.start();
			}
		});
	}

	CityUtils cu = new CityUtils();

	class chaxun implements Runnable {

		public void run() {

			pd.cancel();
			// TODO Auto-generated method stub
			try {
				String city=cityline.getText().toString();
				String l=line.getText().toString();
				String json = cu.getcitybus(city, l);
				JSONObject jo = new JSONObject(json);
				JSONObject lines = jo.getJSONObject("lines");
				JSONArray ja = lines.getJSONArray("line");
				Message msg = handler.obtainMessage();
				msg.obj = ja;
				handler.sendMessage(msg);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pd.cancel();
				pd = null;
			}
		}

	}

	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			try {
				JSONArray request = (JSONArray) msg.obj;
				if(request==null){
					return;
				}
				int i = request.length();
				LinearLayout sv = new LinearLayout(Gongjiao.this);
				sv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.WRAP_CONTENT));
				sv.setOrientation(LinearLayout.VERTICAL);
				for (int j = 0; j < i; j++) {
					View view = LayoutInflater.from(Gongjiao.this).inflate(
							R.layout.gongjiao_item, null);
					TextView name = (TextView) view.findViewById(R.id.Name);
					TextView Info = (TextView) view.findViewById(R.id.Info);
					TextView Stats = (TextView) view.findViewById(R.id.Stats);
					JSONObject so = request.getJSONObject(j);
					name.setText(so.getString("name"));
					Info.setText(so.getString("info"));
					Stats.setText(so.getString("stats"));
					sv.addView(view, j);
				}
				ll.removeAllViews();
				ll.addView(sv, new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.WRAP_CONTENT));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	};

}
