package com.unmeshjoshi.homework1;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageButton;

import java.io.Serializable;
import java.util.Date;

public class ContactInfo implements Serializable {

    byte[] imageButton;
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


    public ContactInfo()
    {}


    public ContactInfo(byte[] imageButton, String first, String last, String company, String phone, String email, String url, String address, String birthday, String nickname, String fbURL, String twitterURL, String skype, String youtube) {
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

    public byte[] getImageButton() {
        return imageButton;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getCompany() {
        return company;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFbURL() {
        return fbURL;
    }

    public String getTwitterURL() {
        return twitterURL;
    }

    public String getSkype() {
        return skype;
    }

    public String getYoutube() {
        return youtube;
    }
}
