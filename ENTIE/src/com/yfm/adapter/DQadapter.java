package com.yfm.adapter;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
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

public class DQadapter extends BaseExpandableListAdapter {

	private Context context;
	private JSONArray jo;
	private String[] leibie = new String[] { "插座", "空调", "窗帘" };
	private List<String[]> ls=new ArrayList<String[]>();
	public DQadapter(Context context) {
		this.context = context;
		jo = getdate();
		if (jo == null || jo.length() <= 0) {
			initdate();
			jo = getdate();
		}
		String[] s1=new String[]{"开","关"};
		String[] s2=new String[]{"开","关"};
		ls.clear();
		ls.add(s1);
		ls.add(s2);
	}

	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		try {
			return jo.getJSONArray(groupPosition).getJSONObject(childPosition);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return childPosition;
	}

	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	public View getChildView(int gp, int childPosition, boolean isLastChild,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		final int groupPosition = gp;
		if (gp != 2) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.dianqi_item, null);
			TextView tv = (TextView) convertView.findViewById(R.id.dqitem);
			
			ToggleButton tbn = (ToggleButton) convertView
					.findViewById(R.id.dqopenorclose);
			tbn.setOnClickListener(new OnClickListenerimpl(groupPosition,
					childPosition));
			try {
				tv.setOnClickListener(new rename(jo.getJSONArray(groupPosition)
						.getJSONObject(childPosition)));
				tv.setText(jo.getJSONArray(groupPosition)
						.getJSONObject(childPosition).getString("name"));
				tbn.setChecked(jo.getJSONArray(groupPosition)
						.getJSONObject(childPosition).getBoolean("check"));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (gp == 2){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.dianqi_item2, null);
			Button close = (Button) convertView
					.findViewById(R.id.close);
			Button open = (Button) convertView
					.findViewById(R.id.open);
			TextView tv = (TextView) convertView.findViewById(R.id.dqitem);
			close.setOnClickListener(new OCOnClickListenerimpl(groupPosition,
					childPosition,close,open));
			open.setOnClickListener(new OCOnClickListenerimpl(groupPosition,
					childPosition,close,open));
			try {
				tv.setOnClickListener(new rename(jo.getJSONArray(groupPosition)
						.getJSONObject(childPosition)));
				tv.setText(jo.getJSONArray(groupPosition)
						.getJSONObject(childPosition).getString("name"));
				String[] s=ls.get(childPosition);
				open.setText(s[0]);
				close.setText(s[1]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return convertView;
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
				String name = rename.getString("name");
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
									rename.put("name", te.getText().toString());
									SharedPreferences sp = context
											.getSharedPreferences("dianqi",
													Context.MODE_PRIVATE);
									Editor e = sp.edit();
									e.putString("dq", jo.toString());
									DQadapter.this.notifyDataSetChanged();
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
	class OCOnClickListenerimpl implements OnClickListener{
		int groupPosition;
		int childPosition;
		Button close;
		Button open;
		byte[] data = null;
		public OCOnClickListenerimpl(int groupPosition, int childPosition,Button close,Button open) {
			this.groupPosition = groupPosition;
			this.childPosition = childPosition;
			this.close=close;
			this.open=open;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button bv=(Button)v;
			String jdpass = Instruction.getpass(context, 1);
			if (groupPosition == 2) {
				
				Log.i("tag", bv.getText()+"");
				if("开".equals(bv.getText())){
					data = Instruction.setNum(0x90, 0x04, childPosition + 1,
							0x00, 0x00, jdpass);
					open.setText("开");
					close.setText("关");
					bv.setText("停");
				}else if("关".equals(bv.getText())){
					data = Instruction.setNum(0x90, 0x04, childPosition + 1,
							0xFF, 0x00, jdpass);
					open.setText("开");
					close.setText("关");
					bv.setText("停");
				}else{
					data = Instruction.setNum(0x90, 0x04, childPosition + 1,
							0x01, 0x00, jdpass);
					open.setText("开");
					close.setText("关");
				}
				String[] p=ls.get(childPosition);
				p[0]=open.getText().toString();
				p[1]=close.getText().toString();
				SendThread st = new SendThread(data, context);
				st.setHandler(handler);
				st.start();
				
			}
		}
		
	}
	class OnClickListenerimpl implements OnClickListener {

		int groupPosition;
		int childPosition;
		
		public OnClickListenerimpl(int groupPosition, int childPosition) {
			this.groupPosition = groupPosition;
			this.childPosition = childPosition;
			
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			String jdpass = Instruction.getpass(context, 1);
			ToggleButton tb = (ToggleButton) v;
			byte[] data = null;
			if (groupPosition == 0) {
				if (!tb.isChecked()) {
					data = Instruction.setNum(0x90, 0x02, childPosition + 1,
							0x00, 0x00, jdpass);
				} else {
					data = Instruction.setNum(0x90, 0x02, childPosition + 1,
							0xFF, 0x00, jdpass);
				}
			}
			if (groupPosition == 1) {
				if (!tb.isChecked()) {
					data = Instruction.setNum(0x90, 0x03, childPosition + 1,
							0x00, 0x00, jdpass);
				} else {
					data = Instruction.setNum(0x90, 0x03, childPosition + 1,
							0xFF, 0x00, jdpass);
				}
			}
			SendThread st = new SendThread(data, context);
			st.setHandler(handler);
			st.start();
		}

	}

	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		try {
			return jo.getJSONArray(groupPosition).length();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupPosition;
	}

	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		try {
			return jo.getJSONArray(groupPosition);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupPosition;
	}

	public int getGroupCount() {
		// TODO Auto-generated method stub
		return jo.length();
	}

	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.dianqi_group, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.groupname);
		tv.setText(leibie[groupPosition]);
		isExpanded = true;
		return convertView;
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	public void initdate() {
		try {
			JSONArray ja = new JSONArray();
			for (int i = 0; i < 4; i++) {
				JSONObject jo = new JSONObject();
				jo.put("name", "插座" + (i + 1));
				jo.put("check", false);
				ja.put(jo);
			}
			JSONArray jc = new JSONArray();
			for (int i = 0; i < 2; i++) {
				JSONObject jo = new JSONObject();
				jo.put("name", "空调" + (i + 1));
				jo.put("check", false);
				jc.put(jo);
			}
			JSONArray cl = new JSONArray();
			for (int i = 0; i < 2; i++) {
				JSONObject jo = new JSONObject();
				jo.put("name", "窗帘" + (i + 1));
				jo.put("check", false);
				cl.put(jo);
			}
			JSONArray jarray = new JSONArray();
			jarray.put(ja);
			jarray.put(jc);
			jarray.put(cl);
			SharedPreferences sp = context.getSharedPreferences("dianqi",
					Context.MODE_PRIVATE);
			Editor e = sp.edit();
			e.putString("dq", jarray.toString());

			e.commit();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONArray getdate() {
		JSONArray jo = null;
		try {
			SharedPreferences sp = context.getSharedPreferences("dianqi",
					Context.MODE_PRIVATE);
			String json = sp.getString("dq", "");
			jo = new JSONArray(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo;

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			try {
				if (msg.what == 1) {
					Toast.makeText(context, msg.obj.toString(),
							Toast.LENGTH_SHORT).show();
				}
				if (msg.what == 2) {
					byte[] date = (byte[]) msg.obj;
					int bz = date[19] & 0xFF;// 1
					int b7 = date[24] & 0xFF;// 1
					int b8 = date[25] & 0xFF;// 1
					int b9 = date[26] & 0xFF;// -1
					int b10 = date[27] & 0xFF;// -1
					Log.i("状态", bz+"--"+b7+"--"+b8+"--"+b9);
					if (b7 == 2 || b7 == 3) {

						JSONObject jb = jo.getJSONArray(b7 - 2).getJSONObject(
								b8 - 1);
						if (b9 == (0xFF & 0xFF)) {
							jb.put("check", true);
						} else if(b9 == (0x00 & 0xFF)){
							jb.put("check", false);
						}
						DQadapter.this.notifyDataSetChanged();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	};

	public void startTongbu() {
		new tongbu().start();
	}

	class tongbu extends Thread {
		byte[] data = null;
		String jdpass = Instruction.getpass(context, 1);

		@Override
		public void run() {
			try {
				int jol = jo.length();
				for (int i = 0; i < jol; i++) {
					int cl = jo.getJSONArray(i).length();
					for (int j = 0; j < cl; j++) {
						data = Instruction.setNum(0x91, i + 2, j + 1, 0xFF,
								0x00, jdpass);
						SendUtils su = new SendUtils(context, handler);
						su.Sendmsg(data);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
