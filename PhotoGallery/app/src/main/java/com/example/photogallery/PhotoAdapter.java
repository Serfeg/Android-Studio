package com.example.photogallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photogallery.db.PhotosDao;
import com.example.photogallery.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{
    private List<Photo> ph;
    private ImageView img;
    private TextView img_tv;
    PhotosDao photosDao;
    Context context;
    boolean del;
    View view;

    public PhotoAdapter(List<Photo> photos, Context contxt, PhotosDao dao, boolean delete){
        ph = photos;
        context = contxt;
        photosDao = dao;
        del = delete;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rview_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String txt = ph.get(position).getTitle();
        img_tv.setText(txt);
        Picasso.with(context).load(ph.get(position).getUrl_s()).into(img);
    }

    @Override
    public int getItemCount() {
        return ph.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            img_tv = itemView.findViewById(R.id.img_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getLayoutPosition();
                    Photo photo = ph.get(id);
                    if (del == false) {
                        photosDao.insertPhoto(photo);
                        Toast.makeText(context, "Successful Insert", Toast.LENGTH_SHORT).show();
                    }
                    else if (del == true) {
                        photosDao.deletePhoto(photo);
                        Toast.makeText(context, "Successful Delete", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
