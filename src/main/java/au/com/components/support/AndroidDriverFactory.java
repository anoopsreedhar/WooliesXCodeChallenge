package au.com.components.support;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.java.Log;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Log
public class AndroidDriverFactory {

    @Value("${implicit.wait}")
    public int implicitWaitTime;

    public AppiumDriver<? extends MobileElement> getDriver() throws MalformedURLException {
        log.info("\nRunning Android Test");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_API_26");
        capabilities.setCapability("appPackage", "com.google.android.calendar");
        capabilities.setCapability("appActivity", "com.android.calendar.AllInOneActivity");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        final AppiumDriver<? extends MobileElement> driver = new io.appium.java_client.android.AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        TestExecutionProperties.setPlatformOnce("Android");
        TestExecutionProperties.setImplicitWaitTimeOnce(implicitWaitTime);
        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
        return driver;
    }
}