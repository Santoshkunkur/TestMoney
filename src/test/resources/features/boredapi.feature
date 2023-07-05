Feature: Gererate the activity

  @boredapi
  Scenario: Generating three social activities for two people
    When User request '3' activities for '2' people with type 'social'
    Then User should receive appropriate suggestions