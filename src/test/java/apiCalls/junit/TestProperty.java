package apiCalls.junit;

import apiCalls.java.propertyOwner.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class TestProperty {

    @Test
    @Order(1)
    public void testPostPropertyOwner1() {
        PostPropertyOwner1 getPostPropertyOwner1 = new PostPropertyOwner1();
        PostPropertyOwner1.main(null);
        Assertions.assertEquals(201, getPostPropertyOwner1.getStatusCodePostPropertyOwner1());
    }

    @Test
    @Order(2)
    public void testPostPropertyOwner2() {
        PostPropertyOwner2 getPostPropertyOwner2 = new PostPropertyOwner2();
        PostPropertyOwner2.main(null);
        Assertions.assertEquals(201, getPostPropertyOwner2.getStatusCodePostPropertyOwner2());
    }

    @Test
    @Order(3)
    public void testGetPropertyOwnerById() {
        GetPropertyOwnerById getPropertyOwnerById = new GetPropertyOwnerById();
        GetPropertyOwnerById.main(null);
        Assertions.assertEquals(200, getPropertyOwnerById.getStatusCodeGetPropertyOwnerById());
    }

    @Test
    @Order(4)
    public void testPutPropertyOwner1ByTin() {
        PutPropertyOwner1ByTin putPropertyOwner1ByTin = new PutPropertyOwner1ByTin();
        PutPropertyOwner1ByTin.main(null);
        Assertions.assertEquals(200, putPropertyOwner1ByTin.getStatusCodePutPropertyOwner1ByTin());
    }

    @Test
    @Order(5)
    public void testDeletePropertyOwnerByTin() {
        DeletePropertyOwnerByTin deletePropertyOwnerByTin = new DeletePropertyOwnerByTin();
        DeletePropertyOwnerByTin.main(null);
        Assertions.assertEquals(200, deletePropertyOwnerByTin.getStatusCodeDeletePropertyOwnerByTin());
    }
}
