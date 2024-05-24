package stepDefinitions;

import apiCalls.java.homeController.GetHome;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static stepDefinitions.TestSetup.driver;

public class MainPageFunctionalities {

    @Given("The Application is Up And Running")
    public void applicationUpRunning() {
        GetHome getHome = new GetHome();
        GetHome.main(null);
        assertEquals(200, getHome.getStatusCodeGetHome());
    }

    @When("I click the Log-In Button to Navigate to the Log In Page")
    public void mainToLogInPageClickLogIn() {
        WebElement clickLogInFromMainToLogIn = driver.findElement(By.id("mainPageLogInButton"));
        clickLogInFromMainToLogIn.click();
    }

    @Then("I am Redirected to the Log In Page or the Log In Window Pops Up")
    public void mainToLogInPageSuccessfulTransition() {
        WebElement fromMainToLogInSuccessTrans = driver.findElement(By.id("logInPageLogInTitle"));
        String logInPageTitle = fromMainToLogInSuccessTrans.getText();

        Assert.assertEquals("Login", logInPageTitle);
    }



    @When("I click the Sign Up Button to Navigate to the Sign Up Page")
    public void mainToSignUpPageClickLogIn() {
        WebElement clickSignUpFromMainToLogIn = driver.findElement(By.id("mainPageSignUpButton"));
        clickSignUpFromMainToLogIn.click();
    }

    @Then("I am Redirected to the Sign Up Page or the Sign Up Window Pops Up")
    public void mainToSignUpPageSuccessfulTransition() {
        WebElement fromMainToSignUpSuccessTrans = driver.findElement(By.id("logInPageSignUpTitle"));
        String signUpPageTitle = fromMainToSignUpSuccessTrans.getText();

        Assert.assertEquals("Login", signUpPageTitle);
    }



    @Given("I am at the Log In Page")
    public void beingAtLogInPage() {

    }

    @When("I click the Sign Up Button")
    public void clickSignUpFromLogIn() {

    }

    @Then("I am Redirected to the Sign Up Page or the Sign Up Window Pops Up")
    public void fromLogInToSignUpRedirect() {

    }



    @Given("I am at the Sign Up Page")
    public void beingAtSignUpPage() {

    }

    @When("I click the Log In Button")
    public void clickLogInFromSignUp() {

    }

    @Then("I am Redirected to the Log In Page or the Log In Window Pops Up")
    public void fromSignUpToLogInRedirect() {

    }
}
