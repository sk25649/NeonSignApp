package com.siwan.neonapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class addMessage extends Activity {
	
	//initialize UI
    TextView display;
    
	//initialize values
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_screen);
        
        //Connect UI
        display = (TextView)findViewById(R.id.display);
        
        //receive bundle from intent
        Bundle pack = getIntent().getExtras();
        String msg = pack.getString("message");
        int color = pack.getInt("color");
        int size = pack.getInt("size");
             
    	display.setText(msg);
    	display.setTextColor(color);
    	display.setTextSize(size);
        
        
       
    }
	
}
