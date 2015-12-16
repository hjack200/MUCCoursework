package com.example.hollyjack.disneycw;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashScreen extends Activity {
//sets splash screen duration to 4 seconds
    public static int timer = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
//begins running the main activity after the 4 seconds has passed
            @Override
            public void run() {
                Intent i = new Intent(splashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, timer);
    }
}