package org.jsp.RelationEmpDept.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.RelationEmpDept.dto.Department;
import org.jsp.RelationEmpDept.dto.Employee;

public class EmployeeDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Employee SaveEmployee(Employee employee, int dept_id) {

		Department d = manager.find(Department.class, dept_id);
		if (d != null) {
			employee.setDept(d);
			d.getEmps().add(employee);
			EntityTransaction transaction = manager.getTransaction();
			manager.persist(employee);
			transaction.begin();
			transaction.commit();
			return employee;
		}
		return null;
	}
	
	public Employee updateEmployee(Employee employee, int dept_id)
	{
		Department d=manager.find(Department.class,dept_id);
		if(d !=null)
		{
			employee.setDept(d);
			d.getEmps().add(employee);
			EntityTransaction transaction=manager.getTransaction();
			manager.merge(employee);
			transaction.begin();
			transaction.commit();
			return employee;
		}
		return null;
	}
	public List<Employee>findEmpsByDeptId(int id)
	{
		String qry="select d.emps from Department d where d.id=?1";
		Query q=manager.createQuery(qry);
		q.setParameter(1, id);
		return q.getResultList();
	}
	
	public List<Employee>findEmpsByDeptName(String name)
	{
		String qry="select d.emps from Department d where d.name=?1";
		Query q=manager.createQuery(qry);
		q.setParameter(1, name);
		return q.getResultList();
	}
	
	public Employee verifyEmployee(int id, String password)
	{
		String qry="select e from Employee e where e.id=?1 and e.password=?2";
		Query q=manager.createQuery(qry);
		q.setParameter(1, id);
		q.setParameter(2, password);
		
		try {
			 return (Employee) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null; 
		}
	}
		public Employee verifyEmployee(long phone , String password)
		{
			String qry = "select e from Employee e where e.phone=?1 and e.password=?2";
			Query q=manager.createQuery(qry);
			q.setParameter(1, phone);
			q.setParameter(2, password);
			
			try {
				 return (Employee) q.getSingleResult();
			}
			catch (NoResultException e) {
				return null; 
			}
		 
			
	}
		public Employee verifyEmployee(String email, String password)
		{
			String qry="select e from Employee e where e.email=?1 and e.password=?2";
			Query q=manager.createQuery(qry);
			q.setParameter(1, email);
			q.setParameter(2, password);
			
			try {
				 return (Employee) q.getSingleResult();
			}
			catch (NoResultException e) {
				return null; 
			} 
		}
}
