package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Concurrent(threads="4x")
@UseTestDataFrom("testdata\\studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDrivenTest extends TestBase {

	
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

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getCourses() {
		return course;
	}

	public void setCourses(String courses) {
		this.course = courses;
	}

	public StudentSerenitySteps getSsteps() {
		return ssteps;
	}

	public void setSsteps(StudentSerenitySteps ssteps) {
		this.ssteps = ssteps;
	}
	
	private String firstName;
	private String lastName;
	private String email;
	private String programme;
	private String course;
	
	@Steps
	StudentSerenitySteps ssteps;

	@Title("DataDriven Test for adding multiple students to the student App.")
	@Test
	public void createMultipleStudents() {
		
		ArrayList<String> courses = new ArrayList<>();
		courses.add(course);
		
		ssteps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201);
		
	}
	
}


