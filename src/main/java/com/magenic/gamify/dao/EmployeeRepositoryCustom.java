package com.magenic.gamify.dao;

import java.util.Set;
import java.util.SortedSet;

import com.magenic.gamify.model.Badge;
import com.magenic.gamify.model.Employee;
import com.magenic.gamify.model.Skill;
import com.magenic.gamify.model.Trophy;

public interface EmployeeRepositoryCustom {
	SortedSet<Employee> findTop15EmployeesOrderByBadges();
	Employee findEmployeeById(Long id);
	Employee findEmployeeByUsername(String username);
	Employee findEmployeeByEmail(String email);
	Set<Badge> findBadgesByEmployeeId(Long employeeId);
	Set<Skill> findSkillsByEmployeeId(Long employeeId);
	Set<Trophy> findTrophiesByEmployeeId(Long employeeId);
	Set<Employee> findEmployeeByFilters(String name, String guild, String jobClass);
}
