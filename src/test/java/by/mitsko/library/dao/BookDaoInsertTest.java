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

import by.mitsko.library.bean.Book;
import by.mitsko.library.dao.impl.BookDaoDBImpl;
import by.mitsko.library.dao.util.DBConnectionHelper;

public class BookDaoInsertTest {
	private Connection connection;
	private List <Book> beforeList;
	private BookDao dao;
		
	@BeforeClass
	public void initDBConnection(){
		connection = DBConnectionHelper.connect();
		System.out.println("BeforeClass: connection to DB was opened");
	}
	
	@BeforeMethod
	public void getExpectedList() throws SQLException{
		System.out.println("BeforeMethod");
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from book");
		beforeList  = new ArrayList <>();
		while (rs.next()){
			beforeList.add(new Book());
		}
		System.out.println("BeforeMethod: expectedList was recieved");
	}
	
	@BeforeMethod
	public void initDao(){
		dao = new BookDaoDBImpl() ;
		System.out.println("BeforeMethod: BookDao was initialized");
	}
	
	@Test
	public void bookInsertTest(){
		dao.insert("newbook", "author", "brief", 2000);
		List <Book> afterlList = dao.readAll();
		Assert.assertEquals(afterlList.size(), beforeList.size()+1, "The received count of books  is not equal count of books in DB");
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
