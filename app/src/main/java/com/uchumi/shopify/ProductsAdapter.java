package com.uchumi.shopify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    Context context;
    List<Offer> offerList;

    public ProductsAdapter(Context context, List<Offer> offerList) {
        this.context = context;
        this.offerList = offerList;
    }


    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_best_selling, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        holder.mTerm.setText(offerList.get(position).getName());
        holder.mSeller.setText(offerList.get(position).getSeller());
        holder.mPrice.setText(offerList.get(position).getPrice());
        

    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

   TextView mTerm, mPrice, mSeller;
   ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTerm=itemView.findViewById(R.id.itemTitle);
            mPrice=itemView.findViewById(R.id.itemPrice);
            mSeller=itemView.findViewById(R.id.shopName);
            imageView=itemView.findViewById(R.id.itemImageView);

        }
    }
}
