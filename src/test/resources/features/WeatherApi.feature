#Created by Anoop Sreedhar on 9/8/20.
#Copyright © 2020 Anoop Sreedhar. All rights reserved.

Feature: Test for api interface

  Background: Weatherbit.io is a free to use website (and JSON API service) that can be used to query the weather
  forecast of major cities around the world. More information can be found here:
  https://www.weatherbit.io/api/weather-forecast-16-day

  @apiTest
  Scenario: A happy holidaymaker
    Given I like to holiday in "Sydney,AU"
    And I only like to holiday on "Thursday"
    When I look up the the weather forecast for the next 14 days
    Then I can see the max temperature on "Thursday" is always above 10 degrees in "Sydney,AU"
    And I can see that it won't be snowing for the next 14 days