package com.yfm.adapter;

import com.yfm.fileexp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GameAdapter extends BaseAdapter {

	private Context context;
	private int[] tubiao=new int[]{R.drawable.mame,R.drawable.gg,R.drawable.n64,R.drawable.gba,R.drawable.sfc,R.drawable.smd,R.drawable.gbc,R.drawable.nes,R.drawable.emu,R.drawable.icon};
	private String[] tbtext=new String[]{"MAME","GG","N64","GBA","SFC","SMD","GBC","NES","EMU","ALL"};
	public GameAdapter(Context context){
		this.context=context;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return tubiao.length;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return tbtext[arg0];
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.tubiao, null);
		}
		ImageView icon=(ImageView)view.findViewById(R.id.icon);
		icon.setImageResource(tubiao[arg0]);
		TextView tv=(TextView)view.findViewById(R.id.icontext);
		tv.setText(tbtext[arg0]);
		return view;
	}

}
