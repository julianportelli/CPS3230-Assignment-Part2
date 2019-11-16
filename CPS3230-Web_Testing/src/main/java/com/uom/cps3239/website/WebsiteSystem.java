package com.uom.cps3239.website;

public class WebsiteSystem {
    private boolean inSite = true, loggedIn = false, loggedOut = false,
            inCart = false, inProductSearch = false, inProductPage = false , inCheckout = false;

    public boolean isInSite() {
        return inSite;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public boolean isInCart() {
        return inCart;
    }

    public boolean isInProductSearch() {
        return inProductSearch;
    }

    public boolean isInProductPage() {
        return inProductPage;
    }

    public boolean isInCheckout() {
        return inCheckout;
    }

    void loggingInGoodCredentials(){
        if(inSite && !loggedIn && loggedOut) {
            loggedIn = true;
            loggedOut = false;
        }
    }

    void loggingInBadCredentials(){
        if(inSite && !loggedIn && loggedOut) {
            loggedIn = false;
            loggedOut = true;
        }
    }

    void loggingOut(){
        if(inSite && !loggedOut && loggedIn){
            loggedOut = true;
            loggedIn = false;
        }
    }

    void searchingProducts(){
        if(inSite && !inProductSearch){
            inProductSearch = true;
            inProductPage = false;
            inCart = false;
            inCheckout = false;
        }
    }

    void viewingProduct(){
        if(inSite && inProductSearch && !inCart && !inProductPage && !inCheckout){
            inProductSearch = false;
            inProductPage = true;
        }
    }

    void addingProductToCart(){
        if(inSite && inProductPage && !inCart && !inProductSearch && !inCheckout){
            inProductPage = true;
        }
    }

    void viewingCart(){
        if(inSite && !inCart){
            inCart = true;
        }
    }

    void removingProductFromCart(){
        if(inSite && inCart && !inProductSearch && !inProductPage && !inCheckout){
            inCart = true;
        }
    }

    void checkingOut(){
        if(inSite && inCart && !inProductSearch && !inProductPage && !inCheckout){
            inCheckout = true;
            inCart = false;
        }
    }

}
