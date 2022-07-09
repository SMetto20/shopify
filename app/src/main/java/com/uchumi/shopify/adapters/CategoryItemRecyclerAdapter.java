package com.uchumi.shopify.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uchumi.shopify.R;
import com.uchumi.shopify.models.CategoryItem;
import com.uchumi.shopify.models.Offer;
import com.uchumi.shopify.models.Offers;

import java.util.List;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder>{

    private Context mContext;
    private List<CategoryItem> categoryItemList;

    public CategoryItemRecyclerAdapter(Context mContext, List<CategoryItem> categoryItemList) {
        this.mContext = mContext;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.category_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        holder.mImageView.setImageResource(categoryItemList.get(position).getImageUrl());
        holder.mTextView.setText(categoryItemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mTextView = itemView.findViewById(R.id.categoryItemTextView);
            mImageView = itemView.findViewById(R.id.categoryItemImageView);
        }
    }
}
