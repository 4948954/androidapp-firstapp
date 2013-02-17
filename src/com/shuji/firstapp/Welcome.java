package com.shuji.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Welcome extends Activity{
	
	MediaPlayer bgSong;
	
	@Override
	protected void onCreate(Bundle WelcomeScreen) {
		// TODO Auto-generated method stub
		super.onCreate(WelcomeScreen);
		setContentView(R.layout.welcome);
		
		// Configure background music
		bgSong = MediaPlayer.create(Welcome.this,R.raw.welcome_bgsong);
		
		//access the preferences to turn on/off the welcome music
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music_check = getPrefs.getBoolean("checkbox", true);
		if(music_check == true){
			bgSong.start();
		}
		
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(3000);
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
