package com.nightowltechnology.asimyaz.abuja2kaduna;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AdView mAdView1,mAdView2;
    RecyclerView myRoutes;
    private DrawerLayout drawer;
    private boolean mUserSawDrawer = false;
    private static final String FIRST_TIME = "first_time";
    // json object response url
    // json object response url
    private static String TAG = "MAIN";
    SharedPreferences.Editor editor = null;

    SharedPreferences prefs;
    private String urlJsonObj = "http://api.openweathermap.org/data/2.5/find?q=Abuja&units=metric&appid=4596c6a4634fdf973bec510065eabc47";

    private TextView txtResponse;

    private DatabaseReference mDatabase,statusRef;
    // Progress dialog
    private ProgressDialog pDialog;

    private TextView loc,weather,temo,date,status;


    // temporary string to show the parsed response
    private String jsonResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int dayOfYr = cal.get(Calendar.DAY_OF_YEAR);
        int dayOfWk = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);

        statusRef = FirebaseDatabase.getInstance().getReference().child("status");

        prefs = this.getSharedPreferences("mySwitch", Context.MODE_PRIVATE);
        String value = prefs.getString("me","");

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAdView1 = (AdView) findViewById(R.id.adView);
        //mAdView2 = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest1 = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // An example device ID
                .build();
        //AdRequest adRequest2 = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
        //        .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // An example device ID
        //        .build();
        mAdView1.loadAd(adRequest1);
        //mAdView2.loadAd(adRequest2);

        if(value.isEmpty())
        {
            urlJsonObj = "http://api.openweathermap.org/data/2.5/find?q=Abuja&units=metric&appid=4596c6a4634fdf973bec510065eabc47";
        }
        else
        {
            urlJsonObj = value;
        }

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        makeJsonObjectRequest();

        statusRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String myStatus;
                myStatus = dataSnapshot.getValue().toString();

                status = (TextView) findViewById(R.id.status);

                status.setText(myStatus);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Date now = new Date();

        SimpleDateFormat day = new SimpleDateFormat("E");
        SimpleDateFormat year = new SimpleDateFormat("y");
        SimpleDateFormat m = new SimpleDateFormat("M");

        String abvDay = String.valueOf(day.format(now));
        String abvYear = String.valueOf(year.format(now));
        String abvMonth = String.valueOf(m.format(now));

        String dayOfMonthStr = String.valueOf(dayOfMonth);

        temo = (TextView)  findViewById(R.id.temp);
        weather = (TextView)  findViewById(R.id.weather);
        loc = (TextView)  findViewById(R.id.loc);
        date = (TextView)  findViewById(R.id.date);

        date.setText(abvDay+"/"+dayOfMonthStr+"/"+abvMonth+"/"+abvYear);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


