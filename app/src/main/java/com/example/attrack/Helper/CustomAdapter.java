package com.example.attrack.Helper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.attrack.Activities.Teacher;
import com.example.attrack.Pojo.TeacherPojo;
import com.example.attrack.R;

import java.text.BreakIterator;
import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<TeacherPojo> values;
    private Context context;

    public CustomAdapter(Context context, ArrayList<TeacherPojo> myDataset) {
        values = myDataset;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.teacher_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TeacherPojo myPojo = values.get(position);
        holder.name.setText(myPojo.getName());
        holder.branch.setText(myPojo.getBranch());
        Glide.with(context).load(myPojo.getImageURL()).into(holder.icon);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Teacher.class);
                intent.putExtra("name", myPojo.getName());
                intent.putExtra("imageUrl", myPojo.getImageURL());
                intent.putExtra("receiverId",myPojo.getId());
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView branch;
        public ImageView icon;
        public LinearLayout linearLayout ;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            branch = (TextView) v.findViewById(R.id.branch);
            icon = v.findViewById(R.id.icon);
            linearLayout = v.findViewById(R.id.linearLayout_teacher);

        }
    }


}