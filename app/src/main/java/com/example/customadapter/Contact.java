package com.example.customadapter;

import android.graphics.Color;

import java.io.Serializable;


public class Contact implements Serializable {
    private String name;
    private int imageResource;
    private String address;
    private String email;
    private String phoneNumber;
    private String online;

    public Contact(String n, int i, String p, String e, String a, String o)
    {
        name = n;
        imageResource = i;
        phoneNumber = p;
        email = e;
        address = a;
        online = o;
    }


    public String getName()
    {
        return name;
    }


    public int getImageResource()
    {
        return imageResource;
    }


    public String getEmail()
    {
        return email;
    }


    public String getPhoneNumber()
    {
        return phoneNumber;
    }


    public String getAddress()
    {
        return address;
    }

    public int getColor()
    {
        int colorInt = 0;

        if(online.contains("Online"))
            colorInt = Color.GREEN;

        if(online.contains("Idle"))
            colorInt = Color.YELLOW;

        if(online.contains("Do Not Disturb"))
            colorInt = Color.RED;

        if(online.contains("Invisible"))
            colorInt = Color.LTGRAY;

        return colorInt;
    }

    public String getStatus()
    {
        return online;
    }
}
