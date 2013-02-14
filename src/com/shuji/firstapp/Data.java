package com.shuji.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener {

	Button start, startFor;
	EditText sendET;
	TextView gotAnswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initializeVariables();

		// set the click actions on buttons
		start.setOnClickListener(this);
		startFor.setOnClickListener(this);

	}

	private void initializeVariables() {
		// TODO Auto-generated method stub
		start = (Button) findViewById(R.id.bSA);
		startFor = (Button) findViewById(R.id.bSAFR);
		sendET = (EditText) findViewById(R.id.etSend);
		gotAnswer = (TextView) findViewById(R.id.tvGot);

	}

	@Override
	public void onClick(View v) {		
		
		String message = sendET.getText().toString();
		Bundle Envelope = new Bundle();
		Envelope.putString("stamp", message);		
		Intent a = new Intent(Data.this, OpenedClass.class);
		a.putExtras(Envelope);
		
		switch (v.getId()) {
		case R.id.bSA:			
			startActivity(a);
			break;
		case R.id.bSAFR:			
			startActivityForResult(a, 0); //0 is the requestCode
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle backmessage = data.getExtras();
			String s = backmessage.getString("answer");
			gotAnswer.setText(s);
		}
	}

}
