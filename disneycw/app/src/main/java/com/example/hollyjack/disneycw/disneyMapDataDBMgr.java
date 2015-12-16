package com.example.hollyjack.disneycw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class disneyMapDataDBMgr extends SQLiteOpenHelper {

    private static final int DB_VER = 1;
    private static final String DB_PATH = "/data/data/com.example.hollyjack.disneycw/databases/";
    private static final String DB_NAME = "disneyLandDB.s3db";
    private static final String TBL_DISNEYLANDLOCATIONS = "disneyLandLocations";

    public static final String COL_PARKID = "parkID";
    public static final String COL_PARKNAME = "parkName";
    public static final String COL_PARKADDRESS = "parkAddress";
    public static final String COL_LATITUDE = "latitude";
    public static final String COL_LONGITUDE = "longitude";

    private final Context appContext;

    public disneyMapDataDBMgr(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.appContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DISNEYLANDLOCATIONS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_DISNEYLANDLOCATIONS + "("
                + COL_PARKID + " INTEGER PRIMARY KEY," + COL_PARKNAME
                + " TEXT," + COL_PARKADDRESS + " TEXT," + COL_LATITUDE + " FLOAT,"
                + COL_LONGITUDE + " FLOAT" +")";
        db.execSQL(CREATE_DISNEYLANDLOCATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + TBL_DISNEYLANDLOCATIONS);
            onCreate(db);
        }
    }

    public void dbCreate() throws IOException {
        boolean dbExist = dbCheck();
        if(!dbExist){
            this.getReadableDatabase();
            try {
                copyDBFromAssets();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean dbCheck(){
        SQLiteDatabase db = null;
        try {
            String dbPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);
        } catch(SQLiteException e) {
            Log.e("SQLHelper", "Database not Found!");
        }
        if(db != null){
            db.close();
        }
        return db != null ? true : false;
    }

    private void copyDBFromAssets() throws IOException {
        InputStream dbInput = null;
        OutputStream dbOutput = null;
        String dbFileName = DB_PATH + DB_NAME;
        try {
            dbInput = appContext.getAssets().open(DB_NAME);
            dbOutput = new FileOutputStream(dbFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
            }
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        } catch (IOException e) {
            throw new Error("Problems copying DB!");
        }
    }

    public void addaMapDisneyLandLocationsEntry(disneyMapData aMapDisneyLandLocations) {
        ContentValues values = new ContentValues();
        values.put(COL_PARKID,aMapDisneyLandLocations.getParkID());
        values.put(COL_PARKNAME, aMapDisneyLandLocations.getParkName());
        values.put(COL_PARKADDRESS, aMapDisneyLandLocations.getParkAddress());
        values.put(COL_LATITUDE, aMapDisneyLandLocations.getLatitude());
        values.put(COL_LONGITUDE, aMapDisneyLandLocations.getLongitude());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TBL_DISNEYLANDLOCATIONS, null, values);
        db.close();
    }

    public disneyMapData getMapDisneyLandLocationsEntry(String aMapDisneyLandLocationsEntry) {
        String query = "Select * FROM " + TBL_DISNEYLANDLOCATIONS + " WHERE " + COL_PARKID + " =  \"" + aMapDisneyLandLocationsEntry + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        disneyMapData MapDataEntry = new disneyMapData();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            MapDataEntry.setParkID(Integer.parseInt(cursor.getString(0)));
            MapDataEntry.setParkName(cursor.getString(1));
            MapDataEntry.setParkAddress(cursor.getString(2));
            MapDataEntry.setLatitude(Float.parseFloat(cursor.getString(3)));
            MapDataEntry.setLongitude(Float.parseFloat(cursor.getString(4)));
            cursor.close();
        } else {
            MapDataEntry = null;
        }
        db.close();
        return MapDataEntry;
    }

    public List<disneyMapData> allMapData()
    {
        String query = "Select * FROM " + TBL_DISNEYLANDLOCATIONS;
        List<disneyMapData> disneyMapDataList = new ArrayList<disneyMapData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast()==false) {
                disneyMapData MapDataEntry = new disneyMapData();
                MapDataEntry.setParkID(Integer.parseInt(cursor.getString(0)));
                MapDataEntry.setParkName(cursor.getString(1));
                MapDataEntry.setParkAddress(cursor.getString(2));
                MapDataEntry.setLatitude(Float.parseFloat(cursor.getString(3)));
                MapDataEntry.setLongitude(Float.parseFloat(cursor.getString(4)));
                disneyMapDataList.add(MapDataEntry);
                cursor.moveToNext();
            }
        } else {
            disneyMapDataList.add(null);
        }
        db.close();
        return disneyMapDataList;
    }
}