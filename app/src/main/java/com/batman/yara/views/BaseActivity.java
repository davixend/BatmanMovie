package com.batman.yara.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        bindViews();
        onCreate();
    }
    protected abstract void onCreate();

    private void bindViews() {
        ButterKnife.bind(this);
    }

    protected abstract int getContentViewId();
}
