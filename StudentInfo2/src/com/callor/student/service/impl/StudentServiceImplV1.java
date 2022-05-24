package com.callor.student.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;
import com.callor.utils.Line;

/* Service클래스에서는
 * 데이터를 관리(입력, 출력, 파일저장)을 할텐데,
 * 1. 내부 전용으로 선언(사용)하는 방법
 * 2. 외부(Controller)에서 선언해 매개변수로 주입하는 방법
 * V1에서는 내부 전용으로 선언하는 방법을 사용하자.
 */
public class StudentServiceImplV1 implements StudentService{
	
	// final은 기본생성자에서 초기화 해서 사용해야 한다.
	protected final List<StudentVO> stdList;// 다른 파일에
	protected final String fileName;		//상속 해주기 위해 protected해주기
	
	public StudentServiceImplV1() {
		this(new ArrayList<>(),"src/com/callor/student/student.txt" );
		// 생성자에서 호출해줘야 외부클래스에서 불러올 수 있도록 하는 것이다.
	}//end 기본생성자
	public StudentServiceImplV1(List<StudentVO> stdList, String fileName) {
		this.stdList = stdList;
		this.fileName = fileName;
		System.out.println("Start");
		loadStudent();
	}//end 임의생성자
	// 이렇게 두 개를 나눠야 implV2에서 사용할 수 있다.
	
	
	/* 
	 * Controller에서 ServiceV1 클래스를 초기화 할 때
	 * student.txt 파일에서 학생 정보를 읽어 stdList에 담아두는
	 * 코드를 작성할 것이다.
	 */
	protected void loadStudent() {
		// TODO 학생 정보 저장된 것 불러오기.(1번)
		// is 객체와 file객체를 선언만 하기.
		InputStream is = null;
		Scanner file = null;
		
		
		try {
			// 파일 이름을 전달해 is 객체 생성
			// student.txt 파일을 열어서 읽을 준비를 해라
			is = new FileInputStream(fileName);
			/* FileInputStream을 Scanner와 연결하는 순간
			 * 이미 운영체제는 student.txt파일을 모두 읽어서
			 * 사용할 준비를 마쳐준다.
			 */
			file = new Scanner(is);
			
			/* 파일에 읽을 내용이 있니?
			 * 있으면 file.hasNext() 가 true가 되고
			 * while(){} 내부의 코드가 실행된다.
			 */
			while(file.hasNext()) {
				String line = file.nextLine();
				System.out.println(line);
				String[] strInfo = line.split(":");
				
				StudentVO stVO = StudentVO.builder()
								.num(strInfo[0])
								.name(strInfo[1])
								.dept(strInfo[2])
								.grade(Integer.valueOf(strInfo[3]))
								.tel(strInfo[4])
								.build();
				stdList.add(stVO);
			}
			is.close();
			file.close();
		
		/* Exception의 계층 구조
		 * 각 등급별로 계층 구조를 가지는데, 가장 상급의 Exception은 
		 * 순수한 Exception 키워드로 처리를 한다.
		 * 보통 catch 의 exception 처리는
		 * 낮은 등급부터 순서대로 처리하며
		 * 가장 등급이 높은 순수  Exception은 가장 마지막에
		 * catch를 실행한다.
		 */
		} catch (FileNotFoundException e) {
			System.out.println(fileName + "파일이 없음");

			/*  return 을 사용하는 이유는: try-catch{} 이후에
			 *  실행해야 할 코드가 있을 수 있으므로
			 *  Exception이 발생하면 더이상 진행하지 않도록 하기 위함이다.
			 */
			return;
		} catch (IOException e) {
			System.out.println("Input Output 오류");
			return;
		} catch (Exception e) {
			System.out.println("원인을 알 수 없는 Exception 발생");
			return;
		}// Exception을 제일 나중에 적어주는 것이 좋다. 그 이유는 
		// 맨 위로 올라가면 나머지 exception을 무시하기 위해서이다.
		
		
	}//end loadStudent
	
	@Override
	public void inputStudent() {
		// TODO 학생 정보 입력.(3번)
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println(Line.dLine);
			System.out.println("학생정보 입력");
			System.out.println(Line.sLine);
			System.out.print("학번(QUIT : 종료) : ");
			String num = scan.nextLine();
			if(num.equals("QUIT")) {
				break;
			}
			System.out.print("이름(QUIT : 종료) : ");
			String name = scan.nextLine();
			if(name.equals("QUIT")) {
				break;
			}
			System.out.print("학과(QUIT : 종료) : ");
			String dept = scan.nextLine();
			if(dept.equals("QUIT")) {
				break;
			}
			System.out.print("학년(QUIT : 종료) : ");
			String grade = scan.nextLine();
			if(num.equals("QUIT")) {
				break;
			}
			int intGrade = 0;
			try {
				intGrade = Integer.valueOf(grade);
			} catch (Exception e) {
				System.out.println("학년은 숫자로 입력해라.");
				continue;
			}// 문자열을 숫자로 바꿀 때는 Exception 처리를 해줘야 한다.
			System.out.print("전화번호(QUIT : 종료) : ");
			String tel = scan.nextLine();
			if(tel.equals("QUIT")) {
				break;
			}
			
			StudentVO stVO = StudentVO.builder()
									.name(name)
									.num(num)
									.dept(dept)
									.grade(intGrade)
									.tel(tel)
									.build();
			stdList.add(stVO);
		}
		saveStudent();	// 5번. => 입력이 끝난가 동시에 저장해주기 위한 것.
		
	}//end inputStudent
	
	protected void saveStudent() {
		// TODO 학생 정보 파일로 저장.(4번)
		OutputStream os = null;
		PrintWriter out = null;
		
		try {
			os = new FileOutputStream(fileName);
			out = new PrintWriter(os);
			
			// out으로 해야 파일로 저장이 된다.
			for(StudentVO stVO : stdList) {
				out.printf("%s:", stVO.getNum());
				out.printf("%s:", stVO.getName());
				out.printf("%s:", stVO.getDept());
				out.printf("%d:", stVO.getGrade());
				out.printf("%s\n:", stVO.getTel());
			}
			out.flush();
			os.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}//end saveStudent
	

	@Override
	public void printStudent() {
		// TODO 학생 정보 출력.(2번)
		int length = 40;
		System.out.println(Line.dLine(length));
		System.out.println("햇살고교 학생정보");
		System.out.println(Line.dLine(length));
		System.out.println("학번\t이름\t학과\t학년\t전화번호");
		System.out.println(Line.sLine(length));
		for(StudentVO stVO : stdList) {
			System.out.print(stVO.getNum() + "\t");
			System.out.print(stVO.getName() + "\t");
			System.out.print(stVO.getDept() + "\t");
			System.out.print(stVO.getGrade() + "\t");
			System.out.print(stVO.getTel() + "\n");
		}
		System.out.println(Line.dLine(length));
		
		
		
	}//end printStudent

}



























