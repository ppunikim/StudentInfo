package com.callor.student.controller;

import java.io.IOException;

import com.callor.student.service.impl.StudentServiceImpl;

public class StudentController {
	public static void main(String[] args) throws IOException {
		
		StudentServiceImpl scService = new StudentServiceImpl();
		scService.inputStudent();
		scService.saveTodo("src/com/callor/student/model/student.txt");
		scService.printStudent();
		
		
		
	}//end main
}//end class
