package com.magenic.gamify.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.magenic.gamify.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>, EmployeeRepositoryCustom {
	Set<Employee> findTop15ByOrderByLevelDesc();
	Set<Employee> findEmployeesByGuild(String guild);
}
