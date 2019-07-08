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
import com.example.attrack.Activities.EventHolidays;
import com.example.attrack.Pojo.EventPojo;
import com.example.attrack.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private ArrayList<EventPojo> values;
    private Context context;

    public EventAdapter(Context context, ArrayList<EventPojo> myDataset) {
        values = myDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.event_item, parent, false);
        EventAdapter.ViewHolder vh = new EventAdapter.ViewHolder(v);
        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        final EventPojo myPojo = values.get(position);

        holder.title.setText(myPojo.getName());
        Glide.with(context).load(myPojo.getImage_url()).into(holder.event_image);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EventDesciptions.class);
                intent.putExtra("name", myPojo.getName());
                intent.putExtra("description",myPojo.getDescription());
                intent.putExtra("imageUrl", myPojo.getImage_url());
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
        public TextView title,description;
        public ImageView event_image;
        public LinearLayout linearLayout ;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.event_title);
            event_image = v.findViewById(R.id.event_image);
            linearLayout = v.findViewById(R.id.linearLayout_event);

        }
    }

}
