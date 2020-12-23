package com.example.photogallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.photogallery.api.FlickrAPI;
import com.example.photogallery.api.ServiceAPI;
import com.example.photogallery.model.Photo;
import com.example.photogallery.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PhotoGallery extends AppCompatActivity {

    RecyclerView rView;
    PhotoAdapter adapter;
    Response resp;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        rView = findViewById(R.id.rView);
        rView.setLayoutManager(new GridLayoutManager(this,3));
        context = this;
        Retrofit retrofit = ServiceAPI.getRetrofit();
        retrofit.create(FlickrAPI.class).getRecent().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                resp = response.body();
                List<Photo> ph = resp.getPhotos().getPhoto();
                adapter = new PhotoAdapter(ph,context);
                rView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}