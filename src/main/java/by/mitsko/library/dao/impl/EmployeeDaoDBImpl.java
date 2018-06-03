package by.mitsko.library.dao.impl;

import java.sql.Connection;
import java.util.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.mitsko.library.bean.Book;
import by.mitsko.library.bean.Employee;
import by.mitsko.library.dao.EmployeeDao;
import static by.mitsko.library.dao.util.DBConnectionHelper.*;

public class EmployeeDaoDBImpl implements EmployeeDao{
	
	private static final String SQL_CREATE_EMPLOYEE = "create table employee (id int auto_increment primary key, name varchar(50) null, surname varchar(50) null, bipthdate Date null, email varchar(50) null )";
	private static final String SQL_SELECT_EMPLOYEE = "select * from employee where id = ?";
	private static final String SQL_SELECT_EMPLOYEES = "select * from employee";
	private static final String SQL_UPDATE_EMPLOYEES = "update employee set surname = ? where id = ?";
	private static final String SQL_DELETE_EMPLOYEES = "delete from employee where id = ?";
	private static final String SQL_INSERT_EMPLOYEE = "insert into employee (name, surname, birthdate, email) VALUES (?, ?, ?, ?)";
			
	public void create() {
		Connection connection = connect();
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(SQL_CREATE_EMPLOYEE);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);

	}

	public List <Employee> read(int id) {
		Connection connection = connect();
		List<Employee> employees = new ArrayList<>();

		try {
			Statement st = connection.createStatement();
			PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_EMPLOYEE);
			stmt.setInt(1, id); 
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Employee em = new Employee();
				em.setId(rs.getInt("id"));
				em.setName(rs.getString("name"));
				em.setSurname(rs.getString("surname"));
				em.setBirthdate(rs.getDate("birthdate"));
				em.setSurname(rs.getString("email"));
				employees.add(em);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);
	
		return employees;
	}

	public List<Employee> readAll() {
		Connection connection = connect();
		List<Employee> employees = new ArrayList<>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_EMPLOYEES );
			
			
						
			while (rs.next()){
				Employee em = new Employee();
				em.setId(rs.getInt("id"));
				em.setName(rs.getString("name"));
				em.setSurname(rs.getString("surname"));
				em.setBirthdate(rs.getDate("birthdate"));
				em.setSurname(rs.getString("email"));
				employees.add(em);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		disconnect (connection);
		
		return employees;
	}
	
	

	public void update(String value, int id) {
		Connection connection = connect();
		try {
			Statement st = connection.createStatement();
			PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_EMPLOYEES);
			stmt.setString(1, value);
			stmt.setInt(2, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);

	}

	

	public void delete(int id) {
		Connection connection = connect();
		
		try {
			Statement st = connection.createStatement();
			PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_EMPLOYEES);
			stmt.setInt(1, id); 
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);

	}
	
	
	@Override
	public void insert(String name, String surname, Date birthdate, String email) {
		Connection connection = connect();
		try {
			Statement st = connection.createStatement();
			PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_EMPLOYEE);
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setDate(3, (java.sql.Date) birthdate);
			stmt.setString(4, email);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);
		
	}

	

}