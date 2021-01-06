package com.frutixpress.automatic_discounts.model;

public class DiscountRule{

    Integer daysBeforeExpiry;

    Integer percentageDiscount;

    public DiscountRule(Integer daysBeforeExpiry, Integer percentageDiscount) {
        this.daysBeforeExpiry = daysBeforeExpiry;
        this.percentageDiscount = percentageDiscount;
    }

    public Integer getDaysBeforeExpiry() {
        return daysBeforeExpiry;
    }

    public void setDaysBeforeExpiry(Integer daysBeforeExpiry) {
        this.daysBeforeExpiry = daysBeforeExpiry;
    }

    public Integer getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Integer percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public String toString() {
        return "DiscountRule [daysBeforeExpiry=" + daysBeforeExpiry + ", percentageDiscount=" + percentageDiscount
                + "]";
    }
    
}
