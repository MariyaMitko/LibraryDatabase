package by.mitsko.library.dao;

import java.util.Date;
import java.util.List;

import by.mitsko.library.bean.Book;
import by.mitsko.library.bean.Entity;

public interface BaseDao<T extends Entity> {

	public void create();
	public List<T> read(int id);
	public List<T> readAll();
	public void update(String value, int id);
	public void delete(int id);
	
	
}
