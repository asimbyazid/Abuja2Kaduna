package com.nightowltechnology.asimyaz.abuja2kaduna;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PricingActivity extends AppCompatActivity {
    private DatabaseReference p1,p2;
    private TextView myPrice1,myPrice2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        myPrice1 = (TextView) findViewById(R.id.price1);
        myPrice2 = (TextView) findViewById(R.id.price2);

        p1 = FirebaseDatabase.getInstance().getReference().child("price1");
        p2 = FirebaseDatabase.getInstance().getReference().child("price2");


        p1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String price = dataSnapshot.getValue().toString();
                myPrice1.setText(price);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        p2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String price = dataSnapshot.getValue().toString();
                myPrice2.setText(price);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
