package com.magenic.gamify.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magenic.gamify.dao.EmployeeRepository;
import com.magenic.gamify.dao.SkillRepository;
import com.magenic.gamify.model.Badge;
import com.magenic.gamify.model.Employee;
import com.magenic.gamify.model.Skill;
import com.magenic.gamify.model.Trophy;
import com.magenic.gamify.services.EmployeeDetailsService;

@Service
@Transactional
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SkillRepository skillRepository;

	@Transactional(readOnly = true)
	public Set<Employee> retrieveTopEmployeesByLevel() {
		return employeeRepository.findTop15ByOrderByLevelDesc();
	}

	@Transactional(readOnly = true)
	public SortedSet<Employee> retrieveTopEmployeesByBadges() {
		return employeeRepository.findTop15EmployeesOrderByBadges();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Employee> retrieveEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Employee retrieveEmployeeByUsername(String username) {
		return employeeRepository.findEmployeeByUsername(username);
	}

	@Transactional
	@Override
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Transactional
	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepository.save(employee);
	}

	@Transactional
	@Override
	public void deleteEmployeeById(Long id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteSkillById(Long id) {
		// TODO Auto-generated method stub
		skillRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Set<Badge> retrieveBadgesOfEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		return employeeRepository.findBadgesByEmployeeId(employeeId);
	}

	@Transactional(readOnly = true)
	@Override
	public Set<Skill> retrieveSkillsOfEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		return employeeRepository.findSkillsByEmployeeId(employeeId);
	}

	@Transactional(readOnly = true)
	@Override
	public Set<Trophy> retrieveTrophiesOfEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		return employeeRepository.findTrophiesByEmployeeId(employeeId);
	}

	@Transactional
	@Override
	public Skill createSkill(Skill skill) {
		// TODO Auto-generated method stub
		return skillRepository.save(skill);
	}

	@Transactional(readOnly = true)
	@Override
	public Set<Employee> retrieveEmployeesByGuild(String guild) {
		// TODO Auto-generated method stub
		return employeeRepository.findEmployeesByGuild(guild);
	}

	@Transactional(readOnly = true)
	@Override
	public Set<Employee> retrieveEmployeesByFilters(String name, String guild, String jobClass) {
		// TODO Auto-generated method stub
		return employeeRepository.findEmployeeByFilters(name, guild, jobClass);
	}

	@Transactional
	@Override
	public Set<Employee> createEmployeeBatch(List<Employee> employees) {
		// TODO Auto-generated method stub
		List<Employee> createdEmployees = new ArrayList<Employee>();
		for (Employee employee : employees) {
			Employee createdEmployee = employeeRepository.save(employee);

			if (createdEmployee != null) {
				createdEmployees.add(createdEmployee);
			}
		}
		Set<Employee> result = new HashSet<Employee>(createdEmployees);
		return result;
	}
}
