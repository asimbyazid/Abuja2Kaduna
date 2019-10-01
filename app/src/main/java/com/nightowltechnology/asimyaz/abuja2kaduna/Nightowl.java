package com.nightowltechnology.asimyaz.abuja2kaduna;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

public class Nightowl extends AppCompatActivity {
    private Button btn,btn_web;
    private ImageView btn2;
    final Intent callIntent = new Intent(Intent.ACTION_CALL);
    private Button web, contact,exit, fb,nightowl, insta,twitter;

    private CollapsingToolbarLayout collapsingToolbarLayout=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nightowl);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        web = (Button ) findViewById(R.id.nightowl_web);
        fb = (Button) findViewById(R.id.nightowl_fb);
        twitter = (Button) findViewById(R.id.nightowl_twitter);
        contact = (Button) findViewById(R.id.nightowl_contact);
        insta = (Button) findViewById(R.id.nightowl_insta);
        nightowl = (Button) findViewById(R.id.nightowl_apps);


        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://nightowl.com.ng")));
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/nightowltechnology/?ref=bookmarks")));
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:08034135061"));
                startActivity(intent);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/nightowltech/")));

            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com/nightowltech/")));
            }
        });
        nightowl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=7357338111791284390")));
            }
        });
      /*  exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });*/
    }
}