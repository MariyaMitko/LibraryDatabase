package by.mitsko.library.dao;

import java.util.List;

import by.mitsko.library.bean.Book;

public interface BookDao extends BaseDao<Book>{
	public void insert(String title, String author, String brief, int year);
}
