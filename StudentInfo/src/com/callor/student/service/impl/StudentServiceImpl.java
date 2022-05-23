package com.callor.student.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;
import com.callor.utils.Line;

public class StudentServiceImpl implements StudentService {

	private final List<StudentVO> stList;
	private final String fileName;
	protected final Scanner scan;
	public StudentServiceImpl() {
		this("src/com/callor/student/model/student.txt");
	}//end 기본생성자

	public StudentServiceImpl(String fileName) {
		this.fileName = fileName;
		stList = new ArrayList<>();
		scan = new Scanner(System.in);
	}//end 임의생성자
	
	/* 지금부터 우리가 해야할 일은
	 * 1. inputStudent에서 키보드를 통해 학번, 이름, 학과, 학년, 전화번호를 입력받는다.
	 * 2. saveStudent 에서 입력받은 정보를 student.txt 파일에 저장하기
	 * 3. loadStudent 에서 이전에 저장한 파일을 읽어오고,
	 * 4. printStudent에서 파일을 출력해야 한다.
	 */

	@Override
	public String inputStudent() {
		System.out.print("학번을 입력해라(QUIT : 종료) >> ");
		String content = scan.nextLine();
		System.out.print("이름을 입력해라(QUIT : 종료) >> ");
		content = scan.nextLine();
		System.out.print("학과를 입력해라(QUIT : 종료) >> ");
		content = scan.nextLine();
		System.out.print("학년을 입력해라(QUIT : 종료) >> ");
		content = scan.nextLine();
		System.out.print("전화번호를 입력해라(QUIT : 종료) >> ");
		content = scan.nextLine();
		Integer intContent = 0;
		try {
			intContent = Integer.valueOf(content);
		} catch (Exception e) {
			return null;
		}
		
//		if(content.equals("QUIT")) {
//			break;
//		}
		
		return content;
	}// end inputStudent
	
	public void saveStudent() {
		FileWriter file = null;
		PrintWriter out = null;
		
		try {
			file = new FileWriter(fileName);
			out = new PrintWriter(file);
			for(StudentVO vo : stList) {
				out.print(vo.getStNum() + ":");
				out.print(vo.getStName() + ":");
				out.print(vo.getStDept() + ":");
				out.print(vo.getStGrade() + ":");
				out.println(vo.getStTel());
			}
			out.flush();
			out.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}//end saveStudent

//	public List<StudentVO> studentSelectAll() {
//		return stList;
//	}// end StudentSelectAll

	@Override
	public void loadStudent() {
		// TODO Auto-generated method stub
		FileInputStream is = null;
		Scanner scan = null;
		
		try {
			is = new FileInputStream(fileName);
			scan = new Scanner(is);
			while (scan.hasNext()) {
				String line = scan.nextLine();
				String[] sts = line.split(":");
				
				StudentVO stVO = StudentVO.builder().
						stName("sunny")
						.stNum("2")
						.stGrade(3).build();
				stList.add(stVO);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println(fileName + "파일이 없다.");
			return;
		}
		
	}//end loadStudent
	
	@Override
	public void printStudent() {
		System.out.println(Line.dLine);
		System.out.println("학생정보");
		System.out.println(Line.sLine);
		System.out.println("학번\t이름\t학과\t학년\t전화번호");
		System.out.println(Line.sLine);
		for(StudentVO vo : stList) {
			System.out.print(vo.getStNum() + "\t");
			System.out.print(vo.getStName() + "\t");
			System.out.print(vo.getStDept() + "\t");
			System.out.print(vo.getStGrade() + "\t");
			System.out.println(vo.getStTel());
		}
		System.out.println(Line.dLine);
		

	}// end printStudent

	@Override
	public void saveStudent(String inputSt) {
		// TODO Auto-generated method stub
		
	}

	


	
}//end class
