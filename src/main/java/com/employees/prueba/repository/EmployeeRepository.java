/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.repository;

import com.employees.prueba.entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Phaxw
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Employee findByNameAndLastname(String name, String lastname);
    
    List<Employee> findAllByJobid(Integer job_id);
}
