package com.mastek.training.hrapp.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "JPA_PROJECT")
@NamedQueries({
	@NamedQuery(name = "Department.findByCustomer",
	 query = "select p from Project p where p.customer = :customer")})
public class Project {
	@Value("-1")
	private int proid;
	@Value("Default Project")
	private String name;
	@Value("Default Customer")
	private String customer;
	private Set<Employee> team = new HashSet<>();
	
	//mappedBY: Check the configuration for Many to Many associations 
	//in employee class getAssignments() method
	@ManyToMany(mappedBy = "assignments")
	public Set<Employee> getTeam() {
		return team;
	}

	public void setTeam(Set<Employee> team) {
		this.team = team;
	}

	public Project() {
		System.out.println("Project Created");
	}
	
	@Id
	@Column(name = "project_number") 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	
	@Column(name = "project_name", nullable=false, length = 45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Project [proid=" + proid + ", name=" + name + ", customer=" + customer + "]";
	}
}


