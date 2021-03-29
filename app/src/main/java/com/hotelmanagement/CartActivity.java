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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {
    // Objects
    ListView listView;
    private DbAdapter mDbHelper;
    Cursor itemsCursor;
    private CustomAdapter6 customAdapter6;
    TextView totalBillTxt;
    Button ordrBtb;
    String itemName;

    //First method to call when activity runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shows layout/activity_item_detail
        setContentView(R.layout.activity_cart);

        totalBillTxt = (TextView) findViewById(R.id.totalBillTxt);
        ordrBtb = (Button)findViewById(R.id.ordrBtn);
        //set title in toolbar
        setTitle("Cart");
        //creates database connection
        mDbHelper = new DbAdapter(this);
        mDbHelper = mDbHelper.open();

        //show back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //assign id to listview
        listView = (ListView)findViewById(R.id.cartList);

        //calls fillData() method
        fillData();

        //opens OrderActivity.java when order button clicked
        ordrBtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OrderActivity.class));
            }
        });

    }

    //method for back button in toolbar
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //method to fetch all values from database and show them in listview
    private void fillData() {

        //gets all items placed in  cart
        itemsCursor = mDbHelper.fetchCartItem();

        //generates total bill of items placed in cart
        int totalBill = mDbHelper.cartTotal();

        //if no items placed in cart
        if(itemsCursor.getCount() == 0) {
            ordrBtb.setVisibility(View.INVISIBLE);
            totalBillTxt.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "No items in Cart", Toast.LENGTH_SHORT).show();
        //if there are items placed in cart
        } else if(itemsCursor.getCount() > 0) {
            ordrBtb.setVisibility(View.VISIBLE);
            totalBillTxt.setVisibility(View.VISIBLE);
        }
        //gets data from cart table and display in listview
        customAdapter6 = new CustomAdapter6(CartActivity.this, itemsCursor, 0);
        listView.setAdapter(customAdapter6);
        //display total bill in textview above order button
        totalBillTxt.setText("Total Bill: "+totalBill);

    }


    public void delCartFunc(View v) {
        //get the row the clicked button is in
        RelativeLayout vwParentRow = (RelativeLayout) v.getParent();

        TextView itemNameTxt = (TextView)vwParentRow.getChildAt(4);

        itemName = itemNameTxt.getText().toString();

        //btnChild.setText("Deleted!");

        vwParentRow.refreshDrawableState();

        AlertDialog.Builder singleDel = new AlertDialog.Builder(this);
        singleDel.setMessage("Do you want to delete this item?");
        singleDel.setCancelable(true);

        singleDel.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Cursor cur = mDbHelper.deleteSpecificCartItem(itemName);
                        int i = cur.getCount();

                        finish();
                        startActivity(getIntent());

                        Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();

                    }
                });

        singleDel.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog singleDelAlrt = singleDel.create();
        singleDelAlrt.show();
    }

    //toolbar right side icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(itemsCursor.getCount() != 0) {
            getMenuInflater().inflate(R.menu.cartdel, menu);
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

        if (id == R.id.cartdel) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Do you want to delete all items?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            mDbHelper.deleteCartItem();

                            finish();
                            startActivity(getIntent());

                            Toast.makeText(getApplicationContext(), "All Items deleted", Toast.LENGTH_SHORT).show();

                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }

        return super.onOptionsItemSelected(item);
    }
}
