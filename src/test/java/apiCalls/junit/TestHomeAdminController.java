package apiCalls.junit;

import apiCalls.java.adminController.GetAdminPropertyActive;
import apiCalls.java.adminController.GetAdminPropertyAll;
import apiCalls.java.homeController.GetContactUs;
import apiCalls.java.homeController.GetHome;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHomeAdminController {

    @Test
    public void testGetHome() {
        GetHome getHome = new GetHome();
        GetHome.main(null);
        assertEquals(200, getHome.getStatusCodeGetHome());
    }

    @Test
    public void testGetContactUs() {
        GetContactUs getContactUs = new GetContactUs();
        GetContactUs.main(null);
        assertEquals(200, getContactUs.getStatusCodeContactUs());
    }

    @Test
    public void testGetAdminPropertyActive() {
        GetAdminPropertyActive getAdminPropertyActive = new GetAdminPropertyActive();
        GetAdminPropertyActive.main(null);
        assertEquals(302, getAdminPropertyActive.getStatusCodeGetAdminPropertyActive());
    }

    @Test
    public void testGetAdminPropertyAll() {
        GetAdminPropertyAll getAdminPropertyActive = new GetAdminPropertyAll();
        GetAdminPropertyAll.main(null);
        assertEquals(302, getAdminPropertyActive.getStatusCodeGetAdminPropertyAll());
    }
}
