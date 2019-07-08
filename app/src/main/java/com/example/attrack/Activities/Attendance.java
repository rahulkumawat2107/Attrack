package com.example.attrack.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.attrack.MainActivity;
import com.example.attrack.Pojo.AttendancePojo;
import com.example.attrack.R;

import android.util.Log;
import android.view.View;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int TOC=1,NOP=1,NOA=1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<AttendancePojo> arrayList = new ArrayList<>();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;
    DatabaseReference dbSubject;
    String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_attendance);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getDataFromFirebase();
        //setDataToPojo();

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
        Glide.with(Attendance.this).load(url1).into(image_profile);

        navUsername.setText(name);
        navBranch.setText(branch);
        navUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance.this, Profile.class);
                startActivity(intent);
                finish();
            }
        });

    }

    /*private void setDataToPojo() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    final AttendancePojo attendancePojo = new AttendancePojo();
                    if(dataSnapshot1.equals(attendancePojo.getDate())){
                        final String date = attendancePojo.getDate();
                        DatabaseReference db_date;
                        db_date = databaseReference.child(date);
                        db_date.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()){
                                    if(dataSnapshot2.equals(attendancePojo.getS_id())){
                                        String sid = attendancePojo.getS_id();
                                        DatabaseReference db_id;
                                        db_id = databaseReference.child(date).child(sid);
                                        db_id.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        })
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        })

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Attendance.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        })
    }*/

    public void getDataFromFirebase() {
        dbAttendance = ref.child("attendance");
        dbSubject = ref.child("subjectData");
        dbAttendance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                String student_id = sharedPreferences.getString("student_id", null);
                Log.d("abcd", "onDataChange: " + student_id);
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                    subject = (String) dsp.child(student_id).child("toc").getValue();
                    Log.d("abcd", "subject " + subject);
                    if (subject.equals("P")) {
                        NOP++;
                        TOC++;
                        Log.d("abcd", "nop toc " + NOP + " " + TOC);
                    }
                    else if(subject.equals("A")) {
                        NOA++;
                        TOC++;
                        Log.d("abcdefg", "nop toc " + NOP + " " + TOC);
                    }
                }

            }
            //AttendancePojo attendancePojo = dataSnapshot2.getValue(AttendancePojo.class);
            //arrayList.add(attendancePojo);

                //AttendanceAdapter attendanceAdapter = new AttendanceAdapter(Attendance.this, arrayList);
                //recyclerView.setAdapter(attendanceAdapter);

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Attendance.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Attendance.this,Dashboard.class);
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
        // as you specify a parent activity in An
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(Attendance.this, MainActivity.class);
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
        } else if (id == R.id.nav_notice) {
            Intent intent2 = new Intent(Attendance.this,Notice.class);
            startActivity(intent2);
            finish();

        } else if (id == R.id.nav_time_table) {
            Intent intent3 = new Intent(Attendance.this,TimeTable.class);
            startActivity(intent3);
            finish();

        } else if (id == R.id.nav_grades) {
            Intent intent4 = new Intent(Attendance.this,Grades.class);
            startActivity(intent4);
            finish();

        } else if(id == R.id.nav_exam){
            Intent intent5 = new Intent(Attendance.this,Examinations.class);
            startActivity(intent5);
            finish();

        } else if (id == R.id.nav_teacher){
            Intent intent7 = new Intent(Attendance.this,Teacher.class);
            startActivity(intent7);
            finish();

        } else if (id == R.id.nav_holidays){
            Intent intent8 = new Intent(Attendance.this,EventHolidays.class);
            startActivity(intent8);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
