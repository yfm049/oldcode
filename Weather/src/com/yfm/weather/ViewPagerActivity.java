package com.yfm.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityGroup;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.utils.GetCity;
import com.yfm.utils.weather;

public class ViewPagerActivity extends ActivityGroup {

	private ImageView add;
	private ImageView shuaxin;
	private ViewPager vp;
	private List<View> lv = new ArrayList<View>();
	private List<JSONObject> ljo = new ArrayList<JSONObject>();
	JSONArray jo = new JSONArray();
	myadapter adapter;
	EditText citys;
	SharedPreferences sp;
	private int id = 0;

	class shuaxin implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			updateView up = new updateView(lv.get(id), id);
			up.start();
		}

	}

	class showcity implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Builder builder = new Builder(ViewPagerActivity.this);
			builder.setTitle("设置城市");
			View view = LayoutInflater.from(ViewPagerActivity.this).inflate(
					R.layout.inputcity, null);
			citys = (EditText) view.findViewById(R.id.city);
			builder.setView(view);
			builder.setPositiveButton("确定", new queding());
			builder.setNegativeButton("取消", null);
			builder.create().show();
		}

	}

	class queding implements android.content.DialogInterface.OnClickListener {

		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			String c = citys.getText().toString();
			GetCity gc = new GetCity(ViewPagerActivity.this);
			String code = gc.getCitycode(c);
			if (code == null || "".equals(code)) {
				Toast.makeText(ViewPagerActivity.this, "城市不存在",
						Toast.LENGTH_SHORT).show();
				return;
			} else {
				try {
					JSONObject jn = new JSONObject();
					jn.put("name", c);
					jn.put("code", code);
					ljo.add(jn);
					savedate();
					View v = LayoutInflater.from(ViewPagerActivity.this)
							.inflate(R.layout.weather, null);
					lv.add(LayoutInflater.from(ViewPagerActivity.this).inflate(
							R.layout.weather, null));
					Toast.makeText(ViewPagerActivity.this, "添加成功",
							Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public void savedate() {
		JSONArray jy = new JSONArray();
		for (int i = 0; i < ljo.size(); i++) {
			jy.put(ljo.get(i));
		}
		sp = this.getSharedPreferences("city", MODE_PRIVATE);
		Editor e = sp.edit();
		e.putString("name", jy.toString());
		e.commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.viewpager);

		shuaxin = (ImageView) super.findViewById(R.id.shuaxin);
		try {
			sp = this.getSharedPreferences("city", MODE_PRIVATE);
			String name = sp.getString("name", "");
			if (name == null || "".equals(name)) {
				Editor e = sp.edit();
				JSONObject jn = new JSONObject();
				jn.put("name", "北京");
				jn.put("code", "/weather/beijing/");
				jo.put(jn);
				e.putString("name", jo.toString());
				e.commit();

			} else {
				jo = new JSONArray(name);

			}
			for (int i = 0; i < jo.length(); i++) {
				ljo.add(jo.getJSONObject(i));
			}
			for (int i = 0; i < jo.length(); i++) {
				View v = LayoutInflater.from(this).inflate(R.layout.weather,
						null);
				lv.add(LayoutInflater.from(this)
						.inflate(R.layout.weather, null));
			}
			vp = (ViewPager) super.findViewById(R.id.mypager);
			adapter = new myadapter();
			vp.setAdapter(adapter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vp.setCurrentItem(0);
		vp.setOnPageChangeListener(new OnPageChangeListenerImpl());
	}

	class OnPageChangeListenerImpl implements OnPageChangeListener {
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			View v = lv.get(arg0);

			try {
				JSONObject jb = ljo.get(arg0);
				TextView city = (TextView) v.findViewById(R.id.city);
				city.setText(jb.getString("name"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new updateView(v, arg0).start();

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

	}

	class updateView extends Thread {
		public TextView city, time, Temp, weather, low, high;
		public ImageView tempimg;
		public LinearLayout talbe;
		public ImageView person;
		private View view;
		private int id;

		public updateView(View view, int id) {
			this.view = view;
			city = (TextView) view.findViewById(R.id.city);
			city.setOnLongClickListener(new OnLongClick(id));
			this.id = id;
			time = (TextView) view.findViewById(R.id.time);
			add = (ImageView) view.findViewById(R.id.add);
			add.setOnClickListener(new showcity());
			person = (ImageView) view.findViewById(R.id.imageView2);
			shuaxin = (ImageView) view.findViewById(R.id.shuaxin);
			shuaxin.setOnClickListener(new shuaxin());
			low = (TextView) view.findViewById(R.id.low);
			high = (TextView) view.findViewById(R.id.high);
			Temp = (TextView) view.findViewById(R.id.Temp);
			weather = (TextView) view.findViewById(R.id.weather);
			talbe = (LinearLayout) view.findViewById(R.id.more);
			tempimg = (ImageView) view.findViewById(R.id.tempimg);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				GetCity gc = new GetCity(ViewPagerActivity.this);
				JSONObject jb = ljo.get(id);

				String citycode = jb.getString("code");
				Log.i("code", citycode);
				List<weather> lw = gc.getWeather(citycode);
				Message msg = handler.obtainMessage();
				msg.obj = lw;
				handler.sendMessage(msg);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		class OnLongClick implements OnLongClickListener {

			private int idp;

			public OnLongClick(int idp) {
				this.idp = idp;
			}

			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				Builder builder = new Builder(ViewPagerActivity.this);
				builder.setTitle("确认删除");
				builder.setMessage("删除城市");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								if (lv.size() > 1) {
									System.out.println(idp + "cityid");
									lv.remove(idp);
									ljo.remove(idp);
									savedate();
									vp.removeAllViews();
									adapter = new myadapter();
									vp.setAdapter(adapter);
									vp.setOnPageChangeListener(new OnPageChangeListenerImpl());
								} else {
									Toast.makeText(ViewPagerActivity.this,
											"必须有一个城市", Toast.LENGTH_SHORT)
											.show();
								}

							}
						});
				builder.setNegativeButton("取消", null);
				builder.create().show();
				return false;
			}

		}

		private Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				try {

					talbe.removeAllViews();
					List<weather> lw = (List<weather>) msg.obj;
					weather wea = lw.get(0);
					time.setText(wea.getDay());
					Temp.setText(wea.getHigh());
					low.setText(wea.getLow());
					high.setText(wea.getHigh());
					person.setOnClickListener(new info(wea.getFeng()));
					weather.setText(wea.getTianqi());
					tempimg.setImageBitmap(wea.getImg());

					for (int i = 1; i < lw.size(); i++) {
						wea = lw.get(i);
						View p = LayoutInflater.from(ViewPagerActivity.this)
								.inflate(R.layout.weather_item, null);
						TextView xq1 = (TextView) p.findViewById(R.id.xq1);
						TextView wh1 = (TextView) p.findViewById(R.id.wh1);

						TextView xl1 = (TextView) p.findViewById(R.id.xl1);
						ImageView tv = (ImageView) p.findViewById(R.id.img1);
						xq1.setText(wea.getDay());
						wh1.setText(wea.getHigh());
						xl1.setText(wea.getLow());
						tv.setImageBitmap(wea.getImg());

						p.setLayoutParams(new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.FILL_PARENT,
								LinearLayout.LayoutParams.FILL_PARENT, 1));
						talbe.addView(p, i - 1);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};

	}

	class info implements OnClickListener {

		String info = "";

		public info(String info) {
			this.info = info;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Builder builder = new Builder(ViewPagerActivity.this);
			builder.setTitle("温馨提醒");
			String p[] = {
					"秋季腹泻：婴幼儿易患，以轮状病毒所致最为典型。多发于深秋初冬季节，起病急，病情重。本病为自限性疾病，用抗生素无效，腹泻多在病后4-7天自愈，家长应做好护理工作。",
					"皮肤感染：秋季皮肤易被病源寄生虫和蚊虫叮咬，出现红肿且奇痒，搔抓后可继发细菌感染，出现脓疱疮(疹)等。所以，被蚊虫叮咬之后切不可抓挠，可涂抹风油精、清凉油消肿止痒。 ",
					"气管炎：秋季是慢性气管炎的高发期。因草枯叶落，空气中过敏物较多，容易诱发气管炎。预防应避免与过敏因素接触，改善居室环境，注意空气流通新鲜。 ",
					"心脑血管疾病：秋天是心脑血管疾病的多发季节，因此，心血管病人要坚持服药，坚持进行力所能及的体育锻炼，积极防治感冒等，以避免诱发加重心血管疾病。 "};
			builder.setMessage(p[new Random().nextInt(p.length)]);
			builder.setPositiveButton("关闭", null);
			builder.create().show();
		}

	}

	class myadapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(lv.get(position));
			try {
				JSONObject jb = ljo.get(position);
				TextView city = (TextView) lv.get(position).findViewById(
						R.id.city);
				city.setText(jb.getString("name"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateView up = new updateView(lv.get(position), position);
			up.start();
			return lv.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lv.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

}
