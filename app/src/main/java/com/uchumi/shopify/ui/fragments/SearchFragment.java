package com.uchumi.shopify.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uchumi.shopify.R;
import com.uchumi.shopify.adapters.MainRecyclerAdapter;
import com.uchumi.shopify.models.Offer;
import com.uchumi.shopify.models.Offers;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private Context mContext;

    private RecyclerView mMainRecyclerView;
    private MainRecyclerAdapter mMainRecyclerAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Offer> offerList1 = new ArrayList<Offer>(){

        };

        List<Offer> offerList2 = new ArrayList<Offer>(){

        };

        List<Offer> offerList3 = new ArrayList<Offer>(){

        };


        List<Offers> allCategoryList = new ArrayList<>();
        allCategoryList.add(new Offers(offerList1.size(), offerList1, "Electronics"));
        allCategoryList.add(new Offers(offerList2.size(), offerList2, "Socks"));
        allCategoryList.add(new Offers(offerList3.size(), offerList3, "Cosmetics"));

    }

    private void setMainCategoryRecycler(List<Offers> allCategoryList){

        mMainRecyclerView = mMainRecyclerView.findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mMainRecyclerView.setLayoutManager(layoutManager);
        mMainRecyclerView.setAdapter(mMainRecyclerAdapter);
        mMainRecyclerAdapter = new MainRecyclerAdapter(getActivity(), allCategoryList);


    }

}