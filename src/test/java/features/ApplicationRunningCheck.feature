Feature: Check That The Application Is Up And Running

  Scenario: Get A 200 Response Code From A Get Http Request
    Given That the Endpoint is Up and Running
    When We make a GET Request for the Property Owner with TIN "123456789"
    Then We Get a response that contains the Details of that Property Owner "{string}"