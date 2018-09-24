package com.magenic.gamify.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.magenic.gamify.exceptions.RequestContext;
import com.magenic.gamify.model.Badge;
import com.magenic.gamify.model.Employee;
import com.magenic.gamify.model.Skill;
import com.magenic.gamify.model.Trophy;
import com.magenic.gamify.services.EmployeeDetailsService;
import com.magenic.gamify.utils.DBUtils;

@RestController
@RequestMapping("/api")
public class EmployeeDetailsController {

	@Autowired
	private EmployeeDetailsService employeeDetailsService;
	
	// https://stackoverflow.com/questions/43502332/how-to-get-the-requestbody-in-an-exceptionhandler-spring-rest
	@Autowired
    private RequestContext requestContext;

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
		return employeeDetailsService.retrieveEmployeeByUsername(username);
	}

	@GetMapping("/employeesbyguild/{guild}")
	public Set<Employee> displayEmployeesByGuild(@PathVariable String guild) {
		return employeeDetailsService.retrieveEmployeesByGuild(guild);
	}

	@GetMapping("/employeesbyfilters")
	public Set<Employee> displayEmployeesByFilters(@RequestParam String name, @RequestParam String guild,
			@RequestParam String jobClass) {
		return employeeDetailsService.retrieveEmployeesByFilters(name, guild, jobClass);
	}

	@GetMapping("/employee/{id}")
	public Employee displayEmployeeById(@PathVariable Long id) {
		return employeeDetailsService.retrieveEmployeeById(id).get();

	}

	@GetMapping("/employeebadges/{id}")
	public Set<Badge> displayEmployeeBadges(@PathVariable Long id) {
		Set<Badge> b = employeeDetailsService.retrieveBadgesOfEmployee(id);
		return b;

	}

	@GetMapping("/employeeskills/{id}")
	public Set<Skill> displayEmployeeSkills(@PathVariable Long id) {
		Set<Skill> s = employeeDetailsService.retrieveSkillsOfEmployee(id);
		return s;

	}

	@GetMapping("/employeetrophies/{id}")
	public Set<Trophy> displayEmployeeTrophies(@PathVariable Long id) {
		Set<Trophy> t = employeeDetailsService.retrieveTrophiesOfEmployee(id);
		return t;

	}

	/* POST Methods */

	@PostMapping("/employee/add")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
		Employee createdEmployee = employeeDetailsService.createEmployee(employee);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdEmployee.getId()).toUri();

		return ResponseEntity.created(location).body(createdEmployee);
	}

	@PostMapping("/employeeskill/add/{id}")
	public ResponseEntity<Object> createSkill(@RequestBody Skill skill, @PathVariable Long id) {
		Optional<Employee> existingEmployee = employeeDetailsService.retrieveEmployeeById(id);

		if (existingEmployee == null) {
			return ResponseEntity.notFound().build();
		}

		skill.setEmployee(existingEmployee.get());
		Skill createdSkill = employeeDetailsService.createSkill(skill);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdSkill.getId()).toUri();

		return ResponseEntity.created(location).body(createdSkill);
	}

	@PostMapping("/employee/addbatch")
	public ResponseEntity<Object> createEmployeeBatch(@RequestBody List<Employee> employees) {
		requestContext.setRequestBody(employees);
		if (employees != null && employees.size() > 0) {
			Set<Employee> createdEmployees = employeeDetailsService.createEmployeeBatch(employees);
			return ResponseEntity.ok().body(createdEmployees);

		}
		return ResponseEntity.noContent().build();
	}

	/* PUT Methods */

	@PutMapping("/employee/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		Optional<Employee> existingEmployee = employeeDetailsService.retrieveEmployeeById(id);

		if (existingEmployee == null) {
			return ResponseEntity.notFound().build();
		}

		/*
		 * [Issue]: JPA/Hibernate sets all fields including unchanged ones. See DBUtils.
		 * Reference:
		 * https://stackoverflow.com/questions/27818334/jpa-update-only-specific-fields/
		 * 27862208
		 */

		DBUtils.copyNonNullProperties(employee, existingEmployee.get());

		employeeDetailsService.updateEmployee(existingEmployee.get());

		return ResponseEntity.ok(existingEmployee.get());
	}

	/* DELETE Methods */

	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeDetailsService.deleteEmployeeById(id);
	}

	@DeleteMapping("/employeeskill/{id}")
	public void deleteSkill(@PathVariable Long id) {
		employeeDetailsService.deleteSkillById(id);
	}

	@ExceptionHandler(value = { TransactionSystemException.class, RollbackException.class, 
			ConstraintViolationException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, HttpServletRequest request) {
		List requestBody = (List) requestContext.getRequestBody();
		
		ConstraintViolationException cex= (ConstraintViolationException) ex.getCause().getCause();
		boolean partiallyCreated = false;
		List<String> errors = new ArrayList<String>();
		for(ConstraintViolation violation: cex.getConstraintViolations()) {
			String rootBeanUsername = ((Employee)violation.getRootBean()).getUsername();
			String firstUsername = ((Employee) requestBody.get(0)).getUsername();
			partiallyCreated = !rootBeanUsername.equals(firstUsername);
			
			errors.add("Failed to create employee with username " + rootBeanUsername
					+ " Error: "  + violation.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		if (partiallyCreated) {
			headers.add("partiallyCreated", "true");
		}
		return new ResponseEntity(errors, headers, HttpStatus.BAD_REQUEST);
	}
}
