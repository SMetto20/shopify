package com.uchumi.shopify.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.uchumi.shopify.Constants;
import com.uchumi.shopify.models.Offer;
import com.uchumi.shopify.ui.fragments.SavedItemsFragment;

import org.parceler.Parcels;
import com.uchumi.shopify.R;

import java.util.ArrayList;
import java.util.List;

public class FirebaseProductsAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseProductsAdapter(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);

    }

    public void bindProduct(Offer offer) {
        ImageView mProductImage = (ImageView) mView.findViewById(R.id.productImage);
        TextView mProductName = (TextView) mView.findViewById(R.id.productName);
        TextView mProductPrice = (TextView) mView.findViewById(R.id.productPrice);
        TextView  mProductRating = (TextView) mView.findViewById(R.id.productRating);
        TextView  mProductSite = (TextView) mView.findViewById(R.id.productSite);

        ImageView mDeleteProduct = (ImageView)mView.findViewById(R.id.deleteBtn);

        if (offer.getImage() == null || offer.getImage().equals("")) {
            String url = "https://www.trendsetter.com/pub/media/catalog/product/placeholder/default/no_image_placeholder.jpg";
            Picasso.get().load(url).into(mProductImage);
        } else {
            Picasso.get().load(offer.getImage()).into(mProductImage);
        }

        mProductName.setText(offer.getName());
        mProductPrice.setText("$" + offer.getPrice());
        mProductRating.setText(offer.getReviewRating() + "/5");
        mProductSite.setText(offer.getSeller());
    }

    @Override
    public void onClick(View v) {

    }
}



