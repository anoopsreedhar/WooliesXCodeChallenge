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
public class IOSDriverFactory {

    @Value("${implicit.wait}")
    public int implicitWaitTime;

    public AppiumDriver<? extends MobileElement> getDriver() throws MalformedURLException {
        log.info("\nRunning iOS Tests");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X");
        capabilities.setCapability("bundleId", "com.apple.mobilecal");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);

        final AppiumDriver<? extends MobileElement> driver = new io.appium.java_client.ios.IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        TestExecutionProperties.setPlatformOnce("iOS");
        TestExecutionProperties.setWindowSizeOnce(driver.manage().window().getSize());
        TestExecutionProperties.setImplicitWaitTimeOnce(implicitWaitTime);
        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
        return driver;
    }
}
