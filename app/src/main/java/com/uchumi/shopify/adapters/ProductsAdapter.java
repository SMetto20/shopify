package com.uchumi.shopify.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import com.uchumi.shopify.R;
import com.uchumi.shopify.models.Offer;
import com.uchumi.shopify.ui.ProductDetailsActivity;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

import java.io.IOException;
import java.util.List;

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

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        holder.mTerm.setText(offerList.get(position).getName());
        holder.mSeller.setText(offerList.get(position).getSeller());
        holder.mPrice.setText((int) offerList.get(position).getPrice() + " $");
        getImages();
        Picasso.get().load(imageUrl).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }


    public void getImages(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(offerList.get(5).getUrl()).get();
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.itemTitle) TextView mTerm;
        @BindView(R.id.itemPrice) TextView mPrice;
        @BindView(R.id.shopName) TextView mSeller;
        ImageView imageView;

        private Context mContext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            imageView=itemView.findViewById(R.id.itemImageView);

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
