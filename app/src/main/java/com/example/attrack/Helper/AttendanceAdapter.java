package com.example.attrack.Helper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.attrack.Activities.EventDesciptions;
import com.example.attrack.Pojo.AttendancePojo;
import com.example.attrack.Pojo.EventPojo;
import com.example.attrack.R;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {

    private ArrayList<AttendancePojo> values;
    private Context context;

    public AttendanceAdapter(Context context, ArrayList<AttendancePojo> myDataset) {
        values = myDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public AttendanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_attendance, parent, false);
        AttendanceAdapter.ViewHolder vh = new AttendanceAdapter.ViewHolder(v);
        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        final AttendancePojo myPojo = values.get(position);

        holder.title.setText(myPojo.getSubject_attendance());

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title,description,percentage,present,absent;
        public ImageView event_image;
        public LinearLayout linearLayout ;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.attendance_subject);
            percentage = (TextView) v.findViewById(R.id.subject_percentage);
            present = (TextView) v.findViewById(R.id.present);
            absent = (TextView) v.findViewById(R.id.absent);
            linearLayout = v.findViewById(R.id.linearLayout_event);

        }
    }

}
