package com.flag.bookmyticket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenSelection extends AppCompatActivity implements
        View.OnClickListener{

    int image;
    String title,rating,runtime,genre;
    private TextView titleText;
    private TextView ratingText;
    private TextView genreText;
    private ImageView imageText;

    private EditText et_date;
    private Button btn_date;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private int mYear, mMonth, mDay;

    private TextView s1_1,s1_2,s1_3,s1_4,s1_5,s1_6;
    private TextView s2_1,s2_2,s2_3,s2_4,s2_5,s2_6;

    Bundle extras;
    Intent intent;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_selection);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d32f2f")));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


         intent = getIntent();
         extras = intent.getExtras();
         image = extras.getInt("imageIntent");
         title = extras.getString("titleIntent");
         rating = extras.getString("ratingIntent");
         runtime = extras.getString("runtimeIntent");
         genre = extras.getString("genreIntent");

        titleText = (TextView)findViewById(R.id.title);
        ratingText = (TextView)findViewById(R.id.rating);
        genreText = (TextView)findViewById(R.id.genre);
        imageText = (ImageView)findViewById(R.id.image);

        et_date = (EditText)findViewById(R.id.in_date);
        btn_date = (Button) findViewById(R.id.btn_date);

        imageText.setImageResource(image);
        titleText.setText(title);
        ratingText.setText(rating+" | "+runtime);
        genreText.setText(genre);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        et_date.setText(date);

        btn_date.setOnClickListener(this);

        s1_1 =(TextView)findViewById(R.id.s1_1);
        s1_2 =(TextView)findViewById(R.id.s1_2);
        s1_3 =(TextView)findViewById(R.id.s1_3);
        s1_4 =(TextView)findViewById(R.id.s1_4);
        s1_5 =(TextView)findViewById(R.id.s1_5);
        s1_6 =(TextView)findViewById(R.id.s1_6);

        s2_1 =(TextView)findViewById(R.id.s2_1);
        s2_2 =(TextView)findViewById(R.id.s2_2);
        s2_3 =(TextView)findViewById(R.id.s2_3);
        s2_4 =(TextView)findViewById(R.id.s2_4);
        s2_5 =(TextView)findViewById(R.id.s2_5);
        s2_6 =(TextView)findViewById(R.id.s2_6);

        s1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 1","11:30 AM",date);
            }
        });

        s1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 1","01:00 PM",date);
            }
        });

        s1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 1","03:30 PM",date);
            }
        });
        s1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 1","04:10 PM",date);
            }
        });
        s1_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 1","06:30 PM",date);
            }
        });
        s1_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 1","11:10 PM",date);
            }
        });



        s2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 2","11:30 AM",date);
            }
        });

        s2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 2","01:00 PM",date);
            }
        });

        s2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 2","03:30 PM",date);
            }
        });
        s2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 2","04:10 PM",date);
            }
        });
        s2_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 2","06:30 PM",date);
            }
        });
        s2_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = et_date.getText().toString();
                sendData("Screen 2","11:10 PM",date);
            }
        });

    }

    private void sendData(String screenName, String showTime, String date){
        Intent intent = new Intent(ScreenSelection.this, SeatSelection.class);
        Bundle extras = new Bundle();
        extras.putString("title",title);
        extras.putString("screenName",screenName);
        extras.putString("showTime",showTime);
        extras.putString("date",date);
        intent.putExtras(extras);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            et_date.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
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