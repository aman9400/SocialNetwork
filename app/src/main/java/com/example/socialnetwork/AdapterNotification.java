package com.example.socialnetwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.MyHolder> {

    final Context context;
    final List<ModelNotification> modelNotification;

    public AdapterNotification(Context context, List<ModelNotification> modelNotification) {
        this.context = context;
        this.modelNotification = modelNotification;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerdashboard, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.descTv.setText(modelNotification.get(position).descNoti);
        holder.timeTv.setText(modelNotification.get(position).timeNoti);
        Picasso.get().load(modelNotification.get(position).imageLeft).fit().into(holder.leftImage);
        Picasso.get().load(modelNotification.get(position).imageRight).fit().into(holder.rightImage);
    }

    @Override
    public int getItemCount() {
        return modelNotification.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        final ImageView leftImage;
        final ImageView rightImage;
        final TextView descTv;
        final TextView timeTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            leftImage = itemView.findViewById(R.id.leftImage);
            rightImage = itemView.findViewById(R.id.rightImage);
            descTv = itemView.findViewById(R.id.descTv);
            timeTv = itemView.findViewById(R.id.timeTv);
        }
    }
}
