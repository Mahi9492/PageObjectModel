package com.example.framework.tests;

import com.example.framework.base.BaseTest;
import com.example.framework.pages.FlightsPage;
import com.example.framework.pages.HomePage;
import com.example.framework.pages.ResultsPage;
import com.example.framework.pages.ResultsPage.FlightDTO;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class FlightSearchTest extends BaseTest {

    @Test
    public void searchFlightsAndFindCheapest() throws Exception {
        // load local demo HTML shipped with the repo
        URL demo = getClass().getClassLoader().getResource("demo/travel_demo.html");
        Assert.assertNotNull(demo, "Demo HTML not found in resources/demo/travel_demo.html");
        String url = Paths.get(demo.toURI()).toUri().toString();

        HomePage home = new HomePage(driver);
        home.open(url);

        // go to Flights
        home.goToFlights();

        FlightsPage flights = new FlightsPage(driver);
        // enter source/destination
        flights.enterFrom("Mumbai");
        flights.enterTo("Bengaluru");

        // select date for next month
        flights.selectDateNextMonth();

        // click search
        flights.clickSearch();

        // results page: find cheapest and second cheapest
        ResultsPage results = new ResultsPage(driver);
        List<FlightDTO> list = results.getAllFlightsSortedByPrice();
        System.out.println("All flights sorted by price:");
        list.forEach(f -> System.out.println(f.toString()));

        if (list.size() >= 1) {
            System.out.println("Cheapest: " + list.get(0));
        }
        if (list.size() >= 2) {
            System.out.println("Second cheapest: " + list.get(1));
        }

        // Additional scenario: assert there are at least 3 results
        Assert.assertTrue(list.size() >= 3, "Expected at least 3 flights in results for demo");

        // Open new tab within same session and navigate to Google
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.google.com");
        System.out.println("Opened Google in new tab: " + driver.getTitle());

        // Switch back to original tab (first tab)
        var handles = driver.getWindowHandles().iterator();
        String firstHandle = handles.next();
        String secondHandle = handles.next();
        driver.switchTo().window(firstHandle);
    }
}
