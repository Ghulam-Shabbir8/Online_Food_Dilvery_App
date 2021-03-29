package com.hotelmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {
    //all database tables
    private static final String DATABASE_NAME = "Hotel";
    private static final String DATABASE_TABLE = "hotel_table";
    private static final String RATING_TABLE = "rating_table";
    private static final String ITEM_TABLE = "item_table";
    private static final String HOTEL_CRED = "hotel_cred";
    private static final String CART_TABLE = "cart_table";
    private static final String ORDERS_TABLE = "orders_table";
    private static final String ORDER_DETAIL_TABLE = "orders_detail_table";
    private static final String ADMIN_TABLE = "admin_table";
    int  id;

    //database version
    private static final int DATABASE_VERSION = 4;

    //all database tables columns
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INFO = "info";
    public static final String KEY_RATING = "rating";
    public static final String KEY_USER_NO = "user_no";
    public static final String KEY_COMMENTS = "comments";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_HOTEL_ID = "hotel_id";
    public static final String KEY_HOTEL_NAME = "hotel_name";
    public static final String KEY_HOTEL_PROVIDER = "hotel_provider";
    public static final String KEY_ITEM_NAME = "item_name";
    public static final String KEY_ITEM_PRICE = "price";
    public static final String KEY_ITEM_QUANT = "quantity";
    public static final String KEY_ITEM_RATING = "rating";
    public static final String KEY_DESC = "desc";
    public static final String KEY_PRICE = "price";
    public static final String KEY_DATE = "date";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORDS = "password";
    public static final String KEY_STATUS = "status";
    public static final String KEY_ORDER_NO = "order_no";
    public static final String KEY_SHIP_NAME = "ship_name";
    public static final String KEY_ITEM_NO = "item_no";
    public static final String KEY_SHIP_CONTACT = "ship_contact";
    public static final String KEY_SHIP_ADDRESS = "ship_address";
    public static final String KEY_ORDERS_ID = "orders_id";
    public static final String KEY_ORDER_NUMBER = "order_number";



    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    //all database table creating queries
    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " (" + KEY_ROWID
            + " integer primary key autoincrement, " + KEY_NAME + " varchar, " +  KEY_IMAGE + " varchar, " + KEY_INFO +
            " varchar, " +  KEY_RATING + " varchar, " + KEY_USER_NO + " varchar, " + KEY_COMMENTS + " varchar, " + KEY_USER_ID + " varchar );";

    private static final String RATING_CREATE = "create table " + RATING_TABLE + " (" + KEY_ROWID
            + " integer primary key autoincrement, " + KEY_NAME + " varchar, " +  KEY_USER_ID + " varchar, " + KEY_USER_NO +
            " varchar, " +  KEY_COMMENTS + " varchar, " + KEY_RATING + " varchar, " + KEY_HOTEL_ID + " varchar, " + KEY_HOTEL_NAME + " varchar );";

    private static final String ITEM_CREATE = "create table " + ITEM_TABLE + " (" + KEY_ROWID
            + " integer primary key autoincrement, " + KEY_NAME + " varchar, " +  KEY_ITEM_PRICE + " varchar, " +  KEY_ITEM_QUANT + " varchar, "
            +  KEY_HOTEL_NAME + " varchar, " + KEY_HOTEL_ID + " varchar, " +  KEY_RATING + " varchar ); ";

    private static final String HOTEL_CRED_CREATE = "create table " + HOTEL_CRED + " (" + KEY_ROWID
            + " integer primary key autoincrement, " + KEY_NAME + " varchar, " +  KEY_USERNAME + " varchar, " +  KEY_PASSWORDS + " varchar, "
            +  KEY_STATUS  + " varchar ); ";

    private static final String CART_CREATE = "create table " + CART_TABLE + " (" + KEY_ROWID
            + " integer primary key autoincrement, " + KEY_ITEM_NAME + " varchar, " + KEY_ITEM_PRICE + " varchar, " +  KEY_ITEM_QUANT + " varchar, " + KEY_HOTEL_PROVIDER  + " varchar ); ";

    private static final String ORDERS_CREATE = "create table " + ORDERS_TABLE + " (" + KEY_ROWID
            + " integer primary key autoincrement, " + KEY_ORDER_NO + " integer, " +  KEY_ITEM_NO + " varchar, " +  KEY_STATUS + " varchar, "
            +  KEY_SHIP_NAME + " varchar, " + KEY_SHIP_CONTACT + " varchar, " +  KEY_SHIP_ADDRESS + " text ); ";

    private static final String ORDER_DETAIL_CREATE = "create table " + ORDER_DETAIL_TABLE + " (" + KEY_ROWID
            + " integer primary key autoincrement, " + KEY_ORDERS_ID + " integer, " +  KEY_ORDER_NUMBER + " varchar, " +  KEY_ITEM_NAME + " varchar, "
            + KEY_ITEM_PRICE + " varchar, " + KEY_ITEM_QUANT + " varchar, " +  KEY_HOTEL_PROVIDER + " varchar, " +  KEY_STATUS + " varchar ); ";


    private static final String ADMIN_CREATE = "create table " + ADMIN_TABLE + " (" + KEY_ROWID
            + " integer primary key autoincrement, " + KEY_NAME + " varchar, " +  KEY_USERNAME + " varchar, " +  KEY_PASSWORDS + " varchar, "
            +  KEY_STATUS + " varchar  ); ";

    //all hotels information insertion to database queries
    private static final String hotel_insert = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Zam Zam', 'R.mipmap.ic_zamzam_round', 'Zam Zam Hotel in Sargodha', '0', '0', '0', '0');";
    private static final String hotel_insert1 = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Mehman Saraey', 'R.mipmap.ic_mehman_saraey_round', 'Mehman Saraey Hotel in Sargodha', '0', '0', '0', '0');";
    private static final String hotel_insert2 = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Fri Chicks', 'R.mipmap.ic_fri_chicks_round', 'Fri Chicks Hotel in Sargodha', '0', '0', '0', '0');";
    private static final String hotel_insert3 = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Charsi', 'R.mipmap.ic_charsi_round', 'Charsi Hotel in Sargodha', '0', '0', '0', '0');";
    private static final String hotel_insert4 = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Char Minar', 'R.mipmap.ic_char_minar_round', 'Char Minar Hotel in Sargodha', '0', '0', '0', '0');";
    private static final String hotel_insert5 = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Fork n Knife', 'R.mipmap.ic_fork_knife_round', 'Fork n Knife Hotel in Sargodha', '0', '0', '0', '0');";
    private static final String hotel_insert6 = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Pizza Hut', 'R.mipmap.ic_pizza_hut_round', 'Pizza Hut Hotel in Sargodha', '0', '0', '0', '0');";
    private static final String hotel_insert7 = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Khan Baba', 'R.mipmap.ic_khan_baba_round', 'Khan Baba Hotel in Sargodha', '0', '0', '0', '0');";
    private static final String hotel_insert8 = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Chilli Chatni', 'R.mipmap.ic_chilli_chatni_round', 'Chilli Chatni Hotel in Sargodha', '0', '0', '0', '0');";
    private static final String hotel_insert9 = "INSERT into hotel_table (name, image, info, rating, user_no, comments, user_id) values('Apna Dera', 'R.mipmap.ic_apna_dera_round', 'Apna Dera Hotel in Sargodha', '0', '0', '0', '0');";


    //Pickle Pizza item information
    private static final String pickle_pizza1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Pickle Pizza', '1500', 'Full', 'Pizza Hut', '7', '5');";
    private static final String pickle_pizza2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Pickle Pizza', '1200', 'Full', 'Fork n Knife', '6', '3');";
    private static final String pickle_pizza3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Pickle Pizza', '1000', 'Full', 'Khan Baba', '8', '4');";
    private static final String pickle_pizza4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Pickle Pizza', '1300', 'Full', 'Charsi', '4', '3');";
    private static final String pickle_pizza5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Pickle Pizza', '1250', 'Full', 'Zam Zam', '1', '2');";

    //Chi Tikka item information
    private static final String chi_tikka1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Tikka', '500', 'Half', 'Zam Zam', '1', '3');";
    private static final String chi_tikka2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Tikka', '1200', 'Full', 'Charsi', '4', '4');";
    private static final String chi_tikka3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Tikka', '1500', 'Full', 'Khan Baba', '8', '5');";
    private static final String chi_tikka4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Tikka', '550', 'Half', 'Pizza Hut', '7', '3');";
    private static final String chi_tikka5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Tikka', '600', 'Half', 'Fork n Knife', '6', '4');";
    private static final String chi_tikka6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Tikka', '700', 'Half', 'Char Minar', '5', '2');";

    //Chi Fajita item information
    private static final String chi_fajita1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Fajita', '2000', 'Full', 'Zam Zam', '1', '4');";
    private static final String chi_fajita2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Fajita', '1800', 'Full', 'Mehman Saraey', '2', '3');";
    private static final String chi_fajita3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Fajita', '1500', 'Full', 'Charsi', '4', '5');";
    private static final String chi_fajita4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Fajita', '1550', 'Full', 'Apna Dera', '10', '3');";
    private static final String chi_fajita5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Fajita', '1600', 'Full', 'Chilli Chatni', '9', '2');";
    private static final String chi_fajita6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chi Fajita', '1700', 'Full', 'Fri Chicks', '3', '4');";

    //Mutton Tikka item information
    private static final String mutton_tikka1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Tikka', '500', 'Full', 'Char Minar', '5', '5');";
    private static final String mutton_tikka2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Tikka', '1200', 'Full', 'Charsi', '4', '3');";
    private static final String mutton_tikka3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Tikka', '1500', 'Full', 'Fri Chicks', '3', '5');";
    private static final String mutton_tikka4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Tikka', '1225', 'Full', 'Apna Dera', '10', '1');";
    private static final String mutton_tikka5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Tikka', '1300', 'Full', 'Mehman Saraey', '2', '3');";
    private static final String mutton_tikka6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Tikka', '135', 'Full', 'Pizza Hut', '7', '4');";

    //Nawain Pizza item information
    private static final String nawain_pizza1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Nawain Pizza', '1200', 'Half', 'Apna Dera', '10', '2');";
    private static final String nawain_pizza2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Nawain Pizza', '1000', 'Half', 'Mehman Saraey', '2', '4');";
    private static final String nawain_pizza3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Nawain Pizza', '1100', 'Half', 'Char Minar', '5', '4');";
    private static final String nawain_pizza4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Nawain Pizza', '1050', 'Half', 'Pizza Hut', '7', '3');";
    private static final String nawain_pizza5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Nawain Pizza', '1150', 'Half', 'Fork n Knife', '6', '2');";
    private static final String nawain_pizza6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Nawain Pizza', '1250', 'Half', 'Zam Zam', '1', '1');";

    //Beef Tikka item information
    private static final String beef_tikka1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Beef Tikka', '2000', 'Full', 'Apna Dera', '10', '5');";
    private static final String beef_tikka2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Beef Tikka', '1800', 'Full', 'Chilli Chatni', '9', '2');";
    private static final String beef_tikka3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Beef Tikka', '1600', 'Full', 'Khan Baba', '8', '3');";
    private static final String beef_tikka4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Beef Tikka', '1700', 'Full', 'Zam Zam', '1', '5');";
    private static final String beef_tikka5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Beef Tikka', '1750', 'Full', 'Fork n Knife', '6', '4');";
    private static final String beef_tikka6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Beef Tikka', '1850', 'Full', 'Pizza Hut', '7', '3');";

    //Vegetable Pizza item information
    private static final String vegetable_pizza1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Vegetable Pizza', '500', 'Half', 'Fork n Knife', '6', '5');";
    private static final String vegetable_pizza2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Vegetable Pizza', '1200', 'Half', 'Charsi', '4', '3');";
    private static final String vegetable_pizza3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Vegetable Pizza', '1500', 'Half', 'Apna Dera', '10', '2');";
    private static final String vegetable_pizza4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Vegetable Pizza', '900', 'Half', 'Pizza Hut', '7', '4');";
    private static final String vegetable_pizza5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Vegetable Pizza', '1100', 'Half', 'Chilli Chatni', '9', '3');";
    private static final String vegetable_pizza6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Vegetable Pizza', '1150', 'Half', 'Char Minar', '5', '1');";

    //Rahoo Fish item information
    private static final String rahoo_fish1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Rahoo Fish', '1200', 'Half', 'Fri Chicks', '3', '3');";
    private static final String rahoo_fish2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Rahoo Fish', '1000', 'Half', 'Chilli Chatni', '9', '4');";
    private static final String rahoo_fish3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Rahoo Fish', '800', 'Half', 'Khan Baba', '8', '5');";
    private static final String rahoo_fish4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Rahoo Fish', '1200', 'Full', 'Apna Dera', '10', '5');";
    private static final String rahoo_fish5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Rahoo Fish', '1250', 'Full', 'Charsi', '4', '4');";
    private static final String rahoo_fish6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Rahoo Fish', '1150', 'Full', 'Char Minar', '5', '3');";

    //Fried Fish item information
    private static final String fried_fish1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Fried Fish', '1100', 'Half', 'Fork n Knife', '6', '4');";
    private static final String fried_fish2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Fried Fish', '900', 'Half', 'Charsi', '4', '4');";
    private static final String fried_fish3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Fried Fish', '1000', 'Half', 'Mehman Saraey', '2', '3');";
    private static final String fried_fish4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Fried Fish', '950', 'Half', 'Fri Chicks', '3', '1');";
    private static final String fried_fish5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Fried Fish', '1050', 'Half', 'Pizza Hut', '7', '2');";
    private static final String fried_fish6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Fried Fish', '1150', 'Half', 'Khan Baba', '8', '5');";

    //Chicken Biryani item information
    private static final String chicken_biryani1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Biryani', '300', 'Full', 'Zam Zam', '1', '5');";
    private static final String chicken_biryani2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Biryani', '350', 'Full', 'Khan Baba', '8', '3');";
    private static final String chicken_biryani3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Biryani', '400', 'Full', 'Fri Chicks', '3', '4');";
    private static final String chicken_biryani4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Biryani', '320', 'Full', 'Apna Dera', '10', '2');";
    private static final String chicken_biryani5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Biryani', '370', 'Full', 'Chilli Chatni', '9', '4');";
    private static final String chicken_biryani6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Biryani', '420', 'Full', 'Char Minar', '5', '1');";


    //Chicken Pulao item information
    private static final String chicken_pulao1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Pulao', '800', 'Full', 'Charsi', '4', '5');";
    private static final String chicken_pulao2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Pulao', '300', 'Half', 'Fri Chicks', '3', '3');";
    private static final String chicken_pulao3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Pulao', '600', 'Full', 'Khan Baba', '8', '2');";
    private static final String chicken_pulao4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Pulao', '700', 'Full', 'Fork n Knife', '6', '1');";
    private static final String chicken_pulao5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Pulao', '750', 'Full', 'Mehman Saraey', '2', '3');";
    private static final String chicken_pulao6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Pulao', '850', 'Full', 'Pizza Hut', '7', '5');";


    //Mutton Biryani item information
    private static final String mutton_biryani1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Biryani', '600', 'Full', 'Mehman Saraey', '2', '3');";
    private static final String mutton_biryani2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Biryani', '500', 'Full', 'Zam Zam', '1', '4');";
    private static final String mutton_biryani3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Biryani', '400', 'Full', 'Fri Chicks', '3', '5');";
    private static final String mutton_biryani4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Biryani', '550', 'Full', 'Apna Dera', '10', '2');";
    private static final String mutton_biryani5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Biryani', '450', 'Full', 'Chilli Chatni', '9', '1');";
    private static final String mutton_biryani6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Mutton Biryani', '650', 'Full', 'Charsi', '4', '5');";


    //Chicken Fried Rice item information
    private static final String chicken_fried_rice1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Fried Rice', '600', 'Full', 'Mehman Saraey', '2', '4');";
    private static final String chicken_fried_rice2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Fried Rice', '400', 'Full', 'Char Minar', '5', '5');";
    private static final String chicken_fried_rice3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Fried Rice', '300', 'Half', 'Khan Baba', '8', '3');";
    private static final String chicken_fried_rice4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Fried Rice', '450', 'Full', 'Apna Dera', '10', '1');";
    private static final String chicken_fried_rice5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Fried Rice', '500', 'Full', 'Pizza Hut', '7', '5');";
    private static final String chicken_fried_rice6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Fried Rice', '550', 'Full', 'Fork n Knife', '6', '4');";

    //Chicken Masala Rice item information
    private static final String chicken_masala_rice1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Masala Rice', '700', 'Full', 'Chilli Chatni', '9', '3');";
    private static final String chicken_masala_rice2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Masala Rice', '550', 'Full', 'Apna Dera', '10', '5');";
    private static final String chicken_masala_rice3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Masala Rice', '600', 'Full', 'Khan Baba', '8', '2');";
    private static final String chicken_masala_rice4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Masala Rice', '500', 'Full', 'Zam Zam', '1', '3');";
    private static final String chicken_masala_rice5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Masala Rice', '650', 'Full', 'Fri Chicks', '3', '4');";
    private static final String chicken_masala_rice6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Chicken Masala Rice', '750', 'Full', 'Mehman Saraey', '2', '5');";

    //Karahi item information
    private static final String karahi1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Karahi', '1000', 'Full', 'Zam Zam', '1', '3');";
    private static final String karahi2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Karahi', '1050', 'Full', 'Mehman Saraey', '2', '5');";
    private static final String karahi3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Karahi', '1100', 'Full', 'Khan Baba', '8', '2');";
    private static final String karahi4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Karahi', '1150', 'Full', 'Chilli Chatni', '9', '3');";


    //Handi item information
    private static final String handi1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Handi', '950', 'Full', 'Zam Zam', '1', '5');";
    private static final String handi2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Handi', '1000', 'Full', 'Fri Chicks', '3', '4');";
    private static final String handi3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Handi', '1050', 'Full', 'Char Minar', '5', '3');";
    private static final String handi4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Handi', '1100', 'Full', 'Khan Baba', '8', '1');";
    private static final String handi5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Handi', '1150', 'Full', 'Fork n Knife', '6', '2');";


    //Shawarma item information
    private static final String shawarma1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Shawarma', '300', 'Full', 'Zam Zam', '1', '5');";
    private static final String shawarma2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Shawarma', '250', 'Full', 'Fri Chicks', '3', '4');";
    private static final String shawarma3 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Shawarma', '230', 'Full', 'Char Minar', '5', '3');";
    private static final String shawarma4 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Shawarma', '320', 'Full', 'Fork n Knife', '6', '1');";
    private static final String shawarma5 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Shawarma', '350', 'Full', 'Khan Baba', '8', '2');";
    private static final String shawarma6 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Shawarma', '370', 'Full', 'Chilli Chatni', '9', '2');";

    //Burger item information
    private static final String burger1 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Burger', '400', 'Full', 'Char Minar', '5', '1');";
    private static final String burger2 = "INSERT into item_table (name, price,quantity, hotel_name, hotel_id, rating) values('Burger', '350', 'Full', 'Mehman Saraey', '2', '3');";
    private static final String burger3 ="INSERT into  cart_table (item_name, price, quantity, hotel_provider) values('asd', 'ads', 'asd', 'das');";

    //admin credentials query
    private static final String admin_insert ="INSERT into  admin_table (name, username, password, status) values('Admin', 'admin', 'admin', '1');";



    private final Context mCtx;

    public DbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            //executes creation of all databases queries
            db.execSQL(DATABASE_CREATE);
            db.execSQL(RATING_CREATE);
            db.execSQL(ITEM_CREATE);
            db.execSQL(HOTEL_CRED_CREATE);
            db.execSQL(CART_CREATE);
            db.execSQL(ORDERS_CREATE);
            db.execSQL(ORDER_DETAIL_CREATE);
            db.execSQL(ADMIN_CREATE);

            //executes insertion of all hotel's information queries
            db.execSQL(hotel_insert);
            db.execSQL(hotel_insert1);
            db.execSQL(hotel_insert2);
            db.execSQL(hotel_insert3);
            db.execSQL(hotel_insert4);
            db.execSQL(hotel_insert5);
            db.execSQL(hotel_insert6);
            db.execSQL(hotel_insert7);
            db.execSQL(hotel_insert8);
            db.execSQL(hotel_insert9);

            //executes insertion of pickle_pizza queries
            db.execSQL(pickle_pizza1);
            db.execSQL(pickle_pizza2);
            db.execSQL(pickle_pizza3);
            db.execSQL(pickle_pizza4);
            db.execSQL(pickle_pizza5);

//executes insertion of chi_tikka queries
            db.execSQL(chi_tikka1);
            db.execSQL(chi_tikka2);
            db.execSQL(chi_tikka3);
            db.execSQL(chi_tikka4);
            db.execSQL(chi_tikka5);
            db.execSQL(chi_tikka6);
//executes insertion of chi_fajita queries
            db.execSQL(chi_fajita1);
            db.execSQL(chi_fajita2);
            db.execSQL(chi_fajita3);
            db.execSQL(chi_fajita4);
            db.execSQL(chi_fajita5);
            db.execSQL(chi_fajita6);
//executes insertion of mutton_tikka queries
            db.execSQL(mutton_tikka1);
            db.execSQL(mutton_tikka2);
            db.execSQL(mutton_tikka3);
            db.execSQL(mutton_tikka4);
            db.execSQL(mutton_tikka5);
            db.execSQL(mutton_tikka6);
//executes insertion of nawain_pizza queries
            db.execSQL(nawain_pizza1);
            db.execSQL(nawain_pizza2);
            db.execSQL(nawain_pizza3);
            db.execSQL(nawain_pizza4);
            db.execSQL(nawain_pizza5);
            db.execSQL(nawain_pizza6);
//executes insertion of beef_tikka queries
            db.execSQL(beef_tikka1);
            db.execSQL(beef_tikka2);
            db.execSQL(beef_tikka3);
            db.execSQL(beef_tikka4);
            db.execSQL(beef_tikka5);
            db.execSQL(beef_tikka6);
//executes insertion of vegetable_pizza queries
            db.execSQL(vegetable_pizza1);
            db.execSQL(vegetable_pizza2);
            db.execSQL(vegetable_pizza3);
            db.execSQL(vegetable_pizza4);
            db.execSQL(vegetable_pizza5);
            db.execSQL(vegetable_pizza6);
//executes insertion of rahoo_fish queries
            db.execSQL(rahoo_fish1);
            db.execSQL(rahoo_fish2);
            db.execSQL(rahoo_fish3);
            db.execSQL(rahoo_fish4);
            db.execSQL(rahoo_fish5);
            db.execSQL(rahoo_fish6);
//executes insertion of fried_fish queries
            db.execSQL(fried_fish1);
            db.execSQL(fried_fish2);
            db.execSQL(fried_fish3);
            db.execSQL(fried_fish4);
            db.execSQL(fried_fish5);
            db.execSQL(fried_fish6);
//executes insertion of chicken_biryani queries
            db.execSQL(chicken_biryani1);
            db.execSQL(chicken_biryani2);
            db.execSQL(chicken_biryani3);
            db.execSQL(chicken_biryani4);
            db.execSQL(chicken_biryani5);
            db.execSQL(chicken_biryani6);
//executes insertion of chicken_pulao queries
            db.execSQL(chicken_pulao1);
            db.execSQL(chicken_pulao2);
            db.execSQL(chicken_pulao3);
            db.execSQL(chicken_pulao4);
            db.execSQL(chicken_pulao5);
            db.execSQL(chicken_pulao6);
//executes insertion of mutton_biryani queries
            db.execSQL(mutton_biryani1);
            db.execSQL(mutton_biryani2);
            db.execSQL(mutton_biryani3);
            db.execSQL(mutton_biryani4);
            db.execSQL(mutton_biryani5);
            db.execSQL(mutton_biryani6);
//executes insertion of chicken_fried_rice queries
            db.execSQL(chicken_fried_rice1);
            db.execSQL(chicken_fried_rice2);
            db.execSQL(chicken_fried_rice3);
            db.execSQL(chicken_fried_rice4);
            db.execSQL(chicken_fried_rice5);
            db.execSQL(chicken_fried_rice6);
//executes insertion of chicken_masala_rice queries
            db.execSQL(chicken_masala_rice1);
            db.execSQL(chicken_masala_rice2);
            db.execSQL(chicken_masala_rice3);
            db.execSQL(chicken_masala_rice4);
            db.execSQL(chicken_masala_rice5);
            db.execSQL(chicken_masala_rice6);

//executes insertion of karahi queries
            db.execSQL(karahi1);
            db.execSQL(karahi2);
            db.execSQL(karahi3);
            db.execSQL(karahi4);
//executes insertion of handi queries
            db.execSQL(handi1);
            db.execSQL(handi2);
            db.execSQL(handi3);
            db.execSQL(handi4);
            db.execSQL(handi5);
//executes insertion of shawarma queries
            db.execSQL(shawarma1);
            db.execSQL(shawarma2);
            db.execSQL(shawarma3);
            db.execSQL(shawarma4);
            db.execSQL(shawarma5);
            db.execSQL(shawarma6);
//executes insertion of burger queries
            db.execSQL(burger1);
            db.execSQL(burger2);
            //executes insertion of admin credentials
            db.execSQL(admin_insert);

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Not used
        }
    }
    //creates db using DatabaseHelper()
    public DbAdapter open() throws  android.database.SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    //not used
    public void close() {
        mDbHelper.close();

    }

    //method to insert rating into database gets name, comment, rating, hotel_id, hotel_name
    public long insertRating(String Name, String Comments, int Rating, String HotelId ,String HotelName) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, Name);
        initialValues.put(KEY_COMMENTS, Comments);
        initialValues.put(KEY_RATING, Rating);
        initialValues.put(KEY_HOTEL_ID, HotelId);
        initialValues.put(KEY_HOTEL_NAME, HotelName);

        Long ret = mDb.insert(RATING_TABLE, null, initialValues);
        return ret;
    }

    //method to insert rating into database gets name, price, quantity, hotel_name
    public long insertCartItem(String itemName, String itemPrice, String itemQuantity ,String HotelName) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ITEM_NAME, itemName);
        initialValues.put(KEY_ITEM_PRICE, itemPrice);
        initialValues.put(KEY_ITEM_QUANT, itemQuantity);
        initialValues.put(KEY_HOTEL_PROVIDER, HotelName);


        Long ret = mDb.insert(CART_TABLE, null, initialValues);

        return ret;

    }

    //method to insert rating into database gets person name, person contact info, person address
    public long insertOrderInfo(String personName, String personContact, String personAddress ) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_SHIP_NAME, personName);
        initialValues.put(KEY_SHIP_CONTACT, personContact);
        initialValues.put(KEY_SHIP_ADDRESS, personAddress);
        initialValues.put(KEY_STATUS, "0");

        Long ret = mDb.insert(ORDERS_TABLE, null, initialValues);

        return ret;

    }


    //method to get last insert id of order's table which will be updated in order_detail table
    public int ordersId() {
        Cursor cursor = mDb.rawQuery("SELECT  * FROM " + ORDERS_TABLE, null);

        if(cursor.moveToLast()){
            //name = cursor.getString(column_index);//to get other values
          id = cursor.getInt(0);//to get id, 0 is the column index
        }
        return  id;
    }

    //method for admin login using username and password
    public Cursor adminLogin(String username, String password ) {
        Cursor cur = mDb.rawQuery("select * from  "+ADMIN_TABLE+ " where username = "+"'" + username + "'"+ " AND password = "+ "'" + password + "'", null);
        return cur;
    }

    //method to update orders table order number
    public void updateOrderInfo(String orderNumber, int orderId ) {
        mDb.execSQL("update "+ORDERS_TABLE+ " set order_no = "+"'" + orderNumber + "'"+ " where _id = "+ "'" + orderId + "'");

    }

    //method to update order detail against specific order
    public void insertOrderDetItem() {

        mDb.execSQL("insert into orders_detail_table (item_name, price, quantity, hotel_provider, status)\n" +
                "SELECT item_name, price, quantity, hotel_provider, 'nc' FROM cart_table;");
    }

    //method  to update order detail item
    public void updateOrderDetItem(int orderId , String orderNumber,  String status) {
        mDb.execSQL("update "+ORDER_DETAIL_TABLE+ " set orders_id = "+"'" + orderId + "'"+ ", order_number = "+"'" + orderNumber + "'"  + " where status = "+ "'" + status + "'");

    }

    //method to update cart items which have nc status
    public void updateNCOrderDetItem(String status) {
        mDb.execSQL("update "+ORDER_DETAIL_TABLE+ " set status = "+"'" + status + "'"+ " where status = 'nc' ");

    }


    //method to delete cart items
    public void deleteCartItem() {

        mDb.execSQL("DELETE FROM cart_table;");


    }

    //method to specific delete cart item
    public Cursor deleteSpecificCartItem(String item) {
        //Cursor cur = mDb.rawQuery("DELETE FROM "+CART_TABLE+ " where item_name = "+"'" + item + "'", null);
        //Cursor cur = mDb.rawQuery("DELETE FROM cart_table where item_name =" + "'" + item + "'", null);
        Cursor cur = mDb.rawQuery("DELETE  FROM cart_table where item_name =" + "'" + item + "'", null);
        return cur;

    }


    //method to get all cart items
    public Cursor fetchCartItem() {
        Cursor cur = mDb.rawQuery("select * from "+CART_TABLE, null);
        return cur;
    }


    //method to get specific cart item
    public Cursor fetchSpecificCartItem(String itemName) {
        Cursor cur = mDb.rawQuery("select * from " + CART_TABLE + " where item_name = " + "'" + itemName + "'" , null);
        return cur;
    }


    //method to get total price of items placed in cart table
    public int cartTotal() {
        Cursor cur = mDb.rawQuery("select sum(price) from " + CART_TABLE , null);
        cur.moveToFirst();
        int i = cur.getInt(0);
        //int i = cur.getCount();
        return i;
    }


    //method to get rating of specific hotel (not used)
    public Cursor fetchRating(String hotelName) {
        Cursor cur = mDb.rawQuery("select * from "+RATING_TABLE, null);
        return cur;
    }

    //method to total number of ratings against specific hotel
    public int fetchRatingNo(String hotelId) {
        Cursor cur = mDb.rawQuery("select sum(rating) from "+RATING_TABLE+" where hotel_id="+hotelId, null);
        cur.moveToFirst();
        int i = cur.getInt(0);
        return i;
    }

    //method to get all data from rating table of specific hotel
    public int fetchHotelNo(String hotelId) {
        Cursor cur = mDb.rawQuery("select * from "+RATING_TABLE+" where hotel_id="+hotelId, null);
        int count = cur.getCount();
        cur.close();
        return count;
    }


    //method to get all comments against specific hotel
    public Cursor fetchComments(String hotel_id) {
        Cursor cur = mDb.rawQuery("select * from "+RATING_TABLE+" where hotel_id="+hotel_id, null);
        return cur;
    }

    //method to get all information about all hotels
    public Cursor fetchAllHotels() {
        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_IMAGE, KEY_INFO, KEY_RATING, KEY_USER_NO, KEY_COMMENTS, KEY_USER_ID},
                null, null, null, null, null, null);
    }

    //method to get all orders
    public Cursor fetchAllOrders() {
        Cursor cur = mDb.rawQuery("select * from "+ORDERS_TABLE + " order by _id desc", null);
        return cur;
    }

    //method to get all data from order detail against specific order or order number
    public Cursor fetchAllOrderDetail(String orderNumber) {
        Cursor cur = mDb.rawQuery("select * from  " + ORDER_DETAIL_TABLE +  " where order_number = " + "'" + orderNumber + "'" + "order by _id desc", null);
        return cur;

    }

    //method to get top three hotels with respect to rating against specific item
    public Cursor fetchTopThreeHotels(String itemName) {
        Cursor cur = mDb.rawQuery("select * from " + ITEM_TABLE + " where name = " + "'" + itemName + "'" + "order by rating desc limit 3", null);
        return cur;
    }

    //method to get all top hotels with respect to rating against specific item
    public Cursor fetchTopAllHotelsByItem(String itemName) {
        Cursor cur = mDb.rawQuery("select * from " + ITEM_TABLE + " where name = " + "'" + itemName + "'" + "order by rating desc", null);
        return cur;
    }

    //method to get all items
    public Cursor fetchAllMenus() {
        Cursor cur = mDb.rawQuery("select * from " + ITEM_TABLE + " group by name ", null);
        return cur;
    }

    //method to get specific item details
    public int fetchMenu(String ItemName) {

        Cursor cur = mDb.rawQuery("select * from " + ITEM_TABLE + " where name = " + "'" + ItemName + "'", null);

        int count = cur.getCount();
        cur.close();
        return count;
    }

    //method to get rating against specific item
    public Cursor fetchMenuByRating(String ItemName) {

        Cursor cur = mDb.rawQuery("select * from " + ITEM_TABLE + " where name = " + "'" + ItemName + "'" + "order by rating desc LIMIT 3", null);
        return cur;

    }

    //method to get items (not used)
    public Cursor fetchItem(long rowId) throws SQLException {
        Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_IMAGE, KEY_DESC,
                KEY_PRICE, KEY_DATE}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if(mCursor  != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}