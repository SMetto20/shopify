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
        categoryItemList.add(new CategoryItem(1, R.drawable.decor, "Decor", "Home decor"));
        categoryItemList.add(new CategoryItem(1, R.drawable.furniture, "Furniture", "Furniture"));
        categoryItemList.add(new CategoryItem(1, R.drawable.cleaning, "Cleaning", "Cleaning equipment"));
        categoryItemList.add(new CategoryItem(1, R.drawable.lighting, "Lighting", "Home lighting"));
        categoryItemList.add(new CategoryItem(1, R.drawable.plants, "Plants", "Potted plants"));

        // category 2
        List<CategoryItem> categoryItemList2 = new ArrayList<>();
        categoryItemList2.add(new CategoryItem(1, R.drawable.shoes, "Shoes", "Shoes"));
        categoryItemList2.add(new CategoryItem(1, R.drawable.socks, "Socks", "Socks"));
        categoryItemList2.add(new CategoryItem(1, R.drawable.tops, "Tops", "Tops"));
        categoryItemList2.add(new CategoryItem(1, R.drawable.pants, "Bottoms", "Trousers"));
        categoryItemList2.add(new CategoryItem(1, R.drawable.undergarment, "Undergarments", "Undergarments"));

        // category 3
        List<CategoryItem> categoryItemList3 = new ArrayList<>();
        categoryItemList3.add(new CategoryItem(1, R.drawable.hair, "Hair", "Hair products"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.makeup, "Facial", "Makeup"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.lotion, "Lotion/Serum", "Lotions and serums"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.nails, "Nail Care", "Nail products"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.waxing, "Waxing", "Waxing products"));

        // category 4
        List<CategoryItem> categoryItemList4 = new ArrayList<>();
        categoryItemList4.add(new CategoryItem(1, R.drawable.phone, "Phones", "Phones"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.speaker, "Sound", "Sound systems"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.laptops, "Laptops", "Laptops"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.tv, "Tvs", "Television"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.extension, "Accessories", "Electrical accessories"));


        // category 5
        List<CategoryItem> categoryItemList5 = new ArrayList<>();
        categoryItemList5.add(new CategoryItem(1, R.drawable.bikini, "Swim Wear", "Swimming costumes"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.equip, "Gym Equipment", "Gym Equipment"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.balls, "Balls", "Sports balls"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.gymwear, "Sports Clothes", "Gym clothes"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.sportshoes, "Sports Shoes", "Sports Shoes"));

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
