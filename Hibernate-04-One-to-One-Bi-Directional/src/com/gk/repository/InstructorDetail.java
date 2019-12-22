package com.gk.repository;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	
	// Step 1: Annotate the class as an entity  and map to the database
	
	// Step 2: Define the fields
	
	// Step 3: Annotate the fields with db coloumn names
	
	// Step 4: Create a constructor
	
	// Step 5: Generate getter and setter
	
	// Step 6: Generate toString() method to print
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="youTube_channel")
	private String youTube;
	@Column(name="hobby")
	private String hobby;
	
	@OneToOne(mappedBy="instructorDetail",cascade=CascadeType.ALL)
	private Instructor instructor;
	
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public InstructorDetail() {
		
	}

	public InstructorDetail(String youTube, String hobby) {
		super();
		this.youTube = youTube;
		this.hobby = hobby;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYouTube() {
		return youTube;
	}

	public void setYouTube(String youTube) {
		this.youTube = youTube;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youTube=" + youTube + ", hobby=" + hobby + "]";
	}
	
		
	
	
}






