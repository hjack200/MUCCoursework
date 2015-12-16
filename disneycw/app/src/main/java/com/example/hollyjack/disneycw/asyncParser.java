package com.example.hollyjack.disneycw;


import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class asyncParser extends AsyncTask<Void, Void, Void> {

    public ArrayList<data> GCData;
    public parsedData GCParser;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        GCData = new ArrayList<data>();
        GCParser = new parsedData();
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (true) {
            try {
                GCData = GCParser.retrieveData();
            } catch (Exception e) {
            }
            break;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}