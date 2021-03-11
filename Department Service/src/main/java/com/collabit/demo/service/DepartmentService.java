package com.collabit.demo.service;

import com.collabit.demo.entity.Department;
import com.collabit.demo.repo.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public Department save(Department department) {
        log.info("Creating department: " + department.toString());
       return departmentRepository.save(department);
    }

    public Department findDeptById(Long id) {
        log.info("Service of fetch department: " + id);
        return departmentRepository.findById(id).orElseThrow();
    }
}
