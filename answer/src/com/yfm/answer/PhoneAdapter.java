package com.yfm.answer;

import java.util.List;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class PhoneAdapter extends BaseAdapter {

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		ls=su.querylist();
		super.notifyDataSetChanged();
	}
	public static String add="添加"; 
	public static String update="修改"; 
	public static String delete="删除"; 
	private SqlUtils su;
	public SqlUtils getSu() {
		return su;
	}
	private List<String> ls=null;
	private Context context;
	public PhoneAdapter(Context context){
		this.context=context;
		initdate();
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return ls.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return ls.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		view=LayoutInflater.from(context).inflate(R.layout.phone_item, null);
		final TextView tv=(TextView)view.findViewById(R.id.myphonenum);
		tv.setText(ls.get(arg0));
		return view;
	}
	private void initdate(){
		su=new SqlUtils(context);
		ls=su.querylist();
	}
	public void showDialgo(final String p,final String phone){
		try {
			Builder builder=new Builder(context);
			builder.setTitle(p);
			View view=LayoutInflater.from(context).inflate(R.layout.addorupdate, null);
			final EditText tv=(EditText)view.findViewById(R.id.myphonenum);
			tv.setText(phone);
			builder.setView(view);
			builder.setPositiveButton(p, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if(PhoneAdapter.update.equals(p)){
						CharSequence to=tv.getText();
						su.update(phone,to );
						PhoneAdapter.this.notifyDataSetChanged();
					}
					if(PhoneAdapter.add.equals(p)){
						CharSequence to=tv.getText();
						su.add(to);
						PhoneAdapter.this.notifyDataSetChanged();
					}
				}
			});
			builder.setNegativeButton("取消", null);
			builder.create().show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
