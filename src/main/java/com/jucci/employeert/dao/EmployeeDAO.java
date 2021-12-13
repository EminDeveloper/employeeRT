package com.jucci.employeert.dao;

import com.jucci.employeert.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDAO {

    private static final Map<String, Employee> empMap = new HashMap<String, Employee>();

    static {
        initEmps();
    }

    private static void initEmps() {
        Employee emp1 = new Employee("C01", "Madina", "Bagdanova");
        Employee emp2 = new Employee("C02", "Leyla", "Balayeva");
        Employee emp3 = new Employee("C03", "Mehman", "Alibeyli");

        empMap.put(emp1.getEmpNo(), emp1);
        empMap.put(emp2.getEmpNo(), emp2);
        empMap.put(emp3.getEmpNo(), emp3);
    }

    public Employee getEmployee(String empNo) {
        return empMap.get(empNo);
    }

    public Employee addEmployee(Employee emp) {
        empMap.put(emp.getEmpNo(), emp);
        return emp;
    }

    public Employee updateEmployee(Employee emp) {
        empMap.put(emp.getEmpNo(), emp);
        return emp;
    }

    public void deleteEmployee(String empNo) {
        empMap.remove(empNo);
    }

    public List<Employee> getAllEmployees() {
        Collection<Employee> c = empMap.values();
        List<Employee> list = new ArrayList<Employee>();
        list.addAll(c);
        return list;
    }

}