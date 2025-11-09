package com.example.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightsPage {
    private WebDriver driver;

    private By fromInput = By.id("from");
    private By toInput = By.id("to");
    private By dateInput = By.id("date");
    private By searchBtn = By.id("btnSearch");

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFrom(String src) {
        driver.findElement(fromInput).clear();
        driver.findElement(fromInput).sendKeys(src);
    }

    public void enterTo(String dst) {
        driver.findElement(toInput).clear();
        driver.findElement(toInput).sendKeys(dst);
    }

    /** Select a date in the next month: sets the date input to the 15th of next month */
    public void selectDateNextMonth() {
        LocalDate nextMonth = LocalDate.now().plusMonths(1).withDayOfMonth(15);
        String dateStr = nextMonth.format(DateTimeFormatter.ISO_LOCAL_DATE);
        // Some date inputs are readonly — set via JS for demo
        ((JavascriptExecutor) driver).executeScript("document.getElementById('date').value = arguments[0];", dateStr);
    }

    public void clickSearch() {
        driver.findElement(searchBtn).click();
    }
}
