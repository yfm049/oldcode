package com.yfm.pro;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKEvent;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MKLocationManager;
import com.baidu.mapapi.MKPlanNode;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;
import com.baidu.mapapi.RouteOverlay;


public class DiTuActivity extends MapActivity {

	public double mx=-1,my=-1;
	MapView mMapView = null;
	String mStrKey = "A9EA980650DC36DE79E3524D48F92CF43B189A9B";
	BMapManager mBMapMan = null;
	MKSearch mSearch = null;
	MKLocationManager mLocationManager = null;
	LocationListener mLocationListener = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ditu);

		mBMapMan = new BMapManager(this);
		mBMapMan.init(this.mStrKey, new MyGeneralListener());
		mBMapMan.getLocationManager().setNotifyInternal(10, 5);
		mBMapMan.start();
		super.initMapActivity(mBMapMan);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mMapView.setBuiltInZoomControls(true);
		// 设置在缩放动画过程中也显示overlay,默认为不绘制
		mMapView.setDrawOverlayWhenZooming(true);
		mSearch = new MKSearch();
		mSearch.init(mBMapMan, new MKSearchListenerImpl());
		mSearch.geocode("辽宁省营口市政府", "");
		mLocationManager = mBMapMan.getLocationManager();
		mLocationManager.enableProvider(MKLocationManager.MK_NETWORK_PROVIDER);
		
		// 注册定位事件
		mLocationListener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				if (location != null) {
					GeoPoint pt = new GeoPoint(
							(int) (location.getLatitude() * 1e6),
							(int) (location.getLongitude() * 1e6));
					mMapView.getController().animateTo(pt);
					luxian(location.getLatitude() * 1e6, location.getLongitude() * 1e6);

				}
			}
		};
		
	}

	class MKSearchListenerImpl implements MKSearchListener {

		@Override
		public void onGetAddrResult(MKAddrInfo res, int error) {
			// TODO Auto-generated method stub
			if (error != 0) {
				  String str = String.format("错误号：%d", error);
				  Toast.makeText(DiTuActivity.this, str, Toast.LENGTH_LONG).show();
				  return;
				 }
				mx=res.geoPt.getLatitudeE6();
				my=res.geoPt.getLongitudeE6();
				 mMapView.getController().animateTo(res.geoPt);					
				 String strInfo = String.format("纬度：%f 经度：%f\r\n", res.geoPt.getLatitudeE6()/1e6,res.geoPt.getLongitudeE6()/1e6);
				 //Toast.makeText(DiTuActivity.this, strInfo, Toast.LENGTH_LONG).show();
				 Drawable marker = getResources().getDrawable(R.drawable.iconmarka);  //得到需要标在地图上的资源
				 marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());   //为maker定义位置和边界
				 mMapView.getOverlays().clear();
				 mMapView.getOverlays().add(new OverItemT(marker, DiTuActivity.this, res.geoPt, res.strAddr));
		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult res, int error) {
			// TODO Auto-generated method stub
			// 错误号可参考MKEvent中的定义
			if (error != 0 || res == null) {
				Toast.makeText(DiTuActivity.this, "抱歉，未找到路线"+error,
						Toast.LENGTH_SHORT).show();
				return;
			}
			RouteOverlay routeOverlay = new RouteOverlay(DiTuActivity.this,
					mMapView);
			// 此处仅展示一个方案作为示例
			routeOverlay.setData(res.getPlan(0).getRoute(0));
			mMapView.getOverlays().clear();
			mMapView.getOverlays().add(routeOverlay);
			mMapView.invalidate();

			mMapView.getController().animateTo(res.getStart().pt);
		}

		@Override
		public void onGetPoiResult(MKPoiResult res, int arg1, int error) {
			// TODO Auto-generated method stub
			if (error != 0 || res == null) {
				Toast.makeText(DiTuActivity.this, "解析失败", Toast.LENGTH_LONG).show();
				return;
			}
			if (res != null && res.getCurrentNumPois() > 0) {
				GeoPoint ptGeo = res.getAllPoi().get(0).pt;
				// 移动地图到该点：
				mMapView.getController().animateTo(ptGeo);
				
				String strInfo = String.format("纬度：%f 经度：%f\r\n", ptGeo.getLatitudeE6()/1e6, 
						ptGeo.getLongitudeE6()/1e6);
				strInfo += "\r\n附近有：";
				for (int i = 0; i < res.getAllPoi().size(); i++) {
					strInfo += (res.getAllPoi().get(i).name + ";");
				}
				Toast.makeText(DiTuActivity.this, strInfo, Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

	}
	class OverItemT extends ItemizedOverlay<OverlayItem>{
		private List<OverlayItem> mGeoList = new ArrayList<OverlayItem>();

		public OverItemT(Drawable marker, Context context, GeoPoint pt, String title) {
			super(boundCenterBottom(marker));
			
			mGeoList.add(new OverlayItem(pt, title, null));

			populate();
		}

		@Override
		protected OverlayItem createItem(int i) {
			return mGeoList.get(i);
		}

		@Override
		public int size() {
			return mGeoList.size();
		}

		@Override
		public boolean onSnapToItem(int i, int j, Point point, MapView mapview) {
			Log.e("ItemizedOverlayDemo","enter onSnapToItem()!");
			return false;
		}
	}
	class MyGeneralListener implements MKGeneralListener {
		@Override
		public void onGetNetworkState(int iError) {
			Log.d("MyGeneralListener", "onGetNetworkState error is " + iError);
			Toast.makeText(DiTuActivity.this, "您的网络出错啦！", Toast.LENGTH_LONG)
					.show();
		}

		@Override
		public void onGetPermissionState(int iError) {
			Log.d("MyGeneralListener", "onGetPermissionState error is "
					+ iError);
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				// 授权Key错误：
				Toast.makeText(DiTuActivity.this,
						"请在BMapApiDemoApp.java文件输入正确的授权Key！", Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	@Override
	protected void onPause() {
		mBMapMan.getLocationManager().removeUpdates(mLocationListener);
		mBMapMan.stop();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mBMapMan.getLocationManager().requestLocationUpdates(mLocationListener);
		mBMapMan.start();
		flag=true;
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean flag=true;
	public void luxian(double x, double y) {
		if(mx!=-1&&my!=-1&&flag){
			Log.i("xl", x+"--"+y);
			MKPlanNode start = new MKPlanNode();
			start.pt = new GeoPoint((int) (x), (int) (y));
			MKPlanNode end = new MKPlanNode();
			end.pt = new GeoPoint((int)mx, (int)my);
			// 设置驾车路线搜索策略，时间优先、费用最少或距离最短
			mSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);
			mSearch.drivingSearch(null, start, null, end);
			mBMapMan.getLocationManager().removeUpdates(mLocationListener);
			flag=false;
		}
		
	}
}
