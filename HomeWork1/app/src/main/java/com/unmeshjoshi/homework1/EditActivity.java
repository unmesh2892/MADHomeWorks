package com.unmeshjoshi.homework1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import static com.unmeshjoshi.homework1.ContactListActivity.*;

public class EditActivity extends AppCompatActivity {

    ArrayList<ContactInfo> contactInfoArrayList;
    public static String EDIT_CONTACT_INFO = "EDIT_CONTACT_INFO";
    public static String EDIT_CONTACT_INFO_LIST = "EDIT_CONTACT_INFO_LIST";
    public static int CAMERA_PIC_REQUEST_EDIT = 100;
    int position;

    private transient ImageButton imageButtonEdit;
    EditText first;
    EditText last;
    EditText comapny;
    EditText phone;
    EditText email;
    EditText url;
    EditText address;
    EditText birthday;
    EditText nickname;
    EditText fbURL;
    EditText twitURL;
    EditText skype;
    EditText youtube;
    String birthdays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final ContactInfo contactInfoEdit = (ContactInfo) getIntent().getSerializableExtra(EDIT_CONTACT);
         contactInfoArrayList = (ArrayList<ContactInfo>) getIntent().getSerializableExtra(EDIT_CONTACT_LIST);
         position = (Integer) getIntent().getIntExtra(ContactListActivity.LIST_POSITION,0);

        imageButtonEdit = findViewById(R.id.imageButtonEdit);
        first = findViewById(R.id.FirstNameEdit);
        last = findViewById(R.id.LastNameEdit);
        comapny = findViewById(R.id.companyEdit);
        phone = findViewById(R.id.phoneEdit);
        email = findViewById(R.id.emailEdit);
        url = findViewById(R.id.urlEdit);
        address = findViewById(R.id.addressEdit);
        birthday = findViewById(R.id.birthdayEdit);
        //SetDate setDate = new SetDate(birthday,this);
        nickname = findViewById(R.id.nicknameEdit);
        fbURL = findViewById(R.id.fburlEdit);
        twitURL = findViewById(R.id.twiturlEdit);
        skype = findViewById(R.id.skypeEdit);
        youtube = findViewById(R.id.youtubeEdit);

        first.setText(contactInfoEdit.getFirst());

        last.setText(contactInfoEdit.getLast());
        comapny.setText(contactInfoEdit.getCompany());
        phone.setText(contactInfoEdit.getPhone());
        email.setText(contactInfoEdit.getEmail());
        birthday.setText(contactInfoEdit.getBirthday()) ;
        SetDate setDate = new SetDate(birthday,this);
        nickname.setText(contactInfoEdit.getNickname());
        url.setText(contactInfoEdit.getUrl());
        address.setText(contactInfoEdit.getAddress());
        fbURL.setText(contactInfoEdit.getFbURL());
        twitURL.setText(contactInfoEdit.getTwitterURL());
        skype.setText(contactInfoEdit.getSkype());
        youtube.setText(contactInfoEdit.getYoutube());

       //image getting
        byte[] image = contactInfoEdit.getImageButton();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        imageButtonEdit.setImageBitmap(bitmap);


        imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST_EDIT);

            }
        });

       /* final Bitmap bitmapEdit = ((BitmapDrawable)imageButtonEdit.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapEdit.compress(Bitmap.CompressFormat.PNG, 100, stream);
        final byte[] byteArray = stream.toByteArray();
*/


      //  contactInfoEdit = new ContactInfo(byteArray,first.getText().toString(),last.getText().toString(),comapny.getText().toString(),phone.getText().toString(),email.getText().toString(),url.getText().toString(),address.getText().toString(),birthday.getText().toString(),nickname.getText().toString(),fbURL.getText().toString(),twitURL.getText().toString(),skype.getText().toString(),youtube.getText().toString());



        findViewById(R.id.buttonSaveEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEdit =  new Intent();

                final Bitmap bitmap = ((BitmapDrawable)imageButtonEdit.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                final byte[] byteArray = stream.toByteArray();


                contactInfoEdit.setImageButton(byteArray);
                contactInfoEdit.setFirst(first.getText().toString());
                contactInfoEdit.setLast(last.getText().toString());
                contactInfoEdit.setCompany(comapny.getText().toString());
                contactInfoEdit.setPhone(phone.getText().toString());
                contactInfoEdit.setEmail(email.getText().toString());
                contactInfoEdit.setUrl(url.getText().toString());
                contactInfoEdit.setAddress(address.getText().toString());
                contactInfoEdit.setBirthday(birthday.getText().toString());
                contactInfoEdit.setNickname(nickname.getText().toString());
                contactInfoEdit.setFbURL(fbURL.getText().toString());
                contactInfoEdit.setTwitterURL(twitURL.getText().toString());
                contactInfoEdit.setSkype(skype.getText().toString());
                contactInfoEdit.setYoutube(youtube.getText().toString());

                contactInfoArrayList.set(position,contactInfoEdit);
               Log.d("EDIT",contactInfoEdit.getFirst());
                Log.d("EDIT",contactInfoArrayList.get(position).getFirst());

                intentEdit.putExtra(EDIT_CONTACT_INFO,contactInfoEdit);
                intentEdit.putExtra(EDIT_CONTACT_INFO_LIST,contactInfoArrayList);
                setResult(111,intentEdit);
                Log.d("set edit activity","edit activity set");
                finish();
            }
        });











    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST_EDIT) {
            if(data.getExtras() != null){
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageButtonEdit.setImageBitmap(photo);

            }else{
                imageButtonEdit.setImageResource(R.drawable.ic_action_name);
            }
        }
    }
}
