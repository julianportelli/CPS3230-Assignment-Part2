package com.uom.cps3239.website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class WebsiteSystem {

    WebDriver browser;

    private boolean loggedIn = false;
    private boolean loggedOut = true;
    private boolean inCart = false;
    private boolean inProductSearch = false;
    private boolean inProductPage = false;
    private boolean inCheckout = false;

    private String[] keywords = {"Sabaton", "Metallica", "Iron Maiden", "Powerwolf", "AC DC", "Beatles", "Linkin Park", "RHCP",
            "King Crimson", "Marty Robbins", "Nirvana", "Pink Floyd", "Dragonforce", "Queen", "Dire Straits", "Michael Jackson", "Rush"};

    private String getRandomKeyword(){
        Random random = new Random();
        int randomIndex = random.nextInt(keywords.length);
        return keywords[randomIndex];
    }

    public WebsiteSystem(WebDriver browser)  {
        //sleep(1); //for demo purposes
        this.browser = browser;
        browser.get("https://recordsale.de/en");
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

    public void loggingIn() {
        if(!loggedIn && loggedOut) {
            sleep(1); //for demo purposes
            browser.findElement(By.className("navbar__item--account")).click();
            sleep(2); //for demo purposes
            browser.findElement(By.id("email")).sendKeys("CPS3230Test@gmail.com");
            browser.findElement(By.id("password")).sendKeys("Qwerty123");
            browser.findElement(By.className("button--fill")).click();
            loggedIn = true;
            loggedOut = false;
        }
    }

    public void loggingOut() {
        if(loggedIn && !loggedOut){
            sleep(1); //for demo purposes
            browser.get("https://recordsale.de/en/logout");
            loggedIn = false;
            loggedOut = true;
        }
    }

    public void searchingProducts() {
        if(!inProductSearch && (loggedIn || loggedOut)){
            sleep(2);
            browser.findElement(By.className("js-search")).sendKeys(getRandomKeyword());
            browser.findElement(By.className("js-search")).submit();
            inProductSearch = true;
            inProductPage = false;
            inCart = false;
            inCheckout = false;
        }
    }

    public void viewingProduct() {
        if(inProductSearch && !inCart && !inProductPage && !inCheckout && (loggedIn || loggedOut)){
            sleep(2);
            browser.findElement(By.className("release-image")).click();
            inProductSearch = false;
            inProductPage = true;
        }
    }

    public void addingProductToCart() {
        if(inProductPage && !inCart && !inProductSearch && !inCheckout && (loggedIn || loggedOut)){
            sleep(2);
            List<WebElement> e = browser.findElements(By.className("l-paneContent-buy"));
            if(e.size() > 0) {  browser.findElement(By.className("l-paneContent-buy")).findElement(By.className("button--fill")).submit();}
        }
    }

    public boolean viewingCart() {
        if(!inCart && (loggedIn || loggedOut)){
            sleep(2);
            browser.findElement(By.className("navbar__item--cart")).click();
            inCart = true;
            return true;
        }
        return false;
    }

    public boolean removingProductFromCart() {
        if(inCart && !inProductSearch && !inProductPage && !inCheckout && (loggedIn || loggedOut)){
            if(countItemsInCart() > 0) {
                sleep(1);
                browser.findElement(By.className("cartItem-delete")).findElement(By.tagName("a")).click();
                inCart = true;
                return true;
            }
        }
        return false;
    }

    public boolean checkingOut() {
        if(inCart && !inProductSearch && !inProductPage && !inCheckout && (loggedIn || loggedOut)){
            sleep(2);
            if(countItemsInCart() > 0){
                inCheckout = true;
                inCart = false;
                browser.get("https://recordsale.de/en/checkout");
                return true;
            }
        }
        return false;
    }

    public int countItemsInCart(){
        if(browser.findElement(By.className("cart-icon")).getAttribute("data-content") != null){
            return Integer.parseInt(browser.findElement(By.className("cart-icon")).getAttribute("data-content"));
        }
        return 0;
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }
}
