package apiCalls.propertyOwner;
import org.junit.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static stepDefinitions.TestSetup.driver;

public class GetPropertyOwnerById {
    public void applicationUpRunning(String apiEndPoint) throws IOException {
        validateResponseCode200(apiEndPoint);
    }
    private void validateResponseCode200(String apiEndPoint) throws IOException {
        URL url = new URL(apiEndPoint);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        Assert.assertEquals("The Application Is Up And Running", 200, responseCode);
    }
}
