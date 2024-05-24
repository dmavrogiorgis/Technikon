package apiCalls.java.collections;

import apiCalls.java.homeController.GetContactUs;
import apiCalls.java.homeController.GetHome;
import apiCalls.java.propertyOwner.PostPropertyOwner1;
import apiCalls.java.property.GetPropertiesByOwnerId;
import apiCalls.java.property.GetPropertyByPropertyIdAndByOwnerId;
import apiCalls.java.property.PostPropertyByOwnerId;
import apiCalls.java.propertyOwner.GetPropertyOwnerById;
import apiCalls.java.propertyOwner.PutPropertyOwner1ByTin;
import apiCalls.java.repair.PostRepairForPropertyByPropertyId;

public class Presentation {

    public static void main(String[] args) {

        System.out.println("\u001B[1mTesting Main Page Functionality \"Contact Us Button\"\u001B[0m");
        GetContactUs.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1mTesting Main Page Functionality \"Home Button\"\u001B[0m");
        GetHome.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1mTesting Administrator \"View All Properties\" Functionality\u001B[0m");
        GetHome.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1mTesting Administrator \"View Active Properties\" Functionality\u001B[0m");
        GetHome.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m1) Running Post Property Owner\u001B[0m");
        PostPropertyOwner1.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m2) Running Get Property Owner By Id\u001B[0m");
        GetPropertyOwnerById.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m3) Running Put Property Owner By Tin\u001B[0m");
        PutPropertyOwner1ByTin.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m4) Running Get Property Owner By Id after the update\u001B[0m");
        GetPropertyOwnerById.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m5) Running Post Property By Owner Id\u001B[0m");
        PostPropertyByOwnerId.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m6) Running Get Properties By Owner Id\u001B[0m");
        GetPropertiesByOwnerId.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m7) Running Get Property By Property Id And By Owner Id\u001B[0m");
        GetPropertyByPropertyIdAndByOwnerId.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m8) Running Post Repair For Property By Property Id\u001B[0m");
        PostRepairForPropertyByPropertyId.main(args);
        System.out.println();
        waitFor();
    }

    private static void waitFor() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
