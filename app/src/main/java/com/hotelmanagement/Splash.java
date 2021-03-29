package com.hotelmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.List;

public class Splash extends AppCompatActivity {
    /** Duration of wait **/
    //private ProgressBar splashBar;
    private BootstrapProgressBar splashBar;
    private final int SPLASH_DISPLAY_LENGTH = 2500;
    /** Called when the activity is first created. */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();  //bootstrap progressbar

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);


        splashBar = (BootstrapProgressBar)findViewById(R.id.splashBar);

        //timer for progress bar
        CountDownTimer countDownTimer =
                new CountDownTimer(10000, 100) {
                    public void onTick(long millisUntilFinished) {
                        splashBar.setProgress(Math.abs((int) millisUntilFinished / 100 - 100));

                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(getApplicationContext(), ListActivity.class));
                    }
                };

        //method to start count down time
        countDownTimer.start();


    }

}
