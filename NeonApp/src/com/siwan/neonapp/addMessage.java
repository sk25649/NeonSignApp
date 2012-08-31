package com.siwan.neonapp;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class addMessage extends Activity {
	
	//initialize UI
	TextView display;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_screen);
        
        //Connect UI
        display = (TextView)findViewById(R.id.display);
        
        //receive bundle from intent
        Bundle pack = getIntent().getExtras();
        String msg = pack.getString("message");
        String color = pack.getString("color");
        int size = pack.getInt("size");
        
        display.setText(msg);
        
        //display.setTextColor(R.color.)
        
    }
	/*
	 *  this function determines if the color is available in the color library... which it should be... spinner offers a limited selection
	 * 
	 */
	public void setMessageColor(String x){
		
		
	}
	
}
