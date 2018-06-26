package com.unmeshjoshi.homework1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<ContactInfo> contactList = new ArrayList<>();
    public static String CONTACT_LIST ="CONTACT_LIST";
    public static int LIST_REQUEST = 101;
    public static String CONTACT_NUMBER = "CONTACT_NUMBER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //contactList = (LinkedList<ContactInfo>) getIntent().getSerializableExtra(ContactActivity.CONTACT_CLASS);



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

                Intent intent = new Intent(MainActivity.this,ContactListActivity.class);
              //  contactList = (LinkedList<ContactInfo>) getIntent().getSerializableExtra(EditActivity.EDIT_CONTACT_INFO_LIST);
                intent.putExtra(CONTACT_LIST,contactList);
                intent.putExtra(CONTACT_NUMBER,200);

                startActivityForResult(intent,102);
               // finish();
            }
        });

        findViewById(R.id.buttonDisplayContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactListActivity.class);

                intent.putExtra(CONTACT_LIST,contactList);

                intent.putExtra(CONTACT_NUMBER,201);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.buttonDeleteContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // contactList = (LinkedList<ContactInfo>) getIntent().getSerializableExtra(MainActivity.CONTACT_LIST);
                Intent intent = new Intent(MainActivity.this,ContactListActivity.class);
                intent.putExtra(CONTACT_LIST,contactList);
                intent.putExtra(CONTACT_NUMBER,202);
                startActivityForResult(intent,103);
                //finish();
            }
        });

        findViewById(R.id.buttonFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
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
        else if(requestCode == 102 && resultCode == 102){
           contactList = (ArrayList<ContactInfo>) data.getExtras().getSerializable(EditActivity.EDIT_CONTACT_INFO_LIST);
          }
        else if(requestCode == 103 && resultCode == 103){
            contactList  = (ArrayList<ContactInfo>) data.getExtras().getSerializable(ContactListActivity.DELETE_CONTACT_LIST);

        }
    }


}
