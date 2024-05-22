Feature: Administrator And Property Owner Role Distinction

  Scenario: Administrator Log-In
    Given The Log-In Page is displayed
    When I enter the Credentials "adminos" "Admin_p@ss"
    And I click the Log-In Button
    Then I am Logged-In as an Administrator

  Scenario: Property Owner Log-In
    Given The Log-In Page is displayed
    When I enter the Credentials "userPleb" "Us3rpleb"
    And I click the Log-In Button
    Then I am Logged-In as a Property Owner

  Scenario: Administrator Access Level
    Given I am Logged-In as an Administrator and I want to explore all Property Owners' Properties
    When I try to Access the List of other Property Owner Properties
    Then I can see All the Properties present in the Database from All the Property Owners

  Scenario: Property Owner Access Level
    Given I am Logged-In as a Property Owner and I want to explore all of My Properties
    When I try to Access the List of My Properties
    Then I can see All of My Properties present in the Database
