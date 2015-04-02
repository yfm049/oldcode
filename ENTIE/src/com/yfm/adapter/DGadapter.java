package com.yfm.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.yfm.control.Instruction;
import com.yfm.control.SendThread;
import com.yfm.control.SendUtils;
import com.yfm.entie.R;

public class DGadapter extends BaseAdapter {

	private JSONArray ja = null;
	private Context context;
	private int dgcount = 8;

	public DGadapter(Context context) {
		this.context = context;
		ja = getDate();
		if (ja.length() <= 0) {
			initdate();
			ja = getDate();
		}
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return ja.length();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		try {
			return ja.get(arg0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arg0;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.dengguang_item, null);
		}
		try {
			TextView name = (TextView) view.findViewById(R.id.name);
			final ImageView daimage = (ImageView) view
					.findViewById(R.id.dgimage);
			JSONObject jo = ja.getJSONObject(arg0);
			name.setText(jo.getString("dg"));
			name.setOnClickListener(new rename(jo));
			ToggleButton tb = (ToggleButton) view
					.findViewById(R.id.openorclose);
			tb.setChecked(jo.getBoolean("check"));
			tb.setOnClickListener(new OnclickListenerImpl(arg0));
			tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if (isChecked) {
						daimage.setImageResource(R.drawable.eleapp_light_on);
					} else {
						daimage.setImageResource(R.drawable.eleapp_light_off);
					}
				}
			});
			if (tb.isChecked()) {
				daimage.setImageResource(R.drawable.eleapp_light_on);
			} else {
				daimage.setImageResource(R.drawable.eleapp_light_off);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}

	class rename implements OnClickListener {

		private JSONObject rename;

		public rename(JSONObject rename) {
			this.rename = rename;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Builder builder = new Builder(context);
			builder.setTitle("更改名称");
			final EditText te = new EditText(context);
			try {
				String name = rename.getString("dg");
				System.out.println(name);
				te.setText(name);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			builder.setView(te);
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if (te.getText() != null
									&& !"".equals(te.getText().toString())) {
								try {
									rename.put("dg", te.getText().toString());
									SharedPreferences sp = context
											.getSharedPreferences("dengguang",
													Context.MODE_PRIVATE);
									Editor e = sp.edit();
									e.putString("dg", ja.toString());
									e.commit();
									DGadapter.this.notifyDataSetChanged();
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								Toast.makeText(context, "名称不能为空",
										Toast.LENGTH_SHORT).show();
							}
						}
					});
			builder.setNegativeButton("取消", null);
			builder.create().show();
		}

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {
				Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT)
						.show();
				
			}
			if (msg.what == 2) {
				byte[] date = (byte[]) msg.obj;
				int b7 = date[24] & 0xFF;// 1
				int b8 = date[25] & 0xFF;// 1
				int b9 = date[26] & 0xFF;// -1
				int b10 = date[27] & 0xFF;// -1
				try {
					if (b7 == 1) {

						if (b9 != (0x00 & 0xFF)) {
							JSONObject jo = ja.getJSONObject(b8 - 1);
							jo.put("check", true);
							DGadapter.this.notifyDataSetChanged();
						} else {
							JSONObject jo = ja.getJSONObject(b8 - 1);
							jo.put("check", false);
							DGadapter.this.notifyDataSetChanged();
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	};

	class OnclickListenerImpl implements OnClickListener {
		int i = -1;

		public OnclickListenerImpl(int i) {
			this.i = i;
		}

		byte[] data = null;

		public void onClick(View v) {
			String jdpass = Instruction.getpass(context, 1);
			ToggleButton tb = (ToggleButton) v;
			if (!tb.isChecked()) {
				data = Instruction
						.setNum(0x90, 0x01, i + 1, 0x00, 0x00, jdpass);
			} else {
				data = Instruction
						.setNum(0x90, 0x01, i + 1, 0xFF, 0x00, jdpass);
			}
			SendThread st = new SendThread(data, context);
			st.setHandler(handler);
			st.start();
		}

	}

	public void initdate() {
		try {
			JSONArray ja = new JSONArray();

			for (int i = 0; i < dgcount; i++) {

				JSONObject jo = new JSONObject();
				jo.put("dg", "灯光" + (i + 1));
				jo.put("check", false);
				ja.put(jo);

			}
			SharedPreferences sp = context.getSharedPreferences("dengguang",
					Context.MODE_PRIVATE);
			Editor e = sp.edit();
			e.putString("dg", ja.toString());
			e.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONArray getDate() {
		JSONArray ja = new JSONArray();
		try {
			SharedPreferences sp = context.getSharedPreferences("dengguang",
					Context.MODE_PRIVATE);
			String p = sp.getString("dg", "");
			ja = new JSONArray(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ja;
	}

	public void startTongbu() {
		new tongbu().start();
	}


	class tongbu extends Thread {
		byte[] data = null;
		String jdpass = Instruction.getpass(context, 1);

		@Override
		public void run() {
			for (int i = 0; i < dgcount; i++) {
				data = Instruction
						.setNum(0x91, 0x01, i + 1, 0xFF, 0x00, jdpass);
				SendUtils su = new SendUtils(context, handler);
				su.Sendmsg(data);
			}
		}

	}

}
