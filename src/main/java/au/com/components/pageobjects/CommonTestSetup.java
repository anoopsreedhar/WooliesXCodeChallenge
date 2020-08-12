package au.com.components.pageobjects;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import au.com.components.support.AndroidDriverFactory;
import au.com.components.support.IOSDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Log
public class CommonTestSetup extends CommonScreen {
    public static AppiumDriver<? extends MobileElement> driver;

    public static Properties prop;

    public CommonTestSetup()  {
        super(driver);
        prop = new Properties();
        try {
            prop.load(new FileInputStream(System.getProperty("user.dir")+ "/src/main/resources/config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void driverInitialization() {
        final String platform = prop.getProperty("platform");
        try
        {
            if ("android".equalsIgnoreCase(platform)) {
                final AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory();
                driver= androidDriverFactory.getDriver();
            } else {
                final IOSDriverFactory iosDriverFactory = new IOSDriverFactory();
                driver = iosDriverFactory.getDriver();
            }
        }
        catch (Exception e)
        {
            log.info("Test driver not initialised....");
        }
    }
}
