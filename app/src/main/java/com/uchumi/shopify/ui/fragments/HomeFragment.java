package com.uchumi.shopify.ui.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    List<Offer> offerList;
    ProductsAdapter productsAdapter;
    String country="us";
    SearchView searchView;

    private RecyclerView homeRecyclerview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        searchView=v.findViewById(R.id.search_bar);
        homeRecyclerview= v.findViewById(R.id.filterProducts);

        searchView.setOnQueryTextListener(this);

        FloatingActionButton mPrice=(FloatingActionButton) v.findViewById(R.id.filterByPrice);
        FloatingActionButton mRatings =(FloatingActionButton) v.findViewById(R.id.filterByRatings);
        FloatingActionButton mShipping=(FloatingActionButton) v.findViewById(R.id.filterByShipping);
        FloatingActionButton mReviews=(FloatingActionButton) v.findViewById(R.id.filterByReviews);

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
        return v;


    }



    @Override
    public boolean onQueryTextSubmit(String term) {
        term= searchView.getQuery().toString();
        Toast.makeText(getActivity(), "You searched for " + term, Toast.LENGTH_SHORT).show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<OffersResponse> call = apiInterface.getOffers(term, country,1);
        call.enqueue(new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                if (response.isSuccessful()) {
                    offerList = response.body().getOffers().getOffers();
                    productsAdapter = new ProductsAdapter(getActivity(), offerList);
                    homeRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    homeRecyclerview.setAdapter(productsAdapter);
                    homeRecyclerview.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(Call<OffersResponse> call, Throwable t) {

                Log.e("API ERROR: ",t.getMessage());
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("You have no internet connection!")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();

                alert.setTitle(" Connection problem");
                alert.show();

            }
        });
        return true;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}