package au.com.utils;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import lombok.Getter;

@Getter
public enum ServerType {
    LOCAL("https://api.weatherbit.io/"),
    DEV("https://api.weatherbit.io/");

    private String serverUrl;

    ServerType(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
