package com.unmeshjoshi.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ContactInfo contactInfoDisplay = (ContactInfo) getIntent().getSerializableExtra(ContactListActivity.DISPLAY_CONTACT);

        TextView textViewName = findViewById(R.id.textViewName);
        TextView facebookURL = findViewById(R.id.textViewFaceBook);
        TextView twitterURL = findViewById(R.id.textViewTwitter);
        TextView skype = findViewById(R.id.textViewSkype);
        TextView youtube = findViewById(R.id.textViewYoutube);
        TextView url = findViewById(R.id.textViewURL);

        String name = contactInfoDisplay.getFirst() + "  " + contactInfoDisplay.getLast();
        textViewName.setText(name.toString());

        facebookURL.setText(contactInfoDisplay.getFbURL());
        twitterURL.setText(contactInfoDisplay.getTwitterURL());
        skype.setText(contactInfoDisplay.getSkype());
        youtube.setText(contactInfoDisplay.getYoutube());
        url.setText(contactInfoDisplay.getUrl());




    }
}
