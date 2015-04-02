package com.yfm.adapter;

import java.util.List;

import com.yfm.bluetooth.R;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BlueToothAdapter extends BaseAdapter {

	private List<BluetoothDevice> lbd;
	private Context context;
	public BlueToothAdapter(List<BluetoothDevice> lbd,Context context){
		this.lbd=lbd;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lbd.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lbd.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.bluetooth_item, null);
		}
		TextView bluetoothname=(TextView)view.findViewById(R.id.bluetoothname);
		TextView bluetoothmac=(TextView)view.findViewById(R.id.bluetoothmac);
		BluetoothDevice bd=lbd.get(arg0);
		bluetoothname.setText(bd.getName());
		bluetoothmac.setText(bd.getAddress());
		return view;
	}

}
