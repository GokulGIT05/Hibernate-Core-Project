package com.gk.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY) // Commonly used. Auto Increament
	@Column (name="id")
	private int id;
	@Column (name="first_name")
	private String firstName;
	@Column (name="last_name")
	private String lastName;
	@Column (name="email")
	private String email;
	
	// Many to Many Mapping
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH })
	@JoinTable(name = "course_student", 
				joinColumns = @JoinColumn(name = "student_id"), 
				inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> coursesList;
		
	public Student() {
		
	}

	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	// Convenient Method.
	public void addCourseForStudent(Course course) {

		if (coursesList == null) {
			coursesList = new ArrayList<Course>();
		}
		coursesList.add(course);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Course> getCoursesList() {
		return coursesList;
	}

	public void setCoursesList(List<Course> coursesList) {
		this.coursesList = coursesList;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
}


