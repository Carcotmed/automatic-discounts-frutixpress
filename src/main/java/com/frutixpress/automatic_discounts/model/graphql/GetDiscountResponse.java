
package com.frutixpress.automatic_discounts.model.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "extensions"
})
public class GetDiscountResponse {

    @JsonProperty("data")
    private GetDiscountData data;
    @JsonProperty("extensions")
    private Extensions extensions;

    @JsonProperty("data")
    public GetDiscountData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(GetDiscountData data) {
        this.data = data;
    }

    @JsonProperty("extensions")
    public Extensions getExtensions() {
        return extensions;
    }

    @JsonProperty("extensions")
    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

}
