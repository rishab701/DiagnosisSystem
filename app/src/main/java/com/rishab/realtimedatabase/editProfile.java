package com.rishab.realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editProfile extends AppCompatActivity {
    EditText editName,editEmail,editUsername,editPassword;
    Button saveBtn;
    String nameUser,emailUser,usernameUser,passwordUser;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference= FirebaseDatabase.getInstance().getReference("users");

        editName=findViewById(R.id.edit_name);
        editEmail=findViewById(R.id.edit_email);
        editPassword=findViewById(R.id.edit_password);
        editUsername=findViewById(R.id.edit_username);
        saveBtn=findViewById(R.id.save_button);

        showData();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNameChanged() || isEmailChanged() || isPasswordChanged()){
                    Toast.makeText(editProfile.this,"Saved",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(editProfile.this,"No changes found!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isNameChanged(){
        if(!nameUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editName);
            nameUser=editName.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isEmailChanged(){
        if(!nameUser.equals(editEmail.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editEmail);
            emailUser=editEmail.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isPasswordChanged(){
        if(!nameUser.equals(editPassword.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editPassword);
            passwordUser=editPassword.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public void showData(){
        Intent intent=getIntent();

        nameUser=intent.getStringExtra("name");
        emailUser=intent.getStringExtra("email");
        usernameUser=intent.getStringExtra("username");
        passwordUser=intent.getStringExtra("password");

        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editPassword.setText(passwordUser);
        editUsername.setText(usernameUser);
    }
}