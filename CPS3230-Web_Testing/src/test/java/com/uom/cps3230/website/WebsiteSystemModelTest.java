package com.uom.cps3230.website;
import static org.junit.Assert.*;

import com.uom.cps3230.website.enums.WebsiteSystemStates;
import com.uom.cps3239.website.WebsiteSystem;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GraphListener;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.Tester;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import pageobjects.recordsalePageObject;

import java.util.Random;

public class WebsiteSystemModelTest implements FsmModel {
    /*
    This class assumes that a user logs in to search, view, add to cart,
     remove items from cart, view cart and checkout.
    */

    WebDriver browser;
    recordsalePageObject rpo;

    private WebsiteSystemStates modelState;
    private WebsiteSystem sut; //System under test
    private boolean inSite, loggedIn, loggedOut,
            inCart, inProductSearch, inProductPage, inCheckout;

    public WebsiteSystemStates getState() {
        return modelState;
    }

    @Before
    public void openBrowserVisitSite(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Julian Portelli/Downloads/chromedriver_win32/chromedriver.exe");
        browser = new ChromeDriver();
        rpo = new recordsalePageObject(browser);
        rpo.get();
        modelState = WebsiteSystemStates.IN_SITE;
    }

    @After
    public void quitBrowser() {
        browser.quit();
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    public void reset(final boolean b) {
        modelState = WebsiteSystemStates.IN_SITE;
        inSite = true;
        loggedIn = false;
        loggedOut = true;
        inCart = false;
        inProductSearch= false;
        inProductPage= false;
        inCheckout= false;

        if (b) {
            sut = new WebsiteSystem();
        }
    }

//    public @Action void visitSite(){
//        rpo.get();
//        sut.visitingSite();
//
//        inSite = true;
//        modelState = WebsiteSystemStates.IN_SITE;
//
//        assertEquals("The model's visit site in state doesn't match the SUT's state.", inSite, sut.isInSite());
//    }

    public boolean logInGuard(){
        return getState().equals(WebsiteSystemStates.LOGGED_OUT);
    }
    public @Action void logIn(){
        sut.loggingIn();
        sleep(2);
        rpo.login(recordsalePageObject.email, recordsalePageObject.goodPassword);
        loggedIn = true;
        loggedOut = false;
        modelState = WebsiteSystemStates.LOGGED_IN;

        assertEquals("The model's logged in state doesn't match the SUT's state.", loggedIn, sut.isLoggedIn());
    }

    public boolean logOutGuard(){
        return getState().equals(WebsiteSystemStates.LOGGED_IN);
    }
    public @Action void logOut(){
        rpo.logout();
        sut.loggingOut();

        loggedOut = true;
        loggedIn = false;
        modelState = WebsiteSystemStates.LOGGED_OUT;

        assertEquals("The model's logged out state doesn't match the SUT's state.", loggedOut, sut.isLoggedOut());
    }

    public boolean searchGuard(){
        return getState().equals(WebsiteSystemStates.LOGGED_IN);
    }
    public @Action void search(){
        rpo.searchOne();
        sut.searchingProducts();

        inProductSearch = true;
        modelState = WebsiteSystemStates.IN_SEARCH;

        assertTrue("The model's searching state doesn't match the SUT's state.", sut.isInProductSearch());
        assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
    }

    public boolean viewProductGuard(){
        return getState().equals(WebsiteSystemStates.IN_SEARCH);
    }
    public @Action void viewProduct(){
        rpo.clickFirstImage();
        sut.viewingProduct();

        inProductPage = true;
        inProductSearch = false;
        modelState = WebsiteSystemStates.IN_PRODUCT_PAGE;

        assertTrue("The model's viewing product state doesn't match the SUT's state.", sut.isInProductPage());
        assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
    }

    public boolean addProductGuard(){
        return getState().equals(WebsiteSystemStates.IN_PRODUCT_PAGE);
    }
    public @Action void addProduct(){
        rpo.addToCart();
        sut.addingProductToCart();

        inProductPage = true;
        inProductSearch = false;
        modelState = WebsiteSystemStates.IN_PRODUCT_PAGE;

        assertTrue("The model's viewing product state doesn't match the SUT's state.", sut.isInProductPage());
        assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
    }

    public boolean viewCartGuard(){
        return getState().equals(WebsiteSystemStates.IN_PRODUCT_PAGE);
    }
    public @Action void viewCart(){
        rpo.goToCart();
        sut.viewingCart();

        inProductPage = false;
        inProductSearch = false;
        inCart = true;
        modelState = WebsiteSystemStates.IN_CART;

        assertTrue("The model's in cart state doesn't match the SUT's state.", sut.isInCart());
        assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
    }

    public boolean removeProductGuard(){
        return getState().equals(WebsiteSystemStates.IN_CART);
    }
    public @Action void removeProduct(){
        rpo.removeFirstProductInCart();
        sut.removingProductFromCart();

        modelState = WebsiteSystemStates.IN_CART;

        assertTrue("The model's in cart state doesn't match the SUT's state.", sut.isInCart());
        assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
    }

    public boolean checkoutGuard(){
        return getState().equals(WebsiteSystemStates.IN_CART);
    }
    public @Action void checkout(){
        rpo.checkout();
        sut.checkingOut();

        inCart = false;
        inCheckout = true;
        modelState = WebsiteSystemStates.IN_CHECKOUT;

        assertTrue("The model's checkout product state doesn't match the SUT's state.", sut.isInCheckout());
        assertEquals("The model's logged in state doesn't match the SUT's state", loggedIn, sut.isLoggedIn());
    }

    @Test
    public void WebsiteSystemModelRunner(){
        final Tester tester = new GreedyTester(new WebsiteSystemModelTest());
        tester.setRandom(new Random());
        final GraphListener graphListener = tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(250);
        tester.printCoverage();
    }
}
