
package com.uchumi.shopify.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class OffersResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("offers")
    @Expose
    private Offers offers;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OffersResponse() {
    }

    /**
     * 
     * @param offers
     * @param error
     */
    public OffersResponse(Boolean error, Offers offers) {
        super();
        this.error = error;
        this.offers = offers;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Offers getOffers() {
        return offers;
    }

    public void setOffers(Offers offers) {
        this.offers = offers;
    }

}
