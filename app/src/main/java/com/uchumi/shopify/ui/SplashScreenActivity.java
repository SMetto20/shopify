package com.uchumi.shopify.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.skyfishjy.library.RippleBackground;
import com.uchumi.shopify.R;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView mImageView;
    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        rippleBackground.startRippleAnimation();

        mImageView = findViewById(R.id.imageView1);
        mImageView.setTranslationX(300);
        mImageView.setAlpha(v);
        mImageView.animate().translationX(0).alpha(1).setDuration(2000).setStartDelay(600).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        }, 4500);
    }
}