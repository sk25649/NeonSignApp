package com.siwan.neonapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
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
        checkbox_result = pack.getBooleanArray("check_box");
        
        //configure the TextView based on user's input    	
        display.setText(msg);
    	display.setTextColor(color);
    	display.setTextSize(size);
    	
    	
    	
    }
	
	public void setCheckedResults(Boolean[] result){
		/* 
		 * 0 -> blinking
		 * 1 -> N/A
		 * 2 -> N/A
		 */
		
		/*
		//need to send it to service for background task
		if(result[0]){
			display.setVisibility(View.VISIBLE);
		}
		*/
	}
    
	
}
