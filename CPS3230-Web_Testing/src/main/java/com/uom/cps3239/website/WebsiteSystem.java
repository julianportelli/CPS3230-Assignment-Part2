package com.uom.cps3239.website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;

public class WebsiteSystem {

    WebDriver browser;

    private boolean loggedIn = false;
    private boolean loggedOut = true;
    private boolean inCart = false;
    private boolean inProductSearch = false;
    private boolean inProductPage = false;
    private boolean inCheckout = false;

    public WebsiteSystem(WebDriver driver){
        this.browser = driver;
        browser.get("https://recordsale.de/en");
        browser.manage().window().maximize();
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

    public void loggingIn() throws InterruptedException {
        if(!loggedIn && loggedOut) {
            browser.findElement(By.className("navbar__item--account")).click();
            sleep(2); //for demo purposes
            browser.findElement(By.id("email")).sendKeys("CPS3230Test@gmail.com");
            browser.findElement(By.id("password")).sendKeys("Qwerty123");
            browser.findElement(By.className("button--fill")).click();
            loggedIn = true;
            sleep(2); //for demo purposes
        }
    }

    public void loggingOut() throws InterruptedException {
        if(loggedIn && !loggedOut){
            browser.get("https://recordsale.de/en/logout");
            loggedIn = false;
            loggedOut = true;
            sleep(2); //for demo purposes
        }
    }

    public void searchingProducts() throws InterruptedException {
        if(!inProductSearch){
            browser.findElement(By.className("js-search")).sendKeys("Sabaton");
            browser.findElement(By.className("js-search")).submit();
            inProductSearch = true;
            inProductPage = false;
            inCart = false;
            inCheckout = false;
        }
        sleep(2);
    }

    public void viewingProduct() throws InterruptedException {
        if(inProductSearch && !inCart && !inProductPage && !inCheckout){
            browser.findElement(By.className("release-image")).click();
            inProductSearch = false;
            inProductPage = true;

        }
        sleep(2);
    }

    public void addingProductToCart() throws InterruptedException {
        if(inProductPage && !inCart && !inProductSearch && !inCheckout){
            List<WebElement> e = browser.findElements(By.className("l-paneContent-buy"));
            if(e.size() > 0) {  browser.findElement(By.className("l-paneContent-buy")).findElement(By.className("button--fill")).submit();}
        }
        sleep(2);
    }

    public void viewingCart() throws InterruptedException {
        if(!inCart){
            browser.findElement(By.className("navbar__item--cart")).click();
            inCart = true;
        }
        sleep(2);
    }

    public void removingProductFromCart() throws InterruptedException {
        if(inCart && !inProductSearch && !inProductPage && !inCheckout){
            browser.findElement(By.className("cartItem-delete")).findElement(By.tagName("a")).click();
            inCart = true;
        }
        sleep(2);
    }

    public void checkingOut() throws InterruptedException {
        if(inCart && !inProductSearch && !inProductPage && !inCheckout){
            if(countItemsInCart() > 0){
                browser.get("https://recordsale.de/en/checkout");
            }
            inCheckout = true;
            inCart = false;
        }
        sleep(2);
    }

    public int countItemsInCart(){
        if(browser.findElement(By.className("cart-icon")).getAttribute("data-content") != null){
            return Integer.parseInt(browser.findElement(By.className("cart-icon")).getAttribute("data-content"));
        }
        return 0;
    }
}
