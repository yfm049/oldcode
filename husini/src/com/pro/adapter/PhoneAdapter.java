package com.pro.adapter;

import com.pro.husini.PhoneService;
import com.pro.husini.R;
import com.pro.model.HPhone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PhoneAdapter extends BaseAdapter {

	private Context context;
	public PhoneAdapter(Context context){
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PhoneService.phones.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return PhoneService.phones.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		HPhone phone=PhoneService.phones.get(arg0);
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.phone_item, null);
		}
		TextView pn=(TextView)view.findViewById(R.id.phone);
		TextView type=(TextView)view.findViewById(R.id.type);
		TextView time=(TextView)view.findViewById(R.id.time);
		pn.setText(phone.getPhonenum());
		type.setText(HPhone.types[phone.getType()]);
		time.setText(phone.getTime()+"√Î");
		return view;
	}

}
