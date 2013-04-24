package com.comp1008.serveranalytics.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.comp1008.serveranalytics.R;

/* This activity will be a splash page to show when the
 * app is first opened. It will display for 1.5seconds */

public class SplashActivity extends Activity {
    
    //Create a length of time for splash to show, 1500ms
    private final int SPLASH_LENGHT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_splash);
	
	// Create a handler to delay the opening of MainMenuActivity
	new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Create intent that will link to MainMenuActivity
                Intent mainIntent = new Intent(SplashActivity.this, MainMenuActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_LENGHT);
    }
}
