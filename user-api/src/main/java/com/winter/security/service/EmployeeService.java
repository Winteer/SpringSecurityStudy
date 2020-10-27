package com.winter.security.service;


import com.winter.security.model.Employee;

import java.util.List;

public interface EmployeeService {

    public void save(Employee employee);

    public Employee findByName(String name);

    public Employee findById(int id);

    public int getCount();

    public void updateOne(int id, String name);

    public void saveAll(List<Employee> employees);
}
