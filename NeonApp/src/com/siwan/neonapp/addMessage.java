package com.siwan.neonapp;

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
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

public class addMessage extends Activity {
	
	//initialize UI
    TextView display;
        
	//initialize values
	String msg = "";
	int color = 0;
	int size = 0;
	Intent i;
	Context context;
	boolean[] checkbox_result = new boolean[3];
	
	
	//initialize intent
    Intent textAnimation = new Intent("com.siwan.textAnimation");
	
	/****************************************************************
	 * Intent guarantees that bundle will have 3 required attributes.
	 * 
	 * 
	 ****************************************************************
	 */
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_screen);
        
        //Connect UI
        display = (TextView)findViewById(R.id.display);
        
        //context = this.getApplicationContext();
            
        //receive bundle from intent
        Bundle pack = getIntent().getExtras();
        msg = pack.getString("message");
        color = pack.getInt("color");
        size = pack.getInt("size");
        checkbox_result = pack.getBooleanArray("checkbox");
        
        //configure the TextView based on user's input    	
        display.setText(msg);
    	display.setTextColor(color);
    	display.setTextSize(size);
    	
    	setCheckedResults(checkbox_result);
    	
    }
	
	public void setCheckedResults(boolean[] result){
		/* 
		 * 0 -> blinking
		 * 1 -> N/A
		 * 2 -> N/A
		 */
		
		/*
		//need to send it to service for background task
		 * 
		if(result[0]){
			display.setVisibility(View.VISIBLE);
		}
		*/
		
		// works like a charm
		if(result[1]){
			display.setTextAppearance(getApplicationContext(), R.style.Bold);
		}
		
		//move to service (background task)
		if(result[2]){
			
			//getting display info before send off
			//PendingIntent animation = PendingIntent.getBroadcast(addMessage.this, 0, textAnimation, PendingIntent.FLAG_ONE_SHOT);
			//AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			//am.set(AlarmManager.RTC_WAKEUP, 100, animation);
			/*
			try {
				animation.send();
			} catch (CanceledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			Display screenInfo = getWindowManager().getDefaultDisplay();
			//Point size = new Point();
			//screenInfo.getSize(size);
			@SuppressWarnings("deprecation")
			int maxWidth = screenInfo.getWidth();
			@SuppressWarnings("deprecation")
			int maxHeight = screenInfo.getHeight();
			
			TranslateAnimation sliding = new TranslateAnimation(maxWidth, -maxWidth, 0.0f, 0.0f);
			display.setAnimation(sliding);
			sliding.setDuration(7000);
			display.startAnimation(sliding);
		}
		
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			setContentView(R.layout.main_screen);
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
    
	
}
