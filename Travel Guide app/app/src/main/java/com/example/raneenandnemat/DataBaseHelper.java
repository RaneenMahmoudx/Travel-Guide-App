package com.example.raneenandnemat;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper {


    public static final String TABLE_NAME = "USER";
    private static final String DB_NAME = "RaneenAndNemat";



    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int
            version) {
        super(context, name, null, version);

    }

    public DataBaseHelper(Context context) {
        super(context,DB_NAME ,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USER(EMAIL TEXT PRIMARY KEY  NOT NULL,FIRST_NAME TEXT,LAST_NAME TEXT ,PASSWORD TEXT,CONTINENT TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }


    public void UpdateUser(String Email,String FirstName, String LastName, String
            Password,String Continent){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME" ,FirstName);
        contentValues.put("LAST_NAME", LastName);
        contentValues.put("PASSWORD", Password);
        contentValues.put("CONTINENT", Continent);
        db.update("USER", contentValues, "EMAIL" + " = ?", new String[] {String.valueOf(Email)});


    }
    public void AddUsers(String email, String FirstName, String LastName, String
            Password, String Continent){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("FIRST_NAME", FirstName);
        contentValues.put("LAST_NAME", LastName);
        contentValues.put("PASSWORD", Password);
        contentValues.put("CONTINENT", Continent);


        db.insert("USER", null, contentValues);
    }

    public String getCONTINENT(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CONTINENT FROM USER WHERE EMAIL = ?", new String[] {email});
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndexOrThrow("CONTINENT"));
        }
        cursor.close();
        return null;
    }

    public String getEmail(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER" , null);
        cursor.moveToLast();
        String email = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL"));
        cursor.close();
        db.close();
        return email;
    }




    public void deleteUser (String email){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("USER", "EMAIL = ?", new String[]{email});

    }

    public boolean LogIn(String email,String password){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT EMAIL, PASSWORD FROM USER WHERE EMAIL = ?",new String[]{email});
        if(cursor.getCount()>0) {
            if (cursor.moveToFirst ()) {
                String data = cursor.getString(cursor.getColumnIndexOrThrow("PASSWORD"));
                if (password.equals (data))
                    return true;
            }
            return false;
        }
        else
            return  false;
    }





    public Cursor GetFirstAndLastName(String a, String b){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM USER where EMAIL = '"+a+"' and PASSWORD = '"+b+"'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor getProfileDate(){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT EMAIL FROM User  ";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public Cursor getProfileDate1(){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT FIRST_NAME,LAST_NAME FROM User  ";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public Cursor getProfilePass(){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT PASSWORD FROM User ";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }



}


