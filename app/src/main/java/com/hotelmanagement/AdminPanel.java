package com.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminPanel extends AppCompatActivity {

    // Objects
    EditText username, password;
    Button login;
    DbAdapter mDb;
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    // Session Manager Class
    SessionManagement session;

    //First method to call when activity runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shows layout/activity_item_detail
        setContentView(R.layout.activity_admin_panel);

        // Session Manager
        session = new SessionManagement(getApplicationContext());
        //display title on toolbar
        setTitle("Admin Panel");
        //creates database connection
        mDb = new DbAdapter(this);
        mDb.open();

        //assigns id to username, password and login button
        username = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        //method to call when login button is clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //gets username and password and stored in variable cur
                final Cursor cur = mDb.adminLogin(username.getText().toString(), password.getText().toString());

                //shows message if username or password is empty
                if(username.getText().toString().equals("") || password.getText().toString().equals("")) {

                    alert.showAlertDialog(AdminPanel.this, "Login failed..", "Please enter username and password", false);
                 //if username and password matches with database
                } else  if(cur.getCount() > 0)  {

                    session.createLoginSession("FoodStreet", "test@test.com");

                    Intent i = new Intent(getApplicationContext(), AdminOrders.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("Exit", true);

                    finishAffinity();
                    startActivity(i);

                    //if username or password don't matches with database
                } else if(cur.getCount() == 0) {
                    // username / password doesn't match
                    alert.showAlertDialog(AdminPanel.this, "Login failed..", "Username/Password is incorrect", false);


                }

            }
        });
    }

    //toolbar right side icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.usericon, menu);
            return true;

    }

    //Toolbar right side icon action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.usericon) {
            finish();

           startActivity(new Intent(getApplicationContext(), ListActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

}
