package com.yfm.ydbg;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kobjects.base64.Base64;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.yfm.adapter.TypeAdapter;
import com.yfm.pojo.Type;
import com.yfm.webservice.SoapUtils;

public class XxCjActivity extends Activity {

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK == resultCode) {
			if (requestCode == 0) {
				bitmap = data.getParcelableExtra("data");
				Random rm = new Random();
				lujing.setText(rm.nextInt(99999999) + ".PNG");
			}
		}

	}

	private Bitmap bitmap;
	private EditText title, content, lujing;
	private Spinner firsttype, sectype;
	private ImageView pzbut;
	private ImageView submit, quxiao;
	private List<Type> first = new ArrayList<Type>();
	private List<Type> two = new ArrayList<Type>();
	private TypeAdapter fta;
	private TypeAdapter sec;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xxcj);
		title = (EditText) super.findViewById(R.id.title);
		content = (EditText) super.findViewById(R.id.content);

		lujing = (EditText) super.findViewById(R.id.lujing);

		firsttype = (Spinner) super.findViewById(R.id.firsttype);
		fta = new TypeAdapter(this, first);
		firsttype.setAdapter(fta);
		firsttype.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
		sectype = (Spinner) super.findViewById(R.id.sectype);
		sec = new TypeAdapter(this, two);
		sectype.setAdapter(sec);
		submit = (ImageView) super.findViewById(R.id.submit);
		quxiao = (ImageView) super.findViewById(R.id.quxiao);
		pzbut = (ImageView) super.findViewById(R.id.pzbut);
		pzbut.setOnClickListener(new pzOnClickListenerImpl());
		quxiao.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				XxCjActivity.this.finish();
			}
		});
		submit.setOnClickListener(new submitOnClickListener());
		updatespinner(1, null);
		Log.i("oncreate", "oncreate");
	}

	class pzOnClickListenerImpl implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			XxCjActivity.this.startActivityForResult(intent, 0);
		}

	}


	class OnItemSelectedListenerImpl implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			Type t = first.get(position);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pCode", t.getEventTypeCode());
			updatespinner(2, map);
		}

		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	}

	public void updatespinner(int type, Map<String, Object> mo) {
		if (type == 1) {
			Toast.makeText(XxCjActivity.this, "正在获取一级分类列表", Toast.LENGTH_SHORT)
					.show();
			SoapUtils
					.Send(XxCjActivity.this, "GetFirstType", mo, handler, null);
		} else if (type == 2) {
			Toast.makeText(XxCjActivity.this, "正在获取二级分类列表", Toast.LENGTH_SHORT)
					.show();
			SoapUtils.Send(XxCjActivity.this, "GetSecondType", mo, handler2,
					null);
		}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int what = msg.what;
			if (what == 1) {
				try {
					JSONArray ja = new JSONArray(msg.obj.toString());
					first.clear();
					for (int i = 0; i < ja.length(); i++) {
						JSONObject jo = ja.getJSONObject(i);
						Type t = new Type();
						t.setEventTypeName(jo.getString("EventTypeName"));
						t.setEventTypeCode(jo.getString("EventTypeCode"));
						first.add(t);
					}
					fta.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	};
	private Handler handler2 = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int what = msg.what;
			if (what == 1) {
				try {
					JSONArray ja = new JSONArray(msg.obj.toString());
					two.clear();
					for (int i = 0; i < ja.length(); i++) {
						JSONObject jo = ja.getJSONObject(i);
						Type t = new Type();
						t.setEventTypeName(jo.getString("EventTypeName"));
						t.setEventTypeCode(jo.getString("EventTypeCode"));
						two.add(t);
					}
					sec.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	};

	class submitOnClickListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			String biaoti = title.getText().toString();
			String con = content.getText().toString();
			byte [] b=Bitmap2Bytes(bitmap);
			String uploadBuffer = new String(Base64.encode(b));
			Type ft=first.get(firsttype.getSelectedItemPosition());
			Type to=two.get(sectype.getSelectedItemPosition());
			String name=lujing.getText().toString();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("EventUserAccount", FunActivity.account);
			map.put("EventTitle", biaoti);
			map.put("EventContent", con);
			map.put("EventFirstType", ft.getEventTypeCode());
			map.put("EventSecondType", to.getEventTypeCode());
			map.put("EventImgString", uploadBuffer);
			map.put("EventImgName", name);
			ProgressDialog pd=new ProgressDialog(XxCjActivity.this);
			pd.setTitle("提交信息");
			pd.setMessage("请稍后...");
			pd.show();
			SoapUtils.Send(XxCjActivity.this, "UpLoadNetInfo", map, soaphHandler, pd);
		}

	}

	private Handler soaphHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			if(msg.what==1){
				String ms="上传失败....";
				Log.i("status", msg.obj.toString());
				if("true".equals(msg.obj.toString())){
					ms="上传成功...";
					Toast.makeText(XxCjActivity.this, ms, Toast.LENGTH_LONG).show();
					XxCjActivity.this.finish();
				}else{
					Toast.makeText(XxCjActivity.this, ms, Toast.LENGTH_LONG).show();
				}
				
			}else if(msg.what==2){
				Toast.makeText(XxCjActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
			}
			
		}
		
	};
	
	public byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

}
