package apiCalls.collections;

import apiCalls.propertyOwner.DeletePropertyOwnerByTin;
import apiCalls.propertyOwner.GetPropertyOwnerById;
import apiCalls.propertyOwner.PostPropertyOwner1;
import apiCalls.propertyOwner.PutPropertyOwnerByTin;

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
        PutPropertyOwnerByTin.main(args);
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
