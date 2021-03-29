package com.hotelmanagement;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SearchDetail extends AppCompatActivity {

    String item_name;
    DbAdapter objDb;
    Cursor cursor;
    int menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        objDb = new DbAdapter(getApplicationContext());
        objDb = objDb.open();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //gets item name from search activity
        item_name = getIntent().getExtras().getString("item_name");

        //gets values from database
        menu = objDb.fetchMenu(item_name);
        cursor = objDb.fetchMenuByRating(item_name);


        if(menu == 0) {

            //Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        } else {



            while(cursor.moveToNext()){

                Toast.makeText(getApplicationContext(), "Hotel:"+cursor.getString(2)+"\nRating:"+cursor.getString(4), Toast.LENGTH_SHORT).show();


            }



        }


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
