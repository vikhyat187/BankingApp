package com.sleepingpandaaa.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME ="user_table";
    private String TABLE_NAME1="transfers_table";

    public DatabaseHelper(@Nullable Context context){super(context,"User.db",null,2);}


    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+TABLE_NAME+ "(PHONENUMBER INTEGER PRIMARY KEY,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table "+TABLE_NAME1+ "(TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values (9030303030,'Vikhyat',9500,'vikhyat@gmaul.com','XXXXXXXXXXXXXX1233','ABICKC093434')" );
        db.execSQL("insert into user_table values (9034597455,'Akshat',3455,'akshat@gmaul.com','XXXXXXXXXXXXXX2333','ABIC232XADX34')" );
        db.execSQL("insert into user_table values (9030345894,'Santosh',9800,'santosh@gmaul.com','XXXXXXXXXXXXXX3453','ABICKC093434')" );
        db.execSQL("insert into user_table values (3489348993,'Devita',8000,'devita@gmaul.com','XXXXXXXXXXXXXX1253','ABICKC093434')" );
        db.execSQL("insert into user_table values (3535356335,'Sanjay',15000,'sanjay@gmaul.com','XXXXXXXXXXXXXX1533','ABICKC093434')" );
        db.execSQL("insert into user_table values (9030303343,'Anuj',49999,'anuj@gmaul.com','XXXXXXXXXXXXXX1563','ABICKC093434')" );
        db.execSQL("insert into user_table values (9030333939,'Krishna',1001,'krishna@gmaul.com','XXXXXXXXXXXXXX1743','ABICKC093434')" );
        db.execSQL("insert into user_table values (9030343333,'Vinay',10000,'vinay@gmaul.com','XXXXXXXXXXXXXX1833','ABICKC093434')" );
        db.execSQL("insert into user_table values (9030334340,'Vikranth',20000,'vikranth@gmaul.com','XXXXXXXXXXXXXX1003','ABICKC093434')" );
        db.execSQL("insert into user_table values (9030303111,'Ashutosh',29490,'ashutosh@gmaul.com','XXXXXXXXXXXXXX1893','ABICKC093434')" );
        db.execSQL("insert into user_table values (9030303322,'Vicky',29300,'vicky@gmaul.com','XXXXXXXXXXXXXX1404','ABICKC093434')" );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion ,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from user_table ",null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber="+phonenumber,null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from user_table except select * from user_table where phonenumber="+phonenumber,null);
        return cursor;
    }
    public void updateAmount(String phonenumber,String Amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance =" +Amount +"where phonenumber ="+phonenumber);
    }
    public Cursor readtransferdata(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from transfers_table",null);
        return cursor;
    }
    public boolean insertTransferData(String date,String from_name,String to_name,String amount,String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues =new ContentValues();
        contentValues.put("DATE",date);
        contentValues.put("FROMNAME",from_name);
        contentValues.put("TONAME",to_name);
        contentValues.put("AMOUNT",amount);
        contentValues.put("STATUS",status);
        Long result =db.insert(TABLE_NAME1,null,contentValues);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
    }
}
