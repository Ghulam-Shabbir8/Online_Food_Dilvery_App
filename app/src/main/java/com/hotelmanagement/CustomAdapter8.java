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

public class CustomAdapter8 extends CursorAdapter {

    private LayoutInflater mInflater;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public CustomAdapter8(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.row8, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.ordDetItmTxt = (TextView) view.findViewById(R.id.ordDetItmTxt);
        holder.ordDetItmPriceTxt = (TextView) view.findViewById(R.id.ordDetItmPriceTxt);
        holder.ordDetItmQuantTxt = (TextView) view.findViewById(R.id.ordDetItmQuantTxt);
        holder.ordDetHotlTxt = (TextView) view.findViewById(R.id.ordDetHotlTxt);

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
        holder.ordDetItmTxt.setText("Item Name: "+cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ITEM_NAME)));
        holder.ordDetItmPriceTxt.setText("Item Price: "+cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ITEM_PRICE)));
        holder.ordDetItmQuantTxt.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ITEM_QUANT)));
        holder.ordDetHotlTxt.setText("Hotel Name: "+cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_HOTEL_PROVIDER)));

/*        holder.ordDetItmTxt.setText("Name:");
        holder.ordDetItmPriceTxt.setText("Price:");
        holder.ordDetItmQuantTxt.setText("Quantity:");
        holder.ordDetHotlTxt.setText("Hotel:");*/
    }

    static class ViewHolder {
        TextView ordDetItmTxt, ordDetItmPriceTxt, ordDetItmQuantTxt , ordDetHotlTxt;

    }

}