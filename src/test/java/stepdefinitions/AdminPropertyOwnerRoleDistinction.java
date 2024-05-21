package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;


//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%% Feature: Administrator And Property Owner Role Distinction %%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

public class AdminPropertyOwnerRoleDistinction {

    WebDriver driver;

    @Before
    public void setUp() {
        // Select Browser Driver
        driver = new EdgeDriver();

        // Go to the Best Price website
        driver.get("");

        // Maximize window size
        driver.manage().window().maximize();
    }

    @After
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// %%%%%% Scenario: Administrator Log-In %%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// Given The Log-In Button is displayed

    @Given("The Log-In Button is displayed")
    public void loginButtonIsDisplayed() {
        WebElement loginButtonDisplayed = driver.findElement(By.id("loginButton"));
        loginButtonDisplayed.click();
        WebElement loginWindowPoppedUp = driver.findElement(By.id("logInPageLogInTitle"));
        String loginWindowPoppedUpTitle = loginWindowPoppedUp.getText();
        Assert.assertEquals("Login", loginWindowPoppedUpTitle);
    }

// When I enter the Administrator Credentials

    @When("I enter the Administrator Credentials")
    public void enterAdminCredentials() {
        String adminUsername = "adminos";
        String adminPassword = "Admin_p@ss";
        WebElement adminUsernameField = driver.findElement(By.id("logInPageEmailField"));
        WebElement adminPasswordField = driver.findElement(By.id("logInPagePasswordField"));
        adminUsernameField.sendKeys(adminUsername);
        adminPasswordField.sendKeys(adminPassword);
    }

// And I click the Log-In Button

    @And("I click the Log-In Button")
    public void clickLoginButton() {
        WebElement adminUsernameField = driver.findElement(By.id("logInPageEmailField"));
    }

// Then I am Logged-In as an Administrator

    @Then("I am Logged-In as an Administrator")
    public void loggedInAsAdmin() {

    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// %%%%%% Scenario: Property Owner Log-In %%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// %%%%%% Given The Log-In Button is displayed %%%%%%
// %% The same with Scenario: Administrator Log-In %%

// When I enter the Property Owner Credentials

    @When("I enter the Property Owner Credentials")
    public void enterPropertyOwnerCredentials() {

    }

// %%%%%% And I click the Log-In Button %%%%%%
// %% The same with Scenario: Administrator Log-In %%

// Then I am Logged-In as Property Owner

    @Then("I am Logged-In as Property Owner")
    public void loggedInAsPropertyOwner() {

    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// %%%% Scenario: Administrator Access Level %%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// Given I am Logged-In as an Administrator and I want to explore all Property Owners' Properties

    @Given("I am Logged-In as an Administrator and I want to explore all Property Owners' Properties")
    public void adminAccessLevel() {

    }

// When I try to Access the List of other Property Owner Properties

    @When("I try to Access the List of other Property Owner Properties")
    public void adminAccessPropertyList() {

    }

// Then I can see All the Properties present in the Database from All the Property Owners

    @Then("I can see All the Properties present in the Database from All the Property Owners")
    public void adminViewEntirePropertyDatabase() {

    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// %%%% Scenario: Property Owner Access Level %%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// Given I am Logged-In as a Property Owner and I want to explore all of My Properties

    @Given("I am Logged-In as a Property Owner and I want to explore all of My Properties")
    public void propertyOwnerAccessLevel() {

    }

// When I try to Access the List of My Properties

    @When("I try to Access the List of My Properties")
    public void propertyOwnerAccessPropertyList() {

    }

// Then I can see All of My Properties present in the Database

    @Then("I can see All of My Properties present in the Database")
    public void propertyOwnerViewEntirePropertyDatabase() {

    }
}
