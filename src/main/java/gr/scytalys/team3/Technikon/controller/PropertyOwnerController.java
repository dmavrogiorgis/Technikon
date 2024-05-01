package gr.scytalys.team3.Technikon.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propertyOwner/")
public class PropertyOwnerController {

    //responseEntity
//    @GetMapping("{propertyOwnerId}")
//    public PropertyOwner(@PathVariable long propertyOwnerId){
//        return  propertyOwnerService.getPropertyOwnerById(propertyOwnerId);
//    }
//
//    @GetMapping("all/")
//    public List<PropertyOwner> getAllPropertyOwner() {
//        return propertyOwnerService.getAllPropertyOwner();
//    }
//
//    @PostMapping("create")
//    public PropertyOwner (@RequestBody PropertyOwnerDto propertyOwnerDto){
//        return propertyOwnerService.createPropertyOwner(propertyOwnerDto);
//    }
}
