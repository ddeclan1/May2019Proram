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

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.apis.ProjectService;
import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.entities.Employee;
import com.mastek.training.hrapp.entities.Project;

//Initialise the JUnit test with Spring Boot application environment
//Spring Boot Test: Used to test Spring application context with all the test cases identified

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrAppApplicationTests {
	
	@Autowired
	DepartmentService deptService;
	
	@Autowired
	ProjectService proService;
	
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
		int empno = 40;
		assertNotNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void deleteByEmpnoUsingService() {
		int empno = 43;
		empService.deleteByEmpno(empno);
		assertNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void checkFetchBySalary() {
		List<Employee> emps = empService.fetchEmployeesBySalaryRange(25000, 100000);
		for (Employee employee : emps) {
			System.out.println(employee);
		}
		assertEquals(emps.size(),3);
	}
	
	@Test
	public void manageAssociations() {
		Department d1 = new Department();
		d1.setName("Admin");
		d1.setLocation("UK");
		
		Employee emp1 = new Employee();
		emp1.setName("Admin Emp 1");
		emp1.setSalary(3535);
		
		Employee emp2 = new Employee();
		emp2.setName("Admin Emp 2");
		emp2.setSalary(35839);
		
		Project p1 = new Project();
		p1.setName("Delta");
		p1.setCustomer("Jet2");
		
		Project p2 = new Project();
		p2.setName("Beta");
		p2.setCustomer("Asda");
		
		//One to Many: One department has many employees
		d1.getMembers().add(emp1);
		d1.getMembers().add(emp2);
		//Many to One for each employee to assign the department
		emp1.setCurrentDepartment(d1);
		emp2.setCurrentDepartment(d1);
		
		//Many to Many
		emp1.getAssignments().add(p2);
		emp1.getAssignments().add(p1);
		emp2.getAssignments().add(p1);
		
		deptService.registerOrUpdateDepartment(d1);
	}
	
	@Test
	public void simpleTest() {
		System.out.println("System Test Executed");
	}
}
