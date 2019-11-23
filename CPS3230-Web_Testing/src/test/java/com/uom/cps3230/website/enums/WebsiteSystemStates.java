package com.uom.cps3230.website.enums;

public enum WebsiteSystemStates {
    LOGGED_IN, //Logged in successfully
    LOGGED_OUT, //Logged out successfully
    IN_CART, //In cart page
    IN_SEARCH, //Product has been searched
    IN_PRODUCT_PAGE, //After searching, product clicked and details viewed
    IN_CHECKOUT //In the checkout page, a final state in the FSM
}
