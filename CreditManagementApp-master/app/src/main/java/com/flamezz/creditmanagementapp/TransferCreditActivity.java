package com.flamezz.creditmanagementapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TransferCreditActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private ArrayList<Profile> arrayList;
    private AlertDialog.Builder alertdialog;
    private String userName,userEmail,userPhone,userCredit;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_transfer_credit);
        InitializeControls();
        transferCredit();
    }

    private void InitializeControls() {
        userName = getIntent().getStringExtra("userName");
        userEmail= getIntent().getStringExtra("userEmail");
        userPhone = getIntent().getStringExtra("userPhone");
        userCredit= getIntent().getStringExtra("userCredit");
        recyclerView = findViewById(R.id.myRecyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference("Profile");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alertdialog = new AlertDialog.Builder(this);
    }

    private void transferCredit()
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
                TransferAdapter adapter = new TransferAdapter(arrayList, new OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(final String credit, final String name,final String email,final String phone) {
                        View view = getLayoutInflater().inflate(R.layout.alertdialog_transfercredit,null);
                        final EditText enterCreditAmount = view.findViewById(R.id.enterCreditAmount);
                        TextView transfertoUser = view.findViewById(R.id.transfertoUser);
                        transfertoUser.setText("Transferring Credit to\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+name);
                        Button trasnferToUser = view.findViewById(R.id.trasnferToUser);
                        trasnferToUser.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String creditamount = enterCreditAmount.getText().toString();
                                int credit_amount = Integer.parseInt(creditamount);
                                int balance_of_sender,balance_of_receiver;
                                int sender_credit = Integer.parseInt(userCredit);
                                int receiver_credit = Integer.parseInt(credit);
                                    if (credit_amount == 0 || sender_credit <= credit_amount ) {
                                        enterCreditAmount.setError("Error Transfering Credit");
                                    }
                                    else {
                                        balance_of_sender = sender_credit - credit_amount;
                                        balance_of_receiver = credit_amount + receiver_credit;
                                        databaseReference = FirebaseDatabase.getInstance().getReference("Profile").child(userName);//sender database update
                                        String balance_of_sender_new = String.valueOf(balance_of_sender);
                                        Profile profile = new Profile(userName,userEmail,userPhone,balance_of_sender_new);
                                        databaseReference.setValue(profile);

                                        databaseReference = FirebaseDatabase.getInstance().getReference("Profile").child(name);//receiver database update
                                        String balance_of_receiver_new = String.valueOf(balance_of_receiver);
                                        Profile profile1 = new Profile(name,email,phone,balance_of_receiver_new);
                                        databaseReference.setValue(profile1);
                                            Toast.makeText(getApplicationContext(),"Credit Transfered Successfully",Toast.LENGTH_SHORT).show();
                                           finish();
                                    }

                            }
                        });
                        alertdialog.setView(view);
                        alertdialog.create();
                        alertdialog.show();
                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}
