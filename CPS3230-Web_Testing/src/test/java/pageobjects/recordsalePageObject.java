package pageobjects;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class recordsalePageObject {

    WebDriver browser;

    public recordsalePageObject(WebDriver driver) {
        this.browser = driver;
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

    public boolean getLoginSuccessText(String name){
       return browser.findElement(By.className("navbar__item--account")).getText().equals(name);
    }

    public boolean getLoginFailedText(String failed){
        return browser.findElement(By.className("u-boxed--error")).getText().contains(failed);
    }

    public void search(String searchTerm){
        browser.findElement(By.className("js-search")).sendKeys(searchTerm);
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
        browser.findElement(By.className("l-paneContent-buy")).findElement(By.className("button--fill")).click();
    }

    public int countItemsInCart(){
//        System.out.println(browser.findElement(By.className("cart-icon")).getAttribute("data-content"));
        return Integer.parseInt(browser.findElement(By.className("cart-icon")).getAttribute("data-content"));
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }
}
