package com.magenic.gamify.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="skill")
public class Skill implements Serializable{
	
	private Long id;
	
	private String name;
	private int level;
	

	private Long employeeId;
	
	private Employee employee;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="level")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	/*[NPE-DES]*/
	@Column(name="employee_id", insertable=false, updatable=false)
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "id = " + id + ", name = " + name + ", level = " + level + ", employee = " + employee.getName();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		
		if(!(o instanceof Skill)) {
			return false;
		}
		
		Skill skill = (Skill) o;
		
		return skill.name.equals(this.name) &&
				skill.level == this.level &&
				skill.employeeId == this.employeeId;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.name.hashCode();
		result = 31 * result + this.level;
		result = 31 * result + this.employeeId.intValue();
		
		return result;
	}
}
