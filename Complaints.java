package com.ullas.firstphase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.Serializable;

class Complaints extends SQLiteOpenHelper implements Serializable
{
    public static final String DATABASE_NAME = "project";
    public static final String TABLE_NAME = "complaintsproject";
    public static final String COL_0 = "compid";
    public static final String COL_1 = "idc";
    public static final String COL_2 = "complaint";

    public static long comID;


    public Complaints(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME +" (compid integer primary key autoincrement ,idc varchar(10) ,complaint varchar(300))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id,String complaints)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,complaints);
        long result = db.insert(TABLE_NAME,null,contentValues);
        //System.out.println(COL_0);
        comID=result;
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        db.update(TABLE_NAME, contentValues, "idc= ?",new String[] { id });
        return true;
    }

    public Integer deleteData (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_0+"="+id, null);

        //return db.delete(TABLE_NAME, "idc=?",new String[] {id});
    }
}


