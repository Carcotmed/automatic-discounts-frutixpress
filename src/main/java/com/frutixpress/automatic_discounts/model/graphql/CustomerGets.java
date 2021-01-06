
package com.frutixpress.automatic_discounts.model.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "value"
})
public class CustomerGets {

    @JsonProperty("value")
    private PercentageValue percentageValue;

    @JsonProperty("value")
    public PercentageValue getPercentageValue() {
        return percentageValue;
    }

    @JsonProperty("value")
    public void setPercentageValue(PercentageValue value) {
        this.percentageValue = value;
    }

}
