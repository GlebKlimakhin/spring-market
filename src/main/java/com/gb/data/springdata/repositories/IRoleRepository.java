package com.gb.data.springdata.repositories;

import com.gb.data.springdata.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
