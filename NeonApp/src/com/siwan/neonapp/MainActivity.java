package com.siwan.neonapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView neon_msg = (TextView) findViewById(R.id.display);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public String enterMessage(String s){
    	String final_message = "";
    	
    		
    	return final_message;
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
*/
    @Override     
    public boolean onCreateOptionsMenu(Menu menu){
    	new MenuInflater(this).inflate(R.menu.activity_main, menu);
    	
    	
    	setContentView(R.layout.add_message);
    	
    	
    	return(super.onCreateOptionsMenu(menu));
    }
}
