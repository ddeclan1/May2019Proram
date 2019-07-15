package com.mastek.training.hrapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Employee;

@Component 
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	//Declare the custom query method wit the same name as the query
	public List<Employee> findBySalary(
			@Param("min") Double min, //Declare parameter name
			@Param("max") Double max);
}
