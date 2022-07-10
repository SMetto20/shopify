package com.uchumi.shopify.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uchumi.shopify.R;
import com.uchumi.shopify.adapters.ProductsAdapter;
import com.uchumi.shopify.models.Offer;
import com.uchumi.shopify.models.OffersResponse;
import com.uchumi.shopify.network.ApiClient;
import com.uchumi.shopify.network.ApiInterface;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularActivity extends AppCompatActivity {

    List<Offer> offerList;
    ProductsAdapter productsAdapter;
    private RecyclerView homeRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        homeRecyclerview= findViewById(R.id.filterProducts1);

        Intent intent = getIntent();
        String term = intent.getStringExtra("term");

        ApiInterface client = ApiClient.getClient().create(ApiInterface.class);
        Call<OffersResponse> call = client.getOffers(term, "us", 1);
        call.enqueue(new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    offerList = response.body().getOffers().getOffers();
                    productsAdapter = new ProductsAdapter(PopularActivity.this, offerList);
                    homeRecyclerview.setLayoutManager(new GridLayoutManager(PopularActivity.this, 2));
                    homeRecyclerview.setAdapter(productsAdapter);
                    homeRecyclerview.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<OffersResponse> call, Throwable t) {

            }
        });

        FloatingActionButton mPrice=(FloatingActionButton) findViewById(R.id.filterByPrice1);
        FloatingActionButton mRatings =(FloatingActionButton) findViewById(R.id.filterByRatings1);
        FloatingActionButton mShipping=(FloatingActionButton) findViewById(R.id.filterByShipping1);
        FloatingActionButton mReviews=(FloatingActionButton) findViewById(R.id.filterByReviews1);

        // Sort by Price
        mPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(offerList, Offer.sortPrice);
                productsAdapter.notifyDataSetChanged();
            }
        });

        //sort by Ratings
        mRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(offerList, Offer.sortRatings);
                productsAdapter.notifyDataSetChanged();
            }
        });

        //Sort by Reviews
        mReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(offerList, Offer.sortReviews);
                productsAdapter.notifyDataSetChanged();
            }
        });

        //Sort by Shipping
        mShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(offerList, Offer.sortShipping);
                productsAdapter.notifyDataSetChanged();
            }
        });
    }
}