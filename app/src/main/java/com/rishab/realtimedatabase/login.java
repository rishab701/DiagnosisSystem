package com.rishab.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {
    EditText loginUsername,loginPassword;
    Button loginButton;
    TextView SignupRedirectedText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginUsername=findViewById(R.id.login_username);
        loginPassword=findViewById(R.id.login_password);
        loginButton=findViewById(R.id.login_button);
        SignupRedirectedText=findViewById(R.id.LoginRedirectedText);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUsername() | !validatePassword()){
                }else{
                    CheckUser();
                }
            }
        });

        SignupRedirectedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateUsername(){
        String val=loginUsername.getText().toString();
        if(val.isEmpty()){
            loginUsername.setError("Username cannot be empty");
            return false;
        }
        else{
            loginUsername.setError(null);
            return true;
        }
    }
    public Boolean validatePassword(){
        String val=loginPassword.getText().toString();
        if(val.isEmpty()){
            loginPassword.setError("Username cannot be empty");
            return false;
        }
        else{
            loginPassword.setError(null);
            return true;
        }
    }
    public void CheckUser(){
        String username=loginUsername.getText().toString().trim();
        String password=loginPassword.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase=reference.orderByChild("username").equalTo(username);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    loginUsername.setError(null);
                    String passwordFromDb=snapshot.child(username).child("password").getValue(String.class);
                    if(passwordFromDb.equals(password)) {
                        loginUsername.setError(null);

                        //Pass the data from intent

                        String namefromDb=snapshot.child(username).child("name").getValue(String.class);
                        String emailfromDb=snapshot.child(username).child("email").getValue(String.class);
                        String usernamefromDb=snapshot.child(username).child("username").getValue(String.class);

                        Intent intent=new Intent(login.this,OtherActivity.class);

                        intent.putExtra("name",namefromDb);
                        intent.putExtra("username",usernamefromDb);
                        intent.putExtra("email",emailfromDb);
                        intent.putExtra("password",passwordFromDb);

                        startActivity(intent);
                    }
                    else{
                        loginPassword.setError("Invalid credentials");
                        loginPassword.requestFocus();
                    }
                }
                else{
                    loginUsername.setError("User doesnot exist");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}