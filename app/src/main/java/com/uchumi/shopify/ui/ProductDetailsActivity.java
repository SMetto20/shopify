package com.uchumi.shopify.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uchumi.shopify.R;
import com.uchumi.shopify.adapters.ProductPagerAdapter;
import com.uchumi.shopify.models.Offer;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.viewPager) ViewPager mViewPager;
    private ProductPagerAdapter adapterViewPager;
    List<Offer> offerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        offerList = Parcels.unwrap(getIntent().getParcelableExtra("shop"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ProductPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, offerList);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }


    @Override
    public void onClick(View view) {


    }
}