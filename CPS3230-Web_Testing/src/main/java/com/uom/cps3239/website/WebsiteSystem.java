package com.uom.cps3239.website;

public class WebsiteSystem {

    private boolean inSite = false;
    private boolean loggedIn = false;
    private boolean loggedOut = true;
    private boolean inCart = false;
    private boolean inProductSearch = false;
    private boolean inProductPage = false;
    private boolean inCheckout = false;

    public boolean isInSite() {
        return inSite;
    }

    public void setInSite(boolean inSite) {
        this.inSite = inSite;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public boolean isInProductSearch() {
        return inProductSearch;
    }

    public void setInProductSearch(boolean inProductSearch) {
        this.inProductSearch = inProductSearch;
    }

    public boolean isInProductPage() {
        return inProductPage;
    }

    public void setInProductPage(boolean inProductPage) {
        this.inProductPage = inProductPage;
    }

    public boolean isInCheckout() {
        return inCheckout;
    }

    public void setInCheckout(boolean inCheckout) {
        this.inCheckout = inCheckout;
    }

    public void visitingSite(){
        if(!inSite){
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
