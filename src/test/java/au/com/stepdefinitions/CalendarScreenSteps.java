package au.com.stepdefinitions;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import au.com.components.pageobjects.CommonTestSetup;
import au.com.components.pageobjects.calendar.CalendarHomeScreen;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;

@Log
public class CalendarScreenSteps extends CommonTestSetup {

    public CalendarHomeScreen calendarHomeScreen;

    @Before()
    public void setUp(Scenario scenario) {
        driverInitialization();
        PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);
        calendarHomeScreen = new CalendarHomeScreen(driver);
        log.info(scenario.getName()+" - scenario test execution started");
    }

    @After()
    public void tearDown(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String currentDir = System.getProperty("user.dir");
            FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
            byte[] fileContent = Files.readAllBytes(scrFile.toPath());
            scenario.embed(fileContent,"image/png");
        }
        driver.quit();
        log.info(scenario.getName()+" - scenario test execution finished");
    }

    @Given("^I have launched the Calendar App$")
    public void iHaveLaunchedTheApplication() {
        calendarHomeScreen.checkHomeScreen();
    }

    @When("^It is not a weekend$")
    public void itIsNotAWeekend()  {
        assertFalse(calendarHomeScreen.checkWeekend());
    }

    @And("^It is not a public holiday$")
    public void itIsNotAPublicHoliday() {
        calendarHomeScreen.isTodayAHoliday();
    }

    @Then("^I want to book a meeting with the title \"([^\"]*)\"$")
    public void iWantToBookAMeetingWithTheTitle(final String title) {
        calendarHomeScreen.setMeetingTitle(title);
    }

    @And("^I save the meeting$")
    public void iSaveTheMeeting() throws Throwable {
        calendarHomeScreen.saveMeeting();
    }

    @Then("^I Check if the meeting is created as expected$")
    public void iCheckIfTheMeetingIsCreatedAsExpected() {
        if(isiOSPlatform()){
            calendarHomeScreen.checkEventCreated("Introduction, from 9:00 PM to 9:30 PM");
        }else{
            //Specific Element for android as the des
            calendarHomeScreen.checkEventCreated("Event Description matching to Android");
        }
    }

    @And("^Set Meeting duration as <(\\d+) Minutes> in the evening at \"([^\"]*)\"$")
    public void setMeetingDurationAsMinutesInTheEveningAt(int minute, String hour) throws Throwable {
        calendarHomeScreen.setMeetingDuration(minute+"",hour);
    }

    @And("^I invite people$")
    public void iInvitePeople() {
        calendarHomeScreen.sendInvite();
    }

}
