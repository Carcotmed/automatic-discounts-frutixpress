package com.frutixpress.automatic_discounts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "metafields"
})
public class MetafieldsResponse {
    
    @JsonProperty("metafields")
    private List<Metafield> metafields = null;

    @JsonProperty("metafields")
    public List<Metafield> getMetafields() {
        return metafields;
    }

    @JsonProperty("metafields")
    public void setMetafields(List<Metafield> metafields) {
        this.metafields = metafields;
    }

}
