package com.siwan.neonapp;

import android.R.interpolator;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

public class addMessage extends Activity {
	
	//initialize UI
    TextView display;
    HorizontalScrollView hs;
        
	//initialize values
	String msg = "";
	int color = 0;
	int size = 0;
	int total_length;
	int x_true;
	Intent i;
	Context context;
	boolean[] checkbox_result = new boolean[2];
	int move_speed;
	
	//initialize intent
    Intent textAnimation = new Intent("com.siwan.textAnimation");
	
	/********************************************************************
	 * Intent guarantees that bundle will have 3 required attributes.
	 * 
	 * 
	 ********************************************************************
	 */
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.message_screen);
        
        
        //Connect UI
        hs = (HorizontalScrollView)findViewById(R.id.horizontal);
        display = (TextView)findViewById(R.id.display);
        display.setTextAppearance(getApplicationContext(), R.style.Bold);
            
        //receive bundle from intent
        Bundle pack = getIntent().getExtras();
        msg = pack.getString("message");
        msg = "    " + msg;
        color = pack.getInt("color");
        size = pack.getInt("size");
        checkbox_result = pack.getBooleanArray("checkbox");
        x_true = pack.getInt("mWidth");
        move_speed = pack.getInt("move_speed");
        move_speed = 1000 * move_speed;
        
        //configure the TextView based on user's input
        display.setText(msg);
    	display.setTextColor(color);
    	display.setTextSize(size);

    }
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 * Functionality: Upon press back button ( <- ), it returns to main_screen.xml layout 
	 * 
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			setContentView(R.layout.main_screen);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		//update the message accordingly
    	setCheckedResults(checkbox_result);
	}
		public void setCheckedResults(boolean[] result){
			
			/* 
			 * 0 -> blinking
			 * 1 -> Move
			 */
			if(result[0] && result[1]){
				new BlinkAndMove().execute(display,null,null);
			}else{
				if(result[1]){
					new Sliding().execute(display,null,null);
				}else {
					if(result[0]){
						blink();
						display.setSingleLine(false);
					}
				}
			}
		}
		class BlinkAndMove extends AsyncTask<TextView, Integer, String>{

			@Override
			protected String doInBackground(TextView... params) {
				//initialize animation set
				AnimationSet as = new AnimationSet(true);
			
				//first animation
				Animation anim = new AlphaAnimation(0.0f, 1.0f);
				anim.setDuration(100); //You can manage the time of the blink with this parameter
				anim.setStartOffset(25);
				anim.setRepeatMode(Animation.REVERSE);
				anim.setRepeatCount(Animation.INFINITE);
				
				//second animation
				Display screenInfo = getWindowManager().getDefaultDisplay();
				@SuppressWarnings("deprecation")
				int maxWidth = screenInfo.getWidth();
				
				@SuppressWarnings("deprecation")
				int maxHeight = screenInfo.getHeight();
				int endPoint = maxWidth + display.getMeasuredWidth();
				
				TranslateAnimation sliding = new TranslateAnimation(maxWidth, -(endPoint), 0.0f, 0.0f);
				sliding.setDuration(move_speed);
				/*
				 * higher the number --> slower animation
				 * fast: 2000
				 * normal: 6000
				 * slow: 10000
				 */
				sliding.setRepeatMode(Animation.RESTART);
				sliding.setRepeatCount(Animation.INFINITE);
							
				//add animations
				as.addAnimation(anim);
				as.addAnimation(sliding);
				as.setInterpolator(getApplicationContext(), interpolator.linear);
				
				//start animation set			
				display.startAnimation(as);
				return null;	
			}
		}
	
		public void blink(){

			Animation anim = new AlphaAnimation(0.0f, 1.0f);
			anim.setDuration(100); //You can manage the time of the blink with this parameter
			anim.setStartOffset(25);
			anim.setRepeatMode(Animation.REVERSE);
			anim.setRepeatCount(Animation.INFINITE);
			display.startAnimation(anim);

		}
		
		class Sliding extends AsyncTask<TextView, Integer, String>{

			@Override
			protected String doInBackground(TextView... params) {
				Display screenInfo = getWindowManager().getDefaultDisplay();
				@SuppressWarnings("deprecation")
				int maxWidth = screenInfo.getWidth();
				int endPoint = maxWidth + display.getMeasuredWidth();
				TranslateAnimation sliding = new TranslateAnimation(maxWidth, -(endPoint), 0.0f, 0.0f);
				display.setAnimation(sliding);
				sliding.setDuration(move_speed);
				sliding.setRepeatMode(Animation.RESTART);
				sliding.setRepeatCount(Animation.INFINITE);
				display.startAnimation(sliding);
				return null;
			}
			
		}

		
	@Override
	protected void onStop() {
		super.onStop();
		for(int i = 0; i <2; i++){
			checkbox_result[i] = false;
		}
	
	}

}