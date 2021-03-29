package com.hotelmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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

import java.util.HashMap;

public class AdminOrders extends AppCompatActivity {
    // Objects
    ListView listView;
    private DbAdapter mDbHelper;
    Cursor itemsCursor;
    private CustomAdapter7 customAdapter7;
    private final static String TAG = MainActivity.class.getName().toString();
    public boolean flag = true;

    EditText orderNameTxt, orderContactTxt, orderAddressTxt;
    Button ordrCnfrmBtb;
    String ordNum, orderName, orderContact, orderAddress;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManagement session;
    //First method to call when activity runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shows layout/activity_item_detail
        setContentView(R.layout.activity_admin_orders);
        // Session class instance
        session = new SessionManagement(getApplicationContext());

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to AdminPanel.java is he is not
         * logged in
         * */
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManagement.SESSION_NAME);
        //Toast.makeText(getApplicationContext(), "name:"+name, Toast.LENGTH_SHORT).show();
        //display title on toolbar
        setTitle("All Orders");
        //creates database connection
        mDbHelper = new DbAdapter(this);
        mDbHelper = mDbHelper.open();
        //shows back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // assign id to listview
        listView = (ListView)findViewById(R.id.adminOrdList);
//calls fillData() method
        fillData();

    }

    //physical back button method
    @Override
    public void onBackPressed(){
        finish();

        session.logoutUser();
    }

    //back button in toolbar method
    @Override
    public boolean onSupportNavigateUp(){
        finish();

        session.logoutUser();
        return true;
    }

    //method to fetch all values from database and show them in listview
    private void fillData() {

        //gets all orders
        itemsCursor = mDbHelper.fetchAllOrders();

        //checks if no orders exists in database
        if(itemsCursor.getCount() == 0) {

            Toast.makeText(getApplicationContext(), "No Orders", Toast.LENGTH_SHORT).show();
        //display orders in listview if there are orders in database
        } else {

            customAdapter7 = new CustomAdapter7(AdminOrders.this, itemsCursor, 0);
            listView.setAdapter(customAdapter7);

        }

        //method to view order details of specific order by click on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView ordNumTxt = (TextView)view.findViewById(R.id.ordNumTxt);
                TextView shipNameTxt = (TextView)view.findViewById(R.id.shipNameTxt);
                TextView shipContactTxt = (TextView)view.findViewById(R.id.shipContactTxt);
                TextView shipAddressTxt = (TextView)view.findViewById(R.id.shipAddressTxt);

                ordNum = ordNumTxt.getText().toString();
                orderName = shipNameTxt.getText().toString();
                orderContact =  shipContactTxt.getText().toString();
                orderAddress =  shipAddressTxt.getText().toString();

                //opens AdminOrderDetail.java file which shows order details of specific order
                Intent i = new Intent(getApplicationContext(), AdminOrderDetail.class);
                i.putExtra("Order Number", ordNum);
                startActivity(i);

            }
        });

    }

    //toolbar right side icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(itemsCursor.getCount() != 0) {
            getMenuInflater().inflate(R.menu.logout, menu);
            return true;
        } else {

            return false;
        }

        // Inflate the menu; this adds items to the action bar if it is present.
    }

    //Toolbar right side icon action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {

            // Clear the session data
            // This will clear all session data and
            // redirect user to LoginActivity
            session.logoutUser();

        }

        return super.onOptionsItemSelected(item);
    }

}