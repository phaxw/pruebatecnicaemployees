/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.controllers;

import com.employees.prueba.dto.AddWorkedHoursDTO;
import com.employees.prueba.dto.HorasTrabajadasDTO;
import com.employees.prueba.dto.JobId;
import com.employees.prueba.dto.RespuestaEmployeesByJobDTO;
import com.employees.prueba.dto.RespuestaGenerica;
import com.employees.prueba.dto.RespuestaHorasTrabajadas;
import com.employees.prueba.dto.RespuestaPagoTotalDTO;
import com.employees.prueba.entities.Employee;
import com.employees.prueba.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Phaxw
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    
    @PostMapping("/newemployee")
    public RespuestaGenerica prueba(@RequestBody Employee employee){
        return employeeService.save(employee);
    }
    
    @PostMapping("/addworkedhours")
    public RespuestaGenerica addWorkedHours(@RequestBody AddWorkedHoursDTO addWordkedHours){
        return employeeService.addWorkedHours(addWordkedHours);
    }
    
    @PostMapping("/employeesbyjob")
    public RespuestaEmployeesByJobDTO employesbyJobId(@RequestBody JobId id){
        return employeeService.employesbyJobId(id.job_id);
    } 
    
    @PostMapping("/horasTrabajadas")
    public RespuestaHorasTrabajadas horasTrabajadas(@RequestBody HorasTrabajadasDTO horasTrabajadas){
        return employeeService.totalHorasTrabajas(horasTrabajadas);
    }
    
    @PostMapping("/pagoTotal")
    public RespuestaPagoTotalDTO pagoTotal(@RequestBody HorasTrabajadasDTO horasTrabajadas){
        return employeeService.pagoTotal(horasTrabajadas);
    }
}
