
package com.frutixpress.automatic_discounts.model.graphql;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userErrors",
    "automaticDiscountNode"
})
public class DiscountAutomaticBasicUpdate {

    @JsonProperty("userErrors")
    private List<Object> userErrors = null;
    @JsonProperty("automaticDiscountNode")
    private AutomaticDiscountNode automaticDiscountNode;

    @JsonProperty("userErrors")
    public List<Object> getUserErrors() {
        return userErrors;
    }

    @JsonProperty("userErrors")
    public void setUserErrors(List<Object> userErrors) {
        this.userErrors = userErrors;
    }

    @JsonProperty("automaticDiscountNode")
    public AutomaticDiscountNode getAutomaticDiscountNode() {
        return automaticDiscountNode;
    }

    @JsonProperty("automaticDiscountNode")
    public void setAutomaticDiscountNode(AutomaticDiscountNode automaticDiscountNode) {
        this.automaticDiscountNode = automaticDiscountNode;
    }

}
