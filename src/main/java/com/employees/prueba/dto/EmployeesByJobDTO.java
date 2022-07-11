/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.dto;

import com.employees.prueba.entities.Gender;
import com.employees.prueba.entities.Job;
import java.util.Date;

/**
 *
 * @author Phaxw
 */
public class EmployeesByJobDTO {
    public Integer id;
    public String name;
    public String lastname;
    public Date birthdate;
    public Job job = new Job();
    public Gender gender = new Gender();
    
    
    /*public EmployeesByJobDTO(Integer _id, String _name, String _lastname, Date _birthdate, Job _job, Gender _gender){
        id = _id;
        name = _name;
        lastname = _lastname;
        birthdate = _birthdate;
        job = _job;
        gender = _gender;
    }*/
}
