# Travel POM Framework (Demo)

This is a demo automation framework (Java + Maven + TestNG + Selenium) that follows Page Object Model (POM). It contains a small local demo HTML page to demonstrate the following flow:

- Navigate to application (local demo HTML)
- Go to Flights section
- Enter source and destination
- Select a date for next month
- Click Search
- Identify & print cheapest and second cheapest flight details
- Open a new browser tab, navigate to Google
- Additional scenario: asserts at least 3 search results

## Prerequisites

- Java 11+
- Maven 3.6+
- Chrome browser

## Run tests

From project root:




mvn test


This will launch Chrome via WebDriverManager.

## Adapting to a real travel site

- Update `HomePage.open(url)` to the real site URL.
- Update locators in page objects (`HomePage`, `FlightsPage`, `ResultsPage`) to match real site DOM.
- For real sites with complex calendars, implement calendar navigation in `FlightsPage.selectDateNextMonth()`.

## Notes

- Uses thread-local `DriverFactory` to support later parallelization.
- The demo HTML is in `src/test/resources/demo/travel_demo.html`.



