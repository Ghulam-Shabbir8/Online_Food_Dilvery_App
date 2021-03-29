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

public class PHDetail extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phdetail);
        setTitle("Pizza Hut Menu");
        listView = (ListView)findViewById(R.id.PHUserList);
        avgRatingBar = (RatingBar)findViewById(R.id.avgPHRatingBar);

        avgRatingBar.setVisibility(View.INVISIBLE);

        objDb = new DbAdapter(getApplicationContext());
        objDb = objDb.open();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hotelNo = objDb.fetchRatingNo("7");
        ratingNo = objDb.fetchHotelNo("7");


        if(hotelNo == 0 && ratingNo == 0) {
            hotelNo = 0;
            ratingNo = 1;

        }

        actualRating = (int)hotelNo/ratingNo;

        actualRatingFloat = (float)actualRating;

        if(hotelNo != 0 && ratingNo != 0) {

            avgRatingBar.setVisibility(View.VISIBLE);


            avgRatingBar.setRating(actualRatingFloat);

            fillData();

        } else {

            Toast.makeText(getApplicationContext(), "No User rating" , Toast.LENGTH_SHORT).show();


        }

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void fillData() {
        cursor = objDb.fetchComments("7");
        customAdapter3 = new CustomAdapter3(PHDetail.this, cursor, 0);
        listView.setAdapter(customAdapter3);
    }
}
