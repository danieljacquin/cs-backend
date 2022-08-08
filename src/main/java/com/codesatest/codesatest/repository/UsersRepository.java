package com.codesatest.codesatest.repository;

import com.codesatest.codesatest.dtos.UserDto;
import com.codesatest.codesatest.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {
    Optional<UsersModel>  findByName(String name);

    @Query(value = "select * from Users where name like lower(concat('%', :name, '%'))", nativeQuery = true)
    List<UsersModel> findAllByMatchName(String name);
}
