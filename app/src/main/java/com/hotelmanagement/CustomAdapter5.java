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

public class CustomAdapter5 extends CursorAdapter {

    private LayoutInflater mInflater;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public CustomAdapter5(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.row5, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.hotelName = (TextView) view.findViewById(R.id.detHotelName);
        holder.hotelPrice = (TextView) view.findViewById(R.id.detHotelPrice);
        holder.hotelQuantity = (TextView) view.findViewById(R.id.detHotelQuant);
        holder.hotelImg = (ImageView) view.findViewById(R.id.detHotelImg);
        holder.hotelRat = (RatingBar) view.findViewById(R.id.detHotelRating);

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
        holder.hotelName.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_HOTEL_NAME)));
        holder.hotelPrice.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ITEM_PRICE)));
        holder.hotelQuantity.setText("Quantity:"+cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ITEM_QUANT)));

        String str = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ITEM_RATING));
        int i = Integer.valueOf(str);
        float f = i;
        holder.hotelRat.setRating(f);
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
        TextView hotelName, hotelPrice, hotelQuantity;
        ImageView hotelImg;
        RatingBar hotelRat;
    }

}
