package com.rishab.realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class OtherActivity extends AppCompatActivity {
    CardView cardView;
    DrawerLayout drawerLayout;
    ImageView hamburger_icon;
    NavigationView navigationView;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        cardView = findViewById(R.id.diagnostics);
        drawerLayout = findViewById(R.id.drawer_layout);
        hamburger_icon = findViewById(R.id.hamburger_icon);
        navigationView = findViewById(R.id.nav_view);

        hamburger_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(menuItem ->
                onMenuItemSelected(menuItem)
        );
    }
    private boolean onMenuItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.nav_logout){
            performLogout();
            return true;
        }
        else{
            return false;
        }
    }

    private void performLogout() {
        Intent intent = new Intent(OtherActivity.this, login.class);
        startActivity(intent);
        finish(); // Optional: Close the current activity after starting the new one
    }
}
