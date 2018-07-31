package com.magenic.gamify.services.impl;

import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magenic.gamify.dao.EmployeeRepository;
import com.magenic.gamify.model.Employee;
import com.magenic.gamify.services.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Transactional
	public Set<Employee> retrieveTopEmployeesByLevel(){
		return employeeRepository.findTop15ByOrderByLevelDesc();
	}
	
	@Transactional
	public SortedSet<Employee> retrieveTopEmployeesByBadges(){
		return employeeRepository.findTop15EmployeesOrderByBadges();
	}
	
	@Transactional
	@Override
	public Optional<Employee> retrieveEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}
	
	@Transactional
	@Override
	public Optional<Employee> retrieveEmployeeByUsername(String username) {
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
}