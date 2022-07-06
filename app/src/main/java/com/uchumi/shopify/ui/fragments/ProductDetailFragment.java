package com.uchumi.shopify.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uchumi.shopify.R;
import com.uchumi.shopify.models.Offer;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductDetailFragment extends Fragment {

    @BindView(R.id.shippingPriceTextView) TextView mShippingPriceTextView;
    @BindView(R.id.productPriceTextView) TextView mProductPriceTextView;
    @BindView(R.id.productReviewsTextView) TextView mProductReviewTextView;
    @BindView(R.id.productRatingsTextView) TextView mProductRatingsTextView;
    @BindView(R.id.viewSiteButton) Button mViewSiteButton;
    @BindView(R.id.productNameDetailTextView) TextView mProductNameTextView;
    @BindView(R.id.sellerNameDetailTextView) TextView mSellerNameTextView;

    private Offer mShop;


    public ProductDetailFragment() {
        // Required empty public constructor
    }


    public static ProductDetailFragment newInstance(Offer shop) {
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("shop", Parcels.wrap(shop));
        productDetailFragment.setArguments(args);
        return productDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mShop = Parcels.unwrap(getArguments().getParcelable("shop"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this, view);

        mProductNameTextView.setText(mShop.getName());
        mSellerNameTextView.setText(mShop.getSeller());
//        mShippingPriceTextView.setText((int) mShop.getPrice());
//        mProductPriceTextView.setText(mShop.getShipping());
        mProductReviewTextView.setText(mShop.getReviewCount());
        mProductRatingsTextView.setText(mShop.getReviewRating());

        return view;
    }
}