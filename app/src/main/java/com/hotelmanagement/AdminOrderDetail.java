package com.hotelmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminOrderDetail extends AppCompatActivity {

    // Objects
    ListView listView;
    private DbAdapter mDbHelper;
    Cursor itemsCursor;
    private CustomAdapter8 customAdapter8;

    String orderNumber;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManagement session;


    //First method to call when activity runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shows layout/activity_item_detail
        setContentView(R.layout.activity_admin_order_detail);

        // Session class instance
        session = new SessionManagement(getApplicationContext());
        //method to check if admin is logged in or not
        session.checkLogin();

        //creates database connection
        mDbHelper = new DbAdapter(this);
        mDbHelper = mDbHelper.open();

        //gets order number from AdminOrders.java and stores in orderNumber
        orderNumber = getIntent().getExtras().getString("Order Number");

        //display title on toolbar
        setTitle("Order Detail");
        //shows order number as subtitle in toolbar
        getSupportActionBar().setSubtitle("Order # "+orderNumber);

        //shows back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // assign id to listview
        listView = (ListView)findViewById(R.id.ordDetList);
        //calls fillData() method
        fillData();

    }
    //method for back button closes current activity and display previous activity
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //method to fetch all values from database and show them in listview
    private void fillData() {

        itemsCursor = mDbHelper.fetchAllOrderDetail(orderNumber);


            customAdapter8 = new CustomAdapter8(AdminOrderDetail.this, itemsCursor, 0);
            listView.setAdapter(customAdapter8);

    }

    //shows toolbar right side icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(itemsCursor.getCount() != 0) {
            getMenuInflater().inflate(R.menu.logout, menu);
            return true;
        } else {

            return false;
        }

    }

    //Toolbar right side icon action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            // Clear the session data
            session.logoutUser();
        }

        return super.onOptionsItemSelected(item);
    }
}
