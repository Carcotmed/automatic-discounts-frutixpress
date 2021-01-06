
package com.frutixpress.automatic_discounts.model.graphql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "discountAutomaticBasicCreate",
    "discountAutomaticBasicUpdate"
})
public class CreateUpdateDiscountData {

    @JsonProperty("discountAutomaticBasicCreate")
    private DiscountAutomaticBasicCreate discountAutomaticBasicCreate;

    @JsonProperty("discountAutomaticBasicUpdate")
    private DiscountAutomaticBasicUpdate discountAutomaticBasicUpdate;

    @JsonProperty("discountAutomaticBasicCreate")
    public DiscountAutomaticBasicCreate getDiscountAutomaticBasicCreate() {
        return discountAutomaticBasicCreate;
    }

    @JsonProperty("discountAutomaticBasicCreate")
    public void setDiscountAutomaticBasicCreate(DiscountAutomaticBasicCreate discountAutomaticBasicCreate) {
        this.discountAutomaticBasicCreate = discountAutomaticBasicCreate;
    }

    @JsonProperty("discountAutomaticBasicUpdate")
    public DiscountAutomaticBasicUpdate getDiscountAutomaticBasicUpdate() {
        return discountAutomaticBasicUpdate;
    }

    @JsonProperty("discountAutomaticBasicUpdate")
    public void setDiscountAutomaticBasicUpdate(DiscountAutomaticBasicUpdate discountAutomaticBasicUpdate) {
        this.discountAutomaticBasicUpdate = discountAutomaticBasicUpdate;
    }

    

}
