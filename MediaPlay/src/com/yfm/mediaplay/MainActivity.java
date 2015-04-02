package com.yfm.mediaplay;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.yfm.utils.Category;
import com.yfm.utils.FileUtils;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Category> lc=FileUtils.GetProfile(this);
        for(int i=0;i<lc.size();i++){
        	Category c=lc.get(i);
        	
        	System.out.println(c.getType()+"--"+c.getNames().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
