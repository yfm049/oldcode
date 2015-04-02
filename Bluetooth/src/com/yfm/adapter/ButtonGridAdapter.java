package com.yfm.adapter;

import java.util.List;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.yfm.bluetooth.MainActivity;
import com.yfm.utils.Channel;
import com.yfm.utils.OrderUtils;

public class ButtonGridAdapter extends BaseAdapter {

	private List<Channel> lc;
	private Context context;
	private OrderUtils orderutils;

	public ButtonGridAdapter(List<Channel> lc, Context context,
			OrderUtils orderutils) {
		this.lc = lc;
		this.context = context;
		this.orderutils = orderutils;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lc.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lc.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ToggleButton tb = new ToggleButton(context);
		Channel cl = lc.get(pos);
		tb.setTextOff(cl.getId()+" "+cl.getName()+"关闭");
		tb.setTextOn(cl.getId()+" "+cl.getName()+"打开");
		tb.setChecked(cl.isState());
		tb.setOnCheckedChangeListener(new OnCheckedChangeListenerImpl(pos));
		tb.setOnLongClickListener(new OnLongClicklistenerImpl(pos));
		return tb;

	}
	class OnLongClicklistenerImpl implements OnLongClickListener{
		private int pos;

		public OnLongClicklistenerImpl(int pos) {
			this.pos = pos;
		}

		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			final Channel cl = lc.get(pos);
			Builder builder=new Builder(context);
			builder.setTitle("设置名字");
			final EditText et=new EditText(context);
			et.setText(cl.getName());
			builder.setView(et);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					cl.setName(et.getText().toString());
					MainActivity ma=(MainActivity)context;
					ma.save();
					ButtonGridAdapter.this.notifyDataSetChanged();
				}
				
			});
			builder.setNegativeButton("取消", null);
			builder.create().show();
			return true;
		}
		
	}
	class OnCheckedChangeListenerImpl implements OnCheckedChangeListener {

		private int pos;

		public OnCheckedChangeListenerImpl(int pos) {
			this.pos = pos;
		}

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean flag) {
			// TODO Auto-generated method stub
			Channel cl = lc.get(pos);
			StringBuffer sb = new StringBuffer();
			sb.append("fc");
			sb.append("d" + (pos + 1));
			System.out.println(flag);
			if (!flag) {
				sb.append("00");
				cl.setState(false);
			} else {
				sb.append("01");
				cl.setState(true);
			}
			sb.append("fd");
			orderutils.SendOrder(sb.toString());

		}

	}

}
