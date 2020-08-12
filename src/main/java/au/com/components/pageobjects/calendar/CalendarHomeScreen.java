package au.com.components.pageobjects.calendar;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import au.com.components.pageobjects.CommonTestSetup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;

@Log
public class CalendarHomeScreen extends CommonTestSetup {

    public AppiumDriver<? extends MobileElement> driver;

    @iOSFindBy(accessibility = "Continue")
    public MobileElement continueBtn;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Calendars']")
    public MobileElement lblCalendar;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name='Calendar']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeTextField")
    public MobileElement titleTxt;

    @iOSFindBy(accessibility = "Add")
    public MobileElement plusBtn;

    @iOSFindBy(accessibility = "Add")
    public MobileElement addBtn;

    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name='Starts']")
    public MobileElement datePickerStart;

    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name='Ends']")
    public MobileElement datePickerEnd;

    public CalendarHomeScreen(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);
    }

    public void checkHomeScreen(){
        if (driver.findElements(MobileBy.AccessibilityId("SplashScreen")).size()>0) {
            continueBtn.click();
        }else{
            Assert.assertTrue(isElementPresent(lblCalendar));
        }
    }

    public boolean checkWeekend() {
        return asList(Calendar.SUNDAY, Calendar.SATURDAY).contains(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
    }

    public void isTodayAHoliday() {
        final Calendar today = Calendar.getInstance();
        assertFalse(isHoliday(today));
    }

    public boolean isHoliday(final Calendar today) {
        final Map<Integer, List<Integer>> holidays = new HashMap<>();
        holidays.put(Calendar.JANUARY, asList(1, 26));
        holidays.put(Calendar.APRIL, asList(10, 11, 12, 13, 25));
        holidays.put(Calendar.JUNE, asList(8));
        holidays.put(Calendar.OCTOBER, asList(5));
        holidays.put(Calendar.DECEMBER, asList(25, 26));

        final int currentMonth = today.get(Calendar.MONTH);
        final int dayOfMonthToday = today.get(Calendar.DAY_OF_MONTH);

        final List<Integer> holidayDatesInMonth = holidays.get(currentMonth);
        return holidayDatesInMonth != null && holidayDatesInMonth.contains(dayOfMonthToday);
    }

    public void setMeetingTitle(String title) {
        plusBtn.click();
        titleTxt.sendKeys(title);
    }

    public void setMeetingDuration(String minute, String hour) throws Exception {
        datePickerStart.click();
        setTime(hour,"00","PM");
        datePickerEnd.click();
        setTime(hour,minute,"PM");
    }
    public void setTime(String hour,String minute,String dayPart) {
        List <MobileElement> timerValues = (List<MobileElement>) driver.findElements(By.xpath("//XCUIElementTypePickerWheel"));
        timerValues.get(1).sendKeys(hour);
        timerValues.get(2).sendKeys(minute);
        timerValues.get(3).sendKeys("PM");
    }

    public void saveMeeting() throws Exception {
        addBtn.click();
    }

    public void sendInvite() {
        /*
        unable to find option to send invite in Ios calender app in IOS device,
        Hence i am not able to provide script for this action
        */
    }
    public void checkEventCreated(String eventDescription) {
        driver.findElement(MobileBy.AccessibilityId(eventDescription));
    }
}
