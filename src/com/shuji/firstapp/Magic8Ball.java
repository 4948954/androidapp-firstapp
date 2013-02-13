package com.shuji.firstapp;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Magic8Ball extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.magic8ball);
		
		// Save answer into an array
		final String answer[]={"As I see it, yes","It is certain","It is decidedly so","Most likely","Outlook good","Signs point to yes","Without a doubt","Yes","Yes - definitely","You may rely on it","Reply hazy, try again","Ask again later","Better not tell you now","Cannot predict now","Concentrate and ask again","Don't count on it","My reply is no","My sources say no","Outlook not so good","Very doubtful"};
		
		Button B_start = (Button)findViewById(R.id.b_magic8ball_start);
		final TextView tv_result = (TextView)findViewById(R.id.tv_Result);
		final Random randomizor = new Random();
		
		B_start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int random_num = randomizor.nextInt(20);
				tv_result.setText(answer[random_num].toString());
				
			}
		});
		
	}
	
}
