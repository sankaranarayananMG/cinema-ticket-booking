package com.flag.bookmyticket;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="UserDataBase";

    public static final String TABLE_NAME="UserTable";

    public static final String Table_Column_ID="id";
    public static final String Table_Column_1_Name="name";
    public static final String Table_Column_2_Email="email";
    public static final String Table_Column_3_Mobile="mobile";
    public static final String Table_Column_4_Password="password";

    public static final String SEATS_TABLE_NAME="SeatsTable";

    public static final String Seats_Table_Column_ID="s_id";
    public static final String Seats_Table_Title_Name="title_name";
    public static final String Seats_Table_Screen_Name="screen_name";
    public static final String Seats_Table_Show_Name="show_name";
    public static final String Seats_Table_Show_Date="show_date";
    public static final String Seats_Table_Seat_Index="seat_index";
    public static final String Seats_Table_Seat_Name="seat_name";
    public static final String Seats_Booked_user ="seats_booked_user";


    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID +" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Mobile+" VARCHAR, "+Table_Column_4_Password+" VARCHAR)";
        String CREATE_SEATS_TABLE="CREATE TABLE IF NOT EXISTS "+SEATS_TABLE_NAME+" ("+Seats_Table_Column_ID +" INTEGER PRIMARY KEY, "+Seats_Table_Title_Name+" VARCHAR, "+Seats_Table_Screen_Name+" VARCHAR, "+Seats_Table_Show_Name+" VARCHAR, "+Seats_Table_Show_Date+" VARCHAR, "+Seats_Table_Seat_Index+" VARCHAR, "+Seats_Table_Seat_Name+" VARCHAR, "+Seats_Booked_user+" VARCHAR)";

        database.execSQL(CREATE_TABLE);
        database.execSQL(CREATE_SEATS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+SEATS_TABLE_NAME);
        onCreate(db);

    }

}