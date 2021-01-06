
package com.frutixpress.automatic_discounts.model.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "automaticDiscountNode"
})
public class GetDiscountData {

    @JsonProperty("automaticDiscountNode")
    private AutomaticDiscountNode automaticDiscountNode;

    @JsonProperty("automaticDiscountNode")
    public AutomaticDiscountNode getAutomaticDiscountNode() {
        return automaticDiscountNode;
    }

    @JsonProperty("automaticDiscountNode")
    public void setAutomaticDiscountNode(AutomaticDiscountNode automaticDiscountNode) {
        this.automaticDiscountNode = automaticDiscountNode;
    }

}
