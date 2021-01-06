
package com.frutixpress.automatic_discounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "product_id",
    "price",
    "compare_at_price"
})
public class Variant {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("price")
    private String price;
    @JsonProperty("compare_at_price")
    private String compareAtPrice;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("product_id")
    public Long getProductId() {
        return productId;
    }

    @JsonProperty("product_id")
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("compare_at_price")
    public String getCompareAtPrice() {
        return compareAtPrice;
    }

    @JsonProperty("compare_at_price")
    public void setCompareAtPrice(String compareAtPrice) {
        this.compareAtPrice = compareAtPrice;
    }

    @Override
    public String toString() {
        return "Variant [compareAtPrice=" + compareAtPrice + ", price=" + price + "]";
    }

    

}
