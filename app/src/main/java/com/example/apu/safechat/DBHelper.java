package com.example.apu.safechat;

/**
 * Created by apuchakroborti on 11/5/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    //public static final String CONTACTS_TABLE_NAME = "contacts";
    public static  final String CONTACTS_TABLE_NAME="allchat";
    public static final String CONTACTS_COLUMN_ID = "id";
    //public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String ALL_CHAT = "chatMessages";
    public static  String CONTACTS_DIRECTORY_NAME;
    //public static final String CONTACTS_COLUMN_EMAIL = "email";
    //public static final String CONTACTS_COLUMN_STREET = "street";
    //public static final String CONTACTS_COLUMN_CITY = "place";
    //public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table allchat " +
                        "(id integer primary key, chatMessages text,directoryName text,senderName text)"
        );
        /*
        db.execSQL(
                "create table allchat " +
                        "(id integer primary key, name text,phone text,email text, street text,place text)"
        );
         */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        //db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS allchat");
        onCreate(db);
    }

    //public boolean insertContact  (String name, String phone, String email, String street,String place)
    public boolean insertContact  (String directoyName, String messages,String senderName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("directoyName",directoyName);
        contentValues.put("chatMessages",messages);
        contentValues.put("senderName",senderName);
        //contentValues.put("phone", phone);
        //contentValues.put("email", email);
        //contentValues.put("street", street);
        //contentValues.put("place", place);
        db.insert("allchat", null, contentValues);
        return true;
    }


    public Cursor getData(String directoryName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from allchat where directoyName="+directoryName+"", null );
        return res;
    }

    /*public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }*/

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    /*public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }*/

    /*
    public Integer deleteContact (Text directory)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("allchat",
                "direc = ? ",
                new String[] { Integer.toString(id) });
    }*/

    /*
    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

     */

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from allchat", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(ALL_CHAT)));
            res.moveToNext();
        }
        return array_list;
    }
    /*
    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
     */
}