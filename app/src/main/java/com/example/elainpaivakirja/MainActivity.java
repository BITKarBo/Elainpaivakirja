package com.example.elainpaivakirja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Komponentit
        Button nappi = (Button) findViewById(R.id.nabbi);
        TextView teksti = (TextView) findViewById(R.id.WelcomeText);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);



        //  Tapahtumat
        nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teksti.setText("Moroooo");
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }
}