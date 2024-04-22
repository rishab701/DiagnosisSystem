package com.rishab.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView profileName,profileUsername,profileEmail,profilePassword;
    TextView titleName,titleUsername;
    Button editButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName=findViewById(R.id.profileName);
        profileUsername=findViewById(R.id.profileUsername);
        profileEmail=findViewById(R.id.profileEmail);
        profilePassword=findViewById(R.id.profilePassword);
        titleName=findViewById(R.id.titleName);
        titleUsername=findViewById(R.id.titleUsername);
        editButton=findViewById(R.id.EditButton);
        showUserData();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               passUserData();
            }
        });
    }
    public void showUserData(){

        Intent intent=getIntent();

        String nameUser=intent.getStringExtra("name");
        String emailUser=intent.getStringExtra("email");
        String usernameUser=intent.getStringExtra("username");
        String passwordUser=intent.getStringExtra("password");

        titleName.setText(nameUser);
        titleUsername.setText(usernameUser);
        profileName.setText(nameUser);
        profileEmail.setText(emailUser);
        profileUsername.setText(usernameUser);
        profilePassword.setText(passwordUser);
    }
    public void passUserData(){
        String username=profileUsername.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase=reference.orderByChild("username").equalTo(username);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String usernamefromDb=snapshot.child(username).child("username").getValue(String.class);
                    String emailfromDb=snapshot.child(username).child("email").getValue(String.class);
                    String namefromDb=snapshot.child(username).child("name").getValue(String.class);
                    String passwordfromDb=snapshot.child(username).child("password").getValue(String.class);

                    Intent intent=new Intent(ProfileActivity.this,editProfile.class);
                    intent.putExtra("name",namefromDb);
                    intent.putExtra("email",emailfromDb);
                    intent.putExtra("username",usernamefromDb);
                    intent.putExtra("password",passwordfromDb);

                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}