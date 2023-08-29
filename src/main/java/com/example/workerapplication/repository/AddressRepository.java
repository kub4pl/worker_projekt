package com.example.workerapplication.repository;


import com.example.workerapplication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    boolean existsByZipCode(String ZipCode);
}