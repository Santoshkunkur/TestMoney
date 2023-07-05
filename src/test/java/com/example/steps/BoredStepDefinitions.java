package com.example.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BoredStepDefinitions {

    private String type;
    private Response response;
    JSONObject[] jsonObjects;
    private static final String BASE_URL = "https://www.boredapi.com/api/";

    private String appropriateSuggestions;
    private List<String> myList;
    public static Response getSuggestedActivities(int count, String type) {
        String endpoint = BASE_URL + "activity?type=" + type + "&participants="+Integer.toString(count);
        System.out.println(endpoint);
        return given()
                .config(RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                .when()
                .get(endpoint);
    }

    @When("^User request '([0-9])' activities for '([0-9])' people with type '(.*)'$")
    public void userRequestActivitiesForPeopleWithTypeSocial(int numberOfActivities, int countOfPeople,String type) {

        myList = new ArrayList<>();

        while (myList.size() < numberOfActivities) {
            response = getSuggestedActivities(countOfPeople, type);
            JsonPath jsonPath = response.jsonPath();
            String activity = jsonPath.get("activity");

            if (!myList.contains(activity)) {
                myList.add(activity);
            } else {
                System.out.println(myList);
                System.out.println("Element already exists in the list. Please enter a unique element.");
            }
        }
        System.out.println("List size reached "+Integer.toString(numberOfActivities)+" Elements: " + myList);
        Assert.assertEquals(myList.size(),numberOfActivities,"Number of activities are not matching");
    }


    @Then("User should receive appropriate suggestions")
    public void userShouldReceiveAppropriateSuggestions() {
        for (String suggestions : myList) {
            String str = String.valueOf(suggestions);
            System.out.println("activity: " + str);
        }

    }


}
