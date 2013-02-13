package com.shuji.firstapp;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {

	Button B_chkCmd;
	ToggleButton passTog;
	EditText input;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);

		// set up a method to set variables
		mymethod();

		B_chkCmd.setOnClickListener(this);
		passTog.setOnClickListener(this);
	}

	private void mymethod() {
		B_chkCmd = (Button) findViewById(R.id.bResults);
		passTog = (ToggleButton) findViewById(R.id.tgPassword);
		input = (EditText) findViewById(R.id.et_Commands);
		display = (TextView) findViewById(R.id.tvResults);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bResults:
			// Store the "input" text into a variable
			String check = input.getText().toString();

			if (check.contentEquals("left")) {
				display.setGravity(Gravity.LEFT);
			} else if (check.contentEquals("right")) {
				display.setGravity(Gravity.RIGHT);
			} else if (check.contentEquals("center")) {
				display.setGravity(Gravity.CENTER);
			} else if (check.contentEquals("blue")) {
				display.setTextColor(Color.BLUE);
			} else if (check.contains("ryan")) {
				Random crazy = new Random();
				display.setText("Ryan");
				display.setTextSize(crazy.nextInt(50));
				display.setTextColor(Color.rgb(crazy.nextInt(256),
						crazy.nextInt(256), crazy.nextInt(256)));
				switch (crazy.nextInt(3)) {
				case 0:
					display.setGravity(Gravity.LEFT);
					break;
				case 1:
					display.setGravity(Gravity.RIGHT);
					break;
				case 2:
					display.setGravity(Gravity.CENTER);
					break;
				}
			} else {
				display.setGravity(Gravity.CENTER);
				display.setText("Invaild");
				display.setTextColor(Color.RED);
			}
			break;
		case R.id.tgPassword:
			// Change the "input" between password and plain text by clicking
			// the ToggleButton
			if (passTog.isChecked()) {
				input.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;

		}
	}

}
