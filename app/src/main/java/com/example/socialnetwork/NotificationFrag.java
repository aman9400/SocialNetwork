package com.example.socialnetwork;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NotificationFrag extends Fragment {

    RecyclerView recyclerView;
    final List<ModelNotification> modelNotifications = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = view.findViewById(R.id.recyclerNoti);
        modelNotifications.add(new ModelNotification("https://image.shutterstock.com/image-photo/sunset-coast-lake-nature-landscape-600w-1960131820.jpg",
                "https://image.shutterstock.com/image-photo/sunset-coast-lake-nature-landscape-600w-1960131820.jpg",
                "static boy started following you"," |  2 min ago"));
        modelNotifications.add(new ModelNotification("https://image.shutterstock.com/image-photo/sunset-coast-lake-nature-landscape-600w-1960131820.jpg",
                "https://image.shutterstock.com/image-photo/sunset-coast-lake-nature-landscape-600w-1960131820.jpg",
                "static boy started following you"," |  2 min ago"));
        modelNotifications.add(new ModelNotification("https://image.shutterstock.com/image-photo/sunset-coast-lake-nature-landscape-600w-1960131820.jpg",
                "https://image.shutterstock.com/image-photo/sunset-coast-lake-nature-landscape-600w-1960131820.jpg",
                "static boy started following you"," |  2 min ago"));
        modelNotifications.add(new ModelNotification("https://image.shutterstock.com/image-photo/sunset-coast-lake-nature-landscape-600w-1960131820.jpg",
                "https://image.shutterstock.com/image-photo/sunset-coast-lake-nature-landscape-600w-1960131820.jpg",
                "static boy started following you"," |  2 min ago"));

        AdapterNotification adapterNotification = new AdapterNotification(getContext(),modelNotifications);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterNotification);

        return view;

    }
}