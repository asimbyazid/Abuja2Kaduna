package com.nightowltechnology.asimyaz.abuja2kaduna;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.xml.datatype.Duration;

public class AddressActivity extends AppCompatActivity {

    private DatabaseReference dRef1,dRef2,dRef3,dRef4,dRef5,dRef6;
    private TextView dest1,dest2,dest3,dest4,dest5,dest6;
    private Button b1,b2,b3,b4,b5,b6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        b1 = (Button) findViewById(R.id.dest1);
        b2 = (Button) findViewById(R.id.dest2);
        b3= (Button) findViewById(R.id.dest3);
        b4 = (Button) findViewById(R.id.dest4);
        b5 = (Button) findViewById(R.id.dest5);



        dest1 = (TextView) findViewById(R.id.address_idu);
        dest2 = (TextView) findViewById(R.id.address_kubwa);
        dest3 = (TextView) findViewById(R.id.address_jere);
        dest4 = (TextView) findViewById(R.id.address_rijana);
        dest5 = (TextView) findViewById(R.id.rigasa);




        dRef1 = FirebaseDatabase.getInstance().getReference().child("rigasa");
        dRef2 = FirebaseDatabase.getInstance().getReference().child("kubwa");
        dRef3 = FirebaseDatabase.getInstance().getReference().child("jere");
        dRef4 = FirebaseDatabase.getInstance().getReference().child("rijana");
        dRef5 = FirebaseDatabase.getInstance().getReference().child("rigasa");



        dRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String txt = dataSnapshot.getValue().toString();
                dest1.setText(txt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

 dRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String txt = dataSnapshot.getValue().toString();
                dest2.setText(txt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        dRef3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String txt = dataSnapshot.getValue().toString();
                dest3.setText(txt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        dRef4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String txt = dataSnapshot.getValue().toString();
                dest4.setText(txt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        dRef5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String txt = dataSnapshot.getValue().toString();
                dest5.setText(txt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.ng/maps/place/Idu+Railway+Station/@9.0463693,7.3398985,17z/data=!3m1!4b1!4m5!3m4!1s0x104e76a33d10fe5b:0xb410e1c78df2907b!8m2!3d9.0463693!4d7.3420872")));

            }
        });




        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.ng/maps/place/Train+Station,+Kubwa+terminal/@9.1633771,7.3222823,17z/data=!4m8!1m2!2m1!1skubwa+train+station!3m4!1s0x104dd91e98edd5e1:0xb62b4241679b6434!8m2!3d9.1641552!4d7.3251082")));

            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("")));
                Toast.makeText(getApplicationContext(),"Jere information is currently not available",Toast.LENGTH_LONG ).show();

            }
        });



        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.ng/maps/place/Rijana+Train+Station/@10.0488827,7.373818,17z/data=!3m1!4b1!4m5!3m4!1s0x104d13b42acf67df:0xb2b719ceb07fc68!8m2!3d10.0488827!4d7.3760067")));

            }
        });


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.ng/maps/place/Rigasa+Railway+Station/@10.5478375,7.3530413,17z/data=!3m1!4b1!4m5!3m4!1s0x11b2cb59a429235f:0x8b308c9f9e142c95!8m2!3d10.5478375!4d7.35523")));

            }
        });
    }
}
