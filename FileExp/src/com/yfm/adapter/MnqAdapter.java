package com.yfm.adapter;

import com.yfm.fileexp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MnqAdapter extends BaseAdapter {

	private int[] tubiao=new int[]{R.drawable.fpse,R.drawable.dosbox,R.drawable.ngp,R.drawable.pce,R.drawable.afba,R.drawable.psp,R.drawable.nds4droid};
	private String[] text=new String[]{"fpse","dos","ngp","pce","afba","psp","nds4droid" };
	private Context context;
	public MnqAdapter(Context context){
		this.context=context;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return text.length;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return text[arg0];
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int p, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.mnq_item, null);
		}
		ImageView img=(ImageView)view.findViewById(R.id.img);
		TextView t=(TextView)view.findViewById(R.id.text);
		img.setImageResource(tubiao[p]);
		t.setText(text[p]);
		return view;
	}

}
