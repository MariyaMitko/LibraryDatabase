package by.mitsko.library.service;

import static by.mitsko.library.dao.util.DBConnectionHelper.connect;
import static by.mitsko.library.dao.util.DBConnectionHelper.disconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Service {
	
	private static final String SQL_SELECT_EMPLOYEE_MORE_THEN_2_BOOKS = "SELECT employee.surname, count(book.id) c FROM employee_book, book, employee WHERE employee_book.employee_id = employee.id AND employee_book.book_id = book.id GROUP BY employee.surname HAVING c > 2 Order by count(book.id) asc";
	private static final String SQL_SELECT_EMPLOYEE_LESS_THEN_6_BOOKS = "SELECT employee.surname, employee.birthdate, count(book.id) c FROM employee_book, book, employee WHERE employee_book.employee_id = employee.id AND employee_book.book_id = book.id GROUP BY employee.surname HAVING c <=5 Order by  count(book.id) desc, employee.birthdate asc";
			
	public void employeeList(){
		Connection connection = connect();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_EMPLOYEE_MORE_THEN_2_BOOKS);
			
			while (rs.next()) {
				System.out.println(rs.getString(1) + ": " + rs.getString(2) + " books");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);
		
	}
	
	public void employeeSecondList(){
		Connection connection = connect();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_EMPLOYEE_LESS_THEN_6_BOOKS);
			
			while (rs.next()) {
				System.out.println(rs.getString(1) + ", " + rs.getDate(2)+ ": " + rs.getString(3) + " books");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);
		
	}
	

}