*/
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("destination");

        mDatabase.keepSynced(true);
        String s =""+  mDatabase;
        Log.d("mDatabase is: ", "onCreate: "+s);
        myRoutes = (RecyclerView) findViewById(R.id.listId);
        myRoutes.hasFixedSize();
        myRoutes.setLayoutManager( new LinearLayoutManager(this));


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        // making json object request
        makeJsonObjectRequest();


        // making json array request


        if(isNetworkConnected()) {
        }
        else{
            Snackbar.make(findViewById(R.id.coordinator_main), "No internet please connect!!", Snackbar.LENGTH_LONG).show();

        }
        if (!didUserSeeDrawer()) {
            showDrawer();
            markDrawerSeen();
        } else {
            hideDrawer();
        }


    }

    private boolean didUserSeeDrawer() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = sharedPreferences.getBoolean(FIRST_TIME, false);
        return mUserSawDrawer;
    }

    private void markDrawerSeen() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = true;
        sharedPreferences.edit().putBoolean(FIRST_TIME, mUserSawDrawer).apply();
    }


    private void showDrawer() {
        drawer.openDrawer(GravityCompat.START);
    }

    private void hideDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }




    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void makeJsonObjectRequest() {

        //showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    JSONArray list = response.getJSONArray("list");
                    String main = list.getJSONObject(0).getString("name");
                    loc.setText(main);
                    Log.e("Name",main);
                    double temp = Double.parseDouble(list.getJSONObject(0).getJSONObject("main").getString("temp"));
                    int newTemp = (int) temp;

                    Log.e("Temp",""+temp);
                    temo.setText(""+newTemp);
                    String desc = list.getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description");
                    Log.e("desc",desc);
                    weather.setText(desc);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    /**
     * Method to make json array request where response starts with [
     * */


    /**
     * Method to make json array request where response starts with [
     * */


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ImageAdapter,MDestinationViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter <ImageAdapter, MDestinationViewHolder>(
                ImageAdapter.class,
                R.layout.custom_row,
                MDestinationViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(MDestinationViewHolder viewHolder, ImageAdapter model, int position) {
                viewHolder.setArrivalFirst(model.getArrivalFirst());
                viewHolder.setArrivalSecond(model.getArrivalSecond());
                viewHolder.setDepartureFirst(model.getDepartureFirst());
                viewHolder.setDepartureSecond(model.getDepartureSecond());
                viewHolder.setDestination(model.getDestination());
                viewHolder.setMyRoute1(model.getMyRoute1());
                viewHolder.setMyRoute2(model.getMyRoute2());
                viewHolder.setMyRoute3(model.getMyRoute3());
                viewHolder.setMyRoute4(model.getMyRoute4());
                viewHolder.setStatusImage(getApplicationContext(),model.getStatusImage());


            }
        };
        myRoutes.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MDestinationViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public MDestinationViewHolder(View itemView) {
            super(itemView);


            mView = itemView;
        }

        public void setArrivalFirst(String x)
        {
            TextView arv1 = (TextView) mView.findViewById(R.id.at1);
            arv1.setText(x);
        }


        public void setArrivalSecond(String x)
        {
            TextView arv2 = (TextView) mView.findViewById(R.id.at2);
            arv2.setText(x);
        }

        public void setDepartureFirst(String x)
        {
            TextView dep1 = (TextView) mView.findViewById(R.id.dt1);
            dep1.setText(x);
        }



        public void setDepartureSecond(String x)
        {
            TextView dep2 = (TextView) mView.findViewById(R.id.dt2);
            dep2.setText(x);
        }


        public void setDestination(String x)
        {
            TextView dest = (TextView) mView.findViewById(R.id.destination);
            dest.setText(x);
        }

        public void setMyRoute1(String x)
        {
            TextView dest = (TextView) mView.findViewById(R.id.d1);
            dest.setText(x);
        }

        public void setMyRoute2(String x)
        {
            TextView dest = (TextView) mView.findViewById(R.id.d2);
            dest.setText(x);
        }

        public void setMyRoute3(String x)
        {
            TextView dest = (TextView) mView.findViewById(R.id.d3);
            dest.setText(x);
        }

        public void setMyRoute4(String x)
        {
            TextView dest = (TextView) mView.findViewById(R.id.d4);
            dest.setText(x);
        }


        public void setStatusImage(Context ctx, String m_image)
        {
            ImageView img = (ImageView) mView.findViewById(R.id.img);
            Picasso.with(ctx).load(m_image).resize(10,10).into(img);

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            this.finish();
        }
        if (id == R.id.action_switch_now) {
            prefs =  getSharedPreferences("mySwitch", Context.MODE_APPEND);
            editor = prefs.edit();
            if(urlJsonObj.equals("http://api.openweathermap.org/data/2.5/find?q=Kaduna&units=metric&appid=4596c6a4634fdf973bec510065eabc47"))
            {
                editor.putString("me","http://api.openweathermap.org/data/2.5/find?q=Abuja&units=metric&appid=4596c6a4634fdf973bec510065eabc47");
            }
            else
            {
                editor.putString("me","http://api.openweathermap.org/data/2.5/find?q=Kaduna&units=metric&appid=4596c6a4634fdf973bec510065eabc47");
            }
            editor.apply();

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_price) {

            startActivity(new Intent(MainActivity.this,PricingActivity.class));

        } else if (id == R.id.nav_news) {
            startActivity(new Intent(MainActivity.this,Announcements.class));

        } else if (id == R.id.nav_help) {
            startActivity(new Intent(MainActivity.this,HelpActivity.class));

        }else if (id == R.id.nav_address) {
            startActivity(new Intent(MainActivity.this,AddressActivity.class));

        } else if (id == R.id.nav_developer) {
            startActivity(new Intent(MainActivity.this,Nightowl.class));

        } else if (id == R.id.nav_share) {

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Never missed the train, download the app and get the  latest update on Abuja2Kaduna train" +
                    " click on the link to download the app now!! https://play.google.com/store/apps/details?id=com.nightowltechnology.asimyaz.abuja2kaduna");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Abuja 2 Kaduna App");
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        } else if (id == R.id.nav_rateus) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.nightowltechnology.asimyaz.abuja2kaduna")));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
