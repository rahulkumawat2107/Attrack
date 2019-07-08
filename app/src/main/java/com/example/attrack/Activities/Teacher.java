package com.example.attrack.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.attrack.Helper.CustomAdapter;
import com.example.attrack.MainActivity;
import com.example.attrack.Pojo.TeacherPojo;
import com.example.attrack.R;


import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Teacher extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<TeacherPojo> arrayList = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("teacher_regData");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_teacher);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getDataFromFirebase();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_name);
        TextView navBranch = headerView.findViewById(R.id.nav_branch);
        ImageView image_profile = headerView.findViewById(R.id.imageView);

        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String url1= sharedPreferences.getString("imageUrl",null);
        String name = sharedPreferences.getString("name",null);
        String branch = sharedPreferences.getString("branch",null);
        Glide.with(Teacher.this).load(url1).into(image_profile);

        navUsername.setText(name);
        navBranch.setText(branch);
        navUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher.this, Profile.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void getDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    TeacherPojo teacherPojo = dataSnapshot1.getValue(TeacherPojo.class);

                        arrayList.add(teacherPojo);
                }
                CustomAdapter customAdapter = new CustomAdapter(Teacher.this, arrayList);
                recyclerView.setAdapter(customAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Teacher.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Teacher.this,Dashboard.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard  , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(Teacher.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_attendance) {
            // Handle the camera action
        } else if (id == R.id.nav_notice) {

        } else if (id == R.id.nav_time_table) {

        } else if (id == R.id.nav_grades) {

        } else if(id == R.id.nav_exam){
            Intent intent5 = new Intent(Teacher.this,Examinations.class);
            startActivity(intent5);
            finish();

        } else if (id == R.id.nav_teacher){
            Intent intent7 = new Intent(Teacher.this,Teacher.class);
            startActivity(intent7);
            finish();

        } else if (id == R.id.nav_holidays){
            Intent intent8 = new Intent(Teacher.this,EventHolidays.class);
            startActivity(intent8);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
