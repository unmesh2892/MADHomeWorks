package com.unmeshjoshi.homework1;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;

public class ContactListActivity extends AppCompatActivity {

    ArrayList<ContactInfo> contactList = new ArrayList<>();
    Long contactInfoItemPos;
    int value;
    public static String DISPLAY_CONTACT = "DISPLAY_CONTACT";
    public static String EDIT_CONTACT = "EDIT_CONTACT";
    public static String EDIT_CONTACT_LIST = "EDIT_CONTACT_LIST";
    public static String DELETE_CONTACT_LIST = "DELETE_CONTACT_LIST";
    public static String LIST_POSITION = "LIST_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list2);
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.activity_contact_list2_layout);
        contactList = (ArrayList<ContactInfo>) getIntent().getSerializableExtra(MainActivity.CONTACT_LIST);

        value = (Integer)getIntent().getIntExtra(MainActivity.CONTACT_NUMBER,0);


        //ArrayList<ContactInfo> arrayOfUsers = new ArrayList<ContactInfo>();
// Create the adapter to convert the array to views
        final ContactAdapter adapter = new ContactAdapter(this, contactList);
// Attach the adapter to a ListView
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //EditCOntact
        if(value == 200){

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    contactList = (ArrayList<ContactInfo>) getIntent().getSerializableExtra(MainActivity.CONTACT_LIST);

                    Intent intentEditContact = new Intent(ContactListActivity.this,EditActivity.class);
                    ContactInfo contactInfoEdit = contactList.get(position);

                    intentEditContact.putExtra(EDIT_CONTACT,contactInfoEdit);
                    intentEditContact.putExtra(EDIT_CONTACT_LIST,contactList);
                    intentEditContact.putExtra(LIST_POSITION,position);
                    startActivityForResult(intentEditContact,111);
                   // finish();
     }
            });

        }else if(value == 201){         //DisplayCOntact

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    contactList = (ArrayList<ContactInfo>) getIntent().getSerializableExtra(MainActivity.CONTACT_LIST);

                    Intent intentDisplay = new Intent(ContactListActivity.this,DisplayActivity.class);
                    intentDisplay.putExtra(DISPLAY_CONTACT,contactList.get(position));
                    startActivity(intentDisplay);
                    finish();



                }
            });


        }else if(value  == 202){            //DeleteContact

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    new AlertDialog.Builder(ContactListActivity.this).setTitle("Delete COntact").setMessage("Do you really want to delete this?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentDelete = new Intent(ContactListActivity.this,MainActivity.class);
                            contactList = (ArrayList<ContactInfo>) getIntent().getSerializableExtra(MainActivity.CONTACT_LIST);
                            contactList.remove(position);
                            adapter.notifyDataSetChanged();
                            intentDelete.putExtra(DELETE_CONTACT_LIST,contactList);
                            setResult(103,intentDelete);
                            finish();



                        }
                    })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_menu_delete)
                            .show();


                }
            });

        }


//        String s = getIntent().getStringExtra("firstName");
//
//        Log.d("first" , ""+s);
//
//        byte[] byteArray = getIntent().getByteArrayExtra("image");
//        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//
//        ImageView imageView = findViewById(R.id.imageView);
//        TextView textViewFirst = findViewById(R.id.textViewFirst);
//        TextView textViewLast = findViewById(R.id.textLast);
//        TextView textViewPhone = findViewById(R.id.textNumber);
//
//        imageView.setImageBitmap(bmp);
//
//        textViewFirst.setText(getIntent().getStringExtra("firstName"));
//        textViewLast.setText(getIntent().getStringExtra("lastName"));
//        textViewPhone.setText(getIntent().getStringExtra("phone"));

//        LayoutInflater inflate = getLayoutInflater();
//        View myLayout = inflate.inflate(R.layout.activity_contact_exp,mainLayout,false);
//
//        ContactInfo contactInfo = new ContactInfo();
//
//        for(int i =0;i<contactList.size();i++)
//        {
//           contactInfo = contactList.get(i);
//            String name = "";
//            String firstName = contactInfo.getFirst();
//            String lastname = contactInfo.getLast();
//            name = firstName + " " + lastname;
//            String number = contactInfo.getPhone();
//            byte[] image = contactInfo.getImageButton();
//            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
//
//           TextView textViewName = myLayout.findViewById(R.id.textViewName2);
//            TextView textViewPhone = myLayout.findViewById(R.id.textViewNumber);
//            ImageView imageView = myLayout.findViewById(R.id.imageView);
//           textViewName.setText(name.toString());
//           textViewPhone.setText(number.toString());
//           imageView.setImageBitmap(bitmap);
//
//            mainLayout.addView(myLayout);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 111){
            Log.d("In contactlist activity","In contactlist activity");
            setResult(102,data);
            finish();
        }
    }
}

