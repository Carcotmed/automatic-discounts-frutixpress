package com.frutixpress.automatic_discounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "id",
    "key",
    "value"
})
public class Metafield {
    
    @JsonProperty("id")
    private Long id;
    @JsonProperty("key")
    private String key;
    @JsonProperty("value")
    private String value;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Metafield [key=" + key + ", value=" + value + "]";
    }

    public Metafield(Long id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public Metafield() {
    }


}
