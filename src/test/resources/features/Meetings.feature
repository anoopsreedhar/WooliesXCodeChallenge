#Created by Anoop Sreedhar on 9/8/20.
#Copyright © 2020 Anoop Sreedhar. All rights reserved.

Feature: Create a new meeting using Native Android/iOS Calendar App

  Background:Using the native calendar app in either Android or iOS fulfill the below Feature tests.
  Kindly know that you can launch the app using AppPackage/startActivity or App bundle ID in Android and iOS respectively

  @uiTest
  Scenario: Verify user can create a meeting in default calender app
    Given I have launched the Calendar App
    When It is not a weekend
    And It is not a public holiday
    Then I want to book a meeting with the title "Introduction"
    And Set Meeting duration as <30 Minutes> in the evening at "9"
    And I invite people
    And I save the meeting
    Then I Check if the meeting is created as expected