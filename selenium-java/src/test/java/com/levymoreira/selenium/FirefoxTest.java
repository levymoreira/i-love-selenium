package com.levymoreira.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class FirefoxTest {

    private String testUrl;
    private WebDriver driver;

    @Before
    public void prepare() {
    	testUrl = "https://levymoreira.com";
        System.setProperty("webdriver.gecko.driver","webdriver/geckodriver");
        driver = new FirefoxDriver();
        driver.get(testUrl);
    }

    @Test
    public void testTitle() throws IOException {

        List<WebElement> elements = driver
                .findElements(By.cssSelector("#main > div.archive > div:nth-child(4) > article > h2 > a"));

        elements.get(0).click();

        assertTrue("The page title should be chagned as expected",
                (new WebDriverWait(driver, 3))
                        .until(new ExpectedCondition<Boolean>() {
                            public Boolean apply(WebDriver d) {
                                return d.getTitle().equals("HTTPD Apache SSL configuration with a FREE valid certificate - TL;DR");
                            }
                        })
        );
    }

    @After
    public void teardown() throws IOException {
        driver.quit();
    }

}
