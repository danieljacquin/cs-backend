package com.codesatest.codesatest.controller;

import com.codesatest.codesatest.dtos.UserDto;
import com.codesatest.codesatest.service.UsersService;
import com.codesatest.codesatest.utils.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @CrossOrigin
    @PostMapping("create")
    public ResponseEntity<WrapperResponse<UserDto>> create(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<WrapperResponse<UserDto>>(usersService.create(userDto), HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping("getAll")
    public ResponseEntity<WrapperResponse<List<UserDto>>> getAll(){
        return new ResponseEntity<WrapperResponse<List<UserDto>>>(usersService.getAll(), HttpStatus.OK);
    }
    @CrossOrigin
    @PutMapping("update")
    public ResponseEntity<WrapperResponse<UserDto>> update(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<WrapperResponse<UserDto>>(usersService.update(userDto), HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("delete/{id}")
    public ResponseEntity<WrapperResponse<String>> delete(@PathVariable Long id){
        return new ResponseEntity<WrapperResponse<String>>(usersService.delete(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("findAllByMatchName/{name}")
    public ResponseEntity<WrapperResponse<List<UserDto>>> findAllByMatchName(@PathVariable String name){
        return new ResponseEntity<WrapperResponse<List<UserDto>>>(usersService.findAllByMatchName(name), HttpStatus.OK);
    }
}
