package com.example.workerapplication.repository;

import com.example.workerapplication.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {



}
