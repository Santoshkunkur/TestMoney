package com.example.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class HomePage {


    private static final String MONEYCORP_URL = "https://www.moneycorp.com/en-gb/";

    private static Properties properties;
    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    //WebElements
    @FindBy(how= How.XPATH, using="(//button[@aria-label='Login'])[1]")
    private WebElement loginButton;

    @FindBy(how= How.XPATH, using="//a[@aria-label='USA English']")
    private WebElement usaEnglish;

    @FindBy(how= How.XPATH, using="//button[@id='language-dropdown-flag']")
    private WebElement languageButton;

    @FindBy(how= How.XPATH, using="//button/span/img[@src='/globalassets/images/icons/flags/united-states-of-america.svg']")
    private WebElement selectedLanguage;

    @FindBy(how= How.XPATH, using="//a[@href='/en-us/business/foreign-exchange-solutions/'][@aria-label='Find out more ']")
    private WebElement findOutMoreForeignExchange;

    @FindBy(how= How.XPATH, using="//h1[contains(text(),'Foreign exchange solutions for your business')]")
    private WebElement foreignExchangePageTitle;

    @FindBy(how= How.XPATH, using="(//form//input[@id='nav_search'])[2]")
    private WebElement foreignExchangeSearchBox;

    @FindBy(how= How.XPATH, using="//a[@aria-label='Regular International Payments']")
    private WebElement regularInternationalPaymentsLink;


    public HomePage(WebDriver driver) throws Exception {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor)driver;
    }

    public void waitForPageLoad() throws Exception{
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("return document.readyState").toString().equals("complete");
    }

    public void openMoneyCorpWebsite() throws Exception {
        driver.get(MONEYCORP_URL);
        waitForPageLoad();
        Assert.assertTrue(languageButton.isDisplayed(),"Money Corp page is not displayed");
    }

    public void changeLanguageAndRegion(String language, String region) throws Exception {
        waitForPageLoad();
        languageButton.click();
        waitForPageLoad();
        usaEnglish.click();

    }

    public void changeRegion() throws Exception {
        waitForPageLoad();
        languageButton.click();
        waitForPageLoad();
        usaEnglish.click();
    }

    public void verifyUSAIsSelected() throws Exception {
       waitForPageLoad();
       Assert.assertTrue(selectedLanguage.isDisplayed(),"USA is displayed ");
    }

    public void clickOnFindOutMoreForForeignExchangeSolutions() throws Exception {
        waitForPageLoad();
        js.executeScript("arguments[0].click();", findOutMoreForeignExchange);
        waitForPageLoad();
    }

    public void verifyForeignExchangePage() throws Exception{
        waitForPageLoad();
        Assert.assertTrue( foreignExchangePageTitle.isDisplayed(),"Foreign Exchange page is not displayed");
    }

    public void clickOnSearchboxAndEnterText(String text) {
        foreignExchangeSearchBox.click();
        foreignExchangeSearchBox.sendKeys(text);
        foreignExchangeSearchBox.sendKeys(Keys.ENTER);
    }

    public void verifyInternationalPaymentsPage() throws Exception{
        waitForPageLoad();
        wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Search Moneycorp')]")));
        Assert.assertTrue(element.isDisplayed(),"International Payments Page is not displayed");
    }

    public void clickUntilNotVisibile() throws InterruptedException {

        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 20);
        By showMoreResults = By.xpath("//a[contains(text(),'Show more results')]");
        boolean isElementVisible = true;
        int counter = 0;
        while (isElementVisible && counter < 5) {
            counter++;
            System.out.println(counter);
            // Wait for the element to be visible
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(showMoreResults));
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
            // Wait for 2 seconds
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Check if the element is still visible
            isElementVisible = !driver.findElements(showMoreResults).isEmpty();
            sleep(3000);

        }

    }

    public void validateLinks() throws InterruptedException {
        // Find all the articles on the page
        List<WebElement> articles = driver.findElements(By.xpath("//div[@class='results-wrapper']//div[@class='results']//a"));
        // Iterate through each article and validate the link
        for (WebElement article : articles) {
            String href = article.getAttribute("href");
            if (href != null && href.startsWith("https://www.moneycorp.com/en-us/")) {
                System.out.println("Link is valid: " + href);
            } else {
                System.out.println("Link is invalid: " + href);
            }

        }
        System.out.println(articles.size());

    }

}
