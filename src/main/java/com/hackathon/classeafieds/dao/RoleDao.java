package com.hackathon.classeafieds.dao;

import com.hackathon.classeafieds.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long>{
}
