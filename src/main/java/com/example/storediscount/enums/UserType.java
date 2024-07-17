package com.example.storediscount.enums;

public enum UserType {
    EMPLOYEE("employee"),
    AFFILIATE("affiliate"),
    CUSTOMER("customer");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}

