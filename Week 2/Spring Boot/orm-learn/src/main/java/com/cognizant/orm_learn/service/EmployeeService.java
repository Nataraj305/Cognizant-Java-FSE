package com.cognizant.orm_learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getPermanentEmployees() {
        return employeeRepository.getPermanentEmployees();
    }
    public List<Employee> getPermanentEmployeesJoinFetch() {
    return employeeRepository.getPermanentEmployeesJoinFetch();
}
public List<Employee> getPermanentEmployeesNative() {
    return employeeRepository.getPermanentEmployeesNative();
}
public double getAverageSalary() {
    return employeeRepository.getAverageSalary();
}

public double getAverageSalary(int id) {
    return employeeRepository.getAverageSalary(id);
}
}