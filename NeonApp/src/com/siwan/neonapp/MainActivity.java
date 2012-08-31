package com.siwan.neonapp;

import android.os.Bundle;
import android.app.Activity;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//make UI global
	Spinner spinner;
	EditText neon_msg;
	EditText neon_size;
	Button btn;
	String selectedColor;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        
      	//connect UI with coding
    	spinner = (Spinner) findViewById(R.id.user_color);
        neon_msg = (EditText) findViewById(R.id.user_message);
        neon_size = (EditText) findViewById(R.id.user_size);
        btn = (Button)findViewById(R.id.user_save);
        
        
        //Initialize Spinner for colors
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_color, android.R.layout.simple_spinner_dropdown_item);
        //Toast.makeText(getBaseContext(), adapter.getItem(0), Toast.LENGTH_SHORT).show();
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        
        //upon user selection of a color
        spinner.setOnItemSelectedListener((OnItemSelectedListener) new userOnItemSelectedListener());
        
        
        //Need to save user's input
        btn.setOnClickListener(new btnOnClickListern());
    }
    
    // I might even need this... will find out later
    public class userOnItemSelectedListener  implements OnItemSelectedListener{
    	
    	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
    		selectedColor = parent.getSelectedItem().toString();
    		
    		//let's try this
    		//Intent colorPicker = new Intent("com.siwan.colorpick");
    		//startActivity(colorPicker);
    		
    	}

		public void onNothingSelected(AdapterView<?> arg0) {
			//do nothing
		}
    	
    }
    
    //Upon clicking NeonIT! button...
    public class btnOnClickListern implements OnClickListener{

		public void onClick(View arg0) {
			
			//String dummy = neon_size.getText().toString();
			//int size = Integer.parseInt(dummy);
			int size = 1;
			
			//create custom bundle
			Bundle bundle = new Bundle();
			bundle.putString("message", neon_msg.getText().toString()); 
			bundle.putInt("size", size);
			bundle.putString("color", selectedColor);
			
			//create a new intent
			Intent i = new Intent("com.siwan.message");
			
			//put bundle into intent
			i.putExtra("bundle", bundle);
			
			//fire up new activity
			startActivity(i);
		}
		
    }
    
    @Override     
    public boolean onCreateOptionsMenu(Menu menu){
    	new MenuInflater(this).inflate(R.menu.activity_main, menu);
    	return(super.onCreateOptionsMenu(menu));
    }
    
}
