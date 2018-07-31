package com.magenic.gamify.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import com.magenic.gamify.dao.EmployeeRepositoryCustom;
import com.magenic.gamify.model.Badge;
import com.magenic.gamify.model.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{
	
	@Autowired
	private EntityManagerFactory emf;
	
	@SuppressWarnings("deprecation")
	@Override
	public SortedSet<Employee> findTop15EmployeesOrderByBadges() {
		
		EntityManager em = entityManager();
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> crit = builder.createTupleQuery();
		Root<Employee> emp = crit.from(Employee.class);
		Join<Employee,Badge> badges = emp.join("badges");
		crit.multiselect(emp.get("name"),emp.get("username"),emp.get("joinDate"),
				emp.get("status"),emp.get("guild"),emp.get("jobClass"),
				emp.get("avatar"),builder.count(badges));
		crit.groupBy(emp.get("name"));
		crit.orderBy(builder.desc(builder.count(badges)));
		
		Query query = session.createQuery(crit);
		@SuppressWarnings("unchecked")
		List<Employee> tupleResult = query.unwrap(org.hibernate.query.Query.class)
				.setResultTransformer(new ResultTransformer() {

					@Override
					public Object transformTuple(Object[] tuple, String[] aliases) {
						// TODO Auto-generated method stub
						Employee emp = new Employee();
						emp.setName((String)tuple[0]);
						emp.setUsername((String)tuple[1]);
						emp.setJoinDate((Date)tuple[2]);
						emp.setStatus((String)tuple[3]);
						emp.setGuild((String)tuple[4]);
						emp.setJobClass((String)tuple[5]);
						emp.setAvatar((String)tuple[6]);
						emp.setNumberOfBadges((Long)tuple[7]);
						return emp;
					}

					@Override
					public List transformList(List collection) {
						// TODO Auto-generated method stub
						return collection;
					}
					
				})
				.setMaxResults(15).getResultList();
		SortedSet<Employee> result = new TreeSet<Employee>(tupleResult);
		return result;
	}
	
	public EntityManager entityManager() {
		return emf.createEntityManager();
	}

	@Override
	public Employee findEmployeeById(Long id) {
		// TODO Auto-generated method stub
		EntityManager em = entityManager();
		Session session = em.unwrap(Session.class);
		
		Employee result = (Employee) session.createQuery("select e from Employee e where e.id = :id")
		.setParameter("id", id)
		.list().get(0);
		return result;
	}


}