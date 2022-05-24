package com.callor.student.controller;

import java.io.IOException;

import com.callor.student.service.StudentService;
import com.callor.student.service.impl.StudentServiceImplV1;

public class StudentControllerV1 {
	public static void main(String[] args) throws IOException {
		
		
		//객체를 생성하는 순간 student.txt파일에서 데이터를 읽어
		//stdList가 준비된다.
		//이러한 이유로 loadStudent를 생성자에 써주는 것이다.
		StudentService stService = new StudentServiceImplV1();
		stService.printStudent();
		
		
	}//end main
}//end class
