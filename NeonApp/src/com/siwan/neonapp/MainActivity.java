package com.siwan.neonapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//make UI global
	Spinner spinner;
	TextView neon_msg;
	Button btn;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        
      	//connect UI with coding
    	spinner = (Spinner) findViewById(R.id.user_color);
        neon_msg = (TextView) findViewById(R.id.display);
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
    		//for testing purpose, toast selected items
    		//Toast.makeText(parent.getContext(), "Selected " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    		
    	}

		public void onNothingSelected(AdapterView<?> arg0) {
			//do nothing
		}
    	
    }
    
    public class btnOnClickListern implements OnClickListener{

		public void onClick(View arg0) {
			Toast.makeText(getBaseContext(), "Enjoy", Toast.LENGTH_SHORT).show();
			//setContentView(R.layout.message_screen);
			Intent i = new Intent("com.siwan.message");
			startActivity(i);
		}
		
    }
    
    @Override     
    public boolean onCreateOptionsMenu(Menu menu){
    	new MenuInflater(this).inflate(R.menu.activity_main, menu);
    	return(super.onCreateOptionsMenu(menu));
    }
    
}
