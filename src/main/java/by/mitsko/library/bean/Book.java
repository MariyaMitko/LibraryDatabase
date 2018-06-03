package by.mitsko.library.bean;

public class Book   extends Entity{
	
	private String title;
	private String author;
	private String brief;
	private int year;
	
	public Book() {
		super();
	}

	public Book(int id, String title, String author, String brief, int year) {
		super(id);
		this.title = title;
		this.author = author;
		this.brief = brief;
		this.year = year;
	}

	public synchronized String getTitle() {
		return title;
	}

	public synchronized void setTitle(String title) {
		this.title = title;
	}

	public synchronized String getAuthor() {
		return author;
	}

	public synchronized void setAuthor(String author) {
		this.author = author;
	}

	public synchronized String getBrief() {
		return brief;
	}

	public synchronized void setBrief(String brief) {
		this.brief = brief;
	}

	public synchronized int getYear() {
		return year;
	}

	public synchronized void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", brief=" + brief + ", year=" + year + "]";
	}

	
}
