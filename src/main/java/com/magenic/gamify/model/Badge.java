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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="badge")
public class Badge implements Serializable {

	private Long id;
	
	
	private Long employeeId;
	
	private String name;
	@JsonProperty(access=Access.WRITE_ONLY)
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
	
	/*[Issue-NPE-DES]: Deserialization of the child entity e.g badge during update is causing NPE
	 * This is because the parent entity employee cannot be deserialized. It's null. The below methods
	 * Will throw NPE when called during update. 
	 * Use Alternative described here:
	 * https://stackoverflow.com/questions/5741750/implementing-equals-hashcode-using-a-referenced-manytoone-entity */
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		
		if(!(o instanceof Badge)) {
			return false;
		}
		
		Badge badge = (Badge) o;
		
		return badge.name.equals(this.name) &&
				badge.employeeId == this.employeeId;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.name.hashCode();
		result = 31 * result + this.employeeId.intValue();
		
		return result;
	}
}
