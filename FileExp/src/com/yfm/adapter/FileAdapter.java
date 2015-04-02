package com.yfm.adapter;

import java.io.File;
import java.util.List;

import com.yfm.fileexp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter{

	private List<File> files;
	private Context context;
	public FileAdapter(Context context,List<File> files){
		this.context=context;
		this.files=files;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return files.size();
	}

	public Object getItem(int location) {
		// TODO Auto-generated method stub
		return files.get(location);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int location, View view, ViewGroup root) {
		// TODO Auto-generated method stub
		File file=files.get(location);
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.file_item, null);
		}
		ImageView fileimg=(ImageView)view.findViewById(R.id.fileimg);
		TextView filename=(TextView)view.findViewById(R.id.filename);
		if(file.isFile()){
			fileimg.setImageResource(R.drawable.file);
		}else{
			fileimg.setImageResource(R.drawable.folder);
		}
		filename.setText(file.getName());
		return view;
	}

}
