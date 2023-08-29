package com.example.workerapplication.core.controller;


import com.example.workerapplication.dto.DepartmentDto;
import com.example.workerapplication.model.Department;
import com.example.workerapplication.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/department")
    public DepartmentDto createDepartment(@RequestBody DepartmentDto departmentDto) {

        Department department = new Department();
        department.setDepartment(departmentDto.getDepartment());
        departmentRepository.save(department);
        return departmentDto;
    }

}
