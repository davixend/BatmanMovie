package com.batman.yara.views.video_details;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;

import com.batman.yara.R;
import com.batman.yara.db.ModelRatingDB;
import com.batman.yara.db.ModelVideoDetailsDB;
import com.batman.yara.db.RatingViewModel;
import com.batman.yara.db.VideoDetailsViewModel;
import com.batman.yara.models.DetailsVideoModel;
import com.batman.yara.utils.AppDialog;
import com.batman.yara.utils.Utils;
import com.batman.yara.views.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoDetailsActivity extends BaseActivity implements VideoDetailsPresenter.View {

    private VideoDetailsPresenter videoDetailsPresenter;
    private VideoDetailsViewModel videoDetailsViewModel;
    private RatingViewModel ratingViewModel;
    private AppDialog appDialog;
    private boolean isShowDialog = true;

    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.main_details)
    NestedScrollView main_details;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarImage)
    ImageView imageVideo;

    @BindView(R.id.year)
    TextView year;

    @BindView(R.id.rate)
    TextView rated;

    @BindView(R.id.rate1)
    TextView rated1;

    @BindView(R.id.rate2)
    TextView rated2;

    @BindView(R.id.name_rate)
    TextView nameRated;

    @BindView(R.id.name_rate1)
    TextView nameRated1;

    @BindView(R.id.name_rate2)
    TextView nameRated2;

    @BindView(R.id.released)
    TextView relesed;

    @BindView(R.id.runtime)
    TextView runTime;

    @BindView(R.id.genre)
    TextView genre;

    @BindView(R.id.writer)
    TextView writer;

    @BindView(R.id.plot)
    TextView plot;

    @BindView(R.id.awards)
    TextView awards;

    @BindView(R.id.production)
    TextView production;

    @BindView(R.id.website)
    TextView website;

    @BindView(R.id.country)
    TextView country;

    @BindView(R.id.language)
    TextView language;

    @BindView(R.id.director)
    TextView director;

    @BindView(R.id.actors)
    TextView actors;

    @BindView(R.id.more_item)
    TextView moreItem;

    @BindView(R.id.more_item_layout)
    ConstraintLayout moreItemLayout;

    @OnClick(R.id.more_item)
    void moreItem() {
        moreItem.setVisibility(View.GONE);
        moreItemLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        appDialog = new AppDialog(this);
        videoDetailsPresenter = new VideoDetailsPresenter();
        videoDetailsPresenter.setView(this);
        videoDetailsViewModel = new VideoDetailsViewModel(this.getApplication());
        ratingViewModel = new RatingViewModel(this.getApplication());

        if (getIntent().hasExtra("idVideo")) {
            getDataDetailFromDB(getIntent().getExtras().getString("idVideo"));
            getDataFromServer();
        } else {
            finish();
            overridePendingTransition(0, 0);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_details_video;
    }

    @Override
    public void onSuccessDetailsVideo(DetailsVideoModel details) {
        ModelVideoDetailsDB model = new ModelVideoDetailsDB(details.getTitle(), details.getYear(), details.getRated(), details.getReleased(),
                details.getRuntime(), details.getGenre(), details.getDirector(), details.getWriter(),
                details.getActors(), details.getPlot(), details.getLanguage(), details.getCountry(), details.getAwards(),
                details.getPoster(), details.getMetascore(), details.getImdbRating(), details.getImdbVotes(),
                details.getImdbID(), details.getType(), details.getDVD(), details.getBoxOffice(), details.getProduction(),
                details.getWebsite(), details.getResponse());

        for (int i = 0; i < details.getRatings().size(); i++) {
            ModelRatingDB ratingDB = new ModelRatingDB(details.getRatings().get(i).getSource(),
                    details.getRatings().get(i).getValue(), details.getImdbID());
            ratingViewModel.insert(ratingDB);
        }
        videoDetailsViewModel.insert(model);
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        videoDetailsPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
        super.onBackPressed();
    }

    private void getDataFromServer() {
        if (Utils.isNetworkConnected(this))
            videoDetailsPresenter.getDetailsVideo("3e974fca", getIntent().getExtras().getString("idVideo"));
        else {
            showMessage("No Internet Connection");
        }
    }

    private void getDataDetailFromDB(String idVideo) {
        videoDetailsViewModel.getDetailsVideo(idVideo).observe(this, new Observer<ModelVideoDetailsDB>() {
            @Override
            public void onChanged(ModelVideoDetailsDB modelVideoDetailsDB) {
                if (modelVideoDetailsDB != null) {
                    if (!modelVideoDetailsDB.getTitle().isEmpty()) {
                        main_details.setVisibility(View.VISIBLE);
                        isShowDialog = false;
                        hideLoading();
                    }
                    if (!modelVideoDetailsDB.getPoster().isEmpty()) {
                        Glide.with(VideoDetailsActivity.this)
                                .load(modelVideoDetailsDB.getPoster())
                                .diskCacheStrategy(DiskCacheStrategy.DATA)
//                                    .apply(new RequestOptions().placeholder(R.drawable.comic))
                                .into(imageVideo);
                    }
                    collapsingToolbarLayout.setTitle(modelVideoDetailsDB.getTitle());
                    year.setText(modelVideoDetailsDB.getYear());
                    relesed.setText(modelVideoDetailsDB.getReleased());
                    runTime.setText(modelVideoDetailsDB.getRuntime());
                    genre.setText(modelVideoDetailsDB.getGenre());
                    writer.setText(modelVideoDetailsDB.getWriter());
                    plot.setText(modelVideoDetailsDB.getPlot());
                    actors.setText(modelVideoDetailsDB.getActors());
                    production.setText(modelVideoDetailsDB.getProduction());
                    website.setText(modelVideoDetailsDB.getWebsite());
                    language.setText(modelVideoDetailsDB.getLanguage());
                    country.setText(modelVideoDetailsDB.getCountry());
                    director.setText(modelVideoDetailsDB.getDirector());
                    awards.setText(modelVideoDetailsDB.getAwards());
                    getDataRatingFromDB(modelVideoDetailsDB);
                }
            }
        });
    }

    private void getDataRatingFromDB(ModelVideoDetailsDB modelVideoDetailsDB) {
        ratingViewModel.getAllRating(modelVideoDetailsDB.getImdbID()).observe(this, new Observer<List<ModelRatingDB>>() {
            @Override
            public void onChanged(List<ModelRatingDB> modelRatingDBS) {
                for (int i = 0; i < modelRatingDBS.size(); i++) {
                    switch (i){
                        case 0:
                            if (!modelRatingDBS.get(i).getValue().isEmpty()){
                                nameRated.setVisibility(View.VISIBLE);
                                rated.setVisibility(View.VISIBLE);
//                                nameRated.setText(modelRatingDBS.get(i).getSource()+": ");
                                rated.setText(modelRatingDBS.get(i).getValue());
                            }
                            break;
                        case 1:
                            if (!modelRatingDBS.get(i).getValue().isEmpty()){
                                nameRated1.setVisibility(View.VISIBLE);
                                rated1.setVisibility(View.VISIBLE);
                                nameRated1.setText(modelRatingDBS.get(i).getSource()+": ");
                                rated1.setText(modelRatingDBS.get(i).getValue());
                            }
                            break;
                        case 2:
                            if (!modelRatingDBS.get(i).getValue().isEmpty()){
                                nameRated2.setVisibility(View.VISIBLE);
                                rated2.setVisibility(View.VISIBLE);
                                nameRated2.setText(modelRatingDBS.get(i).getSource()+": ");
                                rated2.setText(modelRatingDBS.get(i).getValue());
                            }
                            break;
                    }
                }
            }
        });
    }
}