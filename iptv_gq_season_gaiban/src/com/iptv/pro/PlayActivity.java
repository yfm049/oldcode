package com.iptv.pro;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.iptv.adapter.NoticeAdapter;
import com.iptv.adapter.TvinfoAdapter;
import com.iptv.pojo.LiveTV;
import com.iptv.pojo.Notice;
import com.iptv.pojo.TvInfo;
import com.iptv.pojo.User;
import com.iptv.season.R;
import com.iptv.thread.NetSpeedThread;
import com.iptv.thread.NoticeThread;
import com.iptv.thread.PlayThread;
import com.iptv.thread.ShouCangThread;
import com.iptv.utils.LayoutListener;
import com.iptv.utils.SqliteUtils;
import com.iptv.utils.Utils;
import com.tr.decryption.Decryption;

public class PlayActivity extends Activity {

	private SurfaceView palyview;
	private PopupWindow pw;
	private LinearLayout progress,errorview,fingview;
	private ListView playlist, noticelist;
	private TvinfoAdapter adapter;
	private NoticeAdapter nadapter;
	private MediaPlayer player;
	private SurfaceHolder holder;
	private int screenWidth;
	private int screenHeight;
	private MediaPlayerListenerImpl MediaPlayerListener;
	private TvInfo tvinfo, sctv;
	private TextView title, code,errormsg,fingmsg,speed;
	private PlayThread playthread;
	private LinearLayout tl;
	private TextView ptitle;
	private ProgressDialog pd;
	private ShouCangThread scthread;
	private User user;
	private SharedPreferences sp;
	private SqliteUtils su;
	private int itemheight;
	private Decryption decry;
	private List<TvInfo> tvlist = new ArrayList<TvInfo>();
	private List<Notice> ntlist = new ArrayList<Notice>();
	private NoticeThread nt;
	private NetSpeedThread netthread;
	private String num = "";
	private int zw=9091;
	private ImageView lostimg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		super.setContentView(R.layout.activity_play);
		code = (TextView) this.findViewById(R.id.code);
		
		errorview = (LinearLayout) super.findViewById(R.id.errorview);
		errormsg = (TextView) super.findViewById(R.id.errormsg);
		
		lostimg=(ImageView)super.findViewById(R.id.lostimg);
		
		fingview = (LinearLayout) super.findViewById(R.id.fingview);
		fingmsg = (TextView) super.findViewById(R.id.fing);

