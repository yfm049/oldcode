package com.yfm.ydbg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.yfm.adapter.DbsxAdapter;
import com.yfm.pojo.Dbsx;
import com.yfm.webservice.SoapUtils;

public class DbsxActivity extends Activity {

	private DbsxAdapter fa;
	private ListView dbsxlist;
	
	private List<Dbsx> ld=new ArrayList<Dbsx>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dbsx);
        fa=new DbsxAdapter(this,ld);
        dbsxlist=(ListView)super.findViewById(R.id.dbsxlist);
        dbsxlist.setAdapter(fa);
        builderdata();
        dbsxlist.setOnItemClickListener(new dbsxlistOnItemClickListener());
    }
    class dbsxlistOnItemClickListener implements OnItemClickListener{

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Dbsx dbsx=ld.get(position);
			Intent intent=new Intent(DbsxActivity.this,DbsxHuiFuActivity.class);
			intent.putExtra("dbsx", dbsx);
			DbsxActivity.this.startActivity(intent);
		}
    	
    }
    public void builderdata(){
    	Map<String, Object> map=new HashMap<String, Object>();
    	//map.put("account", FunActivity.account);zhbm
    	map.put("account", "zhbm");
    	ProgressDialog pd=new ProgressDialog(this);
    	pd.setTitle("正在获取待办事项列表");
    	pd.setMessage("请稍后");
    	pd.show();
    	SoapUtils.Send(this, "GetUserEvents", map, handler, pd);
    }
    private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int what=msg.what;
			if(what==1){
				try {
					JSONArray ja=new JSONArray(msg.obj.toString());
					for(int i=0;i<ja.length();i++){
						JSONObject jo=ja.getJSONObject(i);
						Dbsx dbsx=new Dbsx();
						dbsx.setEventID(jo.getString("EventID"));
						dbsx.setEventTitle(jo.getString("EventTitle"));
						dbsx.setEventContent(jo.getString("EventContent"));
						dbsx.setEventDateTime(jo.getString("EventDateTime"));
						dbsx.setEventImgUrl(jo.getString("EventImgUrl"));
						ld.add(dbsx);
					}
					fa.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(what==2){
				Toast.makeText(DbsxActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
		}
    	
    };
    
    

    
}
