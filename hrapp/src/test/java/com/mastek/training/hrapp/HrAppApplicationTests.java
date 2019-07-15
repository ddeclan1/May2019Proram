package com.mastek.training.hrapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.entities.Employee;

//Initialise the JUnit test with Spring Boot application environment
//Spring Boot Test: Used to test Spring application context with all the test cases identified

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrAppApplicationTests {
	
	//Scan all the components and provide the best match object in the component within memory
	@Autowired
	EmployeeService empService;
	
	@Autowired
	Employee emp;
	
	@Test 
	public void addEmployeeUsingService() {
		emp.setEmpno(6); //Setting empno to 0 adds a new object, changing to a current empno updates an object
		emp.setName("Fred");
		emp.setSalary(23952);
		emp = empService.registerOrUpdateEmployee(emp);
		assertNotNull(emp);
	}
	
	@Test
	public void findByEmpnoUsingService() {
		int empno = 5;
		assertNotNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void deleteByEmpnoUsingService() {
		int empno = 4;
		empService.deleteByEmpno(empno);
		assertNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void checkFetchBySalary() {
		List<Employee> emps = empService.fetchEmployeesBySalaryRange(25000, 100000);
		for (Employee employee : emps) {
			System.out.println(employee);
		}
		assertEquals(emps.size(),1);
	}
	
	@Test
	public void simpleTest() {
		System.out.println("System Test Executed");
	}
}
