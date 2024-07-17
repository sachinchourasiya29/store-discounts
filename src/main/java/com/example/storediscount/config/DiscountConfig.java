package com.example.storediscount.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "discount")
public class DiscountConfig {

    private double employee;
    private double affiliate;
    private double customer;
    private double flat;
    private double flatThreshold;

    public double getEmployee() {
        return employee;
    }

    public void setEmployee(double employee) {
        this.employee = employee;
    }

    public double getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(double affiliate) {
        this.affiliate = affiliate;
    }

    public double getCustomer() {
        return customer;
    }

    public void setCustomer(double customer) {
        this.customer = customer;
    }

    public double getFlat() {
        return flat;
    }

    public void setFlat(double flat) {
        this.flat = flat;
    }

    public double getFlatThreshold() {
        return flatThreshold;
    }

    public void setFlatThreshold(double flatThreshold) {
        this.flatThreshold = flatThreshold;
    }
}
