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
import com.example.attrack.Activities.Examinations;
import com.example.attrack.Activities.Teacher;
import com.example.attrack.Pojo.ExamPojo;
import com.example.attrack.Pojo.TeacherPojo;
import com.example.attrack.R;

import java.util.ArrayList;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    private ArrayList<ExamPojo> values;
    private Context context;

    public ExamAdapter(Context context, ArrayList<ExamPojo> myDataset) {
        values = myDataset;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.exam_item, parent, false);
        ExamAdapter.ViewHolder vh = new ExamAdapter.ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ExamAdapter.ViewHolder holder, int position) {
        final ExamPojo myPojo = values.get(position);
        holder.name.setText(myPojo.getName());
        holder.start_date.setText(myPojo.getStart_date());
        holder.end_date.setText(myPojo.getEnd_date());
        /*holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Examinations.class);
                intent.putExtra("name", myPojo.getName());
                intent.putExtra("start_date",myPojo.getStart_date());
                intent.putExtra("receiverId",myPojo.getId());
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView start_date;
        public  TextView end_date;
        public LinearLayout linearLayout ;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.exam_name);
            start_date = (TextView) v.findViewById(R.id.start_date);
            end_date = (TextView) v.findViewById(R.id.end_date);
            linearLayout = v.findViewById(R.id.linear_layout_exam);

        }
    }

}
