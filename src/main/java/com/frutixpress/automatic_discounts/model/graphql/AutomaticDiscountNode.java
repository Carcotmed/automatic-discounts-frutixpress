
package com.frutixpress.automatic_discounts.model.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "automaticDiscount"
})
public class AutomaticDiscountNode {

    @JsonProperty("id")
    private String id;
    @JsonProperty("automaticDiscount")
    private AutomaticDiscount automaticDiscount;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("automaticDiscount")
    public AutomaticDiscount getAutomaticDiscount() {
        return automaticDiscount;
    }

    @JsonProperty("automaticDiscount")
    public void setAutomaticDiscount(AutomaticDiscount automaticDiscount) {
        this.automaticDiscount = automaticDiscount;
    }

}
