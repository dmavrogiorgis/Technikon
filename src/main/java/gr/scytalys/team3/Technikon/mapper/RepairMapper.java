package gr.scytalys.team3.Technikon.mapper;

import gr.scytalys.team3.Technikon.dto.RepairDTO;
import gr.scytalys.team3.Technikon.model.Repair;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
@MapperConfig(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepairMapper {
    @Mapping(source = "property.propertyId", target = "propertyId")
    RepairDTO repairDTO(Repair repair);


    @Mapping(source = "propertyId", target = "property.propertyId")
    Repair repair(RepairDTO repairDTO);
}
