package au.com;

 /* Created by Anoop Sreedhar on 9/8/20.
 Copyright © 2020 Anoop Sreedhar. All rights reserved.
 */

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(
        jsonReport = "build/cucumber/ExtendedReport/cucumber.json",
        jsonUsageReport = "build/cucumber/ExtendedReport/cucumber-usage.json",
        usageReport = false,
        detailedReport = true,
        detailedAggregatedReport = false,
        overviewReport = true,
        overviewChartsReport = false,
        pdfPageSize = "A4 Landscape",
        toPDF = true,
        outputFolder = "build/cucumber/ExtendedReport/",
        retryCount = 0
)
@CucumberOptions(
        features = { "src/test/resources/features" },
        tags = {"@apiTest,@uiTest","~@ignore"},
        glue = "au/com/stepdefinitions",
        plugin = {
        "html:build/cucumber/ExtendedReport", "json:build/cucumber/ExtendedReport/cucumber.json",
        "pretty:build/cucumber/ExtendedReport/cucumber-pretty.txt",
        "usage:build/cucumber/ExtendedReport/cucumber-usage.json", "junit:build/cucumber/ExtendedReport/cucumber-results.xml" }
)
public class InvokeTests {
}