package com.example.springboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.Users;
import com.example.springboot.payload.UserDto;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		// userDto is not an entity of Users
		Users user = userDtoToEntity(userDto); //converted userDto to Users
		Users savedUser = userRepository.save(user);
		return entityToUserDto(savedUser);
	}
	
	private Users userDtoToEntity(UserDto userDto) {
		Users users = new Users();
		users.setName(userDto.getName());
		users.setEmail(userDto.getEmail());
		users.setPassword(userDto.getPassword());
		return users;
	}
	
	private UserDto entityToUserDto(Users savedUser) {
		UserDto userDto = new UserDto();
		userDto.setId(savedUser.getId());
		userDto.setEmail(savedUser.getEmail());
		userDto.setPassword(savedUser.getPassword());
		userDto.setName(savedUser.getName());
		return userDto;
		
	}

}
