package com.shuji.firstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	int counter;
	Button B_add,B_sub;
	TextView tv_display;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

}
