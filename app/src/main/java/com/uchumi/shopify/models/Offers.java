
package com.uchumi.shopify.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uchumi.shopify.models.Offer;

@Generated("jsonschema2pojo")
public class Offers {

    @SerializedName("offers_count")
    @Expose
    private Long offersCount;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;

    public Long getOffersCount() {
        return offersCount;
    }

    public void setOffersCount(Long offersCount) {
        this.offersCount = offersCount;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

}
