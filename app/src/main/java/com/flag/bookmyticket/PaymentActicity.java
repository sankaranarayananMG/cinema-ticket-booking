package com.flag.bookmyticket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActicity extends AppCompatActivity {

    TextView movieName,amount;
    EditText cardNumberET;
    Button payment;

    Bundle extras;
    Intent intent;
    Context c;

    String title="",seats="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_acticity);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d32f2f")));
        movieName =(TextView)findViewById(R.id.movieName);
        amount=(TextView)findViewById(R.id.amount);
        payment=(Button)findViewById(R.id.paymentbtn);
        cardNumberET =(EditText)findViewById(R.id.numberET);



        intent = getIntent();
        extras = intent.getExtras();

        title = extras.getString("title");
        seats = extras.getString("seats");

        int ticket_price = Integer.parseInt(seats);
        int amountval = ticket_price*250;

        amount.setText("rupes"+Integer.toString(amountval));
        movieName.setText(title);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActicity.this, BookingsActivity.class);
                Toast.makeText(getApplicationContext(),"Booked Successfully",Toast.LENGTH_LONG).show();
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cardNumberET.addTextChangedListener(new TextWatcher() {
            private static final char space = ' ';

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Remove spacing char
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    final char c = s.charAt(s.length() - 1);
                    if (space == c) {
                        s.delete(s.length() - 1, s.length());
                    }
                }
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    char c = s.charAt(s.length() - 1);
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(space)).length <= 3) {
                        s.insert(s.length() - 1, String.valueOf(space));
                    }
                }
            }
        });


    }


}