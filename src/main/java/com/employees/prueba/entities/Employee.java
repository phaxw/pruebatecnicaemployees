/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Phaxw
 */

@Entity
@Table(name = "Employee")
public class Employee {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer gender_id;
    @Column(name = "job_id")
    public Integer jobid;
    public String name;
    public String lastname;
    public Date birthdate;
}
