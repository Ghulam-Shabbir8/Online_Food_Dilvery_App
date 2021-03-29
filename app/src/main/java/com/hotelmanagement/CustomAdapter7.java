package com.hotelmanagement;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CustomAdapter7 extends CursorAdapter {

    private LayoutInflater mInflater;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public CustomAdapter7(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.row7, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.ordNumTxt = (TextView) view.findViewById(R.id.ordNumTxt);
        holder.shipNameTxt = (TextView) view.findViewById(R.id.shipNameTxt);
        holder.shipContactTxt = (TextView) view.findViewById(R.id.shipContactTxt);
        holder.shipAddressTxt = (TextView) view.findViewById(R.id.shipAddressTxt);

        view.setTag(holder);
        return view;

    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //For different colors to rows
        if (cursor.getPosition() % 2 == 1) {
            view.setBackgroundResource(R.color.color7);
        } else {
            view.setBackgroundResource(R.color.color8);
        }


        ViewHolder holder  =   (ViewHolder)view.getTag();
        holder.ordNumTxt.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ORDER_NO)));
        holder.shipNameTxt.setText("Item Name: "+cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_SHIP_NAME)));
        holder.shipContactTxt.setText("Contact: "+cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_SHIP_CONTACT)));
        holder.shipAddressTxt.setText("Address: "+cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_SHIP_ADDRESS)));

    }

    static class ViewHolder {
        TextView ordNumTxt, shipNameTxt, shipContactTxt , shipAddressTxt;

    }

}
