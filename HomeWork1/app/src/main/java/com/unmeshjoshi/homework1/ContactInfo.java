package com.unmeshjoshi.homework1;

import android.media.Image;
import android.widget.ImageButton;

import java.io.Serializable;
import java.util.Date;

public class ContactInfo implements Serializable {

    ImageButton imageButton;
    String first;
    String last;
    String company;
    String phone;
    String email;
    String url;
    String address;
    String birthday;
    String nickname;
    String fbURL;
    String twitterURL;
    String skype;
    String youtube;


    public ContactInfo(ImageButton imageButton, String first, String last, String company, String phone, String email, String url, String address, String birthday, String nickname, String fbURL, String twitterURL, String skype, String youtube) {
        this.imageButton = imageButton;
        this.first = first;
        this.last = last;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.url = url;
        this.address = address;
        this.birthday = birthday;
        this.nickname = nickname;
        this.fbURL = fbURL;
        this.twitterURL = twitterURL;
        this.skype = skype;
        this.youtube = youtube;
    }
}
