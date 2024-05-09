package com.flag.bookmyticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BookingsActivity extends AppCompatActivity {

    ArrayList<Bookings> arrayList;
    RecyclerView recyclerView;
    SQLiteHelper database_helper;
    SQLiteDatabase sqLiteDatabaseObj;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Email = "emailKey";

    String currentUser="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d32f2f")));


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        database_helper = new SQLiteHelper(this);
        displayBookings();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(BookingsActivity.this,HomeActivity.class);
                        startActivity(a);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.navigation_dashboard:
                        break;
                    case R.id.navigation_notifications:
                        Intent b = new Intent(BookingsActivity.this,ProfileActivity.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                }
                return false;
            }
        });
    }

    public void displayBookings() {
        arrayList = new ArrayList<>(getBookings());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        BookingsAdapter adapter = new BookingsAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<Bookings> getBookings() {
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        currentUser = sharedpreferences.getString(Email, "");
        String TABLE_NAME = "SeatsTable";
        ArrayList<Bookings> arrayList = new ArrayList<>();

        // select all query
        String group_by_clause="GROUP by  title_name,screen_name,show_name,show_date,seats_booked_user";
        String select_query= "SELECT title_name,screen_name,show_name,show_date,seats_booked_user,group_concat(seat_name) FROM  SeatsTable where seats_booked_user = '" + "" +currentUser+ "" + "' "+group_by_clause;

        sqLiteDatabaseObj = database_helper.getWritableDatabase();
        Cursor cursor = sqLiteDatabaseObj.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bookings bookings = new Bookings();
                bookings.setTitle(cursor.getString(0));
                bookings.setScreen(cursor.getString(1));
                bookings.setShow(cursor.getString(2));
                bookings.setDate(cursor.getString(3));
                bookings.setSeats(cursor.getString(5));
                arrayList.add(bookings);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Quit Application")
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}