package com.magenic.gamify.services;

import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import com.magenic.gamify.model.Badge;
import com.magenic.gamify.model.Employee;

public interface EmployeeDetailsService {
	public Set<Employee> retrieveTopEmployeesByLevel();
	public SortedSet<Employee> retrieveTopEmployeesByBadges();
	public Optional<Employee> retrieveEmployeeById(Long id);
	public Employee retrieveEmployeeByUsername(String username);
	public Employee createEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployeeById(Long id);
	public Set<Badge> retrieveBadgesOfEmployee(Long employeeId);
}
