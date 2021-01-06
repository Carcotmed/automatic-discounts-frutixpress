
package com.frutixpress.automatic_discounts.model.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "requestedQueryCost",
    "actualQueryCost",
    "throttleStatus"
})
public class Cost {

    @JsonProperty("requestedQueryCost")
    private Integer requestedQueryCost;
    @JsonProperty("actualQueryCost")
    private Integer actualQueryCost;
    @JsonProperty("throttleStatus")
    private ThrottleStatus throttleStatus;

    @JsonProperty("requestedQueryCost")
    public Integer getRequestedQueryCost() {
        return requestedQueryCost;
    }

    @JsonProperty("requestedQueryCost")
    public void setRequestedQueryCost(Integer requestedQueryCost) {
        this.requestedQueryCost = requestedQueryCost;
    }

    @JsonProperty("actualQueryCost")
    public Integer getActualQueryCost() {
        return actualQueryCost;
    }

    @JsonProperty("actualQueryCost")
    public void setActualQueryCost(Integer actualQueryCost) {
        this.actualQueryCost = actualQueryCost;
    }

    @JsonProperty("throttleStatus")
    public ThrottleStatus getThrottleStatus() {
        return throttleStatus;
    }

    @JsonProperty("throttleStatus")
    public void setThrottleStatus(ThrottleStatus throttleStatus) {
        this.throttleStatus = throttleStatus;
    }

}
