package com.callor.student.controller;

import java.io.IOException;

import com.callor.student.service.StudentService;
import com.callor.student.service.impl.StudentServiceImpl;

public class StudentController {
	public static void main(String[] args) throws IOException {
		
		StudentService scService = new StudentServiceImpl();
		scService.loadStudent();
		
		while(true) {
			String inputSt = scService.inputStudent();
			if(inputSt == null) {
				System.out.println("정확히 입력하세요.");
				continue;
			}
			if(inputSt.equals("QUIT")) {
				break;
			}
			scService.saveStudent(inputSt);
		}
		
		
	}//end main
}//end class
