package com.android.barcodescanner;

import android.graphics.Bitmap;

public class Products
{
    // Initialization attributes //
    int index;
    String serial_number;
    String model_number;
    String model_name;
    String wlan_mac;
    String box_number;
    Bitmap picture;

    // Constructor //
    public Products(int index_number, String serial_number, String model_number, String model_name, String wlan_mac, String box_number)
    {
        this.index = index;
        this.serial_number = serial_number;
        this.model_number = model_number;
        this.model_name = model_name;
        this.wlan_mac = wlan_mac;
        this.box_number = box_number;
//        this.picture = picture;
    }



    // Setter and Getter //
    public int getIndex_Number()
    {
        return index;
    }

    public void setIndex_Number(int index)
    {

        this.index = index;
    }

    public String getSerial_Number()
    {
        return serial_number;
    }

    public void setSerial_Number(String serial_number)
    {
        this.serial_number = serial_number;
    }

    public String getModel_Number()
    {
        return model_number;
    }

    public void setModel_Number(String model_number)
    {
        this.model_number = model_number;
    }

    public String getModel_Name()
    {
        return model_name;
    }

    public void setModel_Name(String model_name)
    {
        this.model_name = model_name;
    }

    public String getWLAN_MAC()
    {
        return wlan_mac;
    }

    public void setWLAN_MAC(String wlan_mac)
    {
        this.wlan_mac = wlan_mac;
    }

    public String getBox_Number()
    {
        return box_number;
    }

    public void setBox_Number(String Box_Number)
    {
        this.box_number = box_number;
    }

    public Bitmap getPicture()
    {
        return picture;
    }

    public void setPicture(Bitmap picture)
    {
        this.picture = picture;
    }
}
