package com.example.raneenandnemat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FavoriteDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorite.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "favorite_table";
    private static final String COLUMN_ITEM = "item";

    private static FavoriteDBHelper instance;

    private FavoriteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static FavoriteDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new FavoriteDBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ITEM + " TEXT);";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addFavItem(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM, item);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<String> getFavItems() {
        ArrayList<String> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ITEM }, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                items.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return items;
    }
}

