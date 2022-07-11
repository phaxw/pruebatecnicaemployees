/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.entities;

import java.time.Instant;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Phaxw
 */
@Entity
@Table(name="Employee_Worked_Hours")
public class EmployeeWorkedHours {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "employee_id",unique = true)
    public Integer employeeid;
    @Column(name = "worked_hours")
    public Integer workedhours;
    @Column(name = "worked_date")
    @Temporal(TemporalType.DATE)
    public Date workeddate;
}