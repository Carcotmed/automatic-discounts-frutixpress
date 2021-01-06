package com.frutixpress.automatic_discounts.model;

public class Discount {

    Integer percentageDiscount;

    public Integer getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Integer percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }


    public Discount(Integer percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
        
    }


    @Override
    public String toString() {
        return "Discount [percentageDiscount=" + percentageDiscount + "]";
    }

    

    

    
}
