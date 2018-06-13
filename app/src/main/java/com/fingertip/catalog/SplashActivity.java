package com.fingertip.catalog;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by lenevo on 26-12-2017.
 */

public class SplashActivity extends Activity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    SharedPreferences pref;
    Boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);




        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                // close this activity
                pref = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
                isFirstTime = pref.getBoolean("isFirstTime", true);
//                    SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
//                    regId = pref.getString("regId", null);

                //  Log.d("Firebase reg id: ",ttt);
                if (isFirstTime) {

                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                } else {

                    SharedPreferences pref1 = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
                    String aa = pref1.getString("id", null);
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    SplashActivity.this.overridePendingTransition(R.anim.righttoleft,
                            R.anim.lefttoright);

                }

                finish();

            }
        }, SPLASH_TIME_OUT);







    }
}

