package com.uchumi.shopify.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.skyfishjy.library.RippleBackground;
import com.uchumi.shopify.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView mImageView1;
    float v = 0;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.homeImageView) ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        rippleBackground.startRippleAnimation();

        mImageView1 = findViewById(R.id.imageView1);
        mImageView1.setTranslationX(300);
        mImageView1.setAlpha(v);
        mImageView1.animate().translationX(0).alpha(1).setDuration(2000).setStartDelay(600).start();

        mImageView.setOnClickListener(v ->{
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        }, 4500);
    }
}