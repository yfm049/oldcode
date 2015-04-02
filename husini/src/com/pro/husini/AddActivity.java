package com.pro.husini;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.pro.model.HPhone;

public class AddActivity extends Activity {

	
	
	private int code=100;
	private EditText phonenum;
	private RadioGroup mode,ptime;
	private ListenerImpl listener;
	private HPhone phone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		phonenum=(EditText)super.findViewById(R.id.phonenum);
		mode=(RadioGroup)super.findViewById(R.id.mode);
		ptime=(RadioGroup)super.findViewById(R.id.ptime);
		
		phone=new HPhone();
		listener=new ListenerImpl();
		mode.setOnCheckedChangeListener(listener);
		ptime.setOnCheckedChangeListener(listener);
	}
	public void select(View v){
		Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI); 
		this.startActivityForResult(intent, code);
	}
	public void save(View v){
		String nm=phonenum.getText().toString();
		if(nm.length()>0){
			phone.setPhonenum(nm);
			PhoneService.phones.add(phone);
			finish();
		}else{
			Toast.makeText(this, "号码不能为空", Toast.LENGTH_SHORT).show();
		}
		
	}
	public void exit(View v){
		finish();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==code&&resultCode==RESULT_OK){
			Uri uri = data.getData();
			phonenum.setText(getPhonenumber(uri));
		}
	}
	
	private String getPhonenumber(Uri uri) {
		String number = "";
        //得到ContentResolver对象
        ContentResolver cr = this.getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(uri, null, null, null, null);
        
        if(cursor != null){
        	cursor.moveToFirst();
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            
            if(phone != null){
            	phone.moveToFirst();
            	number = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                   
            }
          
            phone.close();
            cursor.close();
        }
       
    	 return number;
    }
	
	class ListenerImpl implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup gp, int arg1) {
			// TODO Auto-generated method stub
			int cid=gp.getCheckedRadioButtonId();
			if(gp.getId()==R.id.mode){
				if(cid==R.id.mode1){
					phone.setType(0);
				}else{
					phone.setType(1);
				}
			}else if(gp.getId()==R.id.ptime){
				if(cid==R.id.time0){
					phone.setTime(0);
				}else if(cid==R.id.time1){
					phone.setTime(1);
				}else if(cid==R.id.time3){
					phone.setTime(3);
				}else if(cid==R.id.time5){
					phone.setTime(5);
				}else if(cid==R.id.time10){
					phone.setTime(10);
				}
			}
			
		}
		
	}
}
