package com.callor.student.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;

public class StudentServiceImpl implements StudentService {

	protected Scanner scan = null;
	protected List<StudentVO> stList = null;
	protected final String saveFileName;

	public StudentServiceImpl() {
		this("src/com/callor/student/model/student.txt");
	}//end 기본생성자

	public StudentServiceImpl(String saveFileName) {
		this.saveFileName = saveFileName;
		scan = new Scanner(System.in);
		stList = new ArrayList<>();
	}//end 임의생성자
	
	/* 지금부터 우리가 해야할 일은
	 * 1. inputStudent에서 키보드를 통해 학번, 이름, 학과, 학년, 전화번호를 입력받는다.
	 * 2. 
	 */

	@Override
	public void inputStudent() {

		System.out.print("학번을 입력해라 >> ");
		String content1 = scan.nextLine();

		System.out.print("이름을 입력해라 >> ");
		String content2 = scan.nextLine();

		System.out.print("학과를 입력해라 >> ");
		String content3 = scan.nextLine();

		System.out.print("학년을 입력해라 >> ");
		String content4 = scan.nextLine();

		System.out.print("전화번호를 입력해라 >> ");
		String content5 = scan.nextLine();

	}// end inputStudent

	public void saveTodo(String savefileName) throws IOException {

		FileWriter writer = null;
		PrintWriter out = null;

		writer = new FileWriter(saveFileName);
		out = new PrintWriter(writer);

		for (StudentVO vo : stList) {
			out.printf("%s:", vo.getStNum());
			out.printf("%s:", vo.getStName());
			out.printf("%s:", vo.getStDept());
			out.printf("%s:", vo.getStGrade());
			out.printf("%s:", vo.getStTel());
		}
		out.flush();
		out.close();
		writer.close();

	}// end savaTodo : 저장하는 것.

	public List<StudentVO> studentSelectAll() {
		return stList;
	}// end StudentSelectAll

	protected void loadTodoList() {
		FileInputStream is = null;
		Scanner scan = null;

		try {
			is = new FileInputStream(saveFileName);
		} catch (FileNotFoundException e) {
			System.out.println(saveFileName + "파일이 없다.");
			return;
		}
		scan = new Scanner(is);

		int ST_NUM = 0;
		int ST_NAME = 1;
		int ST_DEPT = 2;
		int ST_GRADE = 3;
		int ST_TEL = 4;

	}// end loadTodoList

	@Override
	public void printStudent() {
		String fileName = "src/com/callor/student/model/student.txt";
		InputStream is = null;
		Scanner scan = null;

		try {
			is = new FileInputStream(fileName);
			scan = new Scanner(is);
			while (scan.hasNext()) {
				String line = scan.nextLine();
				System.out.println(line);
			}
			is.close();
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// end printStudent


	
}//end class
