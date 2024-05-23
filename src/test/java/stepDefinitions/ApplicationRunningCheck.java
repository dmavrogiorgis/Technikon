package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ApplicationRunningCheck {
    public static HttpURLConnection httpURLConnection;
    @Given("That the Endpoint {string} is Up and Running")
    public void APIisRunning(String apiEndPoint)throws Exception{
        validate200ResponseCode(apiEndPoint);
    }



    @When("We make a GET Request for the Property Owner with TIN {string}")
    public void apiCall(String tinNumber)throws Exception{
        URL url = new URL("http://localhost:8080/api/owner/search?tin=" + tinNumber);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        String content = httpURLConnection.getContent().toString();
        String result = IOUtils.toString(httpURLConnection.getInputStream());
        Assert.assertEquals(result, "{\"id\":1,\"tin\":\"123456789\",\"name\":\"Dimitris\",\"surname\":\"Mavrogiorgis\",\"address\":\"Something\",\"phoneNumber\":\"6976500964\",\"email\":\"kati@gmail.com\",\"username\":\"dimmav\",\"password\":\"1234\",\"active\":true}");
        Assert.assertEquals("Something is way off ",200,responseCode);

    }

    @Then("We Get a response that contains the Details of that Property Owner {string}")
    public void assertResponseBody(String something)throws Exception{

        // your code :)

    }

    private void validate200ResponseCode(String apiEndPoint) throws IOException, IOException {
        URL url = new URL(apiEndPoint);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        Assert.assertEquals("Something is way off ",200,responseCode);
    }
}