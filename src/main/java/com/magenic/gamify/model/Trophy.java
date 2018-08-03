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
@Table(name="trophy")
public class Trophy implements Serializable{
	
	private Long id;
	
	private String name;
	private Employee employee;
	

	private Long employeeId;
	
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
	public boolean equals(Object o) {
		if (o == this) return true;
		
		if(!(o instanceof Trophy)) {
			return false;
		}
		
		Trophy trophy = (Trophy) o;
		
		return trophy.name.equals(this.name) &&
				trophy.employeeId == this.employeeId;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.name.hashCode();
		result = 31 * result + this.employeeId.intValue();
		
		return result;
	}

}
