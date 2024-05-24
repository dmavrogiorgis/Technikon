package apiCalls.java.collections;

import apiCalls.java.propertyOwner.DeletePropertyOwnerByTin;
import apiCalls.java.propertyOwner.PostPropertyOwner1;
import apiCalls.java.propertyOwner.PutPropertyOwner1ByTin;
import apiCalls.java.propertyOwner.GetPropertyOwnerById;

public class PropertyOwnerApiCalls {

    public static void main(String[] args) {

        System.out.println("\u001B[1m1) Running PostPropertyOwner\u001B[0m");
        PostPropertyOwner1.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m2) Running GetPropertyOwnerById\u001B[0m");
        GetPropertyOwnerById.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m3) Running PutPropertyOwnerByTin\u001B[0m");
        PutPropertyOwner1ByTin.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m4) Running GetPropertyOwnerById after the update\u001B[0m");
        GetPropertyOwnerById.main(args);
        System.out.println();
        waitFor();

        System.out.println("\u001B[1m5) Running DeletePropertyOwnerByTin\u001B[0m");
        DeletePropertyOwnerByTin.main(args);
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
