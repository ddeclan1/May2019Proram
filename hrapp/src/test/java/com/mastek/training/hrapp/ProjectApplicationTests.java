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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	Project pro;
	
	@Test 
	public void addProjectUsingService() {
		pro.setProid(0);
		pro.setName("Alpha");
		pro.setCustomer("BJSS");
		pro = proService.registerOrUpdateProject(pro);
		assertNotNull(pro);
	}
	
	@Test
	public void findByProidUsingService() {
		int proid = 41;
		assertNotNull(proService.findByProid(proid));
	}
	
	@Test
	public void deleteByProidUsingService() {
		int proid = 42;
		proService.deleteByProid(proid);
		assertNull(proService.findByProid(proid));
	}
	
	@Test
	public void checkFetchByCustomer() {
		List<Project> pros = proService.fetchProjectsByCustomer("BJSS");
		for (Project project : pros) {
			System.out.println(project);
		}
		assertEquals(pros.size(),3);
	}
	
	@Test
	public void simpleTest() {
		System.out.println("System Test Executed");
	}
}
