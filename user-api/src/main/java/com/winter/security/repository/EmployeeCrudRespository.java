package com.winter.security.repository;

import com.winter.security.model.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * @ClassName : EmployeeCrudRespository  //类名
 * @Description : 继承CRUD Respository  //描述
 * @Author : Winter  //作者
 * @Date: 2020-08-24 15:52  //时间
 */
public interface EmployeeCrudRespository extends CrudRepository<Employee, Integer> {


//    public List<Employee> saveAll(List<Employee> employees);
}
