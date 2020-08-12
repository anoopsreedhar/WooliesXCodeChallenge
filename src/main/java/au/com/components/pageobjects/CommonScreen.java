package au.com.components.pageobjects;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import au.com.components.support.TestExecutionProperties;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;


public abstract class CommonScreen {
    @Value("${explicit.wait}")
    public int explicitWaitTime;

    public AppiumDriver<? extends MobileElement> driver;

    public CommonScreen(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
    }

    public AppiumDriver<? extends MobileElement> getDriver() {
        return driver;
    }

    public boolean isElementPresent(MobileElement mobileElement) {
        try {
            return mobileElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isiOSPlatform() {
        return TestExecutionProperties.isIOSPlatform();
    }

}
