package com.codesatest.codesatest.service;

import com.codesatest.codesatest.dtos.RolDto;
import com.codesatest.codesatest.exceptions.NotFoundServiceException;
import com.codesatest.codesatest.exceptions.ValidateServiceException;
import com.codesatest.codesatest.mappers.RolMapper;
import com.codesatest.codesatest.model.RolesModel;
import com.codesatest.codesatest.repository.RolesRepository;
import com.codesatest.codesatest.utils.WrapperResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private RolMapper rolMapper;

     public WrapperResponse<RolDto> create(RolDto rolDto){
         RolesModel rol = rolMapper.toEntity(rolDto);
         RolesModel newRol = rolesRepository.save(rol);
         return new WrapperResponse<RolDto>(true,"Success", rolMapper.toDto(newRol));
     }

     public WrapperResponse<List<RolDto>>  GetAll(){
         List<RolesModel> rolesModelList =  rolesRepository.findAll();
      log.info(String.valueOf(rolesModelList));
      List<RolDto> rolDtoList = rolMapper.toDtos(rolesModelList);
      return new WrapperResponse<List<RolDto>>(true,"Success",rolDtoList);
     }

     public WrapperResponse<RolDto> update(RolDto rolDto){
         RolesModel rol = rolMapper.toEntity(rolDto);
         this.findById(rol.getId());
         RolesModel rolUpdated =   rolesRepository.save(rol);
         return new WrapperResponse<RolDto>(true,"Success",rolMapper.toDto(rolUpdated));
     }

     public WrapperResponse<String> delete(Long id){
         this.findById(id);
         rolesRepository.deleteById(id);
         return new WrapperResponse<String>(true, "Success", "Deleted")  ;
     }

     public WrapperResponse<Optional<RolDto>> findById(Long id){
        Optional<RolesModel> rol = rolesRepository.findById(id);
        if(rol.isEmpty()){
          throw new NotFoundServiceException("Rol does not exist");
        }
            return new WrapperResponse<Optional<RolDto>>(true,"Success", Optional.of(rolMapper.toDto(rol.get())));

     }
}
