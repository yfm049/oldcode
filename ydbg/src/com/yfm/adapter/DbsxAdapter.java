package com.yfm.adapter;

import java.util.List;

import com.yfm.pojo.Dbsx;
import com.yfm.ydbg.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DbsxAdapter extends BaseAdapter {

	private Context context;
	private List<Dbsx> ld;
	public DbsxAdapter(Context context,List<Dbsx> ld){
		this.context=context;
		this.ld=ld;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return ld.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return ld.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.activity_dbsx_item, null);
		}
		Dbsx db=ld.get(arg0);
		TextView EventTitle=(TextView)view.findViewById(R.id.EventTitle);
		TextView EventContent=(TextView)view.findViewById(R.id.EventContent);
		TextView EventDateTime=(TextView)view.findViewById(R.id.EventDateTime);
		EventTitle.setText(db.getEventTitle());
		EventContent.setText(db.getEventContent());
		EventDateTime.setText(db.getEventDateTime());
		return view;
	}
	

}
