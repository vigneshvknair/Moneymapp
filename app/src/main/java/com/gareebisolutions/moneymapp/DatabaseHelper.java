package com.gareebisolutions.moneymapp;

/**
 * Created by Vigneshvk on 3/26/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Money.db";
   // public static final String TABLE_NAME = "student_table";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1="CREATE TABLE IF NOT EXISTS `websiteusers` (\n" +
                " `email` varchar(255) NOT NULL\n" +
                ");";
        String query2 ="CREATE TABLE IF NOT EXISTS `budget` (\n" +
                "  `date` date NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                "  `budget` int(20) NOT NULL,\n" +
                "  `Saving` int(11) DEFAULT NULL,\n" +
                "   `total` int(11) DEFAULT NULL\n" +
                "\n" +
                ");";
        String query3="CREATE TABLE IF NOT EXISTS `expense` (\n" +
                "  `date` date NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                "  `category` int(20) NOT NULL,\n" +
                "  `expense` int(11) DEFAULT NULL\n" +
                ");";
        String query4="CREATE TABLE IF NOT EXISTS `income` (\n" +
                "  `date` date NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                "  `category` int(20) NOT NULL,\n" +
                "  `income` int(11) DEFAULT NULL\n" +
                ");";
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public boolean insertData(String type,String date,Integer category,Integer value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",date);
        contentValues.put("category",category);
        contentValues.put(type,value);
        long result = db.insert(type,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String type,String date1,String date2)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + type+"where date between '"+date1+"' AND '"+date2 +"'", null);
        return res;
    }
}