package com.hotelmanagement;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ADDetail extends AppCompatActivity {

    // Objects
    ImageView nameImg, cmntsImg;
    TextView nameTxt, cmntsTxt;
    RatingBar avgRatingBar;
    DbAdapter objDb;
    Cursor cursor;
    int hotelNo =0, ratingNo =0, actualRating = 0;
    float actualRatingFloat;
    ListView listView;
    private CustomAdapter3 customAdapter3;
    String item_name;

    //First method to call when activity runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shows layout/activity_item_detail
        setContentView(R.layout.activity_addetail);
        //display title on toolbar
        setTitle("Apna Dera Menu");
        // assign id to listview
        listView = (ListView)findViewById(R.id.ADUserList);
        //assign id to rating bar
        avgRatingBar = (RatingBar)findViewById(R.id.avgADRatingBar);

        //set visible to hidden on activity start
        avgRatingBar.setVisibility(View.INVISIBLE);

        //creates database connection
        objDb = new DbAdapter(getApplicationContext());
        objDb = objDb.open();

        //shows back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //calls database ratings and hotels by this activity special id
        hotelNo = objDb.fetchRatingNo("10");
        ratingNo = objDb.fetchHotelNo("10");

        //initializes variable
        if(hotelNo == 0 && ratingNo == 0) {
            hotelNo = 0;
            ratingNo = 1;

        }

        //condition to prevent error divisible zero
        actualRating = (int)hotelNo/ratingNo;
        actualRatingFloat = (float)actualRating;

        //set rating to ratingbar and shows it in activity
        if(hotelNo != 0 && ratingNo != 0) {

            avgRatingBar.setVisibility(View.VISIBLE);
            avgRatingBar.setRating(actualRatingFloat);

            //calls fillData() method
            fillData();

          //display message if no user rating exists
        } else {

            Toast.makeText(getApplicationContext(), "No User rating" , Toast.LENGTH_SHORT).show();

        }

    }

    //method for back button closes current activity and display previous activity
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //method to fetch all values from database and show them in listview
    private void fillData() {
        cursor = objDb.fetchComments("10");
        customAdapter3 = new CustomAdapter3(ADDetail.this, cursor, 0);
        listView.setAdapter(customAdapter3);
    }
}