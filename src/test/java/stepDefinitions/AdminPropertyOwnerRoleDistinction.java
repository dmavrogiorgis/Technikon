package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static stepDefinitions.TestSetup.driver;


//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%% Feature: Administrator And Property Owner Role Distinction %%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

public class AdminPropertyOwnerRoleDistinction {

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// %%%%%% Scenario: Administrator Log-In %%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// Given The Log-In Page is displayed

    @Given("The Log-In Page is displayed")
    public void loginButtonIsDisplayed() {
        WebElement loginPage = driver.findElement(By.id("logInPageLogInTitle"));
        String loginPageTitle = loginPage.getText();

        Assert.assertEquals("Login page", loginPageTitle);
    }

// When I enter the Credentials

    @When("I enter the Credentials {string} {string}")
    public void enterPropertyOwnerCredentials(String username, String password) {
        WebElement adminUsernameField = driver.findElement(By.id("logInUsername"));
        adminUsernameField.sendKeys(username);

        WebElement adminPasswordField = driver.findElement(By.id("logInPassword"));
        adminPasswordField.sendKeys(password);
    }

// And I click the Log-In Button

    @And("I click the Log-In Button")
    public void clickLoginButton() {
        WebElement adminUsernameField = driver.findElement(By.id("logInPageEmailField"));
        adminUsernameField.click();
    }

// Then I am Logged-In as an Administrator

    @Then("I am Logged-In as an Administrator")
    public void loggedInAsAdmin() {
        WebElement adminLogInReturnMessage = driver.findElement(By.id("successfulAdminLogIn"));
        String adminLogInReturnMessageStr = adminLogInReturnMessage.getText();

        Assert.assertEquals("Successful Log-In. Welcome Admin!", adminLogInReturnMessageStr);
    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// %%%%%% Scenario: Property Owner Log-In %%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// %%%%%% Given The Log-In Page is displayed %%%%%%
// %% The same with Scenario: Administrator Log-In %%

// %%%%%% When I enter the Credentials %%%%%%
// %% The same with Scenario: Administrator Log-In %%

// %%%%%% And I click the Log-In Button %%%%%%
// %% The same with Scenario: Administrator Log-In %%

// Then I am Logged-In as Property Owner

    @Then("I am Logged-In as a Property Owner")
    public void loggedInAsPropertyOwner() {
        WebElement propertyOwnerLogInReturnMessage = driver.findElement(By.id("successfulPropertyOwnerLogIn"));
        String propertyOwnerLogInReturnMessageStr = propertyOwnerLogInReturnMessage.toString();

        Assert.assertEquals("Successful Log-In. Welcome Property Owner!", propertyOwnerLogInReturnMessageStr);
    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// %%%% Scenario: Administrator Access Level %%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// Given I am Logged-In as an Administrator and I want to explore all Property Owners' Properties

    @Given("I am Logged-In as an Administrator and I want to explore all Property Owners' Properties")
    public void adminAccessLevel() {
        WebElement adminMainPage = driver.findElement(By.id("adminMainPage"));
        String adminMainPageStr = adminMainPage.getText();

        Assert.assertEquals("Administrator Main Page", adminMainPageStr);
    }

// When I try to Access the List of other Property Owner Properties

    @When("I try to Access the List of other Property Owner Properties")
    public void adminAccessPropertyList() {
        WebElement someoneElsesPropertyAdminView = driver.findElement(By.id("userListViewUserDetails1"));
        someoneElsesPropertyAdminView.click();
    }

// Then I can see All the Properties present in the Database from All the Property Owners

    @Then("I can see All the Properties present in the Database from All the Property Owners")
    public void adminViewEntirePropertyDatabase() {
        WebElement someoneElsesPropertyIdAdminView = driver.findElement(By.id("someoneElsesPropertyId1AdminView"));
        String someoneElsesPropertyId1AdminView = someoneElsesPropertyIdAdminView.getText();
        Assert.assertEquals("userListViewUserDetailsId1", someoneElsesPropertyId1AdminView);

        WebElement someoneElsesPropertyLocationAdminView = driver.findElement(By.id("someoneElsesPropertyLocation2AdminView"));
        String someoneElsesPropertyLocation1AdminView = someoneElsesPropertyLocationAdminView.getText();
        Assert.assertEquals("userListViewUserDetailsLocation2", someoneElsesPropertyLocation1AdminView);
    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// %%%% Scenario: Property Owner Access Level %%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// Given I am Logged-In as a Property Owner and I want to explore all of My Properties

    @Given("I am Logged-In as a Property Owner and I want to explore all of My Properties")
    public void propertyOwnerAccessLevel() {
        WebElement propertyOwnerMainPage = driver.findElement(By.id("adminMainPage"));
        String propertyOwnerMainPageStr = propertyOwnerMainPage.getText();

        Assert.assertEquals("Administrator Main Page", propertyOwnerMainPageStr);
    }

// When I try to Access the List of My Properties

    @When("I try to Access the List of My Properties")
    public void propertyOwnerAccessPropertyList() {
        WebElement myPropertiesPropertyOwnerView = driver.findElement(By.id("myPropertiesViewDetails1"));
        myPropertiesPropertyOwnerView.click();
    }

// Then I can see All of My Properties present in the Database

    @Then("I can see All of My Properties present in the Database")
    public void propertyOwnerViewMyPropertiesDatabase() {
        WebElement myPropertyIdPropertyOwnerView = driver.findElement(By.id("someoneElsesPropertyId1AdminView"));
        String myPropertyId1PropertyOwnerView = myPropertyIdPropertyOwnerView.getText();
        Assert.assertEquals("userListViewUserDetailsId1", myPropertyId1PropertyOwnerView);

        WebElement myPropertyLocationPropertyOwnerView = driver.findElement(By.id("someoneElsesPropertyLocation2AdminView"));
        String myPropertyLocation2PropertyOwnerView = myPropertyLocationPropertyOwnerView.getText();
        Assert.assertEquals("userListViewUserDetailsLocation2", myPropertyLocation2PropertyOwnerView);
    }
}
