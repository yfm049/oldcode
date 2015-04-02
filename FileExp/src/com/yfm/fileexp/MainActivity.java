package com.yfm.fileexp;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.yfm.adapter.FileAdapter;
import com.yfm.adapter.GameAdapter;
import com.yfm.adapter.MnqAdapter;
import com.yfm.adapter.TypeFilter;

public class MainActivity extends Activity {

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (!back()) {
			Builder builder = new Builder(this);
			builder.setTitle("退出程序");
			builder.setMessage("确定要退出程序吗");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							MainActivity.this.finish();
						}
					});
			builder.setNegativeButton("取消", null);
			builder.create().show();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// try {
		// Date d=new Date();
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date date=sdf.parse(time);
		// if(d.getTime()>date.getTime()){
		// Toast.makeText(this, "试用期限已到", Toast.LENGTH_LONG).show();
		// this.finish();
		// }
		//
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		buildDate(currFile);
	}

	private GridView game;
	private ListView filelist;
	private File SdCard = null;
	private File currFile = null;
	private List<File> files = new ArrayList<File>();
	private FileAdapter fileAdapter;
	private GameAdapter gameadapter;
	private String time;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		time = new String(Base64.decode("MjAxMy0wMi0yNSAyMzo1OTo1OQ==",
				Base64.DEFAULT));
		System.out.println(time);
		game = (GridView) super.findViewById(R.id.game);
		Toast.makeText(this, "此版本为试用版期限至2013年02月25日", Toast.LENGTH_LONG).show();
		filelist = (ListView) super.findViewById(R.id.filelist);
		fileAdapter = new FileAdapter(this, files);
		gameadapter = new GameAdapter(this);
		game.setAdapter(gameadapter);
		filelist.setAdapter(fileAdapter);
		filelist.setOnItemClickListener(new FileOnItemClickListener());
		if (Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			SdCard = new File(Environment.getExternalStorageDirectory(), "game");
			if (!SdCard.exists()) {
				SdCard.mkdirs();
			}
			currFile = SdCard;
		} else {
			Toast.makeText(this, "未发现SD卡", Toast.LENGTH_LONG).show();
		}
		game.setOnItemClickListener(new gameOnItemClickListener());
	}

	class allgameOnClickListener implements OnClickListener {

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			buildDate(SdCard);
		}

	}

	class gameOnItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Object type = arg0.getItemAtPosition(arg2);
			if ("ALL".equals(type)) {
				buildDate(SdCard);
			} else {
				search(type.toString().toLowerCase());
			}
		}

	}

	public Boolean back() {
		File file = currFile;
		Log.i("back",
				"返回上一级" + file.getAbsolutePath() + "--"
						+ SdCard.getAbsolutePath());

		if (file.getAbsolutePath().equals(SdCard.getAbsolutePath())) {
			return false;
		} else {
			Log.i("返回", "----" + file.getParentFile().getPath());
			buildDate(file.getParentFile());
			currFile = file.getParentFile();
			return true;
		}
	}

	class FileOnItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int location,
				long arg3) {
			// TODO Auto-generated method stub
			File file = files.get(location);
			if (file.isDirectory()) {
				currFile = file;
				buildDate(file);
			} else if (file.isFile()) {
				Intent localIntent = new Intent();
				if (TypeFilter.isFbaGame(file)) {
					localIntent.setComponent(new ComponentName(
							"com.tiger.game.arcade2",
							"com.tiger.game.arcade2.EmulatorActivity"));
					localIntent.setDataAndType(Uri.fromFile(file),
							"tigeremu/x-arcade-rom");
					localIntent.setAction("android.intent.action.VIEW");
					StartApp(localIntent);
				} else if (TypeFilter.isn64oidGame(file)) {
					localIntent.setComponent(new ComponentName(
							"com.androidemu.n64",
							"com.androidemu.n64.EmulatorActivity"));
					localIntent.setDataAndType(Uri.fromFile(file),
							"application/x-n64-rom");
					localIntent.setAction("android.intent.action.VIEW");
					StartApp(localIntent);
				} else if (TypeFilter.isGbAGame(file)) {
					localIntent.setComponent(new ComponentName(
							"com.androidemu.gba",
							"com.androidemu.gba.EmulatorActivity"));
					localIntent.setDataAndType(Uri.fromFile(file),
							"application/x-gba-rom");
					localIntent.setAction("android.intent.action.VIEW");
					StartApp(localIntent);
				} else if (TypeFilter.isSfcGame(file)) {
					localIntent.setComponent(new ComponentName(
							"com.androidemu.snes",
							"com.androidemu.snes.EmulatorActivity"));
					localIntent.setDataAndType(Uri.fromFile(file),
							"application/x-snes-rom");
					localIntent.setAction("android.intent.action.VIEW");
					StartApp(localIntent);
				} else if (TypeFilter.isSmdGame(file)) {
					localIntent.setComponent(new ComponentName(
							"com.androidemu.gens",
							"com.androidemu.gens.EmulatorActivity"));
					localIntent.setDataAndType(Uri.fromFile(file),
							"application/x-snes-rom");
					localIntent.setAction("android.intent.action.VIEW");
					StartApp(localIntent);
				} else if (TypeFilter.isGbcGame(file)) {
					localIntent.setComponent(new ComponentName(
							"com.androidemu.gbc",
							"com.androidemu.gbc.EmulatorActivity"));
					localIntent.setDataAndType(Uri.fromFile(file),
							"application/x-gbc-rom");
					localIntent.setAction("android.intent.action.VIEW");
					StartApp(localIntent);
				} else if (TypeFilter.isNesGame(file)) {
					localIntent.setComponent(new ComponentName(
							"com.androidemu.nes",
							"com.androidemu.nes.EmulatorActivity"));
					localIntent.setDataAndType(Uri.fromFile(file),
							"application/x-nes-rom");
					localIntent.setAction("android.intent.action.VIEW");
					StartApp(localIntent);
				} else if (TypeFilter.isGeaGame(file)) {
					localIntent.setComponent(new ComponentName(
							"com.androidemu.gg",
							"com.androidemu.gg.EmulatorActivity"));
					localIntent.setDataAndType(Uri.fromFile(file),
							"application/x-gamegear-rom");
					localIntent.setAction("android.intent.action.VIEW");
					StartApp(localIntent);
				}

			}
		}

	}

	private void buildDate(File file) {
		files.clear();
		if (file != null) {
			File[] fs = file.listFiles(new FileFilterImpl("all"));
			if (fs != null) {
				for (File f : fs) {
					files.add(f);
				}
			}

		}
		Collections.sort(files, new Mycomparator());
		fileAdapter.notifyDataSetChanged();
	}

	private void search(String type) {
		if ("emu".equals(type)) {
			ShowMnq();
			return;
		}
		files.clear();
		SearchFile(SdCard, new FileFilterImpl(type));
		Collections.sort(files, new Mycomparator());
		fileAdapter.notifyDataSetChanged();
	}

	private void SearchFile(File file, FileFilter filter) {
		if (file != null) {
			if (file.isDirectory()) {
				File[] fs = file.listFiles(filter);
				if (fs != null) {
					for (File f : fs) {
						if (f.isFile()) {
							files.add(f);
						} else {
							SearchFile(f, filter);
						}
					}
				}
			}
		}

	}

	private Dialog dialog;

	private void ShowMnq() {

		Builder builder = new Builder(this);
		View view = LayoutInflater.from(this).inflate(R.layout.mnqlist, null);
		ListView mnq = (ListView) view.findViewById(R.id.mnq);
		MnqAdapter ma = new MnqAdapter(this);
		mnq.setAdapter(ma);
		mnq.setOnItemClickListener(new mnqOnItemClickListener());
		builder.setView(view);
		dialog = builder.create();
		dialog.show();
	}

	class mnqOnItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> adapter, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			dialog.cancel();
			Object type = adapter.getItemAtPosition(arg2);
			Intent localIntent = new Intent();
			if ("fpse".equals(type)) {
				localIntent.setComponent(new ComponentName("com.emulator.fpse",
						"com.emulator.fpse.Main"));
				localIntent.setAction("android.intent.action.VIEW");
				StartApp(localIntent);
			}
			if ("dos".equals(type)) {
				localIntent.setComponent(new ComponentName(
						"com.fishstix.dosbox",
						"com.fishstix.dosbox.DosBoxLauncher"));
				localIntent.setAction("android.intent.action.VIEW");
				StartApp(localIntent);
			}
			if ("ngp".equals(type)) {
				localIntent.setComponent(new ComponentName(
						"com.explusalpha.NgpEmu", "com.imagine.BaseActivity"));
				localIntent.setAction("android.intent.action.VIEW");
				StartApp(localIntent);
			}
			if ("pce".equals(type)) {
				localIntent.setComponent(new ComponentName("com.PceEmu",
						"com.imagine.BaseActivity"));
				localIntent.setAction("android.intent.action.VIEW");
				StartApp(localIntent);
			}
			if ("afba".equals(type)) {
				localIntent.setComponent(new ComponentName("fr.mydedibox.afba",
						"fr.mydedibox.afba.activity.romListActivity"));
				localIntent.setAction("android.intent.action.VIEW");
				StartApp(localIntent);
			}
			if ("nds4droid".equals(type)) {
				localIntent.setComponent(new ComponentName("com.xiaoji.emu.ds4droid",
						"com.xiaoji.emu.ds4droid.Main"));
				localIntent.setAction("android.intent.action.VIEW");
				StartApp(localIntent);
			}
			if ("psp".equals(type)) {
				localIntent.setComponent(new ComponentName("com.xiaoji.emu.psp",
						"com.xiaoji.emu.psp.Main"));
				localIntent.setAction("android.intent.action.VIEW");
				StartApp(localIntent);
			}

		}

	}

	class FileFilterImpl implements FileFilter {

		private String type;

		public FileFilterImpl(String type) {
			this.type = type;
		}

		public boolean accept(File pathname) {
			// TODO Auto-generated method stub
			Log.i("--", pathname.getName() + "--" + pathname.isDirectory()
					+ "--" + pathname.getName().contains("."));

			if (pathname.isFile()) {
				if ("all".equals(type)) {
					if (TypeFilter.isGameFile(pathname)) {
						return true;
					} else {
						return false;
					}
				} else if ("mame".equals(type)) {
					if (TypeFilter.isFbaGame(pathname)) {
						return true;
					} else {
						return false;
					}
				} else if ("gg".equals(type)) {
					if (TypeFilter.isGeaGame(pathname)) {
						return true;
					} else {
						return false;
					}
				} else if ("n64".equals(type)) {
					if (TypeFilter.isn64oidGame(pathname)) {
						return true;
					} else {
						return false;
					}
				} else if ("gba".equals(type)) {
					if (TypeFilter.isGbAGame(pathname)) {
						return true;
					} else {
						return false;
					}
				} else if ("sfc".equals(type)) {
					if (TypeFilter.isSfcGame(pathname)) {
						return true;
					} else {
						return false;
					}
				} else if ("smd".equals(type)) {
					if (TypeFilter.isSmdGame(pathname)) {
						return true;
					} else {
						return false;
					}
				} else if ("gbc".equals(type)) {
					if (TypeFilter.isGbcGame(pathname)) {
						return true;
					} else {
						return false;
					}
				} else if ("nes".equals(type)) {
					if (TypeFilter.isNesGame(pathname)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}

			} else if (pathname.isDirectory()) {
				if (pathname.getName().contains(".")) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}

		}

	}

	public class Mycomparator implements Comparator {

		public int compare(Object o1, Object o2) {
			File file1 = (File) o1;
			File file2 = (File) o2;
			if (file1.isDirectory() && file2.isFile()) {
				return -1;
			} else if (file2.isDirectory() && file1.isFile()) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	public boolean checkPackage(String packageName) {
		if (packageName == null || "".equals(packageName)) {
			return false;
		} else {
			try {
				getPackageManager().getApplicationInfo(packageName,
						PackageManager.GET_UNINSTALLED_PACKAGES);
				return true;
			} catch (NameNotFoundException e) {
				return false;
			}
		}
	}
	public void StartApp(Intent intent){
		if(checkPackage(intent.getComponent().getPackageName())){
			startActivity(intent);
		}else{
			Toast.makeText(this, "模拟器不存在", Toast.LENGTH_SHORT).show();
		}
	}

}
