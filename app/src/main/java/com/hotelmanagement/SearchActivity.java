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
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {
    Button searchBtn, testBtn;
    AutoCompleteTextView searchField;
    String searchStr;
    String[] items =  {"Pickle Pizza", "Chi Tikka", "Chi Fajita", "Mutton Tikka", "Nawain Pizza", "Beef Tikka",
            "Vegetable Pizza", "Rahoo Fish", "Fried Fish", "Chicken Biryani", "Chicken Pulao", "Mutton Biryani",
            "Chicken Fried Rice", "Chicken Masala Rice", "Karahi", "Handi", "Shawarma", "Burger"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchField = (AutoCompleteTextView )findViewById(R.id.searchField);
        searchBtn = (Button)findViewById(R.id.searchBtn);
        //testBtn = (Button)findViewById(R.id.testBtn);

        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, items);

        //sets items in textfield
        searchField.setAdapter(adapter);

        searchStr = searchField.getText().toString();

        //method when search button clicked
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String srx = searchField.getText().toString();
                boolean arraycontains = false;

                //checks if items from array found or not
                for (int y = 0; y < items.length; y++){
                    if (items[y].toString().equals(srx) ) {
                        arraycontains = true;
                        break;
                    }
                    else {
                        arraycontains = false;
                        continue;
                    }
                }

                //if search field is empty
                if(TextUtils.isEmpty(searchField.getText())) {

                    Toast.makeText(getApplicationContext(), "Please enter Item", Toast.LENGTH_SHORT).show();
                //if item entered found in array defined on top
                } else {

                    if (arraycontains) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("item_name", srx);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    //back button method
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}
