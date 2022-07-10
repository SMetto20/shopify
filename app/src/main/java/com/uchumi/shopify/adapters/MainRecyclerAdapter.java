package com.uchumi.shopify.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uchumi.shopify.R;
import com.uchumi.shopify.models.AllCategory;
import com.uchumi.shopify.models.CategoryItem;
import com.uchumi.shopify.models.Offer;
import com.uchumi.shopify.models.Offers;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    private Context mContext;
    private List<AllCategory> allCategoryList;

    public MainRecyclerAdapter(Context mContext, List<AllCategory> allCategoryList) {
        this.mContext = mContext;
        this.allCategoryList = allCategoryList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.categoryTitle.setText(allCategoryList.get(position).getCategoryTitle());
        setCategoryItemRecycler(holder.itemRecycler, allCategoryList.get(position).getCategoryItemList());

    }

    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;
        RecyclerView itemRecycler;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title);
            itemRecycler = itemView.findViewById(R.id.itemRecyclerView);

        }
    }

    private void setCategoryItemRecycler(RecyclerView recyclerView, List<CategoryItem> categoryItemList){
        CategoryItemRecyclerAdapter itemRecyclerAdapter = new CategoryItemRecyclerAdapter(mContext, categoryItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemRecyclerAdapter);
    }
}
