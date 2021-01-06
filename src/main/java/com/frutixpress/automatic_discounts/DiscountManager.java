package com.frutixpress.automatic_discounts;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frutixpress.automatic_discounts.model.Product;
import com.frutixpress.automatic_discounts.model.ProductsResponse;

import com.frutixpress.automatic_discounts.model.Discount;
import com.frutixpress.automatic_discounts.model.DiscountRule;
import com.frutixpress.automatic_discounts.model.Metafield;
import com.frutixpress.automatic_discounts.model.MetafieldsResponse;

public class DiscountManager {

    final static HttpClient httpClient = HttpClient.newHttpClient();

    final static String PRODUCTSRESTENDPOINT = "https://frutixpressonline.myshopify.com/admin/api/2021-01/products.json";
    final static String PRODUCTBYIDENDPOINT = "https://frutixpressonline.myshopify.com/admin/api/2021-01/products/#{id}.json";
    final static String METAFIELDSOFPRODUCTRESTENDPOINT = "https://frutixpressonline.myshopify.com/admin/products/#{id}/metafields.json";
    final static String METAFIELDSPOSTRESTENDPOINT = "https://frutixpressonline.myshopify.com/admin/api/2021-01/products/#{product_id}/metafields.json";
    final static String METAFIELDSPUTDELETERESTENDPOINT = "https://frutixpressonline.myshopify.com/admin/api/2021-01/metafields/#{metafield_id}.json";

    private final String apiKey = "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";

    public void manageDiscounts() {

        List<Product> products = getAllProducts();

        for (Product p : products) {

            System.out.println("\n----------------Product:" + p + "---------------------\n");

            Discount updatedDiscount = calculateDiscount(p);

            if (shouldBeUpdated(p, updatedDiscount)) {
                System.out.println("\nHay que actualizar el descuento");
                updateDiscount(p, updatedDiscount);
            } else {
                System.out.println("\nNo es necesario actualizar el descuento");
            }

        }

    }

