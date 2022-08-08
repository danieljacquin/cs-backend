package com.codesatest.codesatest.mappers;

import com.codesatest.codesatest.dtos.RolDto;
import com.codesatest.codesatest.dtos.UserDto;
import com.codesatest.codesatest.model.RolesModel;
import com.codesatest.codesatest.model.UsersModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UsersModel usersModel);
    List<UserDto> toDtos(List<UsersModel> usersModelList);

    UsersModel toEntity(UserDto userDto);
    List<UsersModel> toEntities(List<UserDto> userDtoList);
}
