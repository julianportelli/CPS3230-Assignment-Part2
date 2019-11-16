package com.uom.cps3239.website;

public class WebsiteSystem {

    private boolean inSite = true;
    private boolean loggedIn = false;
    private boolean loggedOut = true;
    private boolean inCart = false;
    private boolean inProductSearch = false;
    private boolean inProductPage = false;
    private boolean inCheckout = false;

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

    public void visitingSite(){
        if(inSite = false){
            inSite = true;
        }
    }

    public void loggingIn(){
        if(inSite && !loggedIn && loggedOut) {
            loggedIn = true;
        }
    }

    public void loggingOut(){
        if(inSite && loggedIn && !loggedOut){
            loggedIn = false;
        }
    }

    public void searchingProducts(){
        if(inSite && !inProductSearch){
            inProductSearch = true;
            inProductPage = false;
            inCart = false;
            inCheckout = false;
        }
    }

    public void viewingProduct(){
        if(inSite && inProductSearch && !inCart && !inProductPage && !inCheckout){
            inProductSearch = false;
            inProductPage = true;
        }
    }

    public void addingProductToCart(){
        if(inSite && inProductPage && !inCart && !inProductSearch && !inCheckout){
            inProductPage = true;
        }
    }

    public void viewingCart(){
        if(inSite && !inCart){
            inCart = true;
        }
    }

    public void removingProductFromCart(){
        if(inSite && inCart && !inProductSearch && !inProductPage && !inCheckout){
            inCart = true;
        }
    }

    public void checkingOut(){
        if(inSite && inCart && !inProductSearch && !inProductPage && !inCheckout){
            inCheckout = true;
            inCart = false;
        }
    }

}
