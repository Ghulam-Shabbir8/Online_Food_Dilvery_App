package com.hotelmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ItemsActivity extends AppCompatActivity {

    // Objects
    ListView listView;
    private static final int EDIT_MENU = Menu.FIRST;
    private static final int DELETE_MENU = EDIT_MENU + 1;
    private DbAdapter mDbHelper;
    Cursor itemsCursor;
    private CustomAdapter4 customAdapter4;


    //First method to call when activity runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shows layout/activity_item_detail
        setContentView(R.layout.activity_items);
        //display itemName on toolbar
        setTitle("All Items");
        //creates database connection
        mDbHelper = new DbAdapter(this);
        mDbHelper = mDbHelper.open();
        //shows back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // assign id to listview
        listView = (ListView)findViewById(R.id.items_list);
        //call fillData() method
        fillData();

        //method called when any item on list clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //gets value from this activity by clicking on listview and send it to ItemDetailActivity
                Intent item_detail = new Intent(getApplicationContext(), ItemDetailActivity.class);

                switch(position) {
                    case 0:
                        item_detail.putExtra("itemName", "Beef Tikka");
                        startActivity(item_detail);
                        break;
                    case 1:
                        item_detail.putExtra("itemName", "Burger");
                        startActivity(item_detail);
                        break;
                    case 2:
                        item_detail.putExtra("itemName", "Chi Fajita");
                        startActivity(item_detail);
                        break;
                    case 3:
                        item_detail.putExtra("itemName", "Chi Tikka");
                        startActivity(item_detail);
                        break;
                    case 4:
                        item_detail.putExtra("itemName", "Chicken Biryani");
                        startActivity(item_detail);
                        break;
                    case 5:
                        item_detail.putExtra("itemName", "Chicken Fried Rice");
                        startActivity(item_detail);
                        break;
                    case 6:
                        item_detail.putExtra("itemName", "Chicken Masala Rice");
                        startActivity(item_detail);
                        break;
                    case 7:
                        item_detail.putExtra("itemName", "Chicken Pulao");
                        startActivity(item_detail);
                        break;
                    case 8:
                        item_detail.putExtra("itemName", "Fried Fish");
                        startActivity(item_detail);
                        break;
                    case 9:
                        item_detail.putExtra("itemName", "Handi");
                        startActivity(item_detail);
                        break;
                    case 10:
                        item_detail.putExtra("itemName", "Karahi");
                        startActivity(item_detail);
                        break;
                    case 11:
                        item_detail.putExtra("itemName", "Mutton Biryani");
                        startActivity(item_detail);
                        break;
                    case 12:
                        item_detail.putExtra("itemName", "Mutton Tikka");
                        startActivity(item_detail);
                        break;
                    case 13:
                        item_detail.putExtra("itemName", "Nawain Pizza");
                        startActivity(item_detail);
                        break;
                    case 14:
                        item_detail.putExtra("itemName", "Pickle Pizza");
                        startActivity(item_detail);
                        break;
                    case 15:
                        item_detail.putExtra("itemName", "Rahoo Fish");
                        startActivity(item_detail);
                        break;
                    case 16:
                        item_detail.putExtra("itemName", "Shawarma");
                        startActivity(item_detail);
                        break;
                    case 17:
                        item_detail.putExtra("itemName", "Vegetable Pizza");
                        startActivity(item_detail);
                        break;
                }

            }
        });
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

    //method for back button closes current activity and display previous activity
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //method to fetch all values from database and show them in listview
    private void fillData() {

        itemsCursor = mDbHelper.fetchAllMenus();
        customAdapter4 = new CustomAdapter4(ItemsActivity.this, itemsCursor, 0);
        listView.setAdapter(customAdapter4);

    }
}
