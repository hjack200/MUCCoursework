package com.example.hollyjack.disneycw;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class feedResults extends ActionBarActivity {

    public static asyncParser parseAsync;

    public TextView firstTitle, firstLink, firstPubDate;
    public TextView secondTitle, secondLink, secondPubDate;
    public TextView thirdTitle, thirdLink, thirdPubDate;
    public TextView fourthTitle, fourthLink, fourthPubDate;
    public TextView fifthTitle, fifthLink, fifthPubDate;
    public TextView sixthTitle, sixthLink, sixthPubDate;
    public TextView seventhTitle, seventhLink, seventhPubDate;
    public TextView eigththTitle, eigththLink, eigththPubDate;
    public TextView ninthTitle, ninthLink, ninthPubDate;
    public TextView tenthTitle, tenthLink, tenthPubDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_content);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        parseAsync = new asyncParser();
        parseAsync.execute();
    }
//sets text views to show the data that has been parsed
    public void viewContent(View view) {
        firstTitle = (TextView) findViewById(R.id.Title);
        firstTitle.setText(parseAsync.GCData.get(0).FirstString);
        firstLink = (TextView) findViewById(R.id.Date);
        firstLink.setText(parseAsync.GCData.get(0).SecondString);
        firstPubDate = (TextView) findViewById(R.id.Link);
        firstPubDate.setText(parseAsync.GCData.get(0).ThirdString);

        secondTitle = (TextView) findViewById(R.id.Title2);
        secondTitle.setText(parseAsync.GCData.get(1).FirstString);
        secondPubDate = (TextView) findViewById(R.id.Date2);
        secondPubDate.setText(parseAsync.GCData.get(1).SecondString);
        secondLink = (TextView) findViewById(R.id.Link2);
        secondLink.setText(parseAsync.GCData.get(1).ThirdString);

        thirdTitle = (TextView) findViewById(R.id.Title3);
        thirdTitle.setText(parseAsync.GCData.get(2).FirstString);
        thirdPubDate = (TextView) findViewById(R.id.Date3);
        thirdPubDate.setText(parseAsync.GCData.get(2).SecondString);
        thirdLink = (TextView) findViewById(R.id.Link3);
        thirdLink.setText(parseAsync.GCData.get(2).ThirdString);

        fourthTitle = (TextView) findViewById(R.id.Title4);
        fourthTitle.setText(parseAsync.GCData.get(3).FirstString);
        fourthPubDate = (TextView) findViewById(R.id.Date4);
        fourthPubDate.setText(parseAsync.GCData.get(3).SecondString);
        fourthLink = (TextView) findViewById(R.id.Link4);
        fourthLink.setText(parseAsync.GCData.get(3).ThirdString);

        fifthTitle = (TextView) findViewById(R.id.Title5);
        fifthTitle.setText(parseAsync.GCData.get(4).FirstString);
        fifthPubDate = (TextView) findViewById(R.id.Date5);
        fifthPubDate.setText(parseAsync.GCData.get(4).SecondString);
        fifthLink = (TextView) findViewById(R.id.Link5);
        fifthLink.setText(parseAsync.GCData.get(4).ThirdString);

        sixthTitle = (TextView) findViewById(R.id.Title6);
        sixthTitle.setText(parseAsync.GCData.get(5).FirstString);
        sixthPubDate = (TextView) findViewById(R.id.Date6);
        sixthPubDate.setText(parseAsync.GCData.get(5).SecondString);
        sixthLink = (TextView) findViewById(R.id.Link6);
        sixthLink.setText(parseAsync.GCData.get(5).ThirdString);

        seventhTitle = (TextView) findViewById(R.id.Title7);
        seventhTitle.setText(parseAsync.GCData.get(6).FirstString);
        seventhPubDate = (TextView) findViewById(R.id.Date7);
        seventhPubDate.setText(parseAsync.GCData.get(6).SecondString);
        seventhLink = (TextView) findViewById(R.id.Link7);
        seventhLink.setText(parseAsync.GCData.get(6).ThirdString);

        eigththTitle = (TextView) findViewById(R.id.Title8);
        eigththTitle.setText(parseAsync.GCData.get(7).FirstString);
        eigththPubDate = (TextView) findViewById(R.id.Date8);
        eigththPubDate.setText(parseAsync.GCData.get(7).SecondString);
        eigththLink = (TextView) findViewById(R.id.Link8);
        eigththLink.setText(parseAsync.GCData.get(7).ThirdString);

        ninthTitle = (TextView) findViewById(R.id.Title9);
        ninthTitle.setText(parseAsync.GCData.get(8).FirstString);
        ninthPubDate = (TextView) findViewById(R.id.Date9);
        ninthPubDate.setText(parseAsync.GCData.get(8).SecondString);
        ninthLink = (TextView) findViewById(R.id.Link9);
        ninthLink.setText(parseAsync.GCData.get(8).ThirdString);

        tenthTitle = (TextView) findViewById(R.id.Title10);
        tenthTitle.setText(parseAsync.GCData.get(9).FirstString);
        tenthPubDate = (TextView) findViewById(R.id.Date10);
        tenthPubDate.setText(parseAsync.GCData.get(9).SecondString);
        tenthLink = (TextView) findViewById(R.id.Link10);
        tenthLink.setText(parseAsync.GCData.get(9).ThirdString);
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