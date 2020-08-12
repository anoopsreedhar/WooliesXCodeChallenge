package au.com.models;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestParameters {
    private String date;
    private String location;
    private int noOfDaysOfForecast;
}
