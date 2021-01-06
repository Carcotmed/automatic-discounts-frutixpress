
package com.frutixpress.automatic_discounts.model.graphql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "createdAt",
    "customerGets",
    "shortSummary",
    "summary",
    "minimumRequirement",
    "endsAt"
    
})
public class AutomaticDiscount {

    @JsonProperty("title")
    private String title;
    @JsonProperty("shortSummary")
    private String shortSummary;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("customerGets")
    private CustomerGets customerGets;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("minimumRequirement")
    private MinimumRequirement minimumRequirement;
    @JsonProperty("endsAt")
    private String endsAt;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("shortSummary")
    public String getShortSummary() {
        return shortSummary;
    }

    @JsonProperty("shortSummary")
    public void setShortSummary(String shortSummary) {
        this.shortSummary = shortSummary;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("customerGets")
    public CustomerGets getCustomerGets() {
        return customerGets;
    }

    @JsonProperty("customerGets")
    public void setCustomerGets(CustomerGets customerGets) {
        this.customerGets = customerGets;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("minimumRequirement")
    public MinimumRequirement getMinimumRequirement() {
        return minimumRequirement;
    }

    @JsonProperty("minimumRequirement")
    public void setMinimumRequirement(MinimumRequirement minimumRequirement) {
        this.minimumRequirement = minimumRequirement;
    }

    @JsonProperty("endsAt")
    public String getEndsAt() {
        return endsAt;
    }

    @JsonProperty("endsAt")
    public void setEndsAt(String endsAt) {
        this.endsAt = endsAt;
    }
}
