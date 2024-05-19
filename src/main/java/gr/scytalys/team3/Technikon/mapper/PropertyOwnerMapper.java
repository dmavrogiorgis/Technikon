package gr.scytalys.team3.Technikon.mapper;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerResponseDTO;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerUpdateDTO;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
@MapperConfig(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PropertyOwnerMapper {

    PropertyOwnerResponseDTO toPropertyOwnerResponseDto(PropertyOwner propertyOwner);

    PropertyOwner toPropertyOwner(PropertyOwnerDTO propertyOwnerDTO);

    @Mapping(target = "address", source = "address", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", source = "email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", source = "password", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PropertyOwner updatePropertyOwnerFromDto(PropertyOwnerUpdateDTO dto, @MappingTarget PropertyOwner po);
}