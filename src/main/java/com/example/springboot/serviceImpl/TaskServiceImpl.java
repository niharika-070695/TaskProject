package com.example.springboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.exception.TaskNotFound;
import com.example.springboot.exception.UserNotFound;
import com.example.springboot.exception.APIException;
import com.example.springboot.model.Task;
import com.example.springboot.model.Users;
import com.example.springboot.payload.TaskDto;
import com.example.springboot.repository.TaskRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired	
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDto saveTask(long userid, TaskDto taskDto) {
		Users user = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d not found", userid)));
		Task task = modelMapper.map(taskDto, Task.class);
		task.setUsers(user);
		//After setting the user, we are storing the data in DB	
		Task savedTask = taskRepository.save(task);
		return modelMapper.map(savedTask, TaskDto.class);
		
	}

	@Override
	public List<TaskDto> getAllTasks(long userid) {
		userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d not found", userid)));
		List<Task> tasks = taskRepository.findAllByUsersId(userid);
		return tasks.stream().map(
				task -> modelMapper.map(task,TaskDto.class)).collect(Collectors.toList());
		
		
	}

	@Override
	public TaskDto getTask(long userid, long taskid) {
		// TODO Auto-generated method stub
		Users user = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d not found", userid)));
		Task task = taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("Task Id %d not found",taskid)));
		if(user.getId() != task.getUsers().getId()) {
			throw new APIException(String.format("Task Id %d does not belong to User Id %d",taskid,userid));
		}
		return modelMapper.map(task,TaskDto.class);
	}

}
