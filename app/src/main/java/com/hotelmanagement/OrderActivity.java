package com.hotelmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;


public class OrderActivity extends AppCompatActivity {


    ListView listView;
    private DbAdapter mDbHelper;
    Cursor itemsCursor;
    private CustomAdapter6 customAdapter6;
    private final static String TAG = MainActivity.class.getName().toString();
    public boolean flag = true;

    EditText orderNameTxt, orderContactTxt, orderAddressTxt;
    Button ordrCnfrmBtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        orderNameTxt = (EditText)findViewById(R.id.orderNameTxt);
        orderContactTxt  = (EditText)findViewById(R.id.orderContactTxt);
        orderAddressTxt = (EditText)findViewById(R.id.orderAddressTxt);
        ordrCnfrmBtb = (Button)findViewById(R.id.ordrCnfrmBtb);



        setTitle("Order");

        mDbHelper = new DbAdapter(this);
        mDbHelper = mDbHelper.open();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //method to call when order button clicked
        ordrCnfrmBtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if name is empty
                if (TextUtils.isEmpty(orderNameTxt.getText())) {

                    Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                //if contact no is empty
                } else if (TextUtils.isEmpty(orderContactTxt.getText())) {

                    Toast.makeText(getApplicationContext(), "Please Contact No", Toast.LENGTH_SHORT).show();
                //if address is empty
                } else if (TextUtils.isEmpty(orderAddressTxt.getText())) {

                    Toast.makeText(getApplicationContext(), "Please Enter Address", Toast.LENGTH_SHORT).show();
                //if all fields are filled
                } else {

                    mDbHelper.insertOrderInfo(orderNameTxt.getText().toString(), orderContactTxt.getText().toString(),
                            orderAddressTxt.getText().toString() );
                    //gets order id of last inserted order and store in orderId variable
                    int orderId = mDbHelper.ordersId();
                    //create order number like this FS1234
                    String  orderNumber = "FS"+orderId;
                    //update order information its order number using its id
                    mDbHelper.updateOrderInfo(orderNumber, orderId);
                    //insert order detail of this order
                    mDbHelper.insertOrderDetItem();
                    //deletes items from cart
                    mDbHelper.deleteCartItem();
                    //updates order detail's order number got from orders
                    mDbHelper.updateOrderDetItem(orderId, orderNumber, "nc");
                    //updates items that are deleted from cart and inserted in orders table
                    mDbHelper.updateNCOrderDetItem("c");

                    //shows success message on order insertion
                    Toast.makeText(getApplicationContext(), "Order inserted successfully", Toast.LENGTH_SHORT).show();
                    //closes this activity and open hotels list
                    finish();
                    startActivity(new Intent(getApplicationContext(), ListActivity.class));
                }
            }
        });


    }
    //method to back button in toolbar
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
