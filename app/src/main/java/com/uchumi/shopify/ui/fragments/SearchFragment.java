package com.uchumi.shopify.ui.fragments;

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
import com.uchumi.shopify.models.AllCategory;
import com.uchumi.shopify.models.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView mMainRecyclerView;
    private MainRecyclerAdapter mMainRecyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // category 1
        List<CategoryItem> categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryItem(1, R.drawable.decor, "Decor"));
        categoryItemList.add(new CategoryItem(1, R.drawable.furniture, "Furniture"));
        categoryItemList.add(new CategoryItem(1, R.drawable.cleaning, "Cleaning"));
        categoryItemList.add(new CategoryItem(1, R.drawable.lighting, "Lighting"));
        categoryItemList.add(new CategoryItem(1, R.drawable.plants, "Plants"));

        // category 2
        List<CategoryItem> categoryItemList2 = new ArrayList<>();
        categoryItemList2.add(new CategoryItem(1, R.drawable.shoes, "Shoes"));
        categoryItemList2.add(new CategoryItem(1, R.drawable.socks, "Socks"));
        categoryItemList2.add(new CategoryItem(1, R.drawable.tops, "Tops"));
        categoryItemList2.add(new CategoryItem(1, R.drawable.pants, "Bottoms"));
        categoryItemList2.add(new CategoryItem(1, R.drawable.undergarment, "Undergarments"));

        // category 3
        List<CategoryItem> categoryItemList3 = new ArrayList<>();
        categoryItemList3.add(new CategoryItem(1, R.drawable.hair, "Hair"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.makeup, "Facial"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.lotion, "Lotion/Serum"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.nails, "Nail Care"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.waxing, "Waxing"));

        // category 4
        List<CategoryItem> categoryItemList4 = new ArrayList<>();
        categoryItemList4.add(new CategoryItem(1, R.drawable.phone, "Phones"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.speaker, "Sound"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.laptops, "Laptops"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.tv, "Tvs"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.extension, "Accessories"));


        // category 5
        List<CategoryItem> categoryItemList5 = new ArrayList<>();
        categoryItemList5.add(new CategoryItem(1, R.drawable.bikini, "Swim Wear"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.equip, "Gym Equipment"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.balls, "Balls"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.gymwear, "Sports Clothes"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.sportshoes, "Sports Shoes"));

        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory("Home", categoryItemList));
        allCategoryList.add(new AllCategory("Clothing", categoryItemList2));
        allCategoryList.add(new AllCategory("Body", categoryItemList3));
        allCategoryList.add(new AllCategory("Electronics", categoryItemList4));
        allCategoryList.add(new AllCategory("Sports Gear", categoryItemList5));

        setMainCategoryRecycler(allCategoryList);
    }

    private void setMainCategoryRecycler(List<AllCategory> allCategoryList){
        mMainRecyclerView = requireView().findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mMainRecyclerView.setLayoutManager(layoutManager);
        mMainRecyclerAdapter = new MainRecyclerAdapter(getActivity(), allCategoryList);
        mMainRecyclerView.setAdapter(mMainRecyclerAdapter);
    }
}
