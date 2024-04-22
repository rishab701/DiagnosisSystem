package com.rishab.realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rishab.realtimedatabase.R;
import com.rishab.realtimedatabase.helperClass;
import com.rishab.realtimedatabase.login;
import com.rishab.realtimedatabase.splashScreen;

public class signup extends AppCompatActivity {

    EditText signUpName,signUpEmail,signUpPassword,signUpUsername;
    TextView LoginRedirectedText;
    Button signUpBtn;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        signUpName=findViewById(R.id.signup_name);
        signUpEmail=findViewById(R.id.signup_email);
        signUpUsername=findViewById(R.id.signup_username);
        signUpPassword=findViewById(R.id.signup_password);
        signUpBtn=findViewById(R.id.signup_button);
        LoginRedirectedText=findViewById(R.id.signUp_Text);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database= FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String name=signUpName.getText().toString();
                String username=signUpUsername.getText().toString();
                String email=signUpEmail.getText().toString();
                String password=signUpPassword.getText().toString();

                helperClass helperclass=new helperClass(name,email,username,password);
                reference.child(username).setValue(helperclass);

                Toast.makeText(signup.this, "You have signed Up successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(signup.this, login.class);
                startActivity(intent);

            }
        });

        LoginRedirectedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });
    }
}

