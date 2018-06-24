package com.unmeshjoshi.homework1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    static LinkedList<ContactInfo> contactList = new LinkedList<ContactInfo>();
    public static String CONTACT_LIST ="CONTACT_LIST";
    public static int LIST_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.buttonCreateNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactActivity.class);
                startActivityForResult(intent, LIST_REQUEST);

                //finish();
            }
        });



        findViewById(R.id.buttonEditContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.buttonDisplayContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactListActivity.class);
                intent.putExtra(CONTACT_LIST,contactList);

                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.buttonDeleteContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactListActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("results","");
        if (requestCode == LIST_REQUEST) {
            Log.d("in activity result",""+requestCode);
            ContactInfo contactInfo = (ContactInfo) data.getExtras().getSerializable(ContactActivity.CONTACT_CLASS);
            contactList.add(contactInfo);
        }
    }
}
