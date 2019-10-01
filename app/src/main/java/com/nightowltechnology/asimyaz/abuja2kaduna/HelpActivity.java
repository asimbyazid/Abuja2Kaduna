package com.nightowltechnology.asimyaz.abuja2kaduna;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HelpActivity extends AppCompatActivity {

    private DatabaseReference p1,p2;
    private TextView myPrice1,myPrice2;
    private DatabaseReference r,g,y;
    private ImageView colorRed,colorGreen,colorYellow;
    private TextView gText,rText,yText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        colorRed = (ImageView) findViewById(R.id.red);
        colorGreen = (ImageView) findViewById(R.id.green);
        colorYellow = (ImageView) findViewById(R.id.yellow);


        gText = (TextView) findViewById(R.id.gInfo);
        rText = (TextView) findViewById(R.id.rInfo);
        yText = (TextView) findViewById(R.id.yInfo);

       /*
        r = FirebaseDatabase.getInstance().getReference().child("red");


        r.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String color = dataSnapshot.child("img").getValue().toString();
                String text = dataSnapshot.getValue().toString();
                //Picasso.with().load(color).resize(10,10).into(colorRed);

                rText.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


/*


       g = FirebaseDatabase.getInstance().getReference().child("green");


        g.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String color = dataSnapshot.child("img").getValue().toString();
                String text = dataSnapshot.getValue().toString();
                //Picasso.with().load(color).resize(10,10).into(colorRed);

                gText.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        y = FirebaseDatabase.getInstance().getReference().child("yellow");


        y.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String color = dataSnapshot.child("img").getValue().toString();
                String text = dataSnapshot.getValue().toString();
                //Picasso.with().load(color).resize(10,10).into(colorRed);

                yText.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
