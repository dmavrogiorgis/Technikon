package gr.scytalys.team3.Technikon.mapper;

import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.dto.RepairUpdateDTO;
import gr.scytalys.team3.Technikon.model.Repair;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
@MapperConfig(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepairMapper {
    @Mapping(source = "property.propertyId", target = "propertyId")
    RepairResponseDTO toRepairResponseDTO(Repair repair);


    @Mapping(source = "propertyId", target = "property.propertyId")
    Repair toRepair(RepairResponseDTO repairResponseDTO);

    @Mapping(source = "property.propertyId", target = "propertyId")
    RepairCreateDTO repairCreateDTO(Repair repair);


    @Mapping(source = "propertyId", target = "property.propertyId")
    Repair toRepair(RepairCreateDTO repairCreateDTO);

    @Mapping(source = "property.propertyId", target = "propertyId")
    RepairUpdateDTO repairUpdateDTO(Repair repair);


    @Mapping(source = "propertyId", target = "property.propertyId")
    Repair toRepair(RepairUpdateDTO repairUpdateDTO);
}
