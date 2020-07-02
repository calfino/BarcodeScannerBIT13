package com.android.barcodescanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    // Initialization variable //
    public static final String DATABASE_NAME = "BarcodeScanner.db";
    public static final String TABLE_NAME = "Products";
    public static final String COL_1 = "Index_Number";
    public static final String COL_2 = "Serial_Number";
    public static final String COL_3 = "Model_Number";
    public static final String COL_4 = "Model_Name";
    public static final String COL_5 = "WLAN_MAC";
    public static final String COL_6 = "Box_Number";
    public static final String COL_7 = "Picture";

    public ByteArrayOutputStream arrOut;
    public byte[] imageInByte;
    public Context context;

    // When constructor called, database will be created //
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    // Function to create database //
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (Index_Number INTEGER PRIMARY KEY AUTOINCREMENT, Serial_Number TEXT, Model_Number TEXT, Model_Name TEXT, WLAN_MAC TEXT, Box_Number TEXT, Picture BLOB)");
    }

    // Exception Handling if any same database exist //
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Function to insert data //
    public void insertData(Products product)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = product.getPicture();

            arrOut = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, arrOut);
            imageInByte = arrOut.toByteArray();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, product.getSerial_Number());
            contentValues.put(COL_3, product.getModel_Number());
            contentValues.put(COL_4, product.getModel_Name());
            contentValues.put(COL_5, product.getWLAN_MAC());
            contentValues.put(COL_6, product.getBox_Number());
            contentValues.put(COL_7, imageInByte);

            long result = db.insert(TABLE_NAME,null, contentValues);

            if(result == -1)
            {
                Toast.makeText(context, "Data added!", Toast.LENGTH_LONG).show();
                db.close();
            }
            else
            {
                Toast.makeText(context, "Failed to add data!", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Get one spesific product //
    public Products getProduct(int Index_Number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME + "WHERE Index_Number = " + Integer.toString(Index_Number), null );
        db.close();

        if(result != null)
        {
            result.moveToFirst();
            byte[] imageByte = result.getBlob(6);
            Bitmap objBitMap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);

            Products product = new Products(Integer.parseInt(result.getString(0)), result.getString(1), result.getString(2),result.getString(3), result.getString(4),result.getString(5), objBitMap);
            result.close();
            return product;
        }

        else
        {
            return null;
        }
    }

    // Get all product return in cursor//
    public Cursor getProductInCursor()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        db.close();
        return result;
    }

    // Get all product return in class Products //
    public ArrayList<Products> getAllProducts()
    {
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<Products> listProduct = new ArrayList<>();
            Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            if(result.getCount() != 0)
            {
                while(result.moveToNext())
                {
                    int Index_Number = result.getInt(0);
                    String Serial_Number = result.getString(1);
                    String Model_Number = result.getString(2);
                    String Model_Name = result.getString(3);
                    String WLAN_MAC = result.getString(4);
                    String Box_Number = result.getString(5);
                    byte[] imageByte = result.getBlob(6);
                    Bitmap objBitMap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);

                    listProduct.add(new Products(Index_Number,Serial_Number,Model_Number,Model_Name,WLAN_MAC,Box_Number,objBitMap));
                    result.close();
                }

                return listProduct;
            }

            else
            {
                Toast.makeText(context, "No values exists in database", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // Update data //
    public void updateData(Products product)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = product.getPicture();

            arrOut = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, arrOut);
            imageInByte = arrOut.toByteArray();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, product.getSerial_Number());
            contentValues.put(COL_3, product.getModel_Number());
            contentValues.put(COL_4, product.getModel_Name());
            contentValues.put(COL_5, product.getWLAN_MAC());
            contentValues.put(COL_6, product.getBox_Number());
            contentValues.put(COL_7, imageInByte);

            db.update(TABLE_NAME, contentValues, "Index_Number = ?", new String[] {Integer.toString(product.getIndex_Number())});
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete data //
    public void deleteData(int Index_Number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "Index_Number = ?", new String[] {Integer.toString(Index_Number)});
        db.close();
    }
}
