package com.unmeshjoshi.homework1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.LinkedList;

public class ContactActivity extends AppCompatActivity {

    public static int CAMERA_PIC_REQUEST = 100;
    Calendar myCalendar = Calendar.getInstance();
    public static final String CONTACT_CLASS = "CONTACT_LIST";

    private transient ImageButton imageButton;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        final Intent intent1 = new Intent(ContactActivity.this,MainActivity.class);
        imageButton = (ImageButton)  findViewById(R.id.imageButton);
      imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

            }
        });

      first = findViewById(R.id.FirstName);
      last = findViewById(R.id.LastName);
      comapny = findViewById(R.id.company);
      phone = findViewById(R.id.phone);
      email = findViewById(R.id.email);
      url = findViewById(R.id.url);
      address = findViewById(R.id.address);
      birthday = findViewById(R.id.birthday);
      SetDate setDate = new SetDate(birthday,this);
      nickname = findViewById(R.id.nickname);
      fbURL = findViewById(R.id.fburl);
      twitURL = findViewById(R.id.twiturl);
      skype = findViewById(R.id.skype);
      youtube = findViewById(R.id.youtube);
        final Bitmap bitmap = ((BitmapDrawable)imageButton.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        final byte[] byteArray = stream.toByteArray();


      findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ContactInfo contactInfo = new ContactInfo(byteArray,first.getText().toString(),last.getText().toString(),comapny.getText().toString(),phone.getText().toString(),email.getText().toString(),url.getText().toString(),address.getText().toString(),birthday.getText().toString(),nickname.getText().toString(),fbURL.getText().toString(),twitURL.getText().toString(),skype.getText().toString(),youtube.getText().toString());
          //   contactList.add(contactInfo);

              intent1.putExtra(CONTACT_CLASS,contactInfo);
              Log.d("firstName" , first.getText().toString());
//              intent1.putExtra("image",byteArray);
//              intent1.putExtra("firstName",first.getText().toString());
//              intent1.putExtra("lastName",last.getText().toString());
//              intent1.putExtra("phone",phone.getText().toString());
              first.setText("");
              last.setText("");
              comapny.setText("");
              phone.setText("");
              email.setText("");
              url.setText("");
              address.setText("");
              birthday.setText("");
              nickname.setText("");
              fbURL.setText("");
              twitURL.setText("");
              skype.setText("");
              youtube.setText("");

              setResult(MainActivity.LIST_REQUEST,intent1);

              finish();
          }
      });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageButton.setImageBitmap(photo);
        }
    }
}
