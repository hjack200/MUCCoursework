package com.example.hollyjack.disneycw;


import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class parsedData {

    public String RSSFeedURL = "http://allears.net/feed.xml";

    private String result;
    private ArrayList<data> feedData;

    public parsedData() {
        feedData = new ArrayList<data>();
    }

    public ArrayList<data> retrieveData() {
        try {
            result = RSSFeedString(RSSFeedURL);
            ParseData();
            return feedData;
        } catch (Exception ae) {
            return feedData;
        }
    }

    private static String RSSFeedString(String urlString)throws IOException {
        String result = "";
        InputStream anInStream = null;
        int response = -1;
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        // Checks connection to the stream
        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            // Opens connection
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            // Checks that the connection is okay
            if (response == HttpURLConnection.HTTP_OK) {
                Log.i("XML", "Connection Established");
                // if connection is ok it opens the stream
                anInStream = httpConn.getInputStream();
                InputStreamReader in= new InputStreamReader(anInStream);
                BufferedReader bin= new BufferedReader(in);

                // Reads the data from XML stream
                bin.readLine(); // Throws away the header
                String line = new String();
                while (( (line = bin.readLine())) != null) {
                    result = result + "\n" + line;
                }
            }
        } catch (Exception ex){
            throw new IOException("Error connecting");
        }

        // Returns the result as a string value
        return result;
    }

    private void ParseData() throws IOException {
        try {
            String title = "", pubDate = "", link = "";
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(result));
            int eventType = xpp.getEventType();
            Boolean hasCaughtItem = false;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        Log.i("XML", "found item");
                        hasCaughtItem = true;
                    } else {
                        if (xpp.getName().equalsIgnoreCase("title")) {
                            title = xpp.nextText();
                            Log.i("XML", "Found the title: " + title);
                        } else {
                            if (xpp.getName().equalsIgnoreCase("pubDate")) {
                                String[] temp = xpp.nextText().split(" ");
                                pubDate = temp[0] + " " + temp[1] + " " + temp[2] + " "  + temp[3] + ", "  + temp[4];
                                Log.i("XML", "Found the pubDate as: " + pubDate);
                            } else {
                                if (xpp.getName().equalsIgnoreCase("link")) {
                                    link = xpp.nextText();
                                    Log.i("XML", "Found the link as: " + link);
                                }
                            }
                        }
                    }
                }
                if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        feedData.add(new data(title, pubDate, link));
                        hasCaughtItem = false;
                    }
                }
                eventType = xpp.next();
            }
        }
        catch (Exception ae)
        {
            throw new IOException("Error parsing");
        }
    }
}