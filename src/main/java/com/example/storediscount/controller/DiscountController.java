package com.example.storediscount.controller;

import com.example.storediscount.model.BillRequest;
import com.example.storediscount.model.BillResponse;
import com.example.storediscount.model.Item;
import com.example.storediscount.model.User;
import com.example.storediscount.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST Controller for handling discount calculation requests.
 */
@RestController
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    /**
     * Endpoint to calculate discounts based on the provided bill request.
     *
     * @param request The bill request containing user information and items.
     * @return ResponseEntity with the calculated {@link BillResponse} or error message.
     */
    @PostMapping("/discounts")
    public ResponseEntity<?> calculateDiscounts(@RequestBody @Valid BillRequest request) {
        try {
            User user = request.getUser();
            List<Item> items = request.getItems();

            // Calculate discounts using DiscountService
            BillResponse response = discountService.calculateDiscounts(user, items);

            // Return HTTP 200 OK with the calculated response
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            // Handle validation or business logic errors
            return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
        } catch (Exception ex) {
            // Log the exception (consider using a logging framework like SLF4J)
            ex.printStackTrace();

            // Return HTTP 500 Internal Server Error for unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error calculating discounts");
        }
    }
}

