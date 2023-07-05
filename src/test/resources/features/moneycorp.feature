Feature: Moneycorp website testing

  @moneyCorp
  Scenario: Verify moneycorp website
    Given User open the moneycorp website
    When User change the language and region to USA-English
    Then User verify that selected region is displayed
    And User click on find out more for Foreign exchange Solutions
    Then User validate Foreign exchange Solutions page is displayed
    And User search for international payments
    Then User validate international payments page
    And User verify all articles has link starts with en-us


