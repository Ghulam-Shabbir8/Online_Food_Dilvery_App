package com.hotelmanagement;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private DbAdapter mDbHelper;
    Cursor itemsCursor;
    private CustomAdapter2 customAdapter2;
    String item_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        item_name = getIntent().getExtras().getString("item_name");

        setTitle(item_name);

        mDbHelper = new DbAdapter(this);
        mDbHelper = mDbHelper.open();

        listView = (ListView)findViewById(R.id.mainList);

        fillData();

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), ZamZam.class));
                        break;
                }

            }
        });*/
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    private void fillData() {
        itemsCursor = mDbHelper.fetchTopThreeHotels(item_name);
        customAdapter2 = new CustomAdapter2(MainActivity.this, itemsCursor, 0);
        listView.setAdapter(customAdapter2);
    }

}