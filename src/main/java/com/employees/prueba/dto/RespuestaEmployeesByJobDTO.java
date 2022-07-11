/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.dto;

import com.employees.prueba.entities.Gender;
import com.employees.prueba.entities.Job;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Phaxw
 */
public class RespuestaEmployeesByJobDTO {
    public List<EmployeesByJobDTO> employees;
    public boolean success;
}
