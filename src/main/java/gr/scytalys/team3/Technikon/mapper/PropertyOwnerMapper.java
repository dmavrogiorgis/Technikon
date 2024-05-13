package gr.scytalys.team3.Technikon.mapper;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring"  )
@MapperConfig(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PropertyOwnerMapper {

    PropertyOwnerDTO toPropertyOwnerDto(PropertyOwner propertyOwner);

    PropertyOwner toPropertyOwner(PropertyOwnerDTO propertyOwnerDTO);


}