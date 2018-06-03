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

public class BookDaoReadTest {
	private Connection connection;
	private List <Book> expectedBook;
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
		ResultSet rs = st.executeQuery("select * from book where title = 'Grey magik'");
		expectedBook  = new ArrayList <>();
		while (rs.next()){
			Book b = new Book();
			b.setId(rs.getInt("id"));
			b.setTitle(rs.getString("title"));
			b.setAuthor(rs.getString("author"));
			b.setBrief(rs.getString("brief"));
			b.setYear(rs.getInt("year"));
			expectedBook.add(b);
		}
		System.out.println("BeforeMethod: expectedBook was recieved");
	}
	
	@BeforeMethod
	public void initDao(){
		dao = new BookDaoDBImpl() ;
		System.out.println("BeforeMethod: BookDao was initialized");
	}
	
	@Test
	public void bookFindingTest(){
		List <Book> actualBook = dao.read(10);
		Assert.assertEquals(actualBook, expectedBook, "The expected book  is not equal actual book in DB");
	}
	
	@AfterMethod
	public void expectedListClear(){
		expectedBook = null;
		System.out.println("AfterMethod: expectedBook was cleared");
	}
	@AfterClass
	public void disconnection(){
		DBConnectionHelper.disconnect(connection);
		System.out.println("AfterClass: connection to DB was closed");
	}
	


}
