package com.example.exdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PhoenixDB.db";
    public static final int DATABASE_VERSION = 1;

    // Table name and there column

    public static final String TABLE_NAME = "UserProfiles";
    public static final String USERID = "_id";
    public static final String USERNAME = "NAME";
    public static final String USERADDRESS = "Address";
    public static final String USERPHONENUM = "PHONENUMBER";
    Context context;

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + TABLE_NAME + "( " + USERID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME +" TEXT, " +
                USERADDRESS +" TEXT, " + USERPHONENUM +" TEXT); ";

        sqLiteDatabase.execSQL(create);
        Log.d("create","TABLE IS CREATED");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addUser(String userName , String address , String phoneNum){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USERNAME,userName);
        cv.put(USERADDRESS,address);
        cv.put(USERPHONENUM,phoneNum);
        Log.d("inserting","val is "+userName+" "+ address );

        long result = sqLiteDatabase.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Added Succesfully",Toast.LENGTH_LONG).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void deleteRow(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME,"_id=?",new String[]{id});

        if(result == -1){
            Toast.makeText(context,"Failed Deletetion",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Sussfully Deletion",Toast.LENGTH_LONG).show();
        }
    }

    void updateData(String id,String name,String address,String phoneNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USERNAME,name);
        cv.put(USERADDRESS,address);
        cv.put(USERPHONENUM,phoneNumber);
        long  result = sqLiteDatabase.update(TABLE_NAME,cv,"_id=?",new String[]{id});
        Log.d("update","update val is "+ id + " "+ name + " "+address + " " + phoneNumber);

        if(result == -1){
            Toast.makeText(context,"Failed Updation",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Sussfully Updation",Toast.LENGTH_LONG).show();
        }
    }
}
