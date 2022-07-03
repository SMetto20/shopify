
package com.uchumi.shopify.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Offers {

    @SerializedName("offers_count")
    @Expose
    private Integer offersCount;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;

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
    public Offers(Integer offersCount, List<Offer> offers) {
        super();
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

}
