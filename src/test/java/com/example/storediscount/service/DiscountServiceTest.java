package com.example.storediscount.service;

import com.example.storediscount.config.DiscountConfig;
import com.example.storediscount.model.BillResponse;
import com.example.storediscount.model.Item;
import com.example.storediscount.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    @Mock
    private DiscountConfig discountConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Mocking discount configuration values
        when(discountConfig.getEmployee()).thenReturn(0.3);
        when(discountConfig.getAffiliate()).thenReturn(0.1);
        when(discountConfig.getCustomer()).thenReturn(0.05);
        when(discountConfig.getFlat()).thenReturn(5.0);
        when(discountConfig.getFlatThreshold()).thenReturn(100.0);
    }

    @Test
    public void testCalculateDiscounts() {
        User user = new User("1", "John", "EMPLOYEE", LocalDate.of(2020, 1, 1));
        List<Item> items = Arrays.asList(
                new Item("Laptop", 1000, false),
                new Item("Groceries", 200, true)
        );

        BillResponse result = discountService.calculateDiscounts(user, items);

        // Asserting the expected values based on the calculation
        assertEquals(1200.0, result.getTotalAmount());
        assertEquals(300.0, result.getPercentageDiscount());
        assertEquals(60.0, result.getFlatDiscount());
        assertEquals(840.0, result.getNetPayableAmount());
    }
}
