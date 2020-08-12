package au.com.utils;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import com.jayway.restassured.response.Response;
import lombok.extern.java.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static au.com.components.pageobjects.CommonTestSetup.prop;
import static com.jayway.restassured.RestAssured.given;

@Log
public class ApiClient {
    private String apiKey = "b709b89d1fb5441eb17c25d31a79fb79";
    private static final String basePath = "v2.0/forecast/daily";
    private String serverUrl;

    public ApiClient() {
        final String env = prop.getProperty("env");
        log.info("env is " + env);
        serverUrl = ServerType.valueOf(env).getServerUrl();
    }

    public boolean verifyTheTemperatureIsAboveMinimumTemperature(final Response response, final int minimumTemperature, final String date) {
        final String jsonPathFilter = String.format("data.findAll{forecast -> forecast.max_temp > %d && forecast.valid_date == '%s'}", minimumTemperature, date);
        final String matchingElement = response.getBody().jsonPath().getString(jsonPathFilter);
        return matchingElement != null;
    }

    public boolean verifyItIsNotSnowing(final Response response) {
        final String matchingElement = response.getBody().jsonPath().getString("data.findAll{forecast -> forecast.snow != 0}");
        return matchingElement == null;
    }

    public Response getResponse(final String location, final int noOfDaysOfForecast) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("key", apiKey);
        parameters.put("days", String.valueOf(noOfDaysOfForecast));
        parameters.put("city", location);

        final Response response = given()
                .header("Cookie", "X-Auth-Token=" + apiKey)
                .when()
                .basePath(basePath)
                .baseUri(serverUrl)
                .parameters(parameters)
                .get();
        return response;
    }

    private int findDiff(final int dayOfWeek, final int passDay)
    {
        if (dayOfWeek == passDay) {
            return 0;
        } else if (dayOfWeek > passDay) {
            return 7 - dayOfWeek;
        } else {
            return passDay - dayOfWeek;
        }
    }

    public String convertNextOccurrenceOfDayToDate(final String dayOfTheWeek){
        int difference = 0;
        final Calendar calendar = Calendar.getInstance();
        int dayOfWeekToday = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfTheWeek) {
            case "SUNDAY":
                difference= findDiff(dayOfWeekToday,1);
                break;
            case "MONDAY":
                difference= findDiff(dayOfWeekToday,2);
                break;
            case "TUESDAY":
                difference= findDiff(dayOfWeekToday,3);
                break;
            case "WEDNESDAY":
                difference= findDiff(dayOfWeekToday,4);
                break;
            case "THURSDAY":
                difference= findDiff(dayOfWeekToday,5);
                break;
            case "FRIDAY":
                difference= findDiff(dayOfWeekToday,6);
                break;
            case "SATURDAY":
                difference= findDiff(dayOfWeekToday,7);
                break;

        }
        final Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, difference);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }
}
