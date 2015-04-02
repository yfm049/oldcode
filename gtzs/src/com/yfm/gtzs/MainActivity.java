package com.yfm.gtzs;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.yfm.adapter.GSidAdapter;
import com.yfm.adapter.GtypeAdapter;
import com.yfm.adapter.InfoAdapter;
import com.yfm.pojo.Info;

public class MainActivity extends Activity {

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Builder builder=new Builder(this);
		builder.setTitle("退出程序");
		builder.setMessage("确定退出程序吗?");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				MainActivity.this.finish();
			}
		});
		builder.setNegativeButton("取消", null);
		builder.create().show();
		
	}
	public static String ipport="b2b.baiqiso.com:8089";
	//public static String ipport="10.120.148.55:8080";
	private ListView gtlistview;
	private InfoAdapter ia;
	private EditText csize;
	private Spinner cgoodstype,cgoodsid;
	private Button search;
	private GtypeAdapter gtypeadapter;
	private GSidAdapter gsiadapter;
	public String goodstype,goodsid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        gtlistview=(ListView)super.findViewById(R.id.gtlistview);
        ia=new InfoAdapter(this);
        gtlistview.setAdapter(ia);
        gtlistview.setOnItemClickListener(new OnItemClickListenerImpls());
        gtlistview.setOnScrollListener(new OnScrollListenerImpl());
        
        csize=(EditText)super.findViewById(R.id.csize);
        cgoodstype=(Spinner)super.findViewById(R.id.cgoodstype);
        cgoodsid=(Spinner)super.findViewById(R.id.cgoodsid);
        search=(Button)super.findViewById(R.id.search);
        gtypeadapter=new GtypeAdapter(this);
        cgoodstype.setAdapter(gtypeadapter);
        gsiadapter=new GSidAdapter(this);
        cgoodsid.setAdapter(gsiadapter);
        cgoodstype.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
        search.setOnClickListener(new OnClickListenerImpl());
        
    }
    class OnScrollListenerImpl implements OnScrollListener{

		public void onScroll(AbsListView arg0, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
				if(totalItemCount>visibleItemCount){
					Log.i("spa", firstVisibleItem+"--"+visibleItemCount+"--"+totalItemCount);
					if((firstVisibleItem+visibleItemCount)==totalItemCount){
						List<NameValuePair> lnv=new ArrayList<NameValuePair>();
						lnv.add(new BasicNameValuePair("dalei", goodstype));
						lnv.add(new BasicNameValuePair("pinzhong", goodsid));
						lnv.add(new BasicNameValuePair("guige", csize.getText().toString()));
						ia.getData(lnv);
					}
				}
		}

		public void onScrollStateChanged(AbsListView arg0, int arg1) {
			// TODO Auto-generated method stub
			Log.i("----", arg1+"");
		}
    	
    }
    class OnItemClickListenerImpls implements OnItemClickListener{

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Log.i("p", "---");
			Info info=(Info)ia.getItem(arg2);
			Intent intent=new Intent(MainActivity.this,DetailActivity.class);
			intent.putExtra("info", info);
			MainActivity.this.startActivity(intent);
		}
    	
    }
    class OnClickListenerImpl implements OnClickListener{

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			ia.currpage=1;
			ia.getLi().clear();
			ia.notifyDataSetChanged();
			search();
		}
    	
    }
    public void search(){
    	goodstype=cgoodstype.getSelectedItem().toString();
		goodsid=cgoodsid.getSelectedItem().toString();
		
		List<NameValuePair> lnv=new ArrayList<NameValuePair>();
		lnv.add(new BasicNameValuePair("dalei", goodstype));
		lnv.add(new BasicNameValuePair("pinzhong", goodsid));
		lnv.add(new BasicNameValuePair("guige", csize.getText().toString()));
		InfoAdapter.isrun=false;
		ia.getData(lnv);
    }
    class OnItemSelectedListenerImpl implements OnItemSelectedListener{
    	
    	public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			String u=gtypeadapter.getItem(arg2).toString();
			gsiadapter.initdate(u);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
