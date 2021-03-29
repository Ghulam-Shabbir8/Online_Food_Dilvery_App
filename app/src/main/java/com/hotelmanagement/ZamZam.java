package com.hotelmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ZamZam extends AppCompatActivity {

    //Objects
    TextView hotelInfo, hotelLocation, hotelContact;
    TableLayout zamZamTable;
    ViewFlipper zamZamSlider;
    ImageAdapter imageAdapter;
    Button zamZamDetBtn, zamZamRatBtn;
    DbAdapter objDb;
    int hotelNo, ratingNo, actualRating;
    String activityName, activityId;
    Intent activityIntent;
    int[] images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zam_zam_new);
        //assigns id to textfields
        hotelInfo = (TextView)findViewById(R.id.hotelInfo);
        hotelLocation = (TextView)findViewById(R.id.hotelLocation);
        hotelContact = (TextView)findViewById(R.id.hotelContact);

        //gets name and id of hotels from listactivity.java file
        activityName = getIntent().getExtras().getString("activityName");
        activityId = getIntent().getExtras().getString("activityId");

        //shows information for specific hotel
        if(activityName.equals("Zam Zam")) {
          setTitle("Zam Zam");
            images = new int[]{R.drawable.baryani, R.drawable.baryani10, R.drawable.baryani2};
            hotelInfo.setText("Zam Zam Hotel");
            hotelLocation.setText("Katchery Road Sgd");
            hotelContact.setText("048-3723798");


        } else if(activityName.equals("Mehman Saraey")) {
            setTitle("Mehman Saraey");
            images = new int[]{R.drawable.baryani3, R.drawable.baryani4, R.drawable.baryani5};
            hotelInfo.setText("Mehman Saraey Hotel");
            hotelLocation.setText("University Road Sgd");
            hotelContact.setText("048-8525598");

        }  else if(activityName.equals("Fri Chicks")) {
            setTitle("Fri Chicks");
            images = new int[]{R.drawable.baryani6, R.drawable.baryani7, R.drawable.baryani8};
            hotelInfo.setText("Fri Chicks Hotel");
            hotelLocation.setText("University Road Sgd");
            hotelContact.setText("048-3727276");

        }  else if(activityName.equals("Charsi")) {
            setTitle("Charsi");
            images = new int[]{R.drawable.baryani9, R.drawable.bb11, R.drawable.bbbq};
            hotelInfo.setText("Charsi Hotel");
            hotelLocation.setText("Stadium Road Sgd");
            hotelContact.setText("0300-4960341");

        }  else if(activityName.equals("Char Minar")) {
            setTitle("Char Minar");
            images = new int[]{R.drawable.bbbq7, R.drawable.bbq10, R.drawable.bbq2};
            hotelInfo.setText("Char Minar Hotel");
            hotelLocation.setText("Lahore Road Sgd");
            hotelContact.setText("048-3214447");

        }  else if(activityName.equals("Fork n Knife")) {
            setTitle("Fork n Knife");
            images = new int[]{R.drawable.bbq3, R.drawable.bbq4, R.drawable.bbq7};
            hotelInfo.setText("Fork n Knife Hotel");
            hotelLocation.setText("Babar Road Sgd");
            hotelContact.setText("0300-7900716");
        }  else if(activityName.equals("Pizza Hut")) {
            setTitle("Pizza Hut");
            images = new int[]{R.drawable.bbq8, R.drawable.dish, R.drawable.dish11};
            hotelInfo.setText("Pizza Hut Hotel");
            hotelLocation.setText("University Road Sgd");
            hotelContact.setText("0310-8541236");

        }  else if(activityName.equals("Khan Baba")) {
            setTitle("Khan Baba");
            images = new int[]{R.drawable.dish111, R.drawable.dish2, R.drawable.dish222};
            hotelInfo.setText("Khan Babaa Hotel");
            hotelLocation.setText("University Road Sgd");
            hotelContact.setText("048-3212626");

        }  else if(activityName.equals("Chilli Chatni")) {
            setTitle("Chilli Chatni");
            images = new int[]{R.drawable.lahorifish, R.drawable.baryani, R.drawable.chickenbaryani};
            hotelInfo.setText("Chilli Chatni Hotel");
            hotelLocation.setText("Fatima Jinnah Road Sgd");
            hotelContact.setText("0349-6902824");
        }  else if(activityName.equals("Apna Dera")) {
            setTitle("Apna Dera");
            images = new int[]{R.drawable.bb11, R.drawable.bbq8, R.drawable.chickenpotato};
            hotelInfo.setText("Apna Dera Hotel");
            hotelLocation.setText("Awan Colony Sgd");
            hotelContact.setText("0311-6548127");
        }

        //creates database connection
        objDb = new DbAdapter(getApplicationContext());
        objDb = objDb.open();

    //shows back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //assigns id table and buttons
        zamZamTable = (TableLayout) findViewById(R.id.zamZamTable);
        zamZamDetBtn = (Button)findViewById(R.id.zamZamDetBtn);
        zamZamRatBtn = (Button)findViewById(R.id.zamZamRatBtn);


        //assign id to slider
        zamZamSlider =  findViewById(R.id.zamZamSlider);
        //imageAdapter = new ImageAdapter(this);


        //gets all the images of specific hotel and displays in slider
        for(int image : images) {
            flipperImages(image);
        }

        //detail button click method
        zamZamDetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //opens detail activity of related hotel when detail button clicked
                if(activityName.equals("Zam Zam")) {
                    startActivity(new Intent(getApplicationContext(), ZamZamDetail.class));

                }  else if(activityName.equals("Mehman Saraey")) {
                    startActivity(new Intent(getApplicationContext(), MMDetail.class));

                }  else if(activityName.equals("Fri Chicks")) {
                    startActivity(new Intent(getApplicationContext(), FCDetail.class));

                }  else if(activityName.equals("Charsi")) {
                    startActivity(new Intent(getApplicationContext(), CharsiDetail.class));

                }  else if(activityName.equals("Char Minar")) {
                    startActivity(new Intent(getApplicationContext(), CMDetail.class));

                }  else if(activityName.equals("Fork n Knife")) {
                    startActivity(new Intent(getApplicationContext(), FKDetail.class));

                }  else if(activityName.equals("Pizza Hut")) {
                    startActivity(new Intent(getApplicationContext(), PHDetail.class));

                }  else if(activityName.equals("Khan Baba")) {
                    startActivity(new Intent(getApplicationContext(), KBDetail.class));

                }  else if(activityName.equals("Chilli Chatni")) {
                    startActivity(new Intent(getApplicationContext(), CCDetail.class));

                }  else if(activityName.equals("Apna Dera")) {
                    startActivity(new Intent(getApplicationContext(), ADDetail.class));

                }
            }
        });

        //opens rating activity when rating button clicked of related hotel
        zamZamRatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rating = new Intent(getApplicationContext(), ZamZamRating.class);
                rating.putExtra("activityName", activityName);
                rating.putExtra("activityId", activityId);
                startActivity(rating);
            }
        });

    }

    //method to add and view images in slider
    public void flipperImages(int image) {

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        zamZamSlider.addView(imageView);
        zamZamSlider.setFlipInterval(3000);
        zamZamSlider.setAutoStart(true);

        //animation
        zamZamSlider.setInAnimation(this, android.R.anim.slide_in_left);
        //zamZamSlider.setInAnimation(this, android.R.anim.slide_in_left);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    }
