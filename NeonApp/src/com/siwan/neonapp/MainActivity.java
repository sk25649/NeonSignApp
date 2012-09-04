package com.siwan.neonapp;

import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//make UI global
	//Spinner spinner;
	EditText neon_msg;
	EditText neon_size;
	Button btn;
	Button btn2;
	String selectedColor;
	AmbilWarnaDialog dialog;
	Object dialog_needed;
	
	//predefined values
    int initialColor = 0xFFFFFFFF; //white
    int finalColor = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        
      	//connect UI with coding
    	//spinner = (Spinner) findViewById(R.id.user_color);
        neon_msg = (EditText) findViewById(R.id.user_message);
        neon_size = (EditText) findViewById(R.id.user_size);
        btn = (Button)findViewById(R.id.user_save);
        btn2 = (Button)findViewById(R.id.user_picked); 
        
        //initialize color for view
        btn2.setBackgroundColor(initialColor);
        
        //Need to save user's input
        btn.setOnClickListener(new btnOnClickListener());
        btn2.setOnClickListener(new onClickListener(this));
        
    }
    
    public class onClickListener implements OnClickListener {
    	public onClickListener(Object object) {
    		dialog_needed = object;
    	}

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
   
    
    //Upon clicking NeonIT! button...
    public class btnOnClickListener implements OnClickListener{

		public void onClick(View arg0) {
			
			String dummy = neon_size.getText().toString();
			int size = Integer.parseInt(dummy);
			Toast.makeText(getBaseContext(), dummy, Toast.LENGTH_SHORT).show();
			
			//create custom bundle
			Bundle bundle = new Bundle();
			bundle.putString("message", neon_msg.getText().toString()); 
			bundle.putInt("size", size);
			bundle.putInt("color", finalColor);
			
			//create a new intent
			Intent i = new Intent("com.siwan.message");
			
			//put bundle into intent
			i.putExtras(bundle);
			
			//fire up new activity
			startActivity(i);
		}

		
		
    }

    
    @Override     
    public boolean onCreateOptionsMenu(Menu menu){
    	new MenuInflater(this).inflate(R.menu.activity_main, menu);
    	return(super.onCreateOptionsMenu(menu));
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// do nothing for now...
		
	}
    
}
