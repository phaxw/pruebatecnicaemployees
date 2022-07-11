/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author Phaxw
 */
public class HorasTrabajadasDTO {
    public Integer employee_id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd", timezone="UTC")
    public Date start_date;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd", timezone="UTC")
    public Date end_date;
}
