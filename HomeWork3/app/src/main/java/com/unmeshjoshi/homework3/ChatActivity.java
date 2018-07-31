package com.unmeshjoshi.homework3;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.text.format.DateFormat;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ChatActivity extends AppCompatActivity {
    private ArrayList<ChatMessage> chatMessageList = new ArrayList<>();
    TextView newComment,userName;
    ImageView imageFromGallery,sendMessage,logout;
    private  int GALLERY=1;
    String urlId="";
    String userID;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    DatabaseReference databaseReference,dbRef;
    ChatAdapter ctAdapter;
    FirebaseListAdapter<ChatMessage> chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        imageFromGallery = findViewById(R.id.AddImage);
        newComment = findViewById(R.id.AddComment);
        sendMessage = findViewById(R.id.SendMessage);
        logout = findViewById(R.id.logout);
        userName = findViewById(R.id.UserName);



            dbRef =   databaseReference.child("users").child(userID);

            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild("name")){
                        userName.setText(dataSnapshot.child("name").getValue().toString());

                    }
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ChatActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            displayChatMessage();

        }
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChatMessage chatMessage = new ChatMessage();

                chatMessage.setMessageText(newComment.getText().toString());
                chatMessage.setMessageUser(userName.getText().toString());

                   /* chatMessage.messageText=newComment.getText().toString();
                    chatMessage.messageUser=userName.getText().toString();*/

                    if(urlId.isEmpty() || urlId.equals(null)){
                        DatabaseReference mReference =  databaseReference.child("users").child(userID).child("messages");

                       // mReference.setValue(chatMessage);


                        String key = mReference.push().getKey();
                       chatMessage.setKey(key);
                        mReference.child(key).setValue(chatMessage);

                        newComment.setText("");
                        displayChatMessage();
                    }else{
                        chatMessage.setImageUrl(urlId);
                       // chatMessage.imageUrl=urlId;
                        DatabaseReference mReference =  databaseReference.child("users").child(userID).child("messages");
                       // mReference.setValue(chatMessage);


                        String key = mReference.push().getKey();
                        chatMessage.setKey(key);
                        chatMessage.key= key;

                        mReference.child(key).setValue(chatMessage);
                        newComment.setText("");
                        displayChatMessage();
                    }
           }
        });

        imageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhotoFromGallery();
            }
        });


    }

    private void displayChatMessage() {
        final ListView listView = findViewById(R.id.listView);
        Query query = databaseReference.child("users").child(userID).child("messages");


        FirebaseListOptions<ChatMessage> options = new FirebaseListOptions.Builder<ChatMessage>()
                .setQuery(query,ChatMessage.class).setLayout(R.layout.list_view).build();


        chatAdapter = new FirebaseListAdapter<ChatMessage>(options) {
            @Override
            protected void populateView(View v,final ChatMessage model, int position) {
                TextView textMessage = v.findViewById(R.id.messageView);
                TextView textUser = v.findViewById(R.id.messageFrom);
                TextView textDate = v.findViewById(R.id.messageTime);
                ImageView chatReply = v.findViewById(R.id.reply);
                ImageView delete = v.findViewById(R.id.delete);
                ImageView pics = v.findViewById(R.id.displayImage);

                if(model.imageUrl==null){
                    pics.setEnabled(false);
                }else{
                    pics.setVisibility(View.VISIBLE);
                    Picasso.get().load(model.imageUrl).into(pics);
                }

                chatReply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent intent = new Intent(ChatActivity.this,Reply.class);
                    intent.putExtra("KEY",model.getKey());
                    startActivity(intent);
                    finish();

                    }
                });

               delete.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       databaseReference.child("users").child(userID).child("messages").removeValue();
                   }
               });
                textMessage.setText(model.getMessageText());
                textUser.setText(model.getMessageUser());
                textDate.setText(new PrettyTime().format(new Date(System.currentTimeMillis())));


            }

        };
        chatAdapter.startListening();
        listView.setAdapter(chatAdapter);
    }

    public void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();

                firebaseStorage = FirebaseStorage.getInstance();
                storageReference = firebaseStorage.getReference();
                String path = "images/" + UUID.randomUUID().toString();
                final StorageReference ref = storageReference.child(path);
                ref.putFile(contentURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d("image", "uploaded from gallery");
                        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                urlId = task.getResult().toString();
                               // displayChatMessage();
                            }
                        });

                    }
                });


            }
        }
    }


}
