/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employees.prueba.service;

import com.employees.prueba.dto.AddWorkedHoursDTO;
import com.employees.prueba.dto.EmployeesByJobDTO;
import com.employees.prueba.dto.HorasTrabajadasDTO;
import com.employees.prueba.dto.RespuestaEmployeesByJobDTO;
import com.employees.prueba.dto.RespuestaGenerica;
import com.employees.prueba.dto.RespuestaHorasTrabajadas;
import com.employees.prueba.dto.RespuestaPagoTotalDTO;
import com.employees.prueba.dto.TotalWorkedHoursDTO;
import com.employees.prueba.entities.Employee;
import com.employees.prueba.entities.EmployeeWorkedHours;
import com.employees.prueba.entities.Gender;
import com.employees.prueba.entities.Job;
import com.employees.prueba.repository.EmployeeRepository;
import com.employees.prueba.repository.EmployeeWorkedHoursRepository;
import com.employees.prueba.repository.GenderRepository;
import com.employees.prueba.repository.JobRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 *
 * @author Phaxw
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;
    
    @Autowired
    private JobRepository jobRepo;
    
    @Autowired
    private GenderRepository genderRepo;
    
    @Autowired
    private EmployeeWorkedHoursRepository employeeWorkedRepo;
    
    public RespuestaGenerica save(Employee employee){

        RespuestaGenerica respuesta = new RespuestaGenerica();

        if(obtenerEdad(employee.birthdate) < 18){
            respuesta.id = null;
            respuesta.success = false;
            return respuesta;
        }

        if (validarNombreApellido(employee)){
            respuesta.id = null;
            respuesta.success = false;
            return respuesta;
        }

        if(!validarExistePuesto(employee.jobid)){
            respuesta.id = null;
            respuesta.success = false;
            return respuesta;
        }

        if(!validarExisteGender(employee.gender_id)){
            respuesta.id = null;
            respuesta.success = false;
            return respuesta;
        }

        Employee newEmp = employeeRepo.save(employee);
        respuesta.id = newEmp.id;
        respuesta.success = true;

        return respuesta;
    }
    
    public RespuestaGenerica addWorkedHours(AddWorkedHoursDTO addWorkedHours){
        
        RespuestaGenerica respuesta = new RespuestaGenerica();
        
        if(!validaExisteEmpleado(addWorkedHours.employee_id)){
            respuesta.id = null;
            respuesta.success = false;
            return respuesta;
        }
        
        if (addWorkedHours.worked_hours > 20){
            respuesta.id = null;
            respuesta.success = false;
            return respuesta;
        }
        
        Date hoy = new Date();
        
        if (!validarFechaMenorActual(addWorkedHours.worked_date, hoy)){
            respuesta.id = null;
            respuesta.success = false;
            return respuesta;
        }
        
        if (!validaRegistroDelDiaWorkedHours(addWorkedHours.employee_id, addWorkedHours.worked_date)){
            respuesta.id = null;
            respuesta.success = false;
            return respuesta;
        }
        EmployeeWorkedHours ewh = new EmployeeWorkedHours();
        ewh.employeeid = addWorkedHours.employee_id;
        ewh.workedhours = addWorkedHours.worked_hours;
        ewh.workeddate = addWorkedHours.worked_date;
        
        EmployeeWorkedHours nuevoEwh = employeeWorkedRepo.save(ewh);
        
        respuesta.id = nuevoEwh.employeeid;
        respuesta.success = true;
        
        return respuesta;
    }
    
    public RespuestaEmployeesByJobDTO employesbyJobId(Integer job_id){
        
        RespuestaEmployeesByJobDTO respuesta = new RespuestaEmployeesByJobDTO();
        
        if(!validaJobExiste(job_id)){
            respuesta.success = false;
            respuesta.employees = null;
            return respuesta;
        }
        
        List<EmployeesByJobDTO> employeesByJobId = new ArrayList<EmployeesByJobDTO>();
        List<Employee> employees = employeeRepo.findAllByJobid(job_id);
        EmployeesByJobDTO emp = new EmployeesByJobDTO();
        
        
        for (Employee employee: employees){
             
            emp.id = employee.id;
            emp.name = employee.name;
            emp.lastname = employee.lastname;
            emp.birthdate = employee.birthdate;
            emp.job = jobRepo.findById(employee.jobid).get();
            emp.gender = genderRepo.findById(employee.gender_id).get();
            
            employeesByJobId.add(emp);
        }
        
        respuesta.success = true;
        respuesta.employees = employeesByJobId;
        
        return respuesta;
    }
    
    public RespuestaHorasTrabajadas totalHorasTrabajas(HorasTrabajadasDTO horasTrabajadas){
        
        RespuestaHorasTrabajadas respuesta = new RespuestaHorasTrabajadas();
        List<EmployeeWorkedHours> employees = new ArrayList<EmployeeWorkedHours>();
        Integer workedHours = 0;
        
        if (!validarFechaMenorActual(horasTrabajadas.start_date , horasTrabajadas.end_date)){
            respuesta.total_worked_hours = null;
            respuesta.success = false;
            return respuesta;
        }

        if(!validaExisteEmpleado(horasTrabajadas.employee_id)){
            respuesta.total_worked_hours = null;
            respuesta.success = false;
            return respuesta;
        }
        
        employees = employeeWorkedRepo.findByEmployeeidAndWorkeddateBetween(horasTrabajadas.employee_id,horasTrabajadas.start_date, horasTrabajadas.end_date);
        
        
        for(EmployeeWorkedHours employee: employees){
            workedHours += employee.workedhours;
        }
        
        respuesta.total_worked_hours = workedHours;
        respuesta.success = true;
        return respuesta;
    }
    
    public RespuestaPagoTotalDTO pagoTotal(HorasTrabajadasDTO horasTrabajadas){
        RespuestaPagoTotalDTO respuesta = new RespuestaPagoTotalDTO();
        List<EmployeeWorkedHours> employees = new ArrayList<EmployeeWorkedHours>();
        Job job = new Job();
        Employee emp = new Employee();
        Integer payment = 0;
        Integer workedHours = 0;
        
        if (!validarFechaMenorActual(horasTrabajadas.start_date , horasTrabajadas.end_date)){
            respuesta.payment = null;
            respuesta.success = false;
            return respuesta;
        }

        if(!validaExisteEmpleado(horasTrabajadas.employee_id)){
            respuesta.payment = null;
            respuesta.success = false;
            return respuesta;
        }
        
        employees = employeeWorkedRepo.findByEmployeeidAndWorkeddateBetween(horasTrabajadas.employee_id,horasTrabajadas.start_date, horasTrabajadas.end_date);
        
         for(EmployeeWorkedHours employee: employees){
            workedHours += employee.workedhours;
        }
        
        emp = employeeRepo.findById(employees.get(0).employeeid).get();
        job = jobRepo.findById(emp.jobid).get();
        
        respuesta.payment = workedHours * job.salary;
        respuesta.success = true;
        
        
        return respuesta;
    }
    
    public boolean validaJobExiste(Integer job_id){
         
        Optional<Job> job = jobRepo.findById(job_id);
        if(job.isPresent()){
            return true;
        }
        return false;
    }
    
    public boolean validaRegistroDelDiaWorkedHours(Integer employee_id, Date worked_date){
        EmployeeWorkedHours ewh = employeeWorkedRepo.findByEmployeeidAndWorkeddate(employee_id, worked_date);
        
        if(ObjectUtils.isEmpty(ewh)){
            return true;
        }
        
        return false;
    }
    
    public boolean validarFechaMenorActual(Date fechaTrabajada, Date fechaActual){
        
        if (fechaTrabajada.before(fechaActual)){
            return true;
        }
        
        return false;
    }

    public int obtenerEdad(Date birthdate){

        Calendar empBirthDate = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        empBirthDate.setTime(birthdate);
        today.setTime(new Date());

        return today.get(Calendar.YEAR) - empBirthDate.get(Calendar.YEAR);
    }
    
    public boolean validaExisteEmpleado(Integer employee_id){
        Optional<Employee> emp = employeeRepo.findById(employee_id);
        
        if(emp.isPresent()){
            return true;
        }
        return false;
    }

    public boolean validarNombreApellido(Employee employee){

        Employee emp = employeeRepo.findByNameAndLastname(employee.name, employee.lastname);

        if (!ObjectUtils.isEmpty(emp)) {
            return true;
        }
        return false;
    }

    public boolean validarExistePuesto(int idPuesto){

        Optional<Job> job = jobRepo.findById(idPuesto);
        if (job.isPresent()){
            return true;
        }
        return false;
    }

    public boolean validarExisteGender(int idGender){

        Optional<Gender> gender = genderRepo.findById(idGender);
        if(gender.isPresent()){
            return true;
        }
        return false;
    }
    
}
