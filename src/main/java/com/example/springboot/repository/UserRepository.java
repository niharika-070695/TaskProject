package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{

}
