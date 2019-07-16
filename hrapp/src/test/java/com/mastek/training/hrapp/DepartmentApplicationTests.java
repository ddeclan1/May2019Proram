package com.mastek.training.hrapp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.entities.Department;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentApplicationTests {

		@Autowired
		DepartmentService depService;
		
		@Autowired
		Department dep;
		
		@Test 
		public void addDepartmentUsingService() {
			dep.setDepno(0);
			dep.setName("Development");
			dep.setLocation("Bradford");
			dep = depService.registerOrUpdateDepartment(dep);
			assertNotNull(dep);
		}
		
		@Test
		public void findByDepnoUsingService() {
			int depno = 38;
			assertNotNull(depService.findByDepno(depno));
		}
		
		@Test
		public void deleteByDepnoUsingService() {
			int depno = 39;
			depService.deleteByDepno(depno);
			assertNull(depService.findByDepno(depno));
		}
		
		@Test
		public void checkFetchByLocation() {
			List<Department> deps = depService.fetchDepartmentsByLocation("Bradford");
			for (Department department : deps) {
				System.out.println(department);
			}
			assertEquals(deps.size(),3);
		}
		
		@Test
		public void simpleTest() {
			System.out.println("System Test Executed");
		}
	}
