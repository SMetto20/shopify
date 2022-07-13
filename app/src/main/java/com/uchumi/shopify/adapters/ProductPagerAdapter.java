package com.uchumi.shopify.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.uchumi.shopify.models.Offer;
import com.uchumi.shopify.ui.fragments.ProductDetailFragment;

import java.util.List;

public class ProductPagerAdapter extends FragmentPagerAdapter {
    private List<Offer> mShops;
    String imageUrl;

    public ProductPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Offer> shops) {
        super(fm, behavior);
        mShops = shops;


    }


    @Override
    public Fragment getItem(int position) {
       return ProductDetailFragment.newInstance(mShops.get(position));
    }

    @Override
    public int getCount() {
       return mShops.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mShops.get(position).getName();
    }
}
