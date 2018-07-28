package com.unmeshjoshi.homework3;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

public class ChatMessage {

    public String messageText;
    public String messageUser;
    public String messageTime;
    public String imageUrl;
    public String key;


    public ChatMessage() {
    }

    public ChatMessage(String messageText, String messageUser, String key) {
        this.messageText = messageText;
        this.messageUser = messageUser;

        this.key=key;
        this.messageTime = new PrettyTime().format(new Date(System.currentTimeMillis() + 1000*60*10));
    }

    public ChatMessage(String messageText, String messageUser, String imageUrl,String key) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.imageUrl=imageUrl;
        this.key=key;
        this.messageTime = new PrettyTime().format(new Date(System.currentTimeMillis() + 1000*60*10));
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
