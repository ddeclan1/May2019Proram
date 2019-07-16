package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "JPA_DEPARTMENT")
@NamedQueries({
	@NamedQuery(name = "Department.findByLocation",
	 query = "select d from Department d where d.location = :location")})
public class Department implements Serializable{
		@Value("-1")
		private int depno;
		@Value("Default Department")
		private String name;
		@Value("Default Location")
		private String location;
		private Set<Employee> members = new HashSet<>();
		
	    //oneTomany used on the get method of set, to configure associations
	    //  fetch=lazy, delays the initialisation until the user calls getMembers()
	    // fetch=eager, initialises the object on findMethod()
	   
	    // Cascade ensures all entity operations done on department would be performed on
	    //on each associated employee object
	   
	   
	    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "currentDepartment")
		public Set<Employee> getMembers() {
			return members;
		}

		public void setMembers(Set<Employee> members) {
			this.members = members;
		}

		public Department() {
			System.out.println("Department Created");
		}
		
		@Id
		@Column(name = "department_number") 
		@GeneratedValue(strategy = GenerationType.AUTO)
		public int getDepno() {
			return depno;
		}
		public void setDepno(int depno) {
			this.depno = depno;
		}
		
		@Column(name = "department_name", nullable=false, length = 45)
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		@Override
		public String toString() {
			return "Department [depno=" + depno + ", name=" + name + ", location=" + location + "]";
		}
	}

