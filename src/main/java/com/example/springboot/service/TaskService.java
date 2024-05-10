package com.example.springboot.service;

import java.util.List;

import com.example.springboot.payload.TaskDto;

public interface TaskService {
	
	public TaskDto saveTask(long userid,TaskDto taskDto);
	
	public List<TaskDto> getAllTasks(long userid);
	
	public TaskDto getTask(long userid, long todoid);

}
