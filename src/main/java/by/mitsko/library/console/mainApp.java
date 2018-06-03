package by.mitsko.library.console;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import by.mitsko.library.bean.Book;
import by.mitsko.library.dao.BookDao;
import by.mitsko.library.dao.EmployeeDao;
import by.mitsko.library.dao.impl.BookDaoDBImpl;
import by.mitsko.library.dao.impl.EmployeeDaoDBImpl;
import by.mitsko.library.service.Service;

public class mainApp {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
		
     	BookDao dao = new BookDaoDBImpl();
     	EmployeeDao edao = new EmployeeDaoDBImpl();
     	
     	
//     	Date udate=df.parse("1990-02-02"); 
 //    	java.sql.Date sdate=new java.sql.Date(udate.getTime());
     	
//		List <Book> books = dao.read(1);
//		for (Book b: books){
//			System.out.println(b);
//		}
		System.out.println("======================");
		
//		edao.insert("name", "surname", sdate, "m@mail.com");

	//	dao.update("newbook", 10);

	//	dao.insert("newbook", "author", "brief", 2000);
	  //  dao.delete(11);
		
		Service s = new Service();
		s.employeeList();
		System.out.println("======================");
		s.employeeSecondList();
			
			
	}

}
