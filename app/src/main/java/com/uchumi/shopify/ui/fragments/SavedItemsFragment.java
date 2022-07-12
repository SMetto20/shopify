package com.uchumi.shopify.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uchumi.shopify.Constants;
import com.uchumi.shopify.R;
import com.uchumi.shopify.adapters.FirebaseProductsAdapter;
import com.uchumi.shopify.models.Offer;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SavedItemsFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference mDatabase;
    FirebaseRecyclerAdapter<Offer, FirebaseProductsAdapter> mFirebaseAdapter;
    @BindView(R.id.favouritesRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.textViewHolder) TextView mTextViewHolder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_items, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PRODUCTS).child(uid);
        setUpFirebaseAdapter();
        mRecyclerView.setVisibility(View.VISIBLE);
        mAuth = FirebaseAuth.getInstance();
    }

    private void setUpFirebaseAdapter() {
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PRODUCTS);

        FirebaseRecyclerOptions<Offer> options = new FirebaseRecyclerOptions.Builder<Offer>()
                .setQuery(mDatabase, Offer.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Offer, FirebaseProductsAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseProductsAdapter holder, int position, @NonNull Offer model) {
                holder.bindProduct(model);
            }

            @NonNull
            @Override
            public FirebaseProductsAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_recycler_view_list_item, parent, false);
                return new FirebaseProductsAdapter(view);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuthListener != null) {
            mAuth.addAuthStateListener(mAuthListener);
        }
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }
}
