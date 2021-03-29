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

public class CustomAdapter6 extends CursorAdapter {

    private LayoutInflater mInflater;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public CustomAdapter6(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.row6, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.ItemName = (TextView) view.findViewById(R.id.cartItemName);
        holder.ItemPrice = (TextView) view.findViewById(R.id.cartHotelPrice);
        holder.ItemQuant = (TextView) view.findViewById(R.id.cartHotelQuant);
        holder.HotelName = (TextView) view.findViewById(R.id.cartHotelName);
        holder.hotelImg = (ImageView) view.findViewById(R.id.cartHotelImg);

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
        holder.ItemName.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ITEM_NAME)));
        holder.ItemPrice.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ITEM_PRICE)));
        holder.ItemQuant.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ITEM_QUANT)));
        holder.HotelName.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_HOTEL_PROVIDER)));

        if(holder.HotelName.getText().toString().equals("Zam Zam")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_zamzam_round);
        } else if(holder.HotelName.getText().toString().equals("Mehman Saraey")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_mehman_saraey_round);
        } else if(holder.HotelName.getText().toString().equals("Fri Chicks")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_fri_chicks_round);
        } else if(holder.HotelName.getText().toString().equals("Charsi")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_charsi_round);
        } else if(holder.HotelName.getText().toString().equals("Char Minar")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_char_minar_round);
        } else if(holder.HotelName.getText().toString().equals("Fork n Knife")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_fork_knife_round);
        } else if(holder.HotelName.getText().toString().equals("Pizza Hut")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_pizza_hut_round);
        } else if(holder.HotelName.getText().toString().equals("Khan Baba")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_khan_baba_round);
        } else if(holder.HotelName.getText().toString().equals("Chilli Chatni")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_chilli_chatni_round);
        } else if(holder.HotelName.getText().toString().equals("Apna Dera")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_apna_dera_round);
        }

    }

    static class ViewHolder {
        TextView ItemName, ItemPrice, ItemQuant , HotelName;
        ImageView hotelImg;
    }

}
