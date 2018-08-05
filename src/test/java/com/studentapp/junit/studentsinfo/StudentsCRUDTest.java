package com.studentapp.junit.studentsinfo;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class StudentsCRUDTest extends TestBase{

	static String firstName="SMOKEUSER"+TestUtils.getRandomString();
	static String lastName="SMOKEUSER"+TestUtils.getRandomString();
	static String programme="ComputerScience";
	static String email=TestUtils.getRandomString()+"xyz@gmail.com";
	static int studentId;
	
	@Steps
	StudentSerenitySteps steps;
	
	@Title("This test will create a new student")
	@Test
	public void test001() {
		ArrayList<String> courses = new ArrayList<>(); 
		courses.add("Java");
		courses.add("C++");
		
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201)
		.spec(ReusableSpecifications.getGenericResponseSpec());
	}

	@Title("Verify if the student was added to the application")
	@Test
	public void test002() {
		
		HashMap<String,Object> value=steps.getStudentInfoByFirstName(firstName);
		assertThat(value, hasValue(firstName));
		studentId=(int)value.get("id");
	}

	@Title("Verify if the student is updated successfully")
	@Test
	public void test003(){
		String p1="findAll{it.firstName=='";
		String p2="'}.get(0)";
		
		ArrayList<String> courses = new ArrayList<>(); 
		courses.add("Java");
		courses.add("C++");
		
		firstName=firstName+"_Updated";
		steps.updateStudent(studentId, firstName,lastName,email,programme, courses);
		HashMap<String,Object> value=steps.getStudentInfoByFirstName(firstName);
				assertThat(value, hasValue(firstName));
		
		
	}
	
	
	@Title("Verify that the student is deleted successfully")
	@Test
	public void test004() {
		steps.deleteStudent(studentId);	
		steps.getStudentById(studentId).statusCode(404);
	
	
	
	
	
	}
	
}
