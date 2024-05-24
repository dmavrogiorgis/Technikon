package gr.scytalys.team3.Technikon.mapper;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairUpdateDTO;
import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.model.Repair;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
//@MapperConfig(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PropertyMapper {

        @Mapping(source = "propertyOwner.id", target = "propertyOwnerId")
        @Mapping(source = "propertyId", target = "id")
        PropertyDTO toPropertyDto(Property property);

        @Mapping(source = "propertyOwnerId", target = "propertyOwner.id")
        @Mapping(source = "id", target = "propertyId")
        Property toProperty(PropertyDTO propertyDTO);

}
