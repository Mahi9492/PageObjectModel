package com.example.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class ResultsPage {
    private WebDriver driver;
    private By flightRows = By.cssSelector(".flight");

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Returns a list of flight descriptions and prices sorted ascending by price */
    public List<FlightDTO> getAllFlightsSortedByPrice() {
        List<WebElement> rows = driver.findElements(flightRows);
        List<FlightDTO> flights = new ArrayList<>();
        for (WebElement r : rows) {
            String airline = r.findElement(By.cssSelector(".airline")).getText();
            String flightNo = r.findElement(By.cssSelector(".flightno")).getText();
            String priceText = r.findElement(By.cssSelector(".price")).getText(); // like "$175"
            double price = parsePrice(priceText);
            flights.add(new FlightDTO(airline, flightNo, price));
        }
        return flights.stream().sorted(Comparator.comparingDouble(FlightDTO::getPrice)).collect(Collectors.toList());
    }

    private double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }

    public static class FlightDTO {
        private String airline;
        private String flightNo;
        private double price;

        public FlightDTO(String airline, String flightNo, double price) {
            this.airline = airline;
            this.flightNo = flightNo;
            this.price = price;
        }

        public String getAirline() { return airline; }
        public String getFlightNo() { return flightNo; }
        public double getPrice() { return price; }

        @Override
        public String toString() {
            return airline + " " + flightNo + " — $" + price;
        }
    }
}
