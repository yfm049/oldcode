package com.yfm.autoanswer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NewCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent it=new Intent(context, AutoAnswerService.class);
		Log.i("onReceive", this.getResultData());
		if("6565".equals(this.getResultData())){
			this.setResultData("");
			abortBroadcast();
			this.clearAbortBroadcast();
			it.putExtra("msg", true);
		}
		context.startService(it);
		
	}

}
