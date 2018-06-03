package by.mitsko.library.bean;

import java.util.Date;

public class Employee extends Entity{
	private String name;
	private String surname;
	private Date birthdate;
	private String email;
	
	public Employee() {
		super();
	}

	public Employee(int id, String name, String surname, Date birthdate, String email) {
		super(id);
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.email = email;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized String getSurname() {
		return surname;
	}

	public synchronized void setSurname(String surname) {
		this.surname = surname;
	}

	public synchronized Date getBirthdate() {
		return birthdate;
	}

	public synchronized void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public synchronized String getEmail() {
		return email;
	}

	public synchronized void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Employee other = (Employee) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", surname=" + surname + ", birthdate=" + birthdate + ", email=" + email
				+ "]";
	}
	
}