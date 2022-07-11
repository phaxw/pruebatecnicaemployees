/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.repository;

import com.employees.prueba.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Phaxw
 */
public interface GenderRepository extends JpaRepository<Gender,Integer>{
    
}
