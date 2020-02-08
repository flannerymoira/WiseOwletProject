package com.example.parkinGenie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "parkinGenie.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
 

    //insert into database
    public boolean insert(String firstName, String lastName, String email, String password, String telephone, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("firstName",firstName);
        contentValues.put("secondName", lastName);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("telephone",telephone);
        contentValues.put("role", role);
        long ins = db.insert("user", null, contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }

    //check if email is correct
    public Boolean checkEmail(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select email from user where email=?", new String[]{email});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    //check if Password is correct
    public Boolean checkPassword(String Email, String password) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select email, password from user where email=? and password=?", new String[]{Email, password});
        if (cursor.getCount()> 0)
            return true;
        else
            return false;
    }


    public boolean insertBooking(String Email, String carReg, String dateOfBooking){
        Integer carParkId = MainActivity.cpId;

        Integer UserId;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor UserCursor = db.rawQuery("Select userId from user where email=?", new String[]{Email});

        if (UserCursor.moveToFirst()) {
            UserId  = UserCursor.getInt(0);
            contentValues.put("userId",UserId);
            contentValues.put("carParkId",carParkId);
            contentValues.put("carReg",carReg);
            contentValues.put("dateOfBooking",dateOfBooking);

            long result = db.insert("booking", "userId, " +
                    "carParkId, carReg, dateOfBooking", contentValues);

            UserCursor.close();

            if(result==-1)
                 return false;
            else
                return true;
           }
        else
            {UserCursor.close();
                return false;}
    }

    public boolean insertCarPark(String name, String website, String address,
                              String phone, String gps, Integer tot_spaces, Integer free_spaces,
                              String height_restrictions, String payment_methods){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // when owner logs in will use their userId and have option only visisble for owners.
        contentValues.put("userId",1);
        contentValues.put("name",name);
        contentValues.put("website",website);
        contentValues.put("address",address);
        contentValues.put("phone",phone);
        contentValues.put("gps",gps);
        contentValues.put("tot_spaces",tot_spaces);
        contentValues.put("free_spaces",free_spaces);
        contentValues.put("height_restrictions",height_restrictions);
        contentValues.put("payment_methods",payment_methods);

        long result = db.insert("carPark",null,contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
}
