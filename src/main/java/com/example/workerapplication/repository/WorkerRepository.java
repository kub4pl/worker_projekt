package com.example.workerapplication.repository;


import com.example.workerapplication.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {



    Worker findByPESEL(String PESEL);
    boolean existsByPESEL(String PESEL);
    boolean existsByAccountNumber(String accountNumber);
}
