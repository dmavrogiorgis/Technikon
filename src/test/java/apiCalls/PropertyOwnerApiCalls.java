package apiCalls;

import apiCalls.propertyOwner.DeletePropertyOwnerByTin;
import apiCalls.propertyOwner.GetPropertyOwnerById;
import apiCalls.propertyOwner.PostPropertyOwner;
import apiCalls.propertyOwner.PutPropertyOwnerByTin;

public class PropertyOwnerApiCalls {

    public static void main(String[] args) {

        System.out.println("1) Running PostPropertyOwner");
        PostPropertyOwner.main(args);
        System.out.println();
        waitFor(2000);

        System.out.println("2) Running GetPropertyOwnerById");
        GetPropertyOwnerById.main(args);
        System.out.println();
        waitFor(500);

        System.out.println("3) Running PutPropertyOwnerByTin");
        PutPropertyOwnerByTin.main(args);
        System.out.println();
        waitFor(500);

        System.out.println("4) Running GetPropertyOwnerById again");
        GetPropertyOwnerById.main(args);
        System.out.println();
        waitFor(500);

        System.out.println("5) Running DeletePropertyOwnerByTin");
        DeletePropertyOwnerByTin.main(args);
        System.out.println();
        waitFor(500);
    }

    private static void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
