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
import android.widget.TextView;

public class CustomAdapter1 extends CursorAdapter {

    private LayoutInflater mInflater;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public CustomAdapter1(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.row, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.hotelName = (TextView) view.findViewById(R.id.hotelName);
        holder.hotelImg = (ImageView) view.findViewById(R.id.hotelImg);
        //holder.itmPrice   =   (TextView)  view.findViewById(R.id.historyAmount);
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
        holder.hotelName.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NAME)));
        //setting images to categories

        if(holder.hotelName.getText().toString().equals("Zam Zam")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_zamzam_round);
        } else if(holder.hotelName.getText().toString().equals("Mehman Saraey")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_mehman_saraey_round);
        } else if(holder.hotelName.getText().toString().equals("Fri Chicks")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_fri_chicks_round);
        } else if(holder.hotelName.getText().toString().equals("Charsi")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_charsi_round);
        } else if(holder.hotelName.getText().toString().equals("Char Minar")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_char_minar_round);
        } else if(holder.hotelName.getText().toString().equals("Fork n Knife")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_fork_knife_round);
        } else if(holder.hotelName.getText().toString().equals("Pizza Hut")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_pizza_hut_round);
        } else if(holder.hotelName.getText().toString().equals("Khan Baba")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_khan_baba_round);
        } else if(holder.hotelName.getText().toString().equals("Chilli Chatni")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_chilli_chatni_round);
        } else if(holder.hotelName.getText().toString().equals("Apna Dera")) {
            holder.hotelImg.setImageResource(R.mipmap.ic_apna_dera_round);
        }

    }

        static class ViewHolder {
            TextView hotelName;
            ImageView hotelImg;
        }

}
