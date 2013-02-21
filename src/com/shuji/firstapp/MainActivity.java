package com.shuji.firstapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	int counter;
	Button B_add,B_sub;
	TextView tv_display;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//fullscreen the activity
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
		setContentView(R.layout.activity_main);
		
		counter=0;
		B_sub = (Button)findViewById(R.id.bSub);
		B_add = (Button)findViewById(R.id.bAdd);
		tv_display = (TextView)findViewById(R.id.tvDisplay);
				
		B_add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter ++;
				tv_display.setText("Your total is "+ counter);
				
			}
		});
		B_sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter --;
				tv_display.setText("Your total is "+ counter);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cool_menu, menu);
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	
}
