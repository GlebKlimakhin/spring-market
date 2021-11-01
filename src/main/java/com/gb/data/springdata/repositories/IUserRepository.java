package com.gb.data.springdata.repositories;

import com.gb.data.springdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
