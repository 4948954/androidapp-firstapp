package com.shuji.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements View.OnClickListener,
		OnCheckedChangeListener {

	TextView question, test;
	Button returnData;
	RadioGroup selectionList;
	String gotMessage, setData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);

		initializeVariables();

		// set the click actions on button and radiogroup
		returnData.setOnClickListener(this);
		selectionList.setOnCheckedChangeListener(this);
		
		//get the bundle passed by Data.java intent.
		Bundle gotEnvelope = getIntent().getExtras();
		gotMessage = gotEnvelope.getString("stamp");
		//display gotMessage on TextView question
		question.setText(gotMessage + " is");
		
	}

	private void initializeVariables() {
		// TODO Auto-generated method stub
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.bReturn);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
	}

	@Override
	public void onClick(View v) {
		// Give the selection back to Data.java
		Intent person = new Intent();
		Bundle backmessage = new Bundle();
		backmessage.putString("answer",setData);
		person.putExtras(backmessage);
		
		setResult(RESULT_OK, person);
		
		//close this activity
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case R.id.rCrazy:
			setData = "Probably right!";
			break;
		case R.id.rHandsome:
			setData = "Correct!";
			break;
		case R.id.rBoth:
			setData = "Wrong";
			break;
		}
		test.setText(setData);
	}

}
