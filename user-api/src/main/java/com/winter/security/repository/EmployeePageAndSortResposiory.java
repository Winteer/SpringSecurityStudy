package com.winter.security.repository;

import com.winter.security.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeePageAndSortResposiory extends PagingAndSortingRepository<Employee,Integer> {


    public Page<Employee> findAllByAge(Pageable pageAble, int age);

}
