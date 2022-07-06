package com.uchumi.shopify.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uchumi.shopify.R;
import com.uchumi.shopify.models.Offer;
import com.uchumi.shopify.models.Offers;

import java.util.List;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder>{

    private Context mContext;
    private List<Offer> mCategoryOfferItemList;

    public CategoryItemRecyclerAdapter(Context mContext, List<Offer> mCategoryOfferItemList) {
        this.mContext = mContext;
        this.mCategoryOfferItemList = mCategoryOfferItemList;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.category_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        holder.mTextView.setText(mCategoryOfferItemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCategoryOfferItemList.size();
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            mTextView = itemView.findViewById(R.id.categoryItemTextView);
        }
    }
}
