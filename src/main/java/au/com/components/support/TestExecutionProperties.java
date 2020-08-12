package au.com.components.support;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import org.openqa.selenium.Dimension;

public class TestExecutionProperties {
    private static String platform;
    private static long implicitWaitTimeInSeconds;

    public static void setPlatformOnce(String platform) {
        TestExecutionProperties.platform = platform;
    }

    public static boolean isIOSPlatform() {
        if (platform == null) {
            throwNullException("Set Platform");
        }
        return platform.equalsIgnoreCase("iOS");
    }

    private static Dimension windowSize;

    public static void setWindowSizeOnce(Dimension size) {
        windowSize = size;
    }

    public static Dimension getWindowSize() {
        if (windowSize == null) {
            throwNullException("windowSize");
        }
        return windowSize;
    }

    private static void throwNullException(String nullFieldName) {
        throw new NullPointerException(nullFieldName + " should not be nil. It should be set once at the beginning of the test run.");
    }

    public static void setImplicitWaitTimeOnce(long seconds) {
        implicitWaitTimeInSeconds = seconds;
    }

    public static long getImplicitWaitTime() {
        if (implicitWaitTimeInSeconds <= 0) {
            throw new RuntimeException("The implicit wait time should be greater than zero");
        }
        return implicitWaitTimeInSeconds;
    }
}