    private Double checkRealPrice(Product p) {
        Double res = null;

        Map<String, String> metafields;
        try {
            metafields = getMetaFieldsFromProduct(p);

            String realPrice = metafields.get("real_price");

            if (realPrice != null) {
                res = Double.parseDouble(realPrice);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return res;
    }

    private boolean shouldBeUpdated(Product p, Discount discount) {

        boolean res = false;

        Double realPrice = checkRealPrice(p);

        if (realPrice != null) {

            if (discount.getPercentageDiscount() == 0) {
                // Para que no se actualice, el compare debe estar vacío y el precio debe ser el
                // precio real

                Double price = Double.parseDouble(p.getVariants().get(0).getPrice());

                String comparingPrice = p.getVariants().get(0).getCompareAtPrice();

                Boolean check1 = comparingPrice == null;

                Boolean check2 = price.equals(realPrice);

                res = !(check1 && check2);

            } else {
                // Para que no se actualice, el compare debe ser el precio real y el precio debe
                // ser el precio calculado

                Double price = Double.parseDouble(p.getVariants().get(0).getPrice());

                String comparingPrice = p.getVariants().get(0).getCompareAtPrice();

                Double calculatedPrice = realPrice * (1 - (double) discount.getPercentageDiscount() / 100);

                Boolean check1 = (comparingPrice != null) && (realPrice.equals(Double.parseDouble(comparingPrice)));

                Boolean check2 = price.equals(calculatedPrice);

                res = !(check1 && check2);

            }

        } else {
            System.out.println(
                    "\nEl producto no tiene precio en el campo \"realPrice\", por lo que no será manejado automáticamente.");
        }

        return res;

    }

    private Discount calculateDiscount(Product p) {

        Discount res = new Discount(0);

        try {

            Map<String, String> metafields = getMetaFieldsFromProduct(p);

            if (metafields.containsKey("discount-percentage") && metafields.containsKey("expiry-days")) {

                List<Integer> discountPercentage = stringToIntList(metafields.get("discount-percentage"));
                List<Integer> expiryDays = stringToIntList(metafields.get("expiry-days"));

                List<DiscountRule> rules = new ArrayList<>();

                for (int i = 0; i <= expiryDays.size() - 1; i++) {
                    rules.add(new DiscountRule(expiryDays.get(i), discountPercentage.get(i)));
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                        Locale.ENGLISH);
                LocalDate expiryDateValue = LocalDate.parse(metafields.get("expiry_date"), formatter);

                Long daysBeforeExpiry = ChronoUnit.DAYS.between(LocalDate.now(), expiryDateValue);

                DiscountRule chosen = chooseMostRestrictiveDiscount(rules, daysBeforeExpiry);

                if (chosen != null) {
                    if (chosen.getPercentageDiscount() > 0 && chosen.getPercentageDiscount() < 100) {
                        res = new Discount(chosen.getPercentageDiscount());
                    } else {
                        System.out.println(
                                "\nEl porcentaje de descuento de la regla elegida no se encuentra en el rango (0,100), por lo que no se aplicará");
                    }
                } else {
                    res = new Discount(0);
                }

            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return res;

    }

    private DiscountRule chooseMostRestrictiveDiscount(List<DiscountRule> discountRules, Long daysBeforeExpiry) {

        DiscountRule chosen;
        // La regla elegida debe de ser la menor de todas las que sean mayor que los
        // dias que le queden al producto

        try {
            chosen = discountRules.stream().filter(x -> x.getDaysBeforeExpiry() >= daysBeforeExpiry) // Quitamos todas
                                                                                                     // las que aun no
                                                                                                     // apliquen
                    .sorted(Comparator.comparing(DiscountRule::getDaysBeforeExpiry)) // Ordenamos por días hasta
                                                                                     // caducar, de mayor a menor
                                                                                     // restrictivas
                    .findFirst().get(); // Nos quedamos con la primera
        } catch (NoSuchElementException | NullPointerException e) {
            chosen = null;
        }

        System.out.println("\nRegla de descuento elegida: " + chosen);

        return chosen;

    }

    private Map<String, String> getMetaFieldsFromProduct(Product p) throws IOException, InterruptedException {

        String productEndpoint = METAFIELDSOFPRODUCTRESTENDPOINT.replace("#{id}", p.getId().toString());

        //HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(productEndpoint))
                .setHeader("Content-Type", "application/json").setHeader("X-Shopify-Access-Token", apiKey).GET()
                .build();

        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        MetafieldsResponse responsePojo = mapper.readValue(response.body(), MetafieldsResponse.class);

        List<Metafield> metafieldsList = responsePojo.getMetafields();

        return metafieldsList.stream().collect(Collectors.toMap(Metafield::getKey, x -> x.getValue()));
    }

    private List<Integer> stringToIntList(String stringToParse) {

        String formattedString = stringToParse.substring(1, stringToParse.length() - 1);

        return Arrays.asList(formattedString.replace(" ", "").replace("\"", "").split(",")).stream()
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

    }

    public List<Product> getAllProducts() {

        List<Product> res = new ArrayList<>();

        //HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(PRODUCTSRESTENDPOINT))
                .setHeader("Content-Type", "application/json").setHeader("X-Shopify-Access-Token", apiKey).GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            ProductsResponse responsePojo = mapper.readValue(response.body(), ProductsResponse.class);

            res = responsePojo.getProducts();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return res;
    }

    public void updateDiscount(Product product, Discount discount) {

        final String MANAGEDTAG = "Price Automatically Managed";

        Double realPrice = checkRealPrice(product);

        List<String> tags = new ArrayList<>();
        tags.addAll(Arrays.asList(product.getTags().split(", ")));

        Product updatedProduct = product;
        if (discount.getPercentageDiscount() == 0) { // Hay que dejarle el precio real y quitarle el tag
            updatedProduct.getVariants().get(0).setPrice(realPrice.toString());
            updatedProduct.getVariants().get(0).setCompareAtPrice("null");

            if (tags.contains(MANAGEDTAG)) {
                tags.remove(MANAGEDTAG);
                String tagResult = "";
                for (String t : tags) {
                    tagResult += t + ", ";
                }
                updatedProduct.setTags(tagResult);
            }

        } else { // Hay que actualizarle el precio basandolo en el precio real, y si no esta el
                 // tag, añadirlo

            String newPrice = String.format("%.2f",
                    realPrice * (1 - ((double) discount.getPercentageDiscount() / 100)));
            updatedProduct.getVariants().get(0).setPrice(newPrice.toString());
            updatedProduct.getVariants().get(0).setCompareAtPrice(realPrice.toString());

            if (!tags.contains(MANAGEDTAG)) {
                tags.add(MANAGEDTAG);
                String tagResult = "";
                for (String t : tags) {
                    tagResult += t + ", ";
                }
                updatedProduct.setTags(tagResult);
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        String endpoint = PRODUCTBYIDENDPOINT.replace("#{id}", updatedProduct.getId().toString());

        //HttpClient client = HttpClient.newHttpClient();

        try {
            String body = "{\"product\":" + mapper.writeValueAsString(updatedProduct) + "}";

            body = body.replace("\"null\"", "null");

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint))
                    .setHeader("Content-Type", "application/json").setHeader("X-Shopify-Access-Token", apiKey)
                    .PUT(BodyPublishers.ofString(body)).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
