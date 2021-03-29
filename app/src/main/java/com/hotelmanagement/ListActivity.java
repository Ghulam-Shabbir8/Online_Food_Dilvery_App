package com.hotelmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class ListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    private static final int EDIT_MENU = Menu.FIRST;
    private static final int DELETE_MENU = EDIT_MENU + 1;
    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;
    private static final int ACTIVITY_VIEW = 2;

    private DbAdapter mDbHelper;
    Cursor itemsCursor;
    private CustomAdapter customAdapter;
    private CustomAdapter1 customAdapter1;
    private final static String TAG = MainActivity.class.getName().toString();
    public boolean flag = true;

    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDbHelper = new DbAdapter(this);
        mDbHelper = mDbHelper.open();


        listView = (ListView)findViewById(android.R.id.list);

        fillData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {
                    case 0:
                        Intent zamzam =new Intent(getApplicationContext(), ZamZam.class);
                        zamzam.putExtra("activityName", "Zam Zam");
                        zamzam.putExtra("activityId", "1");
                        startActivity(zamzam);
                        break;
                    case 1:
                        Intent mehmansaraey =new Intent(getApplicationContext(), ZamZam.class);
                        mehmansaraey.putExtra("activityName", "Mehman Saraey");
                        mehmansaraey.putExtra("activityId", "2");
                        startActivity(mehmansaraey);
                        break;
                    case 2:
                        Intent frichicks =new Intent(getApplicationContext(), ZamZam.class);
                        frichicks.putExtra("activityName", "Fri Chicks");
                        frichicks.putExtra("activityId", "3");
                        startActivity(frichicks);
                        break;
                    case 3:
                        Intent charsi =new Intent(getApplicationContext(), ZamZam.class);
                        charsi.putExtra("activityName", "Charsi");
                        charsi.putExtra("activityId", "4");
                        startActivity(charsi);
                        break;
                    case 4:
                        Intent charminar =new Intent(getApplicationContext(), ZamZam.class);
                        charminar.putExtra("activityName", "Char Minar");
                        charminar.putExtra("activityId", "5");
                        startActivity(charminar);
                        break;
                    case 5:
                        Intent forknknife =new Intent(getApplicationContext(), ZamZam.class);
                        forknknife.putExtra("activityName", "Fork n Knife");
                        forknknife.putExtra("activityId", "6");
                        startActivity(forknknife);
                        break;
                    case 6:
                        Intent pizzahut =new Intent(getApplicationContext(), ZamZam.class);
                        pizzahut.putExtra("activityName", "Pizza Hut");
                        pizzahut.putExtra("activityId", "7");
                        startActivity(pizzahut);
                        break;
                    case 7:
                        Intent khanbaba =new Intent(getApplicationContext(), ZamZam.class);
                        khanbaba.putExtra("activityName", "Khan Baba");
                        khanbaba.putExtra("activityId", "8");
                        startActivity(khanbaba);
                        break;
                    case 8:
                        Intent chillichatni =new Intent(getApplicationContext(), ZamZam.class);
                        chillichatni.putExtra("activityName", "Chilli Chatni");
                        chillichatni.putExtra("activityId", "9");
                        startActivity(chillichatni);
                        break;
                    case 9:
                        Intent apnadera =new Intent(getApplicationContext(), ZamZam.class);
                        apnadera.putExtra("activityName", "Apna Dera");
                        apnadera.putExtra("activityId", "10");
                        startActivity(apnadera);
                        break;

                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        header.setBackgroundColor(getResources().getColor(R.color.color7));
        navigationView.setBackgroundColor(getResources().getColor(R.color.color8));

    }


    private void fillData() {

        itemsCursor = mDbHelper.fetchAllHotels();
        customAdapter1 = new CustomAdapter1(ListActivity.this, itemsCursor, 0);
        listView.setAdapter(customAdapter1);
}


    //toolbar right side icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
        //return false;
    }

    //Toolbar right side icon action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.adminpanel) {

            startActivity(new Intent(getApplicationContext(), AdminPanel.class));
        }

        return super.onOptionsItemSelected(item);
    }

    //left navigation menu
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.search) {
            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
        } else if (id == R.id.allitems) {
            startActivity(new Intent(getApplicationContext(), ItemsActivity.class));
        } else if (id == R.id.cart) {
            startActivity(new Intent(getApplicationContext(), CartActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
