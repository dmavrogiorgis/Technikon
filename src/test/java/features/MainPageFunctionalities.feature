Feature: Main Page Functionalities

  Scenario: Navigate From The Main Page To The Log In Page
    Given The Application is Up And Running "http://localhost:8080/api/owner/search?tin=123456789"
    When I click the Log-In Button to Navigate to the Log In Page
    Then I am Redirected to the Log In Page/the Log In Window Pops Up

  Scenario: Navigate From The Main Page To The Sign Up Page
    Given The Application is Up And Running "http://localhost:8080/api/owner/search?tin=123456789"
    When I click the Sign Up Button to Navigate to the Sign Up Page
    Then I am Redirected to the Sign Up Page/the Sign Up Window Pops Up

  Scenario: Navigate From The Log In To The Sign Up Page
    Given I am at the Log In Page
    When I click the Sign Up Button
    Then I am Redirected to the Sign Up Page/the Sign Up Window Pops Up

  Scenario: Navigate From The Log In To The Sign Up Page
    Given I am at the Sign Up Page
    When I click the Log In Button
    Then I am Redirected to the Log In Page/the Log In Window Pops Up