		sp = this.getSharedPreferences("key", Context.MODE_PRIVATE);
		inituser();
		MediaPlayerListener = new MediaPlayerListenerImpl();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		palyview = (SurfaceView) super.findViewById(R.id.palyview);
		holder = palyview.getHolder();
		holder.setFixedSize(screenWidth, screenHeight);
		holder.setFormat(PixelFormat.TRANSPARENT); 
		holder.addCallback(MediaPlayerListener);
		progress = (LinearLayout) super.findViewById(R.id.progress);
		
		
		palyview.setOnKeyListener(new OnKeyListenerImpl());
		lostimg.setOnKeyListener(new OnKeyListenerImpl());
		title = (TextView) super.findViewById(R.id.title);
		speed = (TextView) super.findViewById(R.id.speed);
		
		
		
		
		palyview.setOnClickListener(new OnClickListenerImpl());
		palyview.setOnClickListener(new OnClickListenerImpl());
		palyview.requestFocus();

	}

	private void inituser() {
		user = new User();
		su = new SqliteUtils(this);
		List<LiveTV> listlivetv = su.getAllliveTv();
		user.getLivetvlist().addAll(listlivetv);
		int cl = sp.getInt("cruulive", 0);
		if (listlivetv.size() > 0) {
			if (cl < listlivetv.size()) {
				user.setTempliveid(cl);
			}
			LiveTV livetv = listlivetv.get(user.getTempliveid());
			List<TvInfo> listtv = su.getAllTvinfo(livetv.getId());
			user.getTemplist().addAll(listtv);
			int ct = sp.getInt("currtv", 0);
			if (listtv.size() > 0 && ct < listtv.size()) {
				user.setCurrtvid(ct);
			}
		}

	}

	private void initplayer() {
		if (player == null) {
			player = new MediaPlayer();
			player.setOnPreparedListener(MediaPlayerListener);
			player.setOnVideoSizeChangedListener(MediaPlayerListener);
			player.setOnSeekCompleteListener(MediaPlayerListener);
			player.setOnInfoListener(MediaPlayerListener);
			player.setOnCompletionListener(MediaPlayerListener);
			player.setOnErrorListener(MediaPlayerListener);
			player.setOnBufferingUpdateListener(MediaPlayerListener);
			decry = new Decryption(this, handler);
			decry.initTRDecryptionServer();
			Log.i("tvinfo", "解密卡初始化");
		}

	}

	class OnClickListenerImpl implements OnClickListener,DialogInterface.OnClickListener {

		@Override
		public void onClick(View but) {
			// TODO Auto-generated method stub
			Log.i("but", but.getId() + "");
			if (but.getId() == R.id.palyview||but.getId()==R.id.lostimg) {
				if (num != null && !"".equals(num)) {
					Log.i("tvinfo", num + " code");
					handler.sendEmptyMessage(1080);
				} else {
					showpop();
				}
			}
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			PlayActivity.this.finish();
		}

	}

	class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			// 播放当前选中
			user.sethisid();
			TvInfo info = user.playtv(arg2);
			if (info != null) {
				startplay(info);
			}

		}

	}

	public void savedata(int currliveid, int currtvid) {
		Editor edit = sp.edit();
		edit.putInt("cruulive", currliveid);
		edit.putInt("currtv", currtvid);
		edit.commit();
	}

	public void startplay(TvInfo info) {
		if (!info.equals(tvinfo)) {
			lostimg.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
			tvinfo = info;
			closeErrorDialog();
			savedata(user.getCurrliveid(), user.getCurrtvid());
			playlist.setSelection(user.getCurrtvid());
			Log.i("tvinfo", "" + tvinfo.getName());
			if (playthread != null) {
				playthread.close();
			}
			String userid = sp.getString("name", "");
			if (playthread != null) {
				playthread.close();
			}
			int m = decry.resetTRDecryptionServer();
			Log.i("tvinfo", "调用resetTRDecryptionServer换台"+m);
			playthread = new PlayThread(PlayActivity.this, handler, userid,
					tvinfo);
			playthread.start();
		}
		pw.dismiss();
		
	}

	public void play(String url) {
		Log.i("tvinfo", "开始播放 " + tvinfo.getName());
		player.reset();
		try {
			player.setDataSource(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		player.setDisplay(holder);
		player.prepareAsync();
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			Log.i("tvinfo", msg.what + "" + msg.obj);
			if (msg.what == 1) {
				progress.setVisibility(View.VISIBLE);
				title.setText(msg.obj + tvinfo.getName() + " ...");
			}
			if (msg.what == 2) {
				Log.i("tvinfo", msg.obj + tvinfo.getName());
				title.setText(msg.obj + tvinfo.getName() + " ...");
			}
			if (msg.what == 3) {
				title.setText(msg.obj + tvinfo.getName() + " ...");
				play(tvinfo.getplayurl());
				handler.sendEmptyMessageDelayed(3399, 30000);
				if(netthread==null){
					netthread=new NetSpeedThread(PlayActivity.this, tvinfo, handler);
					netthread.start();
				}else{
					netthread.reset(tvinfo);
				}
				
				
			}
			if (msg.what == 4) {
				title.setText(msg.obj + tvinfo.getName() + " ...");
			}
			if (msg.what == 100) {
				pd.dismiss();
				if (msg.arg1 == 1) {
					if ("0".equals(sctv.getFlag())) {
						sctv.setFlag("1");
						su.updatetvinfo(sctv, 1);
						adapter.notifyDataSetChanged();
					} else if ("1".equals(sctv.getFlag())) {
						sctv.setFlag("0");
						su.updatetvinfo(sctv, 0);
						adapter.notifyDataSetChanged();
					} else if ("2".equals(sctv.getFlag())) {
						su.updatetvinfo(sctv, 0);
						user.getTemplist().remove(sctv);
						user.getTvlist().remove(sctv);
						adapter.notifyDataSetChanged();
					}
				} else {
					Toast.makeText(PlayActivity.this, "收藏失败",
							Toast.LENGTH_SHORT).show();
				}
			}
			if (msg.what == 1080) {
				ChangeCode();
				num = "";
				code.setVisibility(View.GONE);
			}
			if (msg.what == Decryption.HANDLER_USB_DEVICES_INIT_SUCCEE) {

				int i = decry.startTRDecryptionServer();
				Log.i("tvinfo", "解密卡初始化" + i);
				TvInfo info = user.playtv(user.getCurrtvid());
				if (info != null) {
					startplay(info);
				}
				
			}
			if (msg.what == Decryption.HANDLER_USB_DEVICES_INIT_FAILE) {
				ShowErrorDialog("未检测到设备或者初始化设备失败");
			}
			if (msg.what == Decryption.HANDLER_USB_DEVICES_PERMISSION_DENIED) {
				ShowErrorDialog("无权限");
			}
			if (msg.what == Decryption.HANDLER_USB_DEVICES_FINGERBANNER) {
				int[] outputInfo = new int[300]; //包含数组有效长度和指纹信息 
				String[] fpstr = new String[1];  //指纹字符串
				fpstr = decry.TRCA_Control(4, null, outputInfo);
				if(fpstr!=null){
					fingview.setVisibility(View.VISIBLE);
					fingmsg.setText(fpstr[0]);
					int time=outputInfo[7];
					System.out.println(time);
					handler.sendEmptyMessageDelayed(zw, time*1000);
				}
			}
			if (msg.what == Decryption.HANDLER_USB_DEVICES_CABANNER) {
				Log.i("设备工作状态索引值", msg.arg1+"  "+Utils.CaBanner_chinese[msg.arg1]);
				if(msg.arg1==0||msg.arg1==23){
					closeErrorDialog();
				}else{
					ShowErrorDialog(Utils.CaBanner_chinese[msg.arg1]);
				}
			}
			if(msg.what==zw){
				fingview.setVisibility(View.INVISIBLE);
			}
			if(msg.what==2098){
				speed.setText(msg.obj.toString());
			}
			if(msg.what==2099){
				
			}
			if(msg.what==3399){
				progress.setVisibility(View.GONE);
				lostimg.setVisibility(View.VISIBLE);
			}
			
		}

	};

	private void show(String title, String msg) {
		pd = new ProgressDialog(this, ProgressDialog.THEME_HOLO_DARK);
		pd.setTitle(title);
		pd.setMessage(msg);
		pd.show();
	}

	class MediaPlayerListenerImpl implements OnPreparedListener,
			OnVideoSizeChangedListener, OnSeekCompleteListener, OnInfoListener,
			OnCompletionListener, OnErrorListener, OnBufferingUpdateListener,
			SurfaceHolder.Callback {

		@Override
		public void onPrepared(MediaPlayer player) {
			// TODO Auto-generated method stub
			player.start();
			handler.removeMessages(3399);
		}

		@Override
		public void onVideoSizeChanged(MediaPlayer arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			Log.i("tvinfo", "onVideoSizeChanged");
			progress.setVisibility(View.GONE);
		}

		@Override
		public void onSeekComplete(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			Log.i("tvinfo", "onSeekComplete");
		}

		@Override
		public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			Log.i("tvinfo", "onInfo");
			return false;
		}

		@Override
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			Log.i("tvinfo", "onCompletion");
		}

		@Override
		public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			Log.i("tvinfo", "出现错误");
			return false;
		}

		@Override
		public void onBufferingUpdate(MediaPlayer arg0, int arg1) {
			// TODO Auto-generated method stub
			Log.i("tvinfo", "onBufferingUpdate");
			title.setText(tvinfo.getName() + "正在缓冲...");
		}

		@Override
		public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			Log.i("tvinfo", "surfaceChanged");

		}

		@Override
		public void surfaceCreated(SurfaceHolder arg0) {
			// TODO Auto-generated method stub
			Log.i("tvinfo", "surfaceCreated");
			initpopwindow();
			initplayer();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder arg0) {
			// TODO Auto-generated method stub
			decry.releaseTRDecryptionServer();
		}

	}

	private void initpopwindow() {
		View view = LayoutInflater.from(this).inflate(
				R.layout.activity_play_qlisg, null);
		int height = (int) (screenHeight * 0.9);
		int width = (int) (height / 0.665);
		pw = new PopupWindow(view, width, height, true);

		ColorDrawable dw = new ColorDrawable(-00000);
		pw.setBackgroundDrawable(dw);
		pw.setOutsideTouchable(true);
		playlist = (ListView) view.findViewById(R.id.playlist);
		noticelist = (ListView) view.findViewById(R.id.noticelist);
		noticelist.setItemsCanFocus(true);
		tl = (LinearLayout) view.findViewById(R.id.titlelayout);
		tl.setOnKeyListener(new OnKeyListenerImpl());
		ptitle = (TextView) view.findViewById(R.id.title);
		playlist.setOnKeyListener(new OnKeyListenerImpl());
		playlist.setOnItemClickListener(new OnItemClickListenerImpl());
		adapter = new TvinfoAdapter(this, tvlist);
		adapter.setVcount(10);
		playlist.setAdapter(adapter);
		playlist.getViewTreeObserver().addOnGlobalLayoutListener(
				new LayoutListener(playlist, adapter));
		playlist.setOnItemSelectedListener(new OnKeyListenerImpl());
		nadapter = new NoticeAdapter(this, ntlist);
		nadapter.setVcount(10);
		noticelist.setAdapter(nadapter);
		noticelist.getViewTreeObserver().addOnGlobalLayoutListener(
				new LayoutListener(noticelist, nadapter));

	}

	class OnKeyListenerImpl implements OnKeyListener, OnItemSelectedListener {

		@Override
		public boolean onKey(View but, int arg1, KeyEvent event) {
			// TODO Auto-generated method stub
			Log.i("code", "--" + event.getKeyCode() + "--");
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				if (but.getId() == R.id.palyview||but.getId()==R.id.lostimg) {
					if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
						user.sethisid();
						TvInfo info = user.up();
						if (info != null) {
							startplay(info);
						}
					}
					if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT){
						if(user.getCurrliveid()!=user.gethisid()&&user.gethisid()!=-1){
							TvInfo info = user.getTvlist().get(user.gethisid());
							user.setCurrtvid(user.gethisid());
							startplay(info);
						}
					}
					if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
						user.sethisid();
						TvInfo info = user.down();
						if (info != null) {
							
							startplay(info);
						}
					} else {
						numChange(event.getKeyCode());
					}
				} else if (but.getId() == R.id.titlelayout) {
					if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
						pw.dismiss();
					}
					if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
						LiveTV livetv = user.left();
						showlist(livetv);
					}
					if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
						LiveTV livetv = user.right();
						showlist(livetv);
					}
				} else if (but.getId() == R.id.playlist) {
					if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
						pw.dismiss();
					}
					if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
						int i = Utils.pageleft(playlist
								.getFirstVisiblePosition(), playlist
								.getLastVisiblePosition(), user.getTvlist()
								.size());
						playlist.setSelectionFromTop(i, 0);
						return true;
					}
					if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
						int i = Utils.pageright(playlist
								.getFirstVisiblePosition(), playlist
								.getLastVisiblePosition(), user.getTvlist()
								.size());
						playlist.setSelectionFromTop(i, 0);
						return true;
					}
					if (event.getKeyCode() == KeyEvent.KEYCODE_PAGE_UP) {
						LiveTV livetv = user.left();
						showlist(livetv);
					}
					if (event.getKeyCode() == KeyEvent.KEYCODE_PAGE_DOWN) {
						LiveTV livetv = user.right();
						showlist(livetv);
					}
					if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
						sctv = (TvInfo) playlist.getSelectedItem();
						if (sctv != null && "0".equals(sctv.getFlag())) {
							show("收藏", "正在收藏....");
							scthread = new ShouCangThread(PlayActivity.this,
									handler, "addshouchang.jsp?active="
											+ sp.getString("name", "")
											+ "&name=" + sctv.getId()
											+ "&action=add");
							scthread.start();
						} else if (sctv != null
								&& ("1".equals(sctv.getFlag()) || "2"
										.equals(sctv.getFlag()))) {
							show("收藏", "正在取消收藏....");
							scthread = new ShouCangThread(PlayActivity.this,
									handler, "addshouchang.jsp?active="
											+ sp.getString("name", "")
											+ "&name=" + sctv.getId()
											+ "&action=delete");
							scthread.start();
						}
					}
				}
			}

			return false;
		}

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			TvInfo tvinfo = tvlist.get(arg2);
			if (nt != null) {
				nt.cancel(true);
			}
			nt = new NoticeThread(nadapter, ntlist);
			nt.execute(tvinfo);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	public void numChange(int keycode) {
		switch (keycode) {
		case KeyEvent.KEYCODE_0:
			num += "0";
			break;
		case KeyEvent.KEYCODE_1:
			num += "1";
			break;
		case KeyEvent.KEYCODE_2:
			num += "2";
			break;
		case KeyEvent.KEYCODE_3:
			num += "3";
			break;
		case KeyEvent.KEYCODE_4:
			num += "4";
			break;
		case KeyEvent.KEYCODE_5:
			num += "5";
			break;
		case KeyEvent.KEYCODE_6:
			num += "6";
			break;
		case KeyEvent.KEYCODE_7:
			num += "7";
			break;
		case KeyEvent.KEYCODE_8:
			num += "8";
			break;
		case KeyEvent.KEYCODE_9:
			num += "9";
			break;
		}
		showChannel();
	}

	public void showChannel() {
		code.setVisibility(View.VISIBLE);
		code.setText(num);
		handler.removeMessages(1080);
		handler.sendEmptyMessageDelayed(1080, 2000);
	}

	public void ChangeCode() {
		if (num != null && !"".equals(num)) {
			int cd = Integer.parseInt(num);
			if (cd > 0 && cd < user.getTvlist().size() + 1) {
				TvInfo info = user.getTvlist().get(cd - 1);
				if (info != null) {
					user.sethisid();
					user.setCurrtvid(cd - 1);
					startplay(info);
				}
			}
		}
	}

	public void showlist(LiveTV lt) {
		Log.i("tvinfo", lt.getName() + "--" + lt.getId());
		user.getTemplist().clear();
		user.getTemplist().addAll(su.getAllTvinfo(lt.getId()));
		ptitle.setText(lt.getName());
		tvlist.clear();
		tvlist.addAll(user.getTemplist());
		playlist.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	private void showpop() {
		if (pw != null) {
			tvlist.clear();
			tvlist.addAll(user.getTvlist());
			pw.showAtLocation(palyview, Gravity.CENTER, 0, 0);
			ptitle.setText(user.gettitle());
			playlist.setSelectionFromTop(user.getCurrtvid(), itemheight);
			adapter.notifyDataSetChanged();
			playlist.requestFocus();
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (player != null) {
			Log.i("player", "player stop");
			player.pause();
			player.stop();
			player.release();
			if(netthread!=null){
				netthread.stopsp();
				netthread=null;
			}
		}
	}
	private void ShowErrorDialog(String msg){
		errorview.setVisibility(View.VISIBLE);
		errormsg.setText(msg);
	}
	private void closeErrorDialog(){
		errorview.setVisibility(View.GONE);
	}
	

}
