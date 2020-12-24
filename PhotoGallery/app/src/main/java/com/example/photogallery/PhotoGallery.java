package com.example.photogallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.photogallery.api.FlickrAPI;
import com.example.photogallery.api.ServiceAPI;
import com.example.photogallery.db.PhotosDB;
import com.example.photogallery.db.PhotosDao;
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
    PhotosDB db;
    PhotosDao dao;
    List<Photo> ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        final RecyclerView rView = findViewById(R.id.rView);
        rView.setLayoutManager(new GridLayoutManager(this,3));
        context = this;

        db = Room.databaseBuilder(context,PhotosDB.class,"database").allowMainThreadQueries().build();
        dao = db.photoDao();
        Retrofit retrofit = ServiceAPI.getRetrofit();
        retrofit.create(FlickrAPI.class).getRecent().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                resp = response.body();
                ph = resp.getPhotos().getPhoto();
                adapter = new PhotoAdapter(ph,context,dao);
                rView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view,menu);
        MenuItem searchItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)searchItem.getActionView();
        rView = findViewById(R.id.rView);
        rView.setLayoutManager(new GridLayoutManager(this,3));
        context = this;
        Retrofit retrofit = ServiceAPI.getRetrofit();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                retrofit.create(FlickrAPI.class).getSearchPhotos(query).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        resp = response.body();
                        ph = resp.getPhotos().getPhoto();
                        adapter = new PhotoAdapter(ph,context,dao);
                        rView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.NoLocalDB:
                Retrofit retrofit = ServiceAPI.getRetrofit();
                retrofit.create(FlickrAPI.class).getRecent().enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        resp = response.body();
                        ph = resp.getPhotos().getPhoto();
                        adapter = new PhotoAdapter(ph,context,dao);
                        rView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
                return true;
            case R.id.LocalDB:
                ph = dao.LoadAll();
                adapter = new PhotoAdapter(ph,context,dao);
                rView.setAdapter(adapter);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}