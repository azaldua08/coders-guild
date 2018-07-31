package com.magenic.gamify.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.magenic.gamify.model.Employee;
import com.magenic.gamify.services.EmployeeDetailsService;
import com.magenic.gamify.utils.DBUtils;

@RestController
@RequestMapping("/api")
public class EmployeeDetailsController {
	
	@Autowired
	EmployeeDetailsService employeeDetailsService;
	
	/* GET Methods */
	
	@GetMapping("/employeesbylevel")
	public Set<Employee> displayTop15EmployeesByLevel() {
		return employeeDetailsService.retrieveTopEmployeesByLevel();
	}
	
	@GetMapping("/employeesbybadges")
	public SortedSet<Employee> displayTop15EmployeesByBadges() {
		return employeeDetailsService.retrieveTopEmployeesByBadges();
	}
	
	@GetMapping("/employeebyusername/{username}")
	public Employee displayEmployeeByUsername(@PathVariable String username) {
		return employeeDetailsService.retrieveEmployeeByUsername(username).get();
	}
	
	@GetMapping("/employee/{id}")
	public Employee displayEmployeeById(@PathVariable Long id) {
		return employeeDetailsService.retrieveEmployeeById(id).get();
		
	}
	
	/* POST Methods */
	
	@PostMapping("/employee/add")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee){
		Employee createdEmployee = employeeDetailsService.createEmployee(employee);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdEmployee.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/employee/addbatch")
	public ResponseEntity<Object> createEmployeeBatch(@RequestBody List<Employee> employees){
		if(employees != null && employees.size() > 0) {
			URI location = null;
			for(Employee employee : employees) {
				Employee createdEmployee = employeeDetailsService.createEmployee(employee);
				
				location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(createdEmployee.getId()).toUri();
			}
			return ResponseEntity.created(location).build();
			
		}
		return ResponseEntity.noContent().build();
	}
	
	/* PUT Methods */
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @PathVariable Long id){
		Optional<Employee> existingEmployee = employeeDetailsService.retrieveEmployeeById(id);
		
		if(existingEmployee == null) {
			return ResponseEntity.notFound().build();
		}
		DBUtils.copyNonNullProperties(employee, existingEmployee.get());
		//employee.setId(id);
		employeeDetailsService.updateEmployee(existingEmployee.get());
		
		return ResponseEntity.ok(employee);
	}
	
	/* DELETE Methods */
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeDetailsService.deleteEmployeeById(id);
	}
}
