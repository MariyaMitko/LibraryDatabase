package by.mitsko.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.mitsko.library.bean.Employee;
import by.mitsko.library.dao.impl.EmployeeDaoDBImpl;
import by.mitsko.library.dao.util.DBConnectionHelper;


public class EmployeeDaoReadTest {
	private Connection connection;
	private List <Employee> expectedEmployee;
	private EmployeeDao dao;
		
	@BeforeClass
	public void initDBConnection(){
		connection = DBConnectionHelper.connect();
		System.out.println("BeforeClass: connection to DB was opened");
	}
	
	@BeforeMethod
	public void getExpectedList() throws SQLException{
		System.out.println("BeforeMethod");
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from employee where surname = 'Petrova'");
		expectedEmployee  = new ArrayList <>();
		while (rs.next()){
			Employee empl = new Employee();
			empl.setId(rs.getInt("id"));
			empl.setName(rs.getString("name"));
			empl.setSurname(rs.getString("surname"));
			expectedEmployee.add(empl);
			
		}
		System.out.println("BeforeMethod: expectedEmployee was recieved");
	}
	
	@BeforeMethod
	public void initDao(){
		dao = new EmployeeDaoDBImpl() ;
		System.out.println("BeforeMethod: EmployeeDao was initialized");
	}
	
	@Test
	public void employeeFindingTest(){
	//	List <Employee> actualEmployee = dao.findBySurname();
	//	Assert.assertEquals(actualEmployee, expectedEmployee, "The expected Employee is not equal actual Employee in DB");
	}
	
	@AfterMethod
	public void expectedListClear(){
		expectedEmployee = null;
		System.out.println("AfterMethod: expectedEmployee was cleared");
	}
	@AfterClass
	public void disconnection(){
		DBConnectionHelper.disconnect(connection);
		System.out.println("AfterClass: connection to DB was closed");
	}
	


}
