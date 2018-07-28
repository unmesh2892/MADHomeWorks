package com.unmeshjoshi.homework3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<ChatMessage> {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String uid;
    private FirebaseAuth auth;

    public ChatAdapter(@NonNull Context context, ArrayList<ChatMessage> resources) {
        super(context, 0, resources);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ChatMessage chatMessage = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);

        TextView message = convertView.findViewById(R.id.messageView);
        TextView messageFrom = convertView.findViewById(R.id.messageFrom);
        TextView messageTime = convertView.findViewById(R.id.messageTime);
        ImageView imageView = convertView.findViewById(R.id.displayImage);


        message.setText(chatMessage.messageText);
        messageFrom.setText(chatMessage.messageUser);
        messageTime.setText(chatMessage.messageTime);




       if (chatMessage.imageUrl != null && !chatMessage.imageUrl.isEmpty()) {
           message.setEnabled(false);
            Picasso.get().load(chatMessage.imageUrl).into(imageView);

        } else {
            imageView.setEnabled(false);

        }

        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users").child(uid).child("contacts");

        /*deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(contacts.key).removeValue();
            }
        });*/
        return convertView;
    }
}
