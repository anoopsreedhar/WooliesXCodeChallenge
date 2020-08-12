package au.com.stepdefinitions;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import au.com.models.RequestParameters;
import au.com.utils.ApiClient;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;


public class ApiSteps {

    private ApiClient apiClient;
    private RequestParameters parameters;
    private Response response;

    public ApiSteps() {
        apiClient = new ApiClient();
        parameters = new RequestParameters();
    }

    @Given("^I like to holiday in \"([^\"]*)\"$")
    public void iLikeToHolidayIn(final String location) throws Throwable {
        parameters.setLocation(location);
    }

    @And("^I only like to holiday on \"([^\"]*)\"$")
    public void iOnlyLikeToHolidayOn(final String dayOfTheWeek) {
        parameters.setDate(apiClient.convertNextOccurrenceOfDayToDate(dayOfTheWeek.toUpperCase()));
    }

    @When("^I look up the the weather forecast for the next (\\d+) days$")
    public void iLookUpTheTheWeatherForecastForTheNextDays(final int noOfDaysOfForecast) {
        parameters.setNoOfDaysOfForecast(noOfDaysOfForecast);
        response = apiClient.getResponse(parameters.getLocation(), noOfDaysOfForecast);
    }

    @Then("^I can see the max temperature on \"([^\"]*)\" is always above (\\d+) degrees in \"([^\"]*)\"$")
    public void iCanSeeTheMaxTemperatureOnIsAlwaysAboveDegreesIn(final String dayOfTheWeek, final int minimumTemperature, final String location) {
        assertTrue(apiClient.convertNextOccurrenceOfDayToDate(dayOfTheWeek.toUpperCase()).equals(parameters.getDate()));
        assertTrue(location.equals(parameters.getLocation()));
        apiClient.verifyTheTemperatureIsAboveMinimumTemperature(response, minimumTemperature, parameters.getDate());
    }

    @And("^I can see that it won't be snowing for the next (\\d+) days$")
    public void iCanSeeThatItWonTBeSnowingForTheNextDays(int noOfDaysOfForecast) {
        assertTrue(noOfDaysOfForecast == parameters.getNoOfDaysOfForecast());
        apiClient.verifyItIsNotSnowing(response);
    }
}
