package by.mitsko.library.dao;

import java.util.Date;

import by.mitsko.library.bean.Employee;

public interface EmployeeDao extends BaseDao <Employee> {

	public void insert(String name, String surname, Date birthdate, String email);

	

}
