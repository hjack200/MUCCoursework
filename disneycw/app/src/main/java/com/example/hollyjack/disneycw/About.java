package com.example.hollyjack.disneycw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class About extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

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
}
