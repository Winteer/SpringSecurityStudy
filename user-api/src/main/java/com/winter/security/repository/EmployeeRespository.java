package com.winter.security.repository;

import com.winter.security.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


public interface EmployeeRespository extends Repository<Employee,Integer> {

    public void save(Employee employee);


    public Employee findByName(String name);

    public Employee findById(int id);


    @Query(nativeQuery = true,value="select count(*) from Employee")
    public int getCount();


    @Modifying
    @Query("update Employee o set o.name = :name where o.id = :id")
    public void updateOne(@Param("id") int id, @Param("name") String name);
}
