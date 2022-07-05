package com.uchumi.shopify.adapters;

import android.content.Context;
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

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        holder.mTerm.setText(offerList.get(position).getName());
        holder.mSeller.setText(offerList.get(position).getSeller());
        holder.mPrice.setText((int) offerList.get(position).getPrice() + " $");
        getImages(position);
        Picasso.get().load(imageUrl).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public void getImages(int position){
        Thread thread = new Thread() {
            @Override
            public void run() {
                
                try {
                    Document document = Jsoup.connect(offerList.get(position).getUrl()).get();
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
   ImageView imageView;

        private Context mContext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mTerm=itemView.findViewById(R.id.itemTitle);
            mPrice=itemView.findViewById(R.id.itemPrice);
            mSeller=itemView.findViewById(R.id.shopName);
            imageView=itemView.findViewById(R.id.itemImageView);

        }
    }
}
