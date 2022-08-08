package com.codesatest.codesatest.service;

import com.codesatest.codesatest.dtos.RolDto;
import com.codesatest.codesatest.dtos.UserDto;
import com.codesatest.codesatest.exceptions.NotFoundServiceException;
import com.codesatest.codesatest.exceptions.ValidateServiceException;
import com.codesatest.codesatest.mappers.UserMapper;
import com.codesatest.codesatest.model.RolesModel;
import com.codesatest.codesatest.model.UsersModel;
import com.codesatest.codesatest.repository.RolesRepository;
import com.codesatest.codesatest.repository.UsersRepository;
import com.codesatest.codesatest.utils.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolesService rolesService;

    public WrapperResponse<UserDto> create(UserDto userDto){
        this.rolesService.findById(userDto.getRolesModel().getId());
       Optional<UserDto> exist = this.findByName(userDto.getName());
       if(exist.isPresent()){
           throw new ValidateServiceException("Name already exists");
       }else {
            UsersModel user = userMapper.toEntity(userDto);
            UsersModel newUser = usersRepository.save(user);
           return new WrapperResponse<UserDto>(true,"Success",userMapper.toDto(newUser) );
       }
    }

    public WrapperResponse<List<UserDto>> getAll(){
        List<UsersModel>  users = usersRepository.findAll();
        return new WrapperResponse<List<UserDto>>(true,"Success", userMapper.toDtos(users) ) ;
    }

    public WrapperResponse<UserDto> update(UserDto userDto){
        this.findById(userDto.getId());
        this.rolesService.findById(userDto.getRolesModel().getId());

        Optional<UserDto> exist = this.findByName(userDto.getName());
        UsersModel user = userMapper.toEntity(exist.get());

        if(userDto.getId().equals(user.getId())){
            UsersModel user1 = userMapper.toEntity(userDto);
            usersRepository.save(user1);
            return new WrapperResponse<UserDto>(true,"Success", userMapper.toDto(user1) ) ;
        }
        throw new ValidateServiceException("Name already exists");

    }

    public WrapperResponse<String> delete(Long id){
        this.findById(id);
        usersRepository.deleteById(id);
        return new WrapperResponse<String>(true, "Success", "Deleted")  ;
    }

    public Optional<UserDto> findByName(String name){
        log.info(String.valueOf(usersRepository.findByName(name)));
        try {
            Optional<UsersModel>  user = usersRepository.findByName(name);
            return Optional.of( userMapper.toDto(user.get()));
        }catch (Exception e){
            return Optional.empty();
        }

    }

    public WrapperResponse<Optional<UserDto>> findById(Long id){
        Optional<UsersModel> user = usersRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundServiceException("User does not exist");
        }
        return new WrapperResponse<Optional<UserDto>>(true,"Success", Optional.of(userMapper.toDto(user.get())));
    }

    public  WrapperResponse<List<UserDto>> findAllByMatchName(String name){
        List<UsersModel> usersModelList = usersRepository.findAllByMatchName(name);
        return new WrapperResponse<List<UserDto>>(true, "Success", userMapper.toDtos(usersModelList));
    }
}
