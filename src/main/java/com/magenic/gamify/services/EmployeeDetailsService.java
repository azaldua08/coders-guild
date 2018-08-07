package com.magenic.gamify.services;

import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import com.magenic.gamify.model.Badge;
import com.magenic.gamify.model.Employee;
import com.magenic.gamify.model.Skill;
import com.magenic.gamify.model.Trophy;

public interface EmployeeDetailsService {
	public Set<Employee> retrieveTopEmployeesByLevel();
	public SortedSet<Employee> retrieveTopEmployeesByBadges();
	public Optional<Employee> retrieveEmployeeById(Long id);
	public Employee retrieveEmployeeByUsername(String username);
	public Employee createEmployee(Employee employee);
	public Skill createSkill(Skill skill);
	public void updateEmployee(Employee employee);
	public void deleteEmployeeById(Long id);
	public Set<Badge> retrieveBadgesOfEmployee(Long employeeId);
	public Set<Skill> retrieveSkillsOfEmployee(Long employeeId);
	public Set<Trophy> retrieveTrophiesOfEmployee(Long employeeId);
	public void deleteSkillById(Long id);
}
