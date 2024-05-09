package com.flag.bookmyticket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeatSelection extends AppCompatActivity {

    Button bookseats;
    public static  String title,screenName,showName,showDate,seatsList,seatsIndex;

    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String s="";
    public static List<String> bookedSeats = new ArrayList<>();

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Email = "emailKey";

    String currentUser="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d32f2f")));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new NumberedAdapter(54));

        bookseats =(Button)findViewById(R.id.bookseats);
        sqLiteHelper = new SQLiteHelper(this);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        currentUser = sharedpreferences.getString(Email, "");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        title = extras.getString("title");
        screenName = extras.getString("screenName");
        showName = extras.getString("showTime");
        showDate = extras.getString("date");


        selectData();
        bookseats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();

                seatsList = NumberedAdapter.seats.toString();
                seatsIndex = NumberedAdapter.seatindex.toString();
                seatsList = seatsList.substring(1, seatsList.length() - 1);
                seatsIndex = seatsIndex.substring(1, seatsIndex.length() - 1);

                String[] seatsNames=seatsList.split(",");
                String[] seatsNumbers=seatsIndex.split(",");


                if(NumberedAdapter.seats.size()!=0) {
                    for (int i = 0; i < seatsNames.length; i++) {
                        seatsList = seatsNames[i];
                        seatsIndex = seatsNumbers[i];
                        InsertDataIntoSQLiteDatabase(title, screenName, showName, showDate, seatsIndex, seatsList,currentUser);
                    }

                    NumberedAdapter.seats.clear();
                    Intent intent = new Intent(SeatSelection.this, PaymentActicity.class);
                    Bundle extras = new Bundle();
                    extras.putString("title",title);
                    extras.putString("seats", String.valueOf(seatsNames.length));
                    intent.putExtras(extras);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please choose seats",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }
    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.SEATS_TABLE_NAME + "(" + SQLiteHelper.Seats_Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Seats_Table_Title_Name + " VARCHAR, " + SQLiteHelper.Seats_Table_Screen_Name + " VARCHAR, " + SQLiteHelper.Seats_Table_Show_Name + " VARCHAR," + SQLiteHelper.Seats_Table_Show_Date + " VARCHAR, " + SQLiteHelper.Seats_Table_Seat_Index + " VARCHAR, " + SQLiteHelper.Seats_Table_Seat_Name + " VARCHAR);");
    }

    public void InsertDataIntoSQLiteDatabase(String title,String screeNames,String showNames, String showDates, String seatIndices, String seatNames, String currentUser){
            SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelper.SEATS_TABLE_NAME + " (title_name,screen_name,show_name,show_date,seat_index,seat_name,seats_booked_user) VALUES('" + title + "','" + screeNames + "', '" + showNames + "','" + showDates + "', '" + seatIndices + "','" + seatNames + "', '" + currentUser + "');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            //sqLiteDatabaseObj.close();
    }

    private void selectData(){
        bookedSeats.clear();
        sqLiteHelper = new SQLiteHelper(this);
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.SEATS_TABLE_NAME, null, " " + SQLiteHelper.Seats_Table_Screen_Name + "=? AND " + SQLiteHelper.Seats_Table_Title_Name + " = ? AND " + SQLiteHelper.Seats_Table_Show_Name + " = ? AND " + SQLiteHelper.Seats_Table_Show_Date + " = ? ", new String[]{SeatSelection.screenName,SeatSelection.title,SeatSelection.showName,SeatSelection.showDate}, null, null, null);
        while (cursor.moveToNext()) {
            bookedSeats.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Seats_Table_Seat_Index)).trim());
        }
        //Toast.makeText(getApplicationContext(),bookedSeats.toString(),Toast.LENGTH_LONG).show();
        cursor.close();

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}