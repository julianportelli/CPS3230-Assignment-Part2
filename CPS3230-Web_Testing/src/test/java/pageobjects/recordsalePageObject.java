package pageobjects;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class recordsalePageObject {

    public static String email = "cps3230test@gmail.com";
    public static String goodPassword = "Qwerty123";
    public static String badPassword = "123456";

    WebDriver browser;

    String prodNames[] = {"Sabaton", "Metallica", "Iron Maiden", "Powerwolf", "Red Hot Chili Peppers",
            "Linkin Park", "Queen", "Dire Straits", "Foo Fighters", "Led Zeppelin"};

    public recordsalePageObject(WebDriver driver) {
        this.browser = driver;
        browser.manage().window().maximize();
    }

    public void get() {
        browser.get("https://recordsale.de/en");
    }

    public void login(String email, String password){
        // Write code here that turns the phrase above into concrete actions
        browser.findElement(By.className("navbar__item--account")).click();
        browser.findElement(By.id("email")).sendKeys(email);
        browser.findElement(By.id("password")).sendKeys(password);
        browser.findElement(By.className("button--fill")).click();
        sleep(2); //for demo purposes
    }

    public boolean getLoginSuccessText(){
       return browser.findElement(By.className("navbar__item--account")).getText().contains("Hi,");
    }

    public boolean getLoginFailedText(){
        return browser.findElement(By.className("navbar__item--account")).getText().contains("Login");
    }

    public String getRandomItem(){
        Random rand = new Random();
        int rnd = rand.nextInt(prodNames.length);
        return prodNames[rnd];
    }

    public void searchOne(){
        browser.findElement(By.className("js-search")).sendKeys(prodNames[0]);
        browser.findElement(By.className("js-search")).submit();
    }

    public void searchById(int i){
        browser.findElement(By.className("js-search")).sendKeys(prodNames[i]);
        browser.findElement(By.className("js-search")).submit();
    }

    public void clickFirstImage(){
        browser.findElement(By.className("release-image")).click();
    }

    public void productDetailsExist(){
        assertNotNull(browser.findElement(By.className("recordPane")));
    }

    public void cartEmpty(){
        assertNull(browser.findElement(By.className("cart-icon")).getAttribute("data-content"));
    }

    public void addToCart(){
        browser.findElement(By.className("l-paneContent-buy")).findElement(By.className("button--fill")).submit();
    }

    public int countItemsInCart(){
        if(browser.findElement(By.className("cart-icon")).getAttribute("data-content") != null){
            return Integer.parseInt(browser.findElement(By.className("cart-icon")).getAttribute("data-content"));
        }
        return 0;
    }

    public void goToCart(){
        browser.findElement(By.className("navbar__item--cart")).click();
    }

    public void removeFirstProductInCart(){
        browser.findElement(By.className("cartItem-delete")).findElement(By.tagName("a")).click();
    }

    public void clearCart(){
//        if(countItemsInCart() >= 0){
//            goToCart();
//            while(countItemsInCart() > 0){
//                removeFirstProductInCart();
//                sleep(2);
//            }
//            sleep(2);
//        }
        goToCart();
        if(countItemsInCart() >= 0){
            while(countItemsInCart() > 0){
                removeFirstProductInCart();
                sleep(2);
            }
            sleep(2);
        }
    }

    public void searchAndAddMultiple(int n){
        for(int i=0; i<n; i++){
            sleep(2);
            searchById(i);
            sleep(2);
            clickFirstImage();
            sleep(2);
            addToCart();
            sleep(2);
        }
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }
}
