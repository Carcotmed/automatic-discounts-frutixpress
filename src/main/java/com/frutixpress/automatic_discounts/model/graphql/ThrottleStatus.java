
package com.frutixpress.automatic_discounts.model.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "maximumAvailable",
    "currentlyAvailable",
    "restoreRate"
})
public class ThrottleStatus {

    @JsonProperty("maximumAvailable")
    private Integer maximumAvailable;
    @JsonProperty("currentlyAvailable")
    private Integer currentlyAvailable;
    @JsonProperty("restoreRate")
    private Integer restoreRate;

    @JsonProperty("maximumAvailable")
    public Integer getMaximumAvailable() {
        return maximumAvailable;
    }

    @JsonProperty("maximumAvailable")
    public void setMaximumAvailable(Integer maximumAvailable) {
        this.maximumAvailable = maximumAvailable;
    }

    @JsonProperty("currentlyAvailable")
    public Integer getCurrentlyAvailable() {
        return currentlyAvailable;
    }

    @JsonProperty("currentlyAvailable")
    public void setCurrentlyAvailable(Integer currentlyAvailable) {
        this.currentlyAvailable = currentlyAvailable;
    }

    @JsonProperty("restoreRate")
    public Integer getRestoreRate() {
        return restoreRate;
    }

    @JsonProperty("restoreRate")
    public void setRestoreRate(Integer restoreRate) {
        this.restoreRate = restoreRate;
    }

}
