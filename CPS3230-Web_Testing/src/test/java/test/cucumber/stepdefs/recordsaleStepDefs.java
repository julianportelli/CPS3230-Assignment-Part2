package test.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.recordsalePageObject;

import static org.junit.Assert.*;

public class recordsaleStepDefs {

    WebDriver browser;
    recordsalePageObject rpo;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Julian Portelli/Downloads/chromedriver_win32/chromedriver.exe");
        browser = new ChromeDriver();
        rpo = new recordsalePageObject(browser);
    }

    @After
    public void teardown() {
        browser.quit();
        rpo = null;
    }

    @Given("I am a user on the website")
    public void i_am_a_user_on_the_website() {
        rpo.get();
    }

    @When("I log in using valid credentials {string} and {string}")
    public void i_log_in_using_valid_credentials(String email, String password) {
        rpo.login(email, password);
    }

    @Then("I should be logged in {string}")
    public void i_should_be_logged_in(String name) {
        assertTrue(rpo.getLoginSuccessText(name));
        sleep(2);
    }

    @When("I log in using invalid credentials {string} and {string}")
    public void i_log_in_using_invalid_credentials(String email, String badPassword) {
        // Write code here that turns the phrase above into concrete actions
        rpo.login(email, badPassword);
        sleep(2);
    }

    @Then("I should not be logged in {string}")
    public void i_should_not_be_logged_in(String failed) {
        sleep(2);
        rpo.getLoginFailedText(failed);
        sleep(2);
    }

    @Given("I am a logged in user on the website {string} and {string} and {string}")
    public void i_am_a_logged_in_user_on_the_website(String email, String password, String name) {
        rpo.get();
        rpo.login(email, password);
        sleep(5);
        assertTrue(rpo.getLoginSuccessText(name));
        sleep(2);
    }

    @When("I search for a product {string}")
    public void i_search_for_a_product(String searchTerm) {
        rpo.search(searchTerm);
        sleep(2);
    }

    @When("I select the first product in the list {string}")
    public void i_select_the_first_product_in_the_list(String className) {
        rpo.clickFirstImage(className);
    }

    @Then("I should see the product details")
    public void i_should_see_the_product_details() {
        sleep(2);
        rpo.productDetailsExist();
    }

    @Given("my shopping cart is empty")
    public void my_shopping_cart_is_empty() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("I view the details of a product")
    public void i_view_the_details_of_a_product() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("I choose to buy the product")
    public void i_choose_to_buy_the_product() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("my shopping cart should contain {int} item")
    public void my_shopping_cart_should_contain_item(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("I add <num-products> products to my shopping cart")
    public void i_add_num_products_products_to_my_shopping_cart() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("my shopping cart should contain <num-products> items")
    public void my_shopping_cart_should_contain_num_products_items() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("my shopping cart has {int} products")
    public void my_shopping_cart_has_products(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("I remove the first product in my cart")
    public void i_remove_the_first_product_in_my_cart() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }
}
