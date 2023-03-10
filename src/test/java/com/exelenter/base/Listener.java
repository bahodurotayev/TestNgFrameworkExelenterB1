package com.exelenter.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.exelenter.utils.CommonMethods;
import com.exelenter.utils.Constants;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Listener implements ITestListener {
    ExtentReports  reports = new ExtentReports();
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(Constants.REPORT_FILEPATH);
    ExtentTest test;
    Instant startTime;
    Instant finishTime;

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());

        test = reports.createTest(result.getName());
        test.pass("Test case Passed : " + result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());

        test = reports.createTest(result.getName());
        test.fail("Test Case Failled : " + result.getName());
        test.addScreenCaptureFromPath(CommonMethods.takeScreenshot("FAIL/" + result.getName()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped : " + result.getName());
        test = reports.createTest(result.getName());
        test.skip("Test Case Skipped : " +result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Test Started ===" + context.getName() + " | " +context.getStartDate());

        //adding report----> start tracking & recording when test starts


        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Exelenter Test Report");
        sparkReporter.config().setReportName("Exelenter Project Test Report");

        reports.attachReporter(sparkReporter);

        startTime = Instant.now();

    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n************************************\n=== Test Finished ===" + context.getName() + " | " +context.getEndDate());
        reports.flush();

        finishTime = Instant.now();
        Duration totalTime = Duration.between(startTime, finishTime);

        /*long milliSeconds =totalTime.toMillis();
        System.out.println("milliSeconds = " + milliSeconds);*/

        long toHour = totalTime.toHours();

        long toMinute = totalTime.toMinutes();

        long toSecond = totalTime.toSeconds();

        System.out.println("Total test completion");
        System.out.println("toHour = " + toHour);
        System.out.println("toMinute = " + toMinute);
        System.out.println("toSecond = " + toSecond);

    }
}
