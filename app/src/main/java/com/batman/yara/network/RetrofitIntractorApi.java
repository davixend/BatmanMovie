package com.batman.yara.network;

import com.batman.yara.models.DetailsVideoModel;
import com.batman.yara.models.VideoModel;
import io.reactivex.Observable;

public class RetrofitIntractorApi {
    private DataService service;

    public RetrofitIntractorApi() {
        service = RetrofitClientInstance.getRetrofitInstance().create(DataService.class);
    }

    public Observable<VideoModel> videoList(String apiKey, String category) {
        return service.getVideoList(apiKey, category);
    }

    public Observable<DetailsVideoModel> videoDetails(String apiKey, String idVideo) {
        return service.getVideoDetails(apiKey, idVideo);
    }
}
