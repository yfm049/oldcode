package com.pro.stu;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.pro.utils.Info;
import com.pro.utils.InfoAdapter;
import com.pro.utils.SQLiteUtils;

public class JiluActivity extends Activity {


	private EditText name;
	private EditText date;
	private ImageButton search;
	private DatePickerDialog dpd;
	private SQLiteUtils su;
	private InfoAdapter ia;
	private ListView infolist;
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_jilu);
		name=(EditText)findViewById(R.id.name);
		date=(EditText)findViewById(R.id.date);
		search=(ImageButton)findViewById(R.id.search);
		infolist=(ListView)findViewById(R.id.infolist);
		search.setOnClickListener(new searchOnClickListenerImpl());
		date.setOnClickListener(new dateOnClickListenerImpl());
		back=(Button)findViewById(R.id.back);
		su=new SQLiteUtils(this);
		ia=new InfoAdapter(this);
		infolist.setAdapter(ia);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				JiluActivity.this.finish();
			}
		});
	}
	class searchOnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			List<Info> linfo=su.GetInfo(name.getText().toString(), date.getText().toString());
			ia.getLinfo().clear();
			ia.getLinfo().addAll(linfo);
			ia.notifyDataSetChanged();
			if(linfo.size()==0){
				Toast.makeText(JiluActivity.this, "无数据", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	class dateOnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Calendar calendar=Calendar.getInstance();
			dpd=new DatePickerDialog(JiluActivity.this, dateListener, calendar.get(Calendar.YEAR),  
                    calendar.get(Calendar.MONTH),  
                    calendar.get(Calendar.DAY_OF_MONTH));
			dpd.setCancelable(false);
			dpd.show();
		}
		
	}
	DatePickerDialog.OnDateSetListener dateListener =   
		    new DatePickerDialog.OnDateSetListener() {  
		        @Override  
		        public void onDateSet(DatePicker datePicker,   
		                int year, int month, int dayOfMonth) {
		        	String mt=(month+1)>10?(month+1)+"":"0"+(month+1);
		        	String dm=dayOfMonth>10?dayOfMonth+"":"0"+dayOfMonth;
		        	date.setText(year+"-"+mt+"-"+dm);
		        }  
		    };  

}
