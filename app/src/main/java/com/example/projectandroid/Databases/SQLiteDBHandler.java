package com.example.projectandroid.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectandroid.Classes.Items;

import java.util.LinkedList;
import java.util.List;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "itemsDB";
    private static final String TABLE_NAME = "Items";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER= "number";
    private static final String KEY_IMG = "image";
    private static final String[] COLUMNS = { KEY_NAME, KEY_NUMBER, KEY_IMG };

    public SQLiteDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATION_TABLE = "CREATE TABLE Items ( " + "name TEXT, " + "number TEXT, " + "image TEXT )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    //Delete item in DB with name
    public void deleteOne(Items item) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name = ?",
                new String[] { String.valueOf(item.getNameItem()) });
        db.close();
    }

    //Get item in DB with name
    public Items getItem(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " name = ?", // c. selections
                new String[] { String.valueOf(name) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Items item = new Items();
        item.setNameItem(cursor.getString(0));
        item.setNumberTV(cursor.getString(1));
        item.setUrlIMG(cursor.getString(2));

        return item;
    }

    //List of every items
    public List<Items> allItems() {

        List<Items> items = new LinkedList<Items>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Items item = null;

        if (cursor.moveToFirst()) {
            do {
                item = new Items();
                item.setNameItem(cursor.getString(0));
                item.setNumberTV(cursor.getString(1));
                item.setUrlIMG(cursor.getString(2));
                items.add(item);
            } while (cursor.moveToNext());
        }
        return items;
    }

    //add item in Db with name, number and image
    public void addItem(Items item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getNameItem());
        values.put(KEY_NUMBER, item.getNumberTV());
        values.put(KEY_IMG, item.getUrlIMG());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }
}
