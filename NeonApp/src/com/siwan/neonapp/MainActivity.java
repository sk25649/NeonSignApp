package com.siwan.neonapp;

import java.util.HashMap;

import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//make UI global
	//Spinner spinner;
	EditText neon_msg, neon_size;
	Button btn, btn2;
	String selectedColor;
	String default_msg = "Si Wan Kim rocks :)";
	AmbilWarnaDialog dialog;
	Object dialog_needed;
	CheckBox blink, move, bold;
	boolean checked;
	boolean[] checkbox_result = new boolean[3];
	
	//predefined values
    int initialColor = 0xFFFFFFFF; //white
    int finalColor = 0;
    
    // Initialize UIs
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
       
        
      	//connect UI with coding
    	//spinner = (Spinner) findViewById(R.id.user_color);   possibly for font size later...
        neon_msg = (EditText) findViewById(R.id.user_message);
        neon_size = (EditText) findViewById(R.id.user_size);
        btn = (Button)findViewById(R.id.user_save);
        btn2 = (Button)findViewById(R.id.user_picked);
        blink = (CheckBox)findViewById(R.id.blinking);
        move = (CheckBox)findViewById(R.id.move);
        bold = (CheckBox)findViewById(R.id.bold);
        
        //initialize color for view
        btn2.setBackgroundColor(initialColor);
        
        //Need to save user's input
        btn.setOnClickListener(new btnOnClickListener());
        btn2.setOnClickListener(new onClickListener(this));
        
    }
    
    // onClickListener for color picker dialog
    public class onClickListener implements OnClickListener {
    	public onClickListener(Object object) {
    		dialog_needed = object;
    	}
    	// update the user picked color
		public void onClick(View v) {
			dialog = new AmbilWarnaDialog((Context) dialog_needed, initialColor, new OnAmbilWarnaListener(){
	            public void onOk(AmbilWarnaDialog dialog, int color) {
	                    // color is the color selected by the user.
	            		finalColor = color;
	            		initialColor = color;
	            		//update the color accordingly
	                    btn2.setBackgroundColor(color);
	            }
	                    
	            public void onCancel(AmbilWarnaDialog dialog) {
	                    // cancel was selected by the user
	            }
	        });
	        dialog.show();
		}	
    }
    
    // updating check_result array upon press of checkboxes
    public void onCheckboxClicked(View view){
    	
    	checked = ((CheckBox)view).isChecked();
    	switch(view.getId()){
	    	case R.id.blinking:
	    		checkbox_result[0] = checked;
	    		break;
	    	
	    	case R.id.bold:
	    		checkbox_result[1] = checked;
	    		break;
	    		
	    	case R.id.move:
	    		checkbox_result[2] = checked;
	    		break;
    	}
    }
   
    //Upon clicking NeonIT! button...
    public class btnOnClickListener implements OnClickListener{

		public void onClick(View arg0) {
			int size = 1;
			//String checker = "";
			String dummy = neon_size.getText().toString();
			if(containsDigit(dummy)){
				size = Integer.parseInt(dummy);
			}
			
			//create custom bundle
			
			/**********************************************************************************************
			 * This makes sure that information is filled before starting addMessage activity
			 * 
			 * 
			 **********************************************************************************************
			 */
			
			Bundle bundle = new Bundle();
			
			//put results into bundle
			bundle.putBooleanArray("checkbox", checkbox_result);
			
			if(!(neon_msg.getText().toString().matches(""))){
				bundle.putString("message", neon_msg.getText().toString());
				int msg_length = neon_msg.getMeasuredWidth();	//actual msg length
				bundle.putInt("mWidth", msg_length);
			}else{
				bundle.putString("message", default_msg);
				bundle.putInt("mWidth", 800);
			}
			
			if(size != 1){
				bundle.putInt("size", size);
			}else{
				bundle.putInt("size", 50);
			}
			
			if(finalColor != 0){
				bundle.putInt("color", finalColor);
			}else{
				bundle.putInt("color", initialColor);
			}
			
			
			//create a new intent
			Intent i = new Intent("com.siwan.message");
			
			//put bundle into intent
			i.putExtras(bundle);
			
			//fire up new activity
			startActivity(i);
		}
	
    }
    
    /*
     * Functionality: testing if size is empty or number or not
     * 
     * 
     */
    public final boolean containsDigit(final String s){
    	for(char c: s.toCharArray()){
    		if(Character.isDigit(c)){
    			return true;
    		}
    	}
    	
    	return false;
    }

    @Override     
    public boolean onCreateOptionsMenu(Menu menu){
    	new MenuInflater(this).inflate(R.menu.activity_main, menu);
    	return(super.onCreateOptionsMenu(menu));
    }
    
	public void onClick(View v) {
		// do nothing for now...
		
	}
    
}
