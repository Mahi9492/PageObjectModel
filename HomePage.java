package com.example.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By flightsBtn = By.id("btnFlights");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void goToFlights() {
        driver.findElement(flightsBtn).click();
    }
}
