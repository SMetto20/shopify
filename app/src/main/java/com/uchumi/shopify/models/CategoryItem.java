package com.uchumi.shopify.models;

public class CategoryItem {
    Integer itemId;
    Integer imageUrl;
    String name;
    String apiName;

    public CategoryItem(Integer itemId, Integer imageUrl, String name, String apiName) {
        this.itemId = itemId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.apiName = apiName;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiName() {return apiName;}

    public void setApiName(String apiName) {this.apiName = apiName;}
}
