package com.uchumi.shopify.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.AsyncTask;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.uchumi.shopify.Constants;
import com.uchumi.shopify.R;
import com.uchumi.shopify.models.Offer;

import com.uchumi.shopify.ui.CreateAccountActivity;
import com.uchumi.shopify.ui.fragments.SavedItemsFragment;
import com.uchumi.shopify.ui.ProductDetailsActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    Context context;
    List<Offer> offerList;
    String imageUrl;

    public ProductsAdapter(Context context, List<Offer> offerList) {
        this.context = context;
        this.offerList = offerList;
    }


    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_best_selling, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.mTerm.setText(offerList.get(position).getName());
        holder.mSeller.setText(offerList.get(position).getSeller());
        holder.mRating.setText(offerList.get(position).getReviewRating() + "/5");
        holder.mShipping.setText("Shipping $" + (int) offerList.get(position).getShipping());
        holder.mPrice.setText("$" + (int) offerList.get(position).getPrice());
        getImages(position);
        if (imageUrl == null || imageUrl.equals("")) {
            String url = "https://www.trendsetter.com/pub/media/catalog/product/placeholder/default/no_image_placeholder.jpg";
            Picasso.get().load(url).into(holder.imageView);
        } else {
            Picasso.get().load(imageUrl).into(holder.imageView);
        }

        holder.mLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                String uid = user.getUid();
                DatabaseReference mDatabase = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_PRODUCTS)
                        .child(uid);

                String pushId = mDatabase.push().getKey();
                offerList.get(position).setPushId(pushId);
//                mDatabase.push().setValue(offerList.get(position));
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String image = getImagesV2(position);
                        offerList.get(position).setImage(image);
                        mDatabase.push().setValue(offerList.get(position));

                    }
                };
                thread.start();
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }


    public void getImages(int position) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(offerList.get(position).getUrl()).timeout(6000)   .get();
                    Elements elements = document.select("div.oR27Gd");
                    imageUrl = elements.select("img").attr("src");
                    Log.i("imageUrl", imageUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    public String getImagesV2(int position) {
        String image = "";

                try {
                    Document document = Jsoup.connect(offerList.get(position).getUrl()).timeout(6000)   .get();
                    Elements elements = document.select("div.oR27Gd");
                    image = elements.select("img").attr("src");
                    Log.i("imageUrl", image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return image;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



   TextView mTerm, mPrice, mSeller, mShipping, mRating;

   ImageView imageView, mLikeButton;

        private Context mContext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mTerm=itemView.findViewById(R.id.itemTitle);
            mPrice=itemView.findViewById(R.id.itemPrice);
            mSeller=itemView.findViewById(R.id.shopName);
            mShipping = itemView.findViewById(R.id.shipping);
            mRating = itemView.findViewById(R.id.shopRatings);
            imageView=itemView.findViewById(R.id.itemImageView);
            mLikeButton = itemView.findViewById(R.id.favoriteItems);

            itemView.setOnClickListener(this);
        }

        public void bindShop(Offer offerList) {

        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ProductDetailsActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("shop", Parcels.wrap(offerList));
            mContext.startActivity(intent);
        }
    }
}
