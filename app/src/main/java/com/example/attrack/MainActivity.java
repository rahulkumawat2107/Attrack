package com.example.attrack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.attrack.Activities.Dashboard;
import com.example.attrack.Pojo.StudentPojo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextInputEditText st_id,pass;
    Button btn_signin,checkbox;
    String student_id,password;
    ProgressDialog mDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("studentData");

        st_id=findViewById(R.id.student_id);
        pass=findViewById(R.id.password);
        btn_signin=findViewById(R.id.sign_in);
        checkbox = findViewById(R.id.LoginCheckBox);

        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        Boolean status=sharedPreferences.getBoolean("loginstatus",false);

        if (status==true){
            Intent intent1=new Intent(MainActivity.this,Dashboard.class);
            startActivity(intent1);
            finish();
        }

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_id = st_id.getText().toString();
                password = pass.getText().toString();
                mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please Wait..." + student_id);
                mDialog.setTitle("Loading");
                mDialog.show();


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            StudentPojo studentPojo = dataSnapshot1.getValue(StudentPojo.class);
                            String s_id = studentPojo.getS_id();
                            String spassword=studentPojo.getPassword();

                            if((student_id.equals(s_id))&&(password.equals(spassword))){
                                SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();

                                editor.putString("id",studentPojo.getId());
                                editor.putString("number",studentPojo.getNumber());
                                editor.putString("roll_no",studentPojo.getRollno());
                                editor.putString("name",studentPojo.getName());
                                editor.putString("year",studentPojo.getYear());
                                editor.putString("branch",studentPojo.getBranch());
                                editor.putString("email",studentPojo.getEmail());

                                editor.putString("password",studentPojo.getPassword());
                                editor.putString("imageUrl",studentPojo.getImageURL());
                                editor.putString("student_id",studentPojo.getS_id());
                                CheckBox ch = (CheckBox) findViewById(R.id.LoginCheckBox);
                                if(ch.isChecked())
                                    editor.putBoolean("loginstatus",true);
                                else
                                    editor.putBoolean("loginstatus",false);
                                editor.commit();
                                flag=1;
                                break;
                            }
                        }
                        if(flag==1){
                            mDialog.dismiss();
                            Intent intent1=new Intent(MainActivity.this,Dashboard.class);
                            startActivity(intent1);
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Invalid Login Details",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this,"Connection Error",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
