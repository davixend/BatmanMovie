package com.batman.yara.network;

import com.batman.yara.models.DetailsVideoModel;
import com.batman.yara.models.VideoModel;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface DataService {

    @GET("/")
    Observable<VideoModel> getVideoList(
            @Query("apikey") String apikey,
            @Query("s") String category
    );

    @GET("/")
    Observable<DetailsVideoModel> getVideoDetails(
            @Query("apikey") String apikey,
            @Query("i") String idVideo
    );

}
