package com.example.storediscount.service;

import com.example.storediscount.config.DiscountConfig;
import com.example.storediscount.enums.UserType;
import com.example.storediscount.model.BillResponse;
import com.example.storediscount.model.Item;
import com.example.storediscount.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Service class for calculating discounts based on user type and bill items.
 */
@Service
public class DiscountService {

    private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);

    @Autowired
    DiscountConfig discountConfig;

    /**
     * Calculates discounts based on the user type and items in the bill.
     *
     * @param user  The user for whom discounts are calculated.
     * @param items The list of items in the bill.
     * @return A {@link BillResponse} object containing total amount, percentage discount,
     * flat discount, and net payable amount after applying discounts.
     * @throws RuntimeException If an error occurs during discount calculation.
     */
    public BillResponse calculateDiscounts(@Valid @NotNull User user, @Valid @NotNull List<Item> items) {
        try {
            logger.info("Calculating discounts for user: {}", user.getName());

            double totalAmount = calculateTotalAmount(items);
            double percentageDiscount = calculatePercentageDiscount(user, items);
            double flatDiscount = calculateFlatDiscount(totalAmount);

            double discountedAmount = totalAmount - percentageDiscount - flatDiscount;
            double netPayableAmount = Math.max(0, discountedAmount);

            logger.info("Discount calculation successful for user: {}. Total amount: {}, Percentage discount: {}, Flat discount: {}, Net payable amount: {}",
                    user.getName(), totalAmount, percentageDiscount, flatDiscount, netPayableAmount);

            return new BillResponse(totalAmount, percentageDiscount, flatDiscount, netPayableAmount);
        } catch (Exception ex) {
            logger.error("Error calculating discounts for user: {}", user.getName(), ex);
            throw new RuntimeException("Error calculating discounts", ex);
        }
    }

    /**
     * Calculates the total amount of the bill items.
     *
     * @param items The list of items in the bill.
     * @return The total amount of the bill.
     */
    private double calculateTotalAmount(List<Item> items) {
        logger.debug("Calculating total amount of items in the bill");
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    /**
     * Calculates the percentage discount based on the user type and non-grocery items in the bill.
     *
     * @param user  The user for whom discounts are calculated.
     * @param items The list of items in the bill.
     * @return The total percentage discount based on the user type and non-grocery items.
     */
    private double calculatePercentageDiscount(User user, List<Item> items) {
        logger.debug("Calculating percentage discount for user: {}", user.getName());

        double percentageDiscount = 0;

        if (UserType.EMPLOYEE.getType().equalsIgnoreCase(user.getType())) {
            percentageDiscount = discountConfig.getEmployee();
        } else if (UserType.AFFILIATE.getType().equalsIgnoreCase(user.getType())) {
            percentageDiscount = discountConfig.getAffiliate();
        } else if (UserType.CUSTOMER.getType().equalsIgnoreCase(user.getType())) {
            LocalDate now = LocalDate.now();
            if (Period.between(user.getJoinedDate(), now).getYears() > 2) {
                percentageDiscount = discountConfig.getCustomer();
            }
        }

        double totalNonGroceryAmount = calculateTotalNonGroceryAmount(items);

        logger.debug("Percentage discount calculated for user: {}. Discount percentage: {}, Total non-grocery amount: {}",
                user.getName(), percentageDiscount, totalNonGroceryAmount);

        return totalNonGroceryAmount * percentageDiscount;
    }

    /**
     * Calculates the total amount of non-grocery items in the bill.
     *
     * @param items The list of items in the bill.
     * @return The total amount of non-grocery items.
     */
    private double calculateTotalNonGroceryAmount(List<Item> items) {
        logger.debug("Calculating total amount of non-grocery items in the bill");
        return items.stream()
                .filter(item -> !item.isGrocery())
                .mapToDouble(Item::getPrice)
                .sum();
    }

    /**
     * Calculates the flat discount based on the total amount of the bill.
     *
     * @param totalAmount The total amount of the bill.
     * @return The flat discount amount based on the total amount.
     */
    private double calculateFlatDiscount(double totalAmount) {
        logger.debug("Calculating flat discount based on total amount: {}", totalAmount);
        return Math.floor(totalAmount / 100) * 5;
    }
}
