package org.jsp.RelationEmpDept.controller;

import java.util.List;
import java.util.Scanner;
import org.jsp.RelationEmpDept.dao.DepartmentDao;
import org.jsp.RelationEmpDept.dao.EmployeeDao;
import org.jsp.RelationEmpDept.dto.Department;
import org.jsp.RelationEmpDept.dto.Employee;

public class Employee_Dept_controller 
{
	static Scanner sc = new Scanner(System.in);
	static EmployeeDao empdao = new EmployeeDao();
	static DepartmentDao deptdao = new DepartmentDao();

	public static void main(String[] args)
	{
		System.out.println("1. Save Department");
		System.out.println("2. Save Employee");
		System.out.println("3. Update Department");
		System.out.println("4. Update Employee");
		System.out.println("5. Find Employee By Department id");
		System.out.println("6. Find Employee By Department name");
		System.out.println("7. Verify Employee By Phone and password");
		System.out.println("8. Verify Employee By Email and password");
		System.out.println("9. Verify Employee By Id and password");
		System.out.println("10.Find Department By Id");

		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			saveDept();
			break;
		}
		case 2:{
			SaveEmp();
			break;
		}
		case 3:{
			updateDept();
			break;
		}
		
		case 4:{
			updateEmp();
			break;
		}
		case 5:{
			findEmpsByDeptId();
			break;
		}
		
		case 6:{
			findEmpsByDeptName();
			break;
		}
		
		case 7:{
			verifyEmpByPhone();
			break;
		}
		
		case 8:{
			verifyEmpByEmail();
			break;
		}
		
		case 9:{
			verifyEmpById();
			break;
		}
		case 10:{
			findDeptById();
			break;
		}
		
		default: {
			System.err.println("Invalid Choice");
		}
		}

	}

	public static void saveDept() {
		System.out.println("enter the  name and location to save department ");
		Department d = new Department();
		d.setName(sc.next());
		d.setLocation(sc.next());
		d=deptdao.saveDepartment(d);
		System.out.println("Department save with Id : "+d.getId());
	}
	
	public static void updateDept()
	{
		System.out.println("Enter the department id to update");
		int id=sc.nextInt();
		System.out.println("enter the name and location to save to department ");
		Department d=new Department();
		d.setId(id);
		d.setName(sc.next());
		d.setLocation(sc.next());
		d=deptdao.UpdateDepartment(d);
		System.out.println("update department");
		
	}
	public static void SaveEmp()
	{
		System.out.println("Enter the department id to add employee");
		int dept_id =sc.nextInt();
		System.out.println("enter the   desg, email,name,password,phone,salary");
		Employee e=new Employee();
		e.setDesignation(sc.next());
		e.setEmail(sc.next());
		e.setName(sc.next());
		e.setPassword(sc.next());
		e.setPhone(sc.nextLong());
		e.setSalary(sc.nextDouble());
		
		e=empdao.SaveEmployee(e, dept_id);
		if(e !=null)
		{
			System.out.println("Employee save with id " + e.getId());
		}
		else
		{
			System.out.println("invalid department id ");
		}
	}
	
	public static void updateEmp()
	{
		System.out.println("enter  the department id  to add Emp");
		int dept_id=sc.nextInt();
		System.out.println("enter the  id, desg, email,name,password,phone,salary");
		Employee e =new Employee();		
		e.setId(sc.nextInt());
		e.setDesignation(sc.next());
		e.setEmail(sc.next());
		e.setName(sc.next());
		e.setPassword(sc.next());
		e.setPhone(sc.nextLong());
		e.setSalary(sc.nextDouble());
		e=empdao.updateEmployee(e, dept_id);
		if(e != null)
		{
			System.out.println("Employee saved with Id : "+ e.getId());
		}
		else
		{
			System.out.println("Invalid Department Id");
		}
		
	}
	
	
	public static void findEmpsByDeptId() {
		System.out.println("Enter the department id to find Employees");
		int dept_id = sc.nextInt();
		List<Employee> emps = empdao.findEmpsByDeptId(dept_id);
		if (emps.size() > 0) {
			for (Employee e : emps) {
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee name:" + e.getName());
				System.out.println("Designation:" + e.getDesignation());
				System.out.println("Phone Number:" + e.getPhone());
				System.out.println("Email Id:" + e.getEmail());
				System.out.println("------------------------------");
			}
		} else {
			System.err.println("Invalid Department id");
		}
	}
	
	
	public static void findEmpsByDeptName() {
		System.out.println("Enter the department name to find Employees");
		String name = sc.next();
		List<Employee> emps = empdao.findEmpsByDeptName(name);
		if (emps.size() > 0) {
			for (Employee e : emps) {
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee name:" + e.getName());
				System.out.println("Designation:" + e.getDesignation());
				System.out.println("Phone Number:" + e.getPhone());
				System.out.println("Email Id:" + e.getEmail());
				System.out.println("------------------------------");
			}
		} else {
			System.err.println("Invalid Department Name");
		}
	}
	
	
	
	public static void verifyEmpById() {
		System.out.println("Enter the Employee id and password to verify");
		int id = sc.nextInt();
		String password = sc.next();
		Employee e = empdao.verifyEmployee(id, password);
		if (e != null) {
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee name:" + e.getName());
			System.out.println("Designation:" + e.getDesignation());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
		} else {
			System.err.println("Invalid Id or password");
		}
	}

	
	public static void verifyEmpByPhone() {
		System.out.println("Enter the phone and password to verify Employee");
		long phone = sc.nextLong();
		String password = sc.next();
		Employee e = empdao.verifyEmployee(phone, password);
		if (e != null) {
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee name:" + e.getName());
			System.out.println("Designation:" + e.getDesignation());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
		} else {
			System.err.println("Invalid Phone Number or password");
		}
	}
	
	
	
	public static void verifyEmpByEmail() {
		System.out.println("Enter the Email id and password to verify ");
		String email = sc.next();
		String password = sc.next();
		Employee e = empdao.verifyEmployee(email, password);
		if (e != null) {
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee name:" + e.getName());
			System.out.println("Designation:" + e.getDesignation());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
		} else {
			System.err.println("Invalid Email Id or password");
		}
	}
	
	public static void findDeptById() {
		int dept_id = sc.nextInt();
		Department d = deptdao.findByid(dept_id);
		if (d != null) {
			System.out.println("Department Id:" + d.getId());
			System.out.println("Department Name:" + d.getName());
			System.out.println("Location:" + d.getLocation());
		} else {
			System.err.println("Invalid Department Id");
		}
	}

}

  
