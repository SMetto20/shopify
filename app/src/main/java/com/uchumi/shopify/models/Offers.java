
package com.uchumi.shopify.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
@Generated("jsonschema2pojo")
public class Offers {

    @SerializedName("offers_count")
    @Expose
    private Integer offersCount;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;
    private String categoryTitle;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Offers() {
    }

    /**
     * 
     * @param offers
     * @param offersCount
     */
    public Offers(Integer offersCount, List<Offer> offers, String categoryTitle) {
        super();
        this.categoryTitle = categoryTitle;
        this.offersCount = offersCount;
        this.offers = offers;
    }

    public Integer getOffersCount() {
        return offersCount;
    }

    public void setOffersCount(Integer offersCount) {
        this.offersCount = offersCount;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }


}
