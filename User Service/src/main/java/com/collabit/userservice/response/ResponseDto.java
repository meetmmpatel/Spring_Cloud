package com.collabit.userservice.response;

import com.collabit.userservice.dto.DepartmentDto;
import com.collabit.userservice.entity.User;

import lombok.Value;

@Value
public class ResponseDto {

    User user;
    DepartmentDto department;
    
}
