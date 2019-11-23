package test.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.recordsalePageObject;
import static com.uom.cps3230.website.slp.sleep;
import static org.junit.Assert.*;

public class recordsaleStepDefs {

    WebDriver browser;
    recordsalePageObject rpo;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Julian Portelli/Downloads/chromedriver_win32/chromedriver.exe");
        browser = new ChromeDriver();
        rpo = new recordsalePageObject(browser);
        browser.manage().window().maximize();
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

    @When("I log in using valid credentials")
    public void i_log_in_using_valid_credentials() {
        rpo.login(recordsalePageObject.email, recordsalePageObject.goodPassword);
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        assertTrue(rpo.getLoginSuccessText());
    }

    @When("I log in using invalid credentials")
    public void i_log_in_using_invalid_credentials() {
        rpo.login(recordsalePageObject.email, recordsalePageObject.badPassword);
    }

    @Then("I should not be logged in")
    public void i_should_not_be_logged_in() {
        sleep(2);
        rpo.getLoginFailedText();
        sleep(2);
    }

    @Given("I am a logged in user on the website")
    public void i_am_a_logged_in_user_on_the_website() {
        rpo.get();
        rpo.login(recordsalePageObject.email, recordsalePageObject.goodPassword);
        sleep(2);
        assertTrue(rpo.getLoginSuccessText());
        sleep(2);
    }

    @When("I search for a product")
    public void i_search_for_a_product() {
        rpo.searchOne();
        sleep(2);
    }

    @When("I select the first product in the list")
    public void i_select_the_first_product_in_the_list() {
        rpo.clickFirstImage();
    }

    @Then("I should see the product details")
    public void i_should_see_the_product_details() {
        sleep(2);
        rpo.productDetailsExist();
    }

    @Given("my shopping cart is empty")
    public void my_shopping_cart_is_empty() {
        rpo.clearCart();
        rpo.cartEmpty();
    }

    @When("I view the details of a product")
    public void i_view_the_details_of_a_product() {
        rpo.searchOne();
        sleep(2);
        rpo.clickFirstImage();
        sleep(2);
        rpo.productDetailsExist();
    }

    @When("I choose to buy the product")
    public void i_choose_to_buy_the_product() {
        sleep(2);
        rpo.addToCart();
    }

    @Then("my shopping cart should contain {int} item")
    public void my_shopping_cart_should_contain_item(int num) {
        sleep(3);
        assertEquals(rpo.countItemsInCart(), num);
    }

    @When("I add {int} products to my shopping cart")
    public void i_add_num_products_products_to_my_shopping_cart(int int1) {
        rpo.searchAndAddMultiple(int1);
    }

    @Then("my shopping cart should contain {int} items")
    public void my_shopping_cart_should_contain_num_products_items(int int1) {
        sleep(3);
        assertEquals(rpo.countItemsInCart(), int1);
    }

    @Given("my shopping cart has {int} products")
    public void my_shopping_cart_has_products(int num) {
        rpo.clearCart();
        rpo.searchAndAddMultiple(num);
        assertEquals(rpo.countItemsInCart(), num);
    }

    @When("I remove the first product in my cart")
    public void i_remove_the_first_product_in_my_cart() {
        rpo.goToCart();
        sleep(2);
        rpo.removeFirstProductInCart();
        sleep(2);
    }

}