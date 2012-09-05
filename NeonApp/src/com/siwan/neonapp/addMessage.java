package com.siwan.neonapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;

public class addMessage extends Activity {
	
	//initialize UI
    TextView display;
        
	//initialize values
	String msg = "";
	int color = 0;
	int size = 0;
	boolean[] checkbox_result = new boolean[3]; 
	
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
		
		//for simple testing of sliding animation -> will move to service (background task) 
		if(result[2]){
			TranslateAnimation sliding = new TranslateAnimation(1000.0f, -1000.0f, 0.0f, 0.0f);
			display.setAnimation(sliding);
			sliding.setDuration(7000);
			display.startAnimation(sliding);
		}
		
	}
    
	
}
