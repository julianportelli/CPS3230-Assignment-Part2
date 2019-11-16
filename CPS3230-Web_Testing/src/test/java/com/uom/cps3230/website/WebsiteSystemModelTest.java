package com.uom.cps3230.website;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebsiteSystemModelTest {
    WebDriver browser;

    @Before
    public void openBrowserVisitSite(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/Julian Portelli/Downloads/chromedriver_win32/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
    }

    public void quitBrowser() {
        browser.quit();
    }

}
