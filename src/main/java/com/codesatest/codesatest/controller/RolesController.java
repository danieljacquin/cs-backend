package com.codesatest.codesatest.controller;

import com.codesatest.codesatest.dtos.RolDto;
import com.codesatest.codesatest.model.RolesModel;
import com.codesatest.codesatest.service.RolesService;
import com.codesatest.codesatest.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @PostMapping("create")
    public ResponseEntity<WrapperResponse<RolDto>> create(@Valid @RequestBody RolDto rolDto){
        return new ResponseEntity<WrapperResponse<RolDto>>(rolesService.create(rolDto), HttpStatus.CREATED);
    }

    @GetMapping("getAll")
    public ResponseEntity<WrapperResponse<List<RolDto>>> getAll(){
       return new ResponseEntity<WrapperResponse<List<RolDto>>>(rolesService.GetAll(), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<WrapperResponse<RolDto>> update(@RequestBody RolDto rolDto){
        return new ResponseEntity<WrapperResponse<RolDto>>(rolesService.update(rolDto), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<WrapperResponse<String>> delete(@PathVariable Long id){
        return new ResponseEntity<WrapperResponse<String>>(rolesService.delete(id), HttpStatus.OK);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<WrapperResponse<Optional<RolDto>>> findById(@PathVariable Long id){
        return new ResponseEntity<WrapperResponse<Optional<RolDto>>>(rolesService.findById(id), HttpStatus.OK);
    }


}
