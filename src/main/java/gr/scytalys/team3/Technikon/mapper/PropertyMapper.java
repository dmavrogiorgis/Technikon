package gr.scytalys.team3.Technikon.mapper;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
//@MapperConfig(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PropertyMapper {

        @Mapping(source = "propertyOwner.id", target = "propertyOwnerId")
        PropertyDTO toPropertyDto(Property property);

        @Mapping(source = "propertyOwnerId", target = "propertyOwner.id")
        Property toProperty(PropertyDTO propertyDTO);
}
