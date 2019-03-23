package com.flamezz.creditmanagementapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectUserActivity extends AppCompatActivity{


        private DatabaseReference databaseReference;
        private TextView retriveName,retriveEmail,retriveNumber,retriveCredit;
        private Button transferCredit;
        private String name,email,phone,credit;
        protected void onCreate(Bundle savedInstance)
        {
            super.onCreate(savedInstance);
            setContentView(R.layout.activity_select_user);
            InitializeControls();
            setUpDetails();
            transferCredit();

        }

        private void InitializeControls()
        {

            databaseReference = FirebaseDatabase.getInstance().getReference("Profile");
            retriveName = findViewById(R.id.retriveName);
            retriveEmail = findViewById(R.id.retriveEmail);
            retriveNumber = findViewById(R.id.retriveNumber);
            retriveCredit = findViewById(R.id.retriveCredit);
            transferCredit = findViewById(R.id.transferCredit);

        }

        @SuppressLint("SetTextI18n")
        private void setUpDetails()
        {

             name = getIntent().getStringExtra("NAME");
             email = getIntent().getStringExtra("EMAIL");
             phone =  getIntent().getStringExtra("PHONE");
             credit = getIntent().getStringExtra("CREDIT");
            retriveName.setText("Name :\t"+name);
            retriveEmail.setText("Email:\t"+email);
            retriveNumber.setText("Phone:\t"+phone);
            retriveCredit.setText("Current Credit:\t"+credit);
        }


    public void OnClick(View view) {
       onBackPressed();
    }
    private void transferCredit()
    {
        transferCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectUserActivity.this,TransferCreditActivity.class);
                intent.putExtra("userName",name);
                intent.putExtra("userEmail",email);
                intent.putExtra("userPhone",phone);
                intent.putExtra("userCredit",credit);
                startActivity(intent);
            }
        });
    }

}
