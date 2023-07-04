package com.example.steps;

import com.example.manage.SeleniumDriverSingleton;
import com.example.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() throws Exception {
        /**
         * implemeted singleton class for now memory leakage
         */
        driver = SeleniumDriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ;
        homePage = new HomePage(driver);


    }

    @Given("User open the moneycorp website")
    public void userOpenMoneyCorpWebsite() throws Exception {
        homePage.openMoneyCorpWebsite();
    }

    @When("User change the language and region to USA-English")
    public void userChangeTheLanguageAndRegionToUSAEnglish() throws Exception {
        homePage.changeRegion();
    }

    @Then("User verify that selected region is displayed")
    public void userVerifyThatSelectedRegionIsDisplayed() throws Exception {
        homePage.verifyUSAIsSelected();
    }

    @And("User click on find out more for Foreign exchange Solutions")
    public void userClickOnFindOutMoreForForeignExchangeSolutions() throws Exception {
        homePage.clickOnFindOutMoreForForeignExchangeSolutions();
    }

    @Then("User validate Foreign exchange Solutions page is displayed")
    public void userValidateForeignExchangeSolutionsPageIsDisplayed() throws Exception{
        homePage.verifyForeignExchangePage();
    }

    @And("User search for international payments")
    public void userSearchForInternationalPayments() {
        homePage.clickOnSearchboxAndEnterText("international payments");
    }

    @Then("User validate international payments page")
    public void userValidateInternationalPaymentsPage() throws Exception{
        homePage.verifyInternationalPaymentsPage();
    }


    @And("click show more results until not available")
    public void clickShowMoreResultsUntilNotAvailable() throws InterruptedException {

    }


    @Then("User verify all articles has link starts with en-us")
    public void userVerifyAllArticlesHasLinkStartingWithENUS() throws Exception {
        homePage.clickUntilNotVisibile();
        homePage.validateLinks();
    }

    @And("i close browser")
    public void iCloseBrowser() {
        driver.quit();
    }


    @After
    public void tearDown() {
//        // Quit the WebDriver instance after each scenario
//        if (driver != null) {
//            driver.quit();
//        }
        SeleniumDriverSingleton.quitDriver();
    }


}
