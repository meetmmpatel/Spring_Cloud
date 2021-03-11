package com.collabit.cloudAPIGateWay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

  @GetMapping("/userServiceFallBack")
  public String userServiceFallBack(){
    return "User Service might be taking longer then expected";
  }
  
  @GetMapping("/deptServiceFallBack")
  public String deptServiceFallBack(){
	return "Department Service might be taking longer then expected";
  }
  
  @GetMapping("/postsServiceFallBack")
  public String postsServiceFallBack(){
    return "Posts/Comments Service might be taking longer then expected";
  }

}
