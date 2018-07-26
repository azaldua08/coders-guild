package com.magenic.gamify.dao;

import java.util.SortedSet;

import com.magenic.gamify.model.Employee;

public interface EmployeeRepositoryCustom {
	SortedSet<Employee> findTop15EmployeesOrderByBadges();
	Employee findEmployeeById(Long id);
}
