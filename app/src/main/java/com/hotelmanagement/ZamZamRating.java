package com.hotelmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class ZamZamRating extends AppCompatActivity {

    Button zamZamGivRat;
    AutoCompleteTextView zamZamRatItm;
    EditText  zamZamRatTxt, zamZamRatCmnts;
    RatingBar zamZamRatBar;
    String activityName, activityId;
    String searchStr;

    String[] items;

    DbAdapter objDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zam_zam_rating);

        //gets hotel name and id
        activityName = getIntent().getExtras().getString("activityName");
        activityId = getIntent().getExtras().getString("activityId");


        //checks for related hotels set title to toolbar
        if(activityName.equals("Zam Zam")) {
            setTitle("Zam Zam Rating");

        } else if(activityName.equals("Mehman Saraey")) {
            setTitle("Mehman Saraey Rating");

        }  else if(activityName.equals("Fri Chicks")) {
            setTitle("Fri Chicks Rating");

        }  else if(activityName.equals("Charsi")) {
            setTitle("Charsi Rating");

        }  else if(activityName.equals("Char Minar")) {
            setTitle("Char Minar Rating");

        }  else if(activityName.equals("Fork n Knife")) {
            setTitle("Fork n Knife Rating");

        }  else if(activityName.equals("Pizza Hut")) {
            setTitle("Pizza Hut Rating");

        }  else if(activityName.equals("Khan Baba")) {
            setTitle("Khan Baba Rating");

        }  else if(activityName.equals("Chilli Chatni")) {
            setTitle("Chilli Chatni Rating");

        }  else if(activityName.equals("Apna Dera")) {
            setTitle("Apna Dera Rating");

        }

        //create database connection
        objDb = new DbAdapter(getApplicationContext());
        objDb = objDb.open();

        //shows back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        zamZamGivRat = (Button)findViewById(R.id.zamZamGivRat);
        zamZamRatTxt = (EditText)findViewById(R.id.zamZamRatTxt);
        zamZamRatCmnts = (EditText)findViewById(R.id.zamZamRatCmnts);
        zamZamRatBar = (RatingBar)findViewById(R.id.zamZamRatBar);

        //rating button click method
        zamZamGivRat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if(TextUtils.isEmpty(zamZamRatTxt.getText())) {

                    Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();

                } else if(TextUtils.isEmpty(zamZamRatCmnts.getText())) {

                    Toast.makeText(getApplicationContext(), "Please enter Comments", Toast.LENGTH_SHORT).show();

                } else if(zamZamRatBar.getRating() == 0) {

                    Toast.makeText(getApplicationContext(), "Please select Rating", Toast.LENGTH_SHORT).show();

                } else {

                        objDb.insertRating(zamZamRatTxt.getText().toString(), zamZamRatCmnts.getText().toString(), (int)zamZamRatBar.getRating(), activityId, activityName);

                        Toast.makeText(getApplicationContext(), "Rating Submitted!", Toast.LENGTH_SHORT).show();
                        finish();

                }

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
