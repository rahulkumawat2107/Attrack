package com.example.attrack.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.attrack.Pojo.EventPojo;
import com.example.attrack.Pojo.GradePojo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attrack.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GradesDetail extends AppCompatActivity {

    FirebaseDatabase database;
    TextView u1,u2,m1,m2;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_desciptions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        u1 = findViewById(R.id.ut1_score);
        u2= findViewById(R.id.ut2_score);
        m1 = findViewById(R.id.mid1_score);
        m2= findViewById(R.id.mid2_score);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("gradesData");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    GradePojo gradePojo = dataSnapshot2.getValue(GradePojo.class);
                    u1.setText(gradePojo.getScore_ut1());
                    u2.setText(gradePojo.getScore_ut2());
                    m1.setText(gradePojo.getScore_mid1());
                    m2.setText(gradePojo.getScore_mid2());

                    Intent intent = getIntent();
                    String profile_imageUrl = intent.getStringExtra("imageUrl");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(GradesDetail.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
