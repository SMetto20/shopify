package com.uchumi.shopify.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Comparator;

import javax.annotation.Generated;

@Parcel
@Generated("jsonschema2pojo")
public class Offer {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("shipping")
    @Expose
    private Float shipping;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("seller")
    @Expose
    private String seller;
    @SerializedName("seller_url")
    @Expose
    private String sellerUrl;
    @SerializedName("review_rating")
    @Expose
    private String reviewRating;
    @SerializedName("review_count")
    @Expose
    private String reviewCount;

//    push ID
    private String pushId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Offer() {
    }

    /**
     * 
     * @param seller
     * @param condition
     * @param shipping
     * @param sellerUrl
     * @param reviewCount
     * @param price
     * @param name
     * @param currency
     * @param reviewRating
     * @param url
     */
    public Offer(String name, String url, float price, String currency, Float shipping, String condition, String seller, String sellerUrl, String reviewRating, String reviewCount) {
        super();
        this.name = name;
        this.url = url;
        this.price = price;
        this.currency = currency;
        this.shipping = shipping;
        this.condition = condition;
        this.seller = seller;
        this.sellerUrl = sellerUrl;
        this.reviewRating = reviewRating;
        this.reviewCount = reviewCount;
    }

    public Offer(String name, String seller) {
        this.name = name;
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getShipping() {
        return shipping;
    }

    public void setShipping(Float shipping) {
        this.shipping = shipping;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerUrl() {
        return sellerUrl;
    }

    public void setSellerUrl(String sellerUrl) {
        this.sellerUrl = sellerUrl;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

//methods to retrieve or assign the relevant ID to an object.

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

// Sort Price method

    public static Comparator<Offer> sortPrice =new Comparator<Offer>() {
        @Override
        public int compare(Offer o1, Offer o2) {
            return (int) (o1.getPrice()-o2.getPrice());
        }
    };

    //sort Ratings method
    public static Comparator<Offer> sortRatings =new Comparator<Offer>() {
        @Override
        public int compare(Offer o1, Offer o2) {
            return o1.getReviewRating().compareTo(o2.getReviewRating());
        }
    };

    //Sort Shipping method
    public static Comparator<Offer> sortShipping=new Comparator<Offer>() {
        @Override
        public int compare(Offer o1, Offer o2) {
            return (int) (o1.getShipping()-o2.getShipping());
        }
    };

    //Sort Reviews method
    public static Comparator<Offer> sortReviews =new Comparator<Offer>() {
        @Override
        public int compare(Offer o1, Offer o2) {
            return o1.getReviewCount().compareTo(o2.getReviewCount());
        }
    };

}
