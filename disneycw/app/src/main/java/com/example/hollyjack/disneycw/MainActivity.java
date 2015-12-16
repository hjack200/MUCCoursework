package com.example.hollyjack.disneycw;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    //Menu that will appear on each page
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    //Watches out for a button click, when button is clicked takes user to the appropraite page.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity:
                Intent dcwMain = new Intent(this, MainActivity.class);
                this.startActivity(dcwMain);
                return true;
            case R.id.disney_news:
                Intent dcwNews = new Intent(this, feedResults.class);
                this.startActivity(dcwNews);
                return true;
            case R.id.meet_char:
                Intent dcwMaps = new Intent(this, maps.class);
                this.startActivity(dcwMaps);
                return true;
            case R.id.about:
                Intent dcwAbout = new Intent(this, About.class);
                this.startActivity(dcwAbout);
                return true;
            case R.id.user_prefs:
                Intent dcwUP = new Intent(this, userPrefs.class);
                this.startActivity(dcwUP);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//Media player code will play the sound clip when each of the 4 buttons are clicked
    // When each button is clicked this code will also take the user to the appropriate page
    public void newsClick(View view) {
        MediaPlayer click = MediaPlayer.create(getApplicationContext(), R.raw.magic);
        click.start();
        Intent news = new Intent(MainActivity.this, feedResults.class);
        startActivity(news);
    }

    public void parkClick(View view) {
        MediaPlayer click = MediaPlayer.create(getApplicationContext(), R.raw.magic);
        click.start();
        Intent park = new Intent(MainActivity.this, maps.class);
        startActivity(park);
    }

    public void abtClick(View view) {
        MediaPlayer click = MediaPlayer.create(getApplicationContext(), R.raw.magic);
        click.start();
        Intent about = new Intent(MainActivity.this, About.class);
        startActivity(about);
    }

    public void prefClick(View view) {
        MediaPlayer click = MediaPlayer.create(getApplicationContext(), R.raw.magic);
        click.start();
        Intent prefs = new Intent(MainActivity.this, userPrefs.class);
        startActivity(prefs);
    }
}