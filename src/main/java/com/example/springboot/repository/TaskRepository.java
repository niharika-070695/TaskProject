package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

	List<Task> findAllByUsersId(long userid);

}
