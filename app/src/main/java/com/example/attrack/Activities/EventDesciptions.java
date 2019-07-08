package com.example.attrack.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.attrack.Pojo.EventPojo;

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

public class EventDesciptions extends AppCompatActivity {

    ImageView img;
    FirebaseDatabase database;
    TextView description;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_desciptions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        img = findViewById(R.id.image_description);
        description = findViewById(R.id.text_description);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("event_Data");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    EventPojo eventPojo = dataSnapshot2.getValue(EventPojo.class);
                    description.setText(eventPojo.getDescription());

                    Intent intent = getIntent();
                    String profile_imageUrl = intent.getStringExtra("imageUrl");
                    Glide.with(EventDesciptions.this).load(profile_imageUrl).into(img);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EventDesciptions.this, "Database Error", Toast.LENGTH_SHORT).show();
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
