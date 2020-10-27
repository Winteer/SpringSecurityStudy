package com.winter.security.service.impl;

import com.winter.security.model.Employee;
import com.winter.security.repository.EmployeeCrudRespository;
import com.winter.security.repository.EmployeeRespository;
import com.winter.security.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName : EmployeeServiceImpl  //类名
 * @Description : 雇员业务实现类  //描述
 * @Author : Winter  //作者
 * @Date: 2020-08-19 17:42  //时间
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository employeeRespository;

    @Autowired
    private EmployeeCrudRespository employeeCrudRespository;

    @Override
    public void save(Employee employee) {
        employeeRespository.save(employee);
    }

    @Override
    public Employee findByName(String name) {
        return employeeRespository.findByName(name);
    }

    @Override
    public Employee findById(int id) {
        return employeeRespository.findById(id);
    }


    @Override
    public int getCount() {
        return employeeRespository.getCount();
    }

    @Transactional
    @Override
    public void updateOne(int id, String name) {
        employeeRespository.updateOne(id, name);
    }


    @Transactional
    @Override
    public void saveAll(List<Employee> employees) {
        employeeCrudRespository.saveAll(employees);
    }


//    public List<Employee> findAllByPage()



}
