package com.flamezz.creditmanagementapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeControls();
    }
    private void InitializeControls()
    {
        Button viewButton;
        viewButton = findViewById(R.id.viewUsersButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewUserActivity.class);
                startActivity(intent);
            }
        });

    }
}
