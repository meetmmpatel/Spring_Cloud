package com.collabit.userservice.service;

import com.collabit.userservice.dto.DepartmentDto;
import com.collabit.userservice.entity.User;
import com.collabit.userservice.repo.UserRepository;
import com.collabit.userservice.response.ResponseDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  
  private final UserRepository userRepository;
  private final RestTemplate restTemplate;
  
  @Cacheable("user")
  public User save(User user) {
    try{
      Thread.sleep(300);
	}catch (InterruptedException e){
      e.printStackTrace();
	}
	log.info("Invoking User service");
	return userRepository.save(user);
	
  }
  
  @Cacheable(value = "id")
  public User findOneById(Long id) {
	return userRepository.findById(id).orElseThrow();
  }
  
  public ResponseDto getUserAndDepartment(Long userId) {
	log.info("finding user");
	User user = findOneById(userId);
	DepartmentDto department = restTemplate
			.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), DepartmentDto.class);
	
	return new ResponseDto(user, department);
  }
  
}
