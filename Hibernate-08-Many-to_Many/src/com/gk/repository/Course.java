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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "title")
	private String title;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.DETACH,CascadeType.MERGE,
							CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name = "course_student", 
				joinColumns = @JoinColumn(name = "course_id"), 
				inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> studentsList;
	
	
	public Course() {

	}

	public Course(String title) {
		super();
		this.title = title;
	}

	// Convenient Method to add students through Course Entity
	public void addStudentInCourse(Student student) {

		if (studentsList == null) {
			studentsList = new ArrayList<Student>();
		}
		studentsList.add(student);
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public List<Student> getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(List<Student> studentsList) {
		this.studentsList = studentsList;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}

	
}
