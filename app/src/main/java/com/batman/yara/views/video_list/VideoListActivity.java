package com.batman.yara.views.video_list;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.batman.yara.R;
import com.batman.yara.adapters.VideosAdapter;
import com.batman.yara.db.ModelVideoDB;
import com.batman.yara.db.VideoViewModel;
import com.batman.yara.models.VideoModel;
import com.batman.yara.utils.AppDialog;
import com.batman.yara.utils.Utils;
import com.batman.yara.views.BaseActivity;
import com.batman.yara.views.video_details.VideoDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VideoListActivity extends BaseActivity implements VideoListPresenter.View, VideosAdapter.onClickListener {

    private VideoListPresenter videoListPresenter;
    private ArrayList<ModelVideoDB> mDataset;
    private RecyclerView.Adapter mAdapter;
    private VideoViewModel videoViewModel;
    private AppDialog appDialog;
    private boolean isShowDialog = true;


    @BindView(R.id.recycler_view_videos)
    RecyclerView listVideo;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate() {
        appDialog = new AppDialog(this);
        videoListPresenter = new VideoListPresenter();
        videoListPresenter.setView(this);
        initAdapter();
        videoViewModel.getAllVideo().observe(this, new Observer<List<ModelVideoDB>>() {
            @Override
            public void onChanged(List<ModelVideoDB> modelVideoDBS) {
                if (modelVideoDBS.size() > 0) {
                    isShowDialog = false;
                    hideLoading();
                }
                mDataset.clear();
                for (int i = 0; i < modelVideoDBS.size(); i++) {
                    mDataset.add(modelVideoDBS.get(i));
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        getDataFromServer();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }
        });
    }

    private void initAdapter() {
        videoViewModel = new VideoViewModel(this.getApplication());
        listVideo.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        listVideo.setLayoutManager(mLayoutManager);
        mDataset = new ArrayList<>();
        mAdapter = new VideosAdapter(mDataset, this, this);
        listVideo.setAdapter(mAdapter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_list_video;
    }

    @Override
    public void onSuccessListVideo(VideoModel list) {
//        videoViewModel.deleteAllVideo();
        refreshLayout.setRefreshing(false);
        for (int i = 0; i < list.getSearch().size(); i++) {
            ModelVideoDB model = new ModelVideoDB(list.getSearch().get(i).getTitle(), list.getSearch().get(i).getYear(),
                    list.getSearch().get(i).getImdbID(), list.getSearch().get(i).getType(), list.getSearch().get(i).getPoster());
            videoViewModel.insert(model);
        }
    }

    @Override
    public void showLoading() {
        if (isShowDialog)
            appDialog.loadingDialog();
    }

    @Override
    public void hideLoading() {
        appDialog.AppDialogCancel();
    }

    @Override
    public void showMessage(String message) {
        refreshLayout.setRefreshing(false);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickItem(String idVideo, ImageView imageView, TextView yearName, TextView year) {
        Intent intent = new Intent(this, VideoDetailsActivity.class);
        intent.putExtra("idVideo", idVideo);
        Pair<View, String> p1 = Pair.create((View) imageView, "cover");
        Pair<View, String> p3 = Pair.create((View) year, "yearName");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1, p3);
        startActivity(intent, options.toBundle());
    }

    @Override
    protected void onDestroy() {
        videoListPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    private void getDataFromServer(){
        if (Utils.isNetworkConnected(this))
            videoListPresenter.getListVideo("3e974fca", "batman");
        else {
            refreshLayout.setRefreshing(false);
            showMessage("No Internet Connection");
        }
    }
}