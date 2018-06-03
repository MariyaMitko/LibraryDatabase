package by.mitsko.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.mitsko.library.bean.Book;
import by.mitsko.library.dao.BookDao;
import static by.mitsko.library.dao.util.DBConnectionHelper.*;

public class BookDaoDBImpl implements BookDao {

	private static final String SQL_CREATE_BOOKS = "create table book (id int auto_increment primary key, title varchar(60) null, author varchar(50) null, brief varchar(300) null, year int null )";
	private static final String SQL_SELECT_BOOK = "select * from book where id = ?";
	private static final String SQL_SELECT_BOOKS = "select * from book";
	private static final String SQL_UPDATE_BOOKS = "update book set title = ? where id = ?";
	private static final String SQL_DELETE_BOOKS = "delete from book where id = ?";
	private static final String SQL_INSERT_BOOK = "insert into book (title, author, brief, year) VALUES (?, ?, ?, ?)";
	
	public void create() {
		Connection connection = connect();
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(SQL_CREATE_BOOKS);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);

	}

	public List<Book> read(int id) {
		Connection connection = connect();
		List<Book> books = new ArrayList<>();

		try {
			Statement st = connection.createStatement();
			PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BOOK);
			stmt.setInt(1, id); 
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setAuthor(rs.getString("author"));
				b.setBrief(rs.getString("brief"));
				b.setYear(rs.getInt("year"));
				books.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);

		return books;
	}
	

	public List<Book> readAll() {
		Connection connection = connect();
		List<Book> books = new ArrayList<>();

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_BOOKS);

			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setAuthor(rs.getString("author"));
				b.setBrief(rs.getString("brief"));
				b.setYear(rs.getInt("year"));
				books.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);

		return books;
	}

	public void update(String value, int id) {
		Connection connection = connect();
		try {
			Statement st = connection.createStatement();
			PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_BOOKS);
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
			PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_BOOKS);
			stmt.setInt(1, id); 
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);

	}
	
	public void insert(String title, String author, String brief, int year) {
		Connection connection = connect();
		try {
			Statement st = connection.createStatement();
			PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_BOOK);
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setString(3, brief);
			stmt.setInt(4, year);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);

	}

	

}
