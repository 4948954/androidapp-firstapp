package com.shuji.firstapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyAnimation extends Activity implements OnTouchListener {

	TestSurface ourSurfaceView;
	float x, y, sX, sY, fX, fY;
	float startx, starty, finishx, finishy, objectx, objecty;
	float dX, dY, scaleX, scaleY, aniX, aniY;
	int counter;
	boolean dd = false;

	Bitmap mybitmap, startlocation, finishlocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// initalize ourSurfaceView, and check the touches on the surfaceview
		ourSurfaceView = new TestSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x = y = 0;
		sX = 0;
		sY = 0;
		fX = 0;
		fY = 0;
		startx = starty = finishx = finishy = objectx = objecty = 0;
		dX = dY = scaleX = scaleY = aniX = aniY = 0;
		counter = 0;
		mybitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.button2);
		startlocation = BitmapFactory.decodeResource(getResources(),
				R.drawable.touchhome);
		finishlocation = BitmapFactory.decodeResource(getResources(),
				R.drawable.touchstop);
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.surface_pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.surface_resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (counter == 0) {
			x=sX = event.getX();
			y=sY = event.getY();
		}

		if (counter >= 1) {
			fX = event.getX();
			fY = event.getY();
		}

		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			counter++;
			if (counter >= 1) {
				
				dX=fX-sX;
				dY=fY-sY;
			}
			break;
		}

		return true;
	}

	public class TestSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;

		public TestSurface(Context mycontext) {
			super(mycontext);
			ourHolder = getHolder();
		}

		public void surface_pause() {
			isRunning = false;

			// block the thread
			while (true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}

		public void surface_resume() {
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunning) {
				if (!ourHolder.getSurface().isValid())
					continue;

				Canvas mycanvas = ourHolder.lockCanvas();
				mycanvas.drawRGB(120, 255, 255);

				
				if (sX != 0 && sY != 0) {
					mycanvas.drawBitmap(startlocation,
							(sX - startlocation.getWidth() / 2),
							(sY - startlocation.getHeight() / 2), null);
				}
				if (fX != 0 && fY != 0) {
					mycanvas.drawBitmap(finishlocation,
							(fX - finishlocation.getWidth() / 2),
							(fY - finishlocation.getHeight() / 2), null);
				}
				if (x != 0 && y != 0) {
					mycanvas.drawBitmap(mybitmap,
							(x - mybitmap.getWidth() / 2),
							(y - mybitmap.getHeight() / 2), null);
				}
				/*****/

				ourHolder.unlockCanvasAndPost(mycanvas);
			}
		}

	}

}
