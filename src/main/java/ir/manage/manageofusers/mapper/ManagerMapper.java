package ir.manage.manageofusers.mapper;

import ir.manage.manageofusers.dto.response.ManagerDto;
import ir.manage.manageofusers.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper extends Convert<Manager, ManagerDto> {

    ManagerDto convert(Manager manager);
}
