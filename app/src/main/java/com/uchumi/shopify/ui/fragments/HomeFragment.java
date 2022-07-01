package com.uchumi.shopify.ui.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uchumi.shopify.network.ApiClient;
import com.uchumi.shopify.network.ApiInterface;
import com.uchumi.shopify.models.Offer;
import com.uchumi.shopify.models.Offers;
import com.uchumi.shopify.adapters.ProductsAdapter;
import com.uchumi.shopify.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    List<Offer>offerList;
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

        return v;

    }


    @Override
    public boolean onQueryTextSubmit(String term) {
       term= searchView.getQuery().toString();
        Toast.makeText(getActivity(), "You searched for " + term, Toast.LENGTH_SHORT).show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Offers> offersCall = apiInterface.getOffers(country, term);
        offersCall.enqueue(new Callback<Offers>() {
            @Override
            public void onResponse(Call<Offers> call, Response<Offers> response) {
                List<Offer> searchProducts= response.body().getOffers();
                ProductsAdapter adapter=new ProductsAdapter(getContext(),searchProducts);
                homeRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
                productsAdapter= new ProductsAdapter(getContext(),offerList);
                homeRecyclerview.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<Offers> call, Throwable t) {

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