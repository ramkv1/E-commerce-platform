package com.ecommerce.entity.enums;

public enum Categories {
    ELECTRONICS,
    FASHION,
    HOME_APPLIANCES,
    BEAUTY,
    BOOKS,
    SPORTS,
    TOYS,
    AUTOMOTIVE,
    GROCERIES,
    HEALTH_CARE,
    FURNITURE;

    // Optional: Method to get a formatted string
    @Override
    public String toString() {
        return name().replace("_", " ").toLowerCase();
    }
}