package com.batman.yara.views.splash;

import android.content.Intent;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.batman.yara.R;
import com.batman.yara.views.BaseActivity;
import com.batman.yara.views.video_list.VideoListActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.animation_view)
    LottieAnimationView animationView;

    @Override
    protected void onCreate() {
        animationView.addAnimatorUpdateListener((animation) -> {
            // Do something.
        });
        animationView.playAnimation();
        if (animationView.isAnimating()) {
            // Do something.
        }
        animationView.setProgress(0.5f);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, VideoListActivity.class));
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 2500);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }
}