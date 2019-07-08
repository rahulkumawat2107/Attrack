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
import com.example.attrack.Activities.GradesDetail;
import com.example.attrack.Pojo.EventPojo;
import com.example.attrack.Pojo.GradePojo;
import com.example.attrack.R;

import java.util.ArrayList;

public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.ViewHolder> {

    private ArrayList<GradePojo> values;
    private Context context;

    public GradesAdapter(Context context, ArrayList<GradePojo> myDataset) {
        values = myDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public GradesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.event_item, parent, false);
        GradesAdapter.ViewHolder vh = new GradesAdapter.ViewHolder(v);
        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        final GradePojo myPojo = values.get(position);

        holder.title.setText(myPojo.getSubject());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, GradesDetail.class);
                intent.putExtra("ut1", myPojo.getScore_ut1());
                intent.putExtra("ut2", myPojo.getScore_ut2());
                intent.putExtra("mid1", myPojo.getScore_mid1());
                intent.putExtra("mid2", myPojo.getScore_mid2());
                intent.putExtra("receiverId",myPojo.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public LinearLayout linearLayout ;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.subject_name);
            linearLayout = v.findViewById(R.id.linear_layout_grades);

        }
    }

}
