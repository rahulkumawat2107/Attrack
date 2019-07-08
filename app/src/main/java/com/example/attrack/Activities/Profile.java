package com.example.attrack.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.attrack.MainActivity;
import com.example.attrack.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView viewName,viewNumber,viewEmail,viewRollno,viewId,viewYear,viewBranch;
    ImageView dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewName=findViewById(R.id.profile_name);
        viewNumber=findViewById(R.id.profile_number);
        viewEmail=findViewById(R.id.profile_mail);
        viewRollno=findViewById(R.id.profile_rollno);
        viewId=findViewById(R.id.profile_clg_id);
        viewYear=findViewById(R.id.profile_year);
        viewBranch=findViewById(R.id.profile_branch);
        dp = findViewById(R.id.profile_image);

        SharedPreferences sharedPreferences1=getSharedPreferences("data",MODE_PRIVATE);
        String name=sharedPreferences1.getString("name",null);
        String number=sharedPreferences1.getString("number",null);
        String email=sharedPreferences1.getString("email",null);
        String roll=sharedPreferences1.getString("roll_no",null);
        String id=sharedPreferences1.getString("student_id",null);
        String year=sharedPreferences1.getString("year",null);
        String branch=sharedPreferences1.getString("branch",null);
        String url= sharedPreferences1.getString("imageUrl",null);

        Glide.with(Profile.this).load(url).into(dp);

        viewEmail.setText(email);
        viewName.setText(name);
        viewNumber.setText(number);
        viewRollno.setText(roll);
        viewBranch.setText(branch);
        viewId.setText(id);
        viewYear.setText(year);

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
        Glide.with(Profile.this).load(url1).into(image_profile);

        navUsername.setText(name);
        navBranch.setText(branch);
        navUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Profile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Profile.this,Dashboard.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
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
            Intent intent = new Intent(Profile.this, MainActivity.class);
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
            Intent intent1 = new Intent(Profile.this,Attendance.class);
            startActivity(intent1);
            finish();
        } else if (id == R.id.nav_notice) {
            Intent intent2 = new Intent(Profile.this,Attendance.class);
            startActivity(intent2);
            finish();

        } else if (id == R.id.nav_time_table) {
            Intent intent3 = new Intent(Profile.this,Attendance.class);
            startActivity(intent3);
            finish();
        } else if (id == R.id.nav_grades) {
            Intent intent4 = new Intent(Profile.this,Attendance.class);
            startActivity(intent4);
            finish();

        } else if(id == R.id.nav_exam){
            Intent intent5 = new Intent(Profile.this,Examinations.class);
            startActivity(intent5);
            finish();
        } else if (id == R.id.nav_teacher){
            Intent intent7 = new Intent(Profile.this,Teacher.class);
            startActivity(intent7);
            finish();

        } else if (id == R.id.nav_holidays){
            Intent intent8 = new Intent(Profile.this,EventHolidays.class);
            startActivity(intent8);
            finish();

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
