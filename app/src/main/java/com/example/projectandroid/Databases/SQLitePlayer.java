package com.example.projectandroid.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectandroid.Classes.Players;

import java.util.ArrayList;

public class SQLitePlayer extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "playersDB";
    private static final String TABLE_NAME = "Players";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER= "number";
    private static final String[] COLUMNS = { KEY_NAME, KEY_NUMBER};

    public SQLitePlayer(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATION_TABLE = "CREATE TABLE PLayers ( " + "name TEXT, " + "number TEXT)";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    //Delete player in DB with name
    public void deleteOne(Players player) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name = ?",
                new String[] { String.valueOf(player.getMyName()) });
        db.close();
    }

    //Get player in DB with name
    public Players getPlayer(String name) {
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

        Players player = new Players();
        player.setMyName(cursor.getString(0));
        player.setMyScore(cursor.getString(1));

        return player;
    }

    //List of every players
    public ArrayList<Players> allPlayers() {

        ArrayList<Players> players = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ KEY_NUMBER+" DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Players player = null;

        if (cursor.moveToFirst()) {
            do {
                player = new Players();
                player.setMyName(cursor.getString(0));
                player.setMyScore(cursor.getString(1));
                players.add(player);
            } while (cursor.moveToNext());
        }
        return players;
    }

    //Add player in DB with name and score
    public void addPlayer(Players player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.getMyName());
        values.put(KEY_NUMBER, player.getMyScore());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }
}
