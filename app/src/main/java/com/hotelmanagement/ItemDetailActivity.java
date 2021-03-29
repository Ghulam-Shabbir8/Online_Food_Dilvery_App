package com.hotelmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorTreeAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetailActivity extends AppCompatActivity {

    // Objects
    ListView listView;
    private DbAdapter mDbHelper;
    Cursor itemsCursor;
    private CustomAdapter5 customAdapter5;
    String itemName, itemPrice, itemQuantity, hotelName;
    private static final int EDIT_MENU = Menu.FIRST;


    //First method to call when activity runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shows layout/activity_item_detail
        setContentView(R.layout.activity_item_detail);

        //shows back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //gets itemName from ItemsActivity.class
        itemName = getIntent().getExtras().getString("itemName");
        //display itemName on toolbar
        setTitle(itemName);

        //creates database connection
        mDbHelper = new DbAdapter(this);
        mDbHelper = mDbHelper.open();

        // assign id to listview
        listView = (ListView)findViewById(R.id.item_detail_list);
        //call fillData() method
        fillData();
    }

    //display toolbar top right icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(itemsCursor.getCount() != 0) {
            getMenuInflater().inflate(R.menu.cart, menu);
            return true;
        } else {

            return false;
        }

    }

    //toolbar top right side icon action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cartdel) {

            startActivity(new Intent(getApplicationContext(), CartActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    //method for adding item into cart using add button in list
    public void myClickHandler(View v) {
        RelativeLayout vwParentRow = (RelativeLayout) v.getParent();

        TextView hotelNameTxt = (TextView)vwParentRow.getChildAt(0);
        TextView priceTxt = (TextView)vwParentRow.getChildAt(6);
        TextView quantityTxt = (TextView)vwParentRow.getChildAt(3);
        Button btnChild = (Button)vwParentRow.getChildAt(5);

        itemPrice = priceTxt.getText().toString();
        hotelName = hotelNameTxt.getText().toString();
        itemQuantity =  quantityTxt.getText().toString();

        vwParentRow.refreshDrawableState();

        //call database method to check the already item exist in
        Cursor cur = mDbHelper.fetchSpecificCartItem(itemName);
        int itemCount = cur.getCount();


        //insert into cart without checking item already exists in cart
        mDbHelper.insertCartItem(itemName, itemPrice, itemQuantity, hotelName);

        Toast.makeText(getApplicationContext(), "Item Added to Cart", Toast.LENGTH_LONG).show();
        btnChild.setText("Added!");

        //insert into cart for checking if same item already exists in cart
        /*if(itemCount == 0) {
            //insert into cart table
            mDbHelper.insertCartItem(itemName, itemPrice, itemQuantity, hotelName);

            Toast.makeText(getApplicationContext(), "Item Added to Cart", Toast.LENGTH_LONG).show();
            btnChild.setText("Added!");
        } else {

            //prevent insertion
            Toast.makeText(getApplicationContext(), "Item already exists in Cart", Toast.LENGTH_LONG).show();
        }*/
    }


    //method for back button closes current activity and display previous activity
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //method to fetch all values from database and show them in listview
    private void fillData() {
        itemsCursor = mDbHelper.fetchTopAllHotelsByItem(itemName);
        customAdapter5 = new CustomAdapter5(ItemDetailActivity.this, itemsCursor, 0);
        listView.setAdapter(customAdapter5);
    }
}
