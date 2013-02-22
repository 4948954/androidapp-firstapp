package com.shuji.firstapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	TestSurface ourSurfaceView;
	float touch_x, touch_y, sX, sY, fX, fY;

	// mybitmap is for showing the user's touch
	// previouslocation to show where the user's touch was pressed
	// releaselocation to show where the user's touch was up

	Bitmap mybitmap, pressdownlocation, releaselocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// initalize ourSurfaceView, and check the touches on the surfaceview
		ourSurfaceView = new TestSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		touch_x = 0;
		touch_y = 0;
		sX = 0;
		sY = 0;
		fX = 0;
		fY = 0;
		mybitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.button2);
		pressdownlocation = BitmapFactory.decodeResource(getResources(),
				R.drawable.touchhome);
		releaselocation = BitmapFactory.decodeResource(getResources(),
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
		touch_x = event.getX();
		touch_y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			// initialize the fX and fY, so that every press down will clean the
			// last "releaselocation" bitmap
			fX = 0;
			fY = 0;
			break;
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
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

				// draw a bitmap according to the user's finger
				if (touch_x != 0 && touch_y != 0) {
					mycanvas.drawBitmap(mybitmap,
							(touch_x - mybitmap.getWidth() / 2),
							(touch_y - mybitmap.getHeight() / 2), null);
				}
				if (sX != 0 && sY != 0) {
					mycanvas.drawBitmap(pressdownlocation,
							(sX - pressdownlocation.getWidth() / 2),
							(sY - pressdownlocation.getHeight() / 2), null);
				}
				if (fX != 0 && fY != 0) {
					mycanvas.drawBitmap(releaselocation,
							(fX - releaselocation.getWidth() / 2),
							(fY - releaselocation.getHeight() / 2), null);
				}

				ourHolder.unlockCanvasAndPost(mycanvas);
			}
		}

	}

}
