package com.unmeshjoshi.homework3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button signUp,cancel;
    private EditText firstName,lastName,username,password;


    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.buttonSignUp);
        cancel = findViewById(R.id.buttonCancel);

        firstName = findViewById(R.id.FrstNameCon);
        lastName = findViewById(R.id.PhoneCon);
        username = findViewById(R.id.UserNameCon);
        password = findViewById(R.id.editTextPassword);




        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String pass = password.getText().toString();
                String firstName1 = firstName.getText().toString();
                String lastName1 = lastName.getText().toString();
                final String fullName = firstName1+" " +lastName1;


                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            mReference = FirebaseDatabase.getInstance().getReference();

                            String uid =   auth.getUid();
                            DatabaseReference mRef = mReference.child("users").child(uid).child("name");
                            mRef.setValue(fullName);

                            startActivity(new Intent(SignUp.this, ChatActivity.class));
                            finish();
                        }
                    }
                });


            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
