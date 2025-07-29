package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

}