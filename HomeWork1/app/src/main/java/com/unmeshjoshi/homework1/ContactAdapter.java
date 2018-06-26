package com.unmeshjoshi.homework1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<ContactInfo> {
    public ContactAdapter(Context context, ArrayList<ContactInfo> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ContactInfo contactInfo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_contact_exp, parent, false);
        }

        // Lookup view for data population
        byte[] image = contactInfo.getImageButton();

        TextView name = (TextView) convertView.findViewById(R.id.textViewName2);
        TextView phone = (TextView) convertView.findViewById(R.id.textViewNumber);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        // Populate the data into the template view using the data object
        name.setText(contactInfo.getFirst() + " " + contactInfo.getLast());
        phone.setText(contactInfo.getPhone());

        if(image.length == 1){
            imageView.setImageResource(R.drawable.ic_action_name);
        }else {

            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bitmap);
        }
        // / Return the completed view to render on screen
        return convertView;
    }
}

