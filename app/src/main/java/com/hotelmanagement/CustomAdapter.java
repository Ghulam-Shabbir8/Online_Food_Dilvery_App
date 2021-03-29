package com.hotelmanagement;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends CursorAdapter {
    private LayoutInflater mInflater;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //gets id from layout and set it to java variables
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View   view    =    mInflater.inflate(R.layout.row, parent, false);
        ViewHolder holder  =   new ViewHolder();
        holder.hotelName    =   (TextView)  view.findViewById(R.id.hotelName);

        //holder.itmPrice   =   (TextView)  view.findViewById(R.id.itmPrice);
        view.setTag(holder);
        return view;

    }


    //gets database values and display them
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //If you want to have zebra lines color effect uncomment below code
        if(cursor.getPosition()%2==1) {
             view.setBackgroundResource(R.color.color7);
        } else {
            view.setBackgroundResource(R.color.color8);
        }

        ViewHolder holder  =   (ViewHolder)    view.getTag();
        holder.hotelName.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NAME)));
    }

    //Objects
    static class ViewHolder {
        TextView hotelName;
        //TextView itmPrice;
        ImageView hotelImg;
    }
}