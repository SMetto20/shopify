package com.uchumi.shopify.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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

    public void bindProduct(Offer offer, String userId) {
        ImageView mProductImage = (ImageView) mView.findViewById(R.id.productImage);
        TextView mProductName = (TextView) mView.findViewById(R.id.productName);
        TextView mProductPrice = (TextView) mView.findViewById(R.id.productPrice);
        TextView mProductRating = (TextView) mView.findViewById(R.id.productRating);
        TextView mProductSite = (TextView) mView.findViewById(R.id.productSite);

        ImageView mDeleteProduct = (ImageView) mView.findViewById(R.id.deleteBtn);

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
        mDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteProduct(offer.getPushId(), userId);
            }
        });
    }

    private void deleteProduct(String pushId, String userId) {
        FirebaseDatabase.getInstance().getReference("products")
                .child(userId)
                .equalTo(pushId, "pushId");

        Query queryRef =  FirebaseDatabase.getInstance().getReference("products")
                .child(userId).orderByChild("pushId").equalTo(pushId);

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                snapshot.getRef().setValue(null);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        Toast.makeText(mContext, "product deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }
}



