package com.flamezz.creditmanagementapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewUserActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ArrayList<Profile> arrayList;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_user_view);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        InitializeControls();
    }

    private void InitializeControls()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Profile");
        retrieveUsers();
    }

    private void retrieveUsers()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot details:dataSnapshot.getChildren())
                {
                       Profile profile = details.getValue(Profile.class);
                        arrayList.add(profile);
                }
                ProfileAdapter adapter = new ProfileAdapter(arrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    }

