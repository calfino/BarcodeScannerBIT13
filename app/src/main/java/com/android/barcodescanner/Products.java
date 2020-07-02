package com.android.barcodescanner;

import android.graphics.Bitmap;

public class Products
{
    // Initialization attributes //
    int Index_Number;
    String Serial_Number;
    String Model_Number;
    String Model_Name;
    String WLAN_MAC;
    String Box_Number;
    Bitmap Picture;

    // Constructor //
    public Products(int Index_Number, String Serial_Number, String Model_Number, String Model_Name, String WLAN_MAC, String Box_Number, Bitmap Picture)
    {
        this.Index_Number = Index_Number;
        this.Serial_Number = Serial_Number;
        this.Model_Number = Model_Number;
        this.Model_Name = Model_Name;
        this.WLAN_MAC = WLAN_MAC;
        this.Box_Number = Box_Number;
        this.Picture = Picture;
    }

    // Setter and Getter //
    public int getIndex_Number()
    {
        return Index_Number;
    }

    public void setIndex_Number(int Index_Number)
    {
        this.Index_Number = Index_Number;
    }

    public String getSerial_Number()
    {
        return Serial_Number;
    }

    public void setSerial_Number(String Serial_Number)
    {
        this.Serial_Number = Serial_Number;
    }

    public String getModel_Number()
    {
        return Model_Number;
    }

    public void setModel_Number(String Model_Number)
    {
        this.Model_Number = Model_Number;
    }

    public String getModel_Name()
    {
        return Model_Name;
    }

    public void setModel_Name(String Model_Name)
    {
        this.Model_Name = Model_Name;
    }

    public String getWLAN_MAC()
    {
        return WLAN_MAC;
    }

    public void setWLAN_MAC(String WLAN_MAC)
    {
        this.WLAN_MAC = WLAN_MAC;
    }

    public String getBox_Number()
    {
        return Box_Number;
    }

    public void setBox_Number(String Box_Number)
    {
        this.Box_Number = Box_Number;
    }

    public Bitmap getPicture()
    {
        return Picture;
    }

    public void setPicture(Bitmap Picture)
    {
        this.Picture = Picture;
    }
}
