package com.codesatest.codesatest.mappers;

import com.codesatest.codesatest.dtos.RolDto;
import com.codesatest.codesatest.model.RolesModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface  RolMapper {

     RolDto toDto(RolesModel rolesModel);
     List<RolDto> toDtos(List<RolesModel> rolesModelList);

     RolesModel toEntity(RolDto rolDto);
     List<RolesModel> toEntities(List<RolDto> rolDtoList);
}
