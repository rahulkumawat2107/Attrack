package com.example.attrack.Helper;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.attrack.Activities.EventDesciptions;
import com.example.attrack.Pojo.EventPojo;
import com.example.attrack.Pojo.NoticePojo;
import com.example.attrack.R;

import java.net.URI;
import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {


    private ArrayList<NoticePojo> values;
    private Context context;

    public NoticeAdapter(Context context, ArrayList<NoticePojo> myDataset) {
        values = myDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.notice_item, parent, false);
        NoticeAdapter.ViewHolder vh = new NoticeAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final NoticePojo myPojo = values.get(position);

        final String urls = myPojo.getPdfUrl();
        holder.title.setText(myPojo.getTitle());
        holder.view.setText(myPojo.getPdfUrl());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(urls), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Log.d("1234","abcd"+urls);
                Intent newIntent = Intent.createChooser(intent, "Open File");
                try {
                    context.startActivity(newIntent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }
                /*Intent intent = new Intent();
                Uri uri = Uri.parse(urls);
                intent.setType(intent.ACTION_VIEW);
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public Button view;
        public LinearLayout linearLayout ;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.text_notice);
            view = (Button) v.findViewById(R.id.view_notice);
            linearLayout = v.findViewById(R.id.linear_layout_notice);

        }
    }
}
