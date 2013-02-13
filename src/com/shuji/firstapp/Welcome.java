package com.shuji.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Welcome extends Activity{
	
	MediaPlayer bgSong;
	
	@Override
	protected void onCreate(Bundle WelcomeScreen) {
		// TODO Auto-generated method stub
		super.onCreate(WelcomeScreen);
		setContentView(R.layout.welcome);
		
		// Configure background music
		bgSong = MediaPlayer.create(Welcome.this,R.raw.welcome_bgsong);
		bgSong.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(1000);
				} catch (InterruptedException e){
					e.printStackTrace();
				} finally{
					Intent openMainActivity = new Intent("com.shuji.firstapp.MENU");
					startActivity(openMainActivity);
				}
			}
		};
		
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		bgSong.release();
		finish();
	}
	
}
