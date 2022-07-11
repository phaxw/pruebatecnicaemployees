/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.repository;

import com.employees.prueba.dto.TotalWorkedHoursDTO;
import com.employees.prueba.entities.EmployeeWorkedHours;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Phaxw
 */
public interface EmployeeWorkedHoursRepository extends JpaRepository<EmployeeWorkedHours, Integer>{
    EmployeeWorkedHours findByEmployeeidAndWorkeddate(Integer employee_id, Date worked_date);
    
    List<EmployeeWorkedHours> findByEmployeeidAndWorkeddateBetween(Integer employeeid, Date start, Date end);
}
