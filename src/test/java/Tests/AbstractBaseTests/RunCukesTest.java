/*
 * Copyright 2014-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package Tests.AbstractBaseTests;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import Pages.NavigationPage;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * An abstract base for all of the Android tests within this package
 *
 * Responsible for setting up the Appium test Driver
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = {"src/test/resources/LoginTest/LoginTest.feature"},
        glue = "Tests",
        plugin = {"pretty"}
)
public class RunCukesTest {
    public static String getName() {
        return "Login Page";
    }

    public  static void setUpPage() {
    }
    final static String URL_STRING = "http://127.0.0.1:4723/wd/hub";
    private static NavigationPage navigationPage;
    public static AndroidDriver<MobileElement> driver;


    @BeforeClass
    public   static  void setUpAppium() throws MalformedURLException {

        System.out.println("in before class");
        URL url = new URL(URL_STRING);
        //Use a empty DesiredCapabilities object
        DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("autoGrantPermissions",true);
        //Set the DesiredCapabilities capabilities only for local development
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "app-debug.apk");
        capabilities.setCapability("appPackage", "com.amazonaws.devicefarm.android.referenceapp");
        capabilities.setCapability("appActivity", "com.amazonaws.devicefarm.android.referenceapp.Activities.MainActivity");
        capabilities.setCapability("udid", "emulator-5554");
        driver = new AndroidDriver<MobileElement>(url, capabilities);
        //Use a higher value if your mobile elements take time to show up
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("exit before suit"+ driver);
        navigationPage = new NavigationPage(driver);
        navigationPage.gotoCategory(getName());

    }

    /**
     * Always remember to quit
     */
    @AfterClass
    public static void  tearDownAppium() {
        driver.quit();
    }

    /**
     *
     *  Creates a navigation page and navigates to the Class' category
     *  within the navigation drawer
     *
     */





}