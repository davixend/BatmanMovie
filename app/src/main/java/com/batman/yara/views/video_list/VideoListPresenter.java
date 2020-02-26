package com.batman.yara.views.video_list;

import com.batman.yara.models.VideoModel;
import com.batman.yara.network.RetrofitIntractorApi;
import com.batman.yara.views.Presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class VideoListPresenter extends Presenter<VideoListPresenter.View> {

    private CompositeDisposable compositeDisposable;
    private RetrofitIntractorApi retrofitIntractorApi;

    public VideoListPresenter() {
        compositeDisposable = new CompositeDisposable();
        retrofitIntractorApi = new RetrofitIntractorApi();
    }

    public void getListVideo(String apiKey, String category) {
        getView().showLoading();
        compositeDisposable.add(
                retrofitIntractorApi.videoList(apiKey, category)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<VideoModel>() {
                            @Override
                            public void onNext(VideoModel list) {
                                getView().onSuccessListVideo(list);
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
        void onSuccessListVideo(VideoModel list);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }
}


