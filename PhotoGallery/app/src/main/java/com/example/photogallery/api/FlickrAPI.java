package com.example.photogallery.api;

import com.example.photogallery.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrAPI {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=1a34bdcc540e105b49dac5f7e270bf4f&extras=url_s&per_page=20&page=1&format=json&nojsoncallback=1")
    Call<Response> getRecent();
    @GET("services/rest/?method=flickr.photos.search&api_key=1a34bdcc540e105b49dac5f7e270bf4f&extras=url_s&per_page=20&page=1&format=json&nojsoncallback=1")
    Call<Response> getSearchPhotos(@Query("text") String keyWord);

}
