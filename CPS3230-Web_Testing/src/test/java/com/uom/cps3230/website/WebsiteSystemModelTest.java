package com.uom.cps3230.website;
import static com.uom.cps3230.website.slp.sleep;
import static org.junit.Assert.*;

import com.uom.cps3230.website.enums.WebsiteSystemStates;
import com.uom.cps3239.website.WebsiteSystem;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;


public class WebsiteSystemModelTest implements FsmModel {
    /*
    This class assumes that a user logs in to search, view, add to cart,
     remove items from cart, view cart and checkout.
    */

    private WebDriver driver;
    private WebsiteSystemStates modelState;
    private WebsiteSystem sut; //System under test
    private boolean loggedIn, loggedOut,
            inCart, inProductSearch, inProductPage, inCheckout;

    public WebsiteSystemStates getState() {
        return modelState;
    }

    WebsiteSystemModelTest(WebDriver driver){
        this.driver = driver;
        sut = new WebsiteSystem(driver);
    }

    public void reset(final boolean b) {
        modelState = WebsiteSystemStates.LOGGED_OUT;
        loggedIn = false;
        loggedOut = true;
        inCart = false;
        inProductSearch = false;
        inProductPage = false;
        inCheckout = false;
        sut.loggingOut();

        if (b) {
            sut = new WebsiteSystem(driver);
        }
    }

    public boolean logInGuard(){
        return getState().equals(WebsiteSystemStates.LOGGED_OUT);
    }
    public @Action void logIn() throws Exception {
        sut.loggingIn();

        sleep(2);

        inProductSearch = false;
        inCheckout = false;
        inProductPage = false;
        inCart = false;
        loggedIn = true;
        loggedOut = false;

        modelState = WebsiteSystemStates.LOGGED_IN;
        assertEquals("The model's logged in state doesn't match the SUT's state.", loggedIn, sut.isLoggedIn());
    }

    public boolean logOutGuard(){
        return getState().equals(WebsiteSystemStates.LOGGED_IN);
    }
    public @Action void logOut() throws InterruptedException {
        sut.loggingOut();

        sleep(2);

        inProductSearch = false;
        inCheckout = false;
        inProductPage = false;
        inCart = false;
        loggedOut = true;
        loggedIn = false;

        modelState = WebsiteSystemStates.LOGGED_OUT;
        assertEquals("The model's logged out state doesn't match the SUT's state.", loggedOut, sut.isLoggedOut());
    }

    public boolean searchGuard(){
        return (getState().equals(WebsiteSystemStates.LOGGED_IN)) || (getState().equals(WebsiteSystemStates.LOGGED_OUT));
    }
    public @Action void search() throws InterruptedException {
        sut.searchingProducts();

        sleep(2);

//        loggedIn = true;
//        loggedOut = false;
        inProductSearch = false;
        inCheckout = false;
        inCart = false;
        inProductSearch = true;

        modelState = WebsiteSystemStates.IN_SEARCH;
        assertTrue("The model's searching state doesn't match the SUT's state.", sut.isInProductSearch());
//        assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
    }

    public boolean viewProductGuard(){
        return getState().equals(WebsiteSystemStates.IN_SEARCH);
    }
    public @Action void viewProduct() throws InterruptedException {
        sut.viewingProduct();

        sleep(1);

//        loggedOut = false;
//        loggedIn = true;
        inCheckout = false;
        inCart = false;
        inProductPage = true;
        inProductSearch = false;

        modelState = WebsiteSystemStates.IN_PRODUCT_PAGE;
        assertTrue("The model's viewing product state doesn't match the SUT's state.", sut.isInProductPage());
//        assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
    }

    public boolean addProductGuard(){
        return getState().equals(WebsiteSystemStates.IN_PRODUCT_PAGE);
    }
    public @Action void addProduct() throws InterruptedException {
        sut.addingProductToCart();

        sleep(2);

//        loggedOut = false;
//        loggedIn = true;
        inCheckout = false;
        inCart = false;
        inProductPage = true;
        inProductSearch = false;

        modelState = WebsiteSystemStates.IN_PRODUCT_PAGE;

        assertTrue("The model's viewing product state doesn't match the SUT's state.", sut.isInProductPage());
//        assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
    }


    public @Action void viewCart() throws InterruptedException {
        if(sut.viewingCart()){

            sleep(1);

//            loggedOut = false;
//            loggedIn = true;
            inProductSearch = false;
            inCheckout = false;
            inProductPage = false;
            inCart = true;

            modelState = WebsiteSystemStates.IN_CART;
            assertTrue("The model's in cart state doesn't match the SUT's state.", sut.isInCart());
//            assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
        }

    }

    public boolean removeProductGuard(){
        return getState().equals(WebsiteSystemStates.IN_CART);
    }
    public @Action void removeProduct() throws InterruptedException {
        if(sut.removingProductFromCart()){
            sleep(1);

//            loggedOut = false;
//            loggedIn = true;
            inProductSearch = false;
            inCheckout = false;
            inProductPage = false;
            inCart = true;

            modelState = WebsiteSystemStates.IN_CART;
            assertTrue("The model's in cart state doesn't match the SUT's state.", sut.isInCart());
//            assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
        }
    }

    public boolean checkoutGuard(){
        return getState().equals(WebsiteSystemStates.IN_CART);
    }
    public @Action void checkout() throws InterruptedException {
        if(sut.checkingOut()){
            sleep(1);

//            loggedOut = false;
//            loggedIn = true;
            inCheckout = false;
            inProductSearch = false;
            inProductPage = false;
            inCart = false;
            inCheckout = true;

            modelState = WebsiteSystemStates.IN_CHECKOUT;
            assertTrue("The model's checkout state doesn't match the SUT's state.", sut.isInCheckout());
//            assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
        }
    }

}
