package com.yfm.entie;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.yfm.adapter.Qjadapter;
import com.yfm.control.Instruction;
import com.yfm.control.SendThread;

public class QingJingModeActivity extends Activity {

	@Override
	protected void onResume() {
		super.onResume();
		jdpass=Instruction.getpass(QingJingModeActivity.this, 1);
        afpass=Instruction.getpass(QingJingModeActivity.this, 2);
	}
	private GridView gridview,afmodeview;
	private Qjadapter adapter=null;
	private String jdpass;
	private String afpass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qingjing);
        gridview=(GridView)super.findViewById(R.id.qjmode);
        adapter=new Qjadapter(this,2);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new OnItemClickListenerimpl(1));
        afmodeview=(GridView)super.findViewById(R.id.afmode);
        adapter=new Qjadapter(this,1);
        afmodeview.setAdapter(adapter);
        afmodeview.setOnItemClickListener(new OnItemClickListenerimpl(2));
        
    }
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==1){
				Toast.makeText(QingJingModeActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
			if(msg.what==2){
				byte[] date=(byte[])msg.obj;
				int b7=date[24]&0xFF;//3
				int b8=date[25]&0xFF;//-1
				int b9=date[26]&0xFF;//0
				int b10=date[27]&0xFF;//-1
				if(b8==(0xAA&0xFF)){
					Toast.makeText(QingJingModeActivity.this, "执行成功", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(QingJingModeActivity.this, "执行失败", Toast.LENGTH_SHORT).show();
				}
			}
		}
		
	};
    class OnItemClickListenerimpl implements OnItemClickListener{
    	private int i;
    	public OnItemClickListenerimpl(int i){
    		this.i=i;
    	}
		public void onItemClick(AdapterView<?> adapter, View view, int pos,
				long id) {
			// TODO Auto-generated method stub
			System.out.println(pos);
			byte[] data = null;
			if(i==1){
				switch (pos+1) {
				case 1:
					//0x01--会客，0x02—夜间,0x03—就餐,0x04—起夜,0x05—影音0x06—影音
					data=Instruction.setNum(0x92, 0x01, 0xFF, 0xFF, 0xFF, jdpass);
					break;
				case 2:
					//影音
					data=Instruction.setNum(0x92, 0x05, 0xFF, 0xFF, 0xFF, jdpass);
					break;
				case 3:
					//影音
					data=Instruction.setNum(0x92, 0x03, 0xFF, 0xFF, 0xFF, jdpass);
					break;
				case 4:
					//影音
					data=Instruction.setNum(0x92, 0x02, 0xFF, 0xFF, 0xFF, jdpass);
					break;	
				case 5:
					
					data=Instruction.setNum(0x92, 0x06, 0xFF, 0xFF, 0xFF, jdpass);
					break;	
				case 6:
					//影音
					data=Instruction.setNum(0x92, 0x04, 0xFF, 0xFF, 0xFF, jdpass);
					break;	
				default:
					break;
				}
			}
			if(i==2){
				switch (pos+7) {
				case 7:
					//影音
					data=Instruction.setNum(0x93, 0x01, 0xFF, 0xFF, 0xFF, afpass);
					break;	
				case 8:
					//影音
					data=Instruction.setNum(0x93, 0x03, 0xFF, 0xFF, 0xFF, afpass);
					break;	
				case 9:
					//影音
					data=Instruction.setNum(0x93, 0x02, 0xFF, 0xFF, 0xFF, afpass);
					break;	
				default:
					break;
				}
			}
			Log.i("date", data+"");
			if(data!=null){
				SendThread st=new SendThread(data, QingJingModeActivity.this);
				st.setHandler(handler);
				st.start();
			}else{
				Toast.makeText(QingJingModeActivity.this, "无此模式..", Toast.LENGTH_SHORT).show();
			}
			
		}
    	
    }


    
}
