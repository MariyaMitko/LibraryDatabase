package by.mitsko.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class EmployeeDaoInsertTest {
	private Connection connection;
	private List <Employee> beforeList;
	private EmployeeDao dao;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
	@BeforeClass
	public void initDBConnection(){
		connection = DBConnectionHelper.connect();
		System.out.println("BeforeClass: connection to DB was opened");
	}
	
	@BeforeMethod
	public void getExpectedList() throws SQLException{
		System.out.println("BeforeMethod");
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from employee");
		beforeList  = new ArrayList <>();
		while (rs.next()){
			beforeList.add(new Employee());
		}
		System.out.println("BeforeMethod: expectedList was recieved");
	}
	
	@BeforeMethod
	public void initDao(){
		dao = new EmployeeDaoDBImpl() ;
		System.out.println("BeforeMethod: EmployeeDao was initialized");
	}
	
	@Test
	public void employeeInsertTest() throws ParseException{
		Date udate=df.parse("1990-02-02"); 
     	java.sql.Date sdate=new java.sql.Date(udate.getTime());
	    dao.insert("name", "surname", sdate, "namesurname@cmail.com");
		List <Employee> afterlList = dao.readAll();
		Assert.assertEquals(afterlList.size(), beforeList.size()+1, "The received count of employees  is not equal count of employees in DB");
	}
	
	@AfterMethod
	public void expectedListClear(){
		beforeList = null;
		System.out.println("AfterMethod: expectedList was cleared");
	}
	@AfterClass
	public void disconnection(){
		DBConnectionHelper.disconnect(connection);
		System.out.println("AfterClass: connection to DB was closed");
	}
	


}
