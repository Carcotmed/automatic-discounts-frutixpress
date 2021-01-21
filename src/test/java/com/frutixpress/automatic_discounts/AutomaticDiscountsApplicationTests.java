package com.frutixpress.automatic_discounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.frutixpress.automatic_discounts.model.Discount;
import com.frutixpress.automatic_discounts.model.DiscountRule;
import com.frutixpress.automatic_discounts.model.Product;
import com.frutixpress.automatic_discounts.model.Variant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// import org.junit.jupiter.api.TestInstance.Lifecycle;

//@TestInstance(Lifecycle.PER_CLASS)
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
//@RunWith( SpringRunner.class )
@SpringBootTest


public class AutomaticDiscountsApplicationTests {

	@Autowired
	DiscountManager discountManager;

	List<Product> mockProductList = new ArrayList();
	List<Variant> mockVariantList = new ArrayList();
	Variant mockVariant = new Variant(1L, 1L, "12", "15");
	Product mockProduct = new Product(1L, "Patata", "status", "tags", mockVariantList);
	Discount mockDiscount = new Discount (50);
	DiscountRule mockDiscountRule1 = new DiscountRule(5, 20);
	DiscountRule mockDiscountRule2 = new DiscountRule(10, 5);
	List<DiscountRule> mockDiscountRuleList = new ArrayList();

	Map <String, String> mockMetafields = new HashMap();

	@Test
	public void test2() {
		System.out.println(discountManager.getAllProducts());
		assert(discountManager.getAllProducts().size() == 1);
	}

	@Test
	public void testCheckRealPrice() {
		assertEquals(discountManager.checkRealPrice(mockProduct), 2.15);
	}

	@Test
	public void testShouldBeUpdated() {
		assertTrue(discountManager.shouldBeUpdated(mockProduct, mockDiscount));
	}

	@Test
	public void testCalculateDiscount() {
		assertEquals(discountManager.calculateDiscount(mockProduct).getPercentageDiscount(), 10);
	}

	@Test
	public void testMostRestrictiveDiscount() {
		assertEquals(discountManager.chooseMostRestrictiveDiscount(mockDiscountRuleList, 4L), mockDiscountRule1);
	}


	@Before
	public void setup() {

		discountManager = Mockito.mock(DiscountManager.class);

		mockVariantList.add(mockVariant);
		mockProductList.add(mockProduct);
		mockDiscountRuleList.add(mockDiscountRule1);
		mockDiscountRuleList.add(mockDiscountRule2);

		mockMetafields.put("expiry_date", LocalDateTime.now().plusDays(19).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:00.000'Z'")));
		mockMetafields.put("expiry-days", "[\"5\",\"10\",\"20\",\"90\"]");
		mockMetafields.put("discount-percentage", "[\"30\",\"20\",\"10\",\"25\"]");
		mockMetafields.put("real_price", "2.15");

		
		when(discountManager.getAllProducts()).thenReturn(mockProductList);
		when(discountManager.getMetaFieldsFromProduct(mockProduct)).thenReturn(mockMetafields);
		when(discountManager.checkRealPrice(mockProduct)).thenCallRealMethod();
		when(discountManager.shouldBeUpdated(mockProduct, mockDiscount)).thenCallRealMethod();
		when(discountManager.calculateDiscount(mockProduct)).thenCallRealMethod();
		when(discountManager.stringToIntList((String)notNull())).thenCallRealMethod();
		when(discountManager.chooseMostRestrictiveDiscount(anyList(), (Long)notNull())).thenCallRealMethod();


	}



}
