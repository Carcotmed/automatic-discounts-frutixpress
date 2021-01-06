package com.frutixpress.automatic_discounts;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        System.out.println("RUNNING");
        DiscountManager manager = new DiscountManager();
        while (true) {
            manager.manageDiscounts();
            System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
            Thread.sleep(5000);
            
        }
    }
}
