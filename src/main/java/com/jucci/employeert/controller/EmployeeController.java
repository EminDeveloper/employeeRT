package com.jucci.employeert.controller;

import com.jucci.employeert.dao.EmployeeDAO;
import com.jucci.employeert.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate.";
    }

    // http://localhost:8080/employees
    @GetMapping(value = "/employees",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
            @RequestHeader(name = "X-COM-LOCATION", defaultValue = "ASIA") String headerLocation
    ) {
        List<Employee> list = employeeDAO.getAllEmployees();
        return ResponseEntity.ok(list);
    }

    // http://localhost:8080/employee/{empNo}
    @GetMapping(value = "/employee/{empNo}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Employee getEmployee(
            @PathVariable("empNo") String empNo) {
        return employeeDAO.getEmployee(empNo);
    }

    // http://localhost:8080/employee
    @PostMapping(value = "/employee",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Employee addEmployee(@RequestBody Employee emp) {
        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
        return employeeDAO.addEmployee(emp);
    }

    // http://localhost:8080/employee
    @PutMapping(value = "/employee",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Employee updateEmployee(
            @RequestBody Employee emp
    ) {
        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
        return employeeDAO.updateEmployee(emp);
    }

    // http://localhost:8080/employee/{empNo}
    @DeleteMapping(value = "/employee/{empNo}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteEmployee(@PathVariable("empNo") String empNo) {
        System.out.println("(Service Side) Deleting employee: " + empNo);
        employeeDAO.deleteEmployee(empNo);
    }
}
