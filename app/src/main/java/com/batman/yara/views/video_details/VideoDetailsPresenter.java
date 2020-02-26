package com.batman.yara.views.video_details;

import com.batman.yara.models.DetailsVideoModel;
import com.batman.yara.models.VideoModel;
import com.batman.yara.network.RetrofitIntractorApi;
import com.batman.yara.views.Presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class VideoDetailsPresenter extends Presenter<VideoDetailsPresenter.View> {

    private CompositeDisposable compositeDisposable;
    private RetrofitIntractorApi retrofitIntractorApi;

    public VideoDetailsPresenter() {
        compositeDisposable = new CompositeDisposable();
        retrofitIntractorApi = new RetrofitIntractorApi();
    }

    public void getDetailsVideo(String apiKey, String idVideo) {
        getView().showLoading();
        compositeDisposable.add(
                retrofitIntractorApi.videoDetails(apiKey, idVideo)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<DetailsVideoModel>() {
                            @Override
                            public void onNext(DetailsVideoModel details) {
                                getView().onSuccessDetailsVideo(details);
                                getView().hideLoading();
                            }

                            @Override
                            public void onError(Throwable e) {
                                getView().hideLoading();
                                getView().showMessage("Error Connection");
                            }

                            @Override
                            public void onComplete() {
                            }
                        })

        );
    }

    public interface View extends Presenter.View {
        void onSuccessDetailsVideo(DetailsVideoModel details);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }
}


