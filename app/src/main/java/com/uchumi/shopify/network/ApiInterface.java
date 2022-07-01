package com.uchumi.shopify.network;

import com.uchumi.shopify.models.Offers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
@GET("search-products")
    Call<Offers>getOffers(
            @Query("term") String term,
            @Query("country") String country
    );
}
