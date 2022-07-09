package com.uchumi.shopify.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

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
        View view= LayoutInflater.from(context).inflate(R.layout.activity_best_selling, null, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.mTerm.setText(offerList.get(position).getName());
        holder.mSeller.setText(offerList.get(position).getSeller());
        holder.mPrice.setText("$" + (int) offerList.get(position).getPrice());
        getImages(position);
        if (imageUrl == "") {
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
                mDatabase.push().setValue(offerList.get(position));
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

    public class ViewHolder extends RecyclerView.ViewHolder {

   TextView mTerm, mPrice, mSeller;
   ImageView imageView, mLikeButton;

        private Context mContext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mTerm=itemView.findViewById(R.id.itemTitle);
            mPrice=itemView.findViewById(R.id.itemPrice);
            mSeller=itemView.findViewById(R.id.shopName);
            imageView=itemView.findViewById(R.id.itemImageView);
            mLikeButton = itemView.findViewById(R.id.favoriteItems);

        }
    }
}
