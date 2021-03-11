package com.collabit.demo.controller;

import com.collabit.demo.entity.Department;
import com.collabit.demo.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/save")
    public String save(@RequestBody Department department){
        departmentService.save(department);
        log.info("Create(POST) new Department");
        return "Save Successfully";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDept(@PathVariable Long id){
        log.info("Fetch department by ID: " + id);
        return ResponseEntity.ok().body(departmentService.findDeptById(id));
    }


    
}
