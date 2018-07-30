package com.magenic.gamify.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.magenic.gamify.dao.EmployeeRepository;
import com.magenic.gamify.model.Employee;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserBuilder builder = null;
		Optional<Employee> employeeOptional = employeeRepository.findEmployeeByUsername(username);
		if(employeeOptional.isPresent()) {
			Employee employee = employeeOptional.get();
			builder = User.withUsername(username)
					.password(new BCryptPasswordEncoder().encode(employee.getPassword()))
					.roles(employee.getRoles());
		}
		return builder.build();
	}

}
