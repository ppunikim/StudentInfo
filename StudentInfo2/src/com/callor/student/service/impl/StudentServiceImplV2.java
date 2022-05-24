package com.callor.student.service.impl;

import java.io.PrintStream;
import java.util.List;

import com.callor.student.model.StudentVO;
import com.callor.utils.Line;

public class StudentServiceImplV2 extends StudentServiceImplV1{
	
	private final PrintStream ps;
	
	public StudentServiceImplV2(List<StudentVO> stdList, String fileName, PrintStream ps) {
		super(stdList, fileName);
		this.ps = ps;
	}//end 임의생성자
	
	
	@Override
	public void printStudent() {
//		PrintStream ps = System.out;
		printAndFileSaveStudent(ps);
	}//end printStudent
	
	//파일로 저장해 뽑아놓을 수 있는 것.
	protected void printAndFileSaveStudent(PrintStream ps) {
		//6번. V1 끝나고 V2로 넘어와서 바로 해주기.
		
		//PrintStream은 System.out과 같은 내용이다.
		PrintStream out = ps;
		out.println(Line.dLine);
		out.println("학생관리");
		out.println(Line.sLine);
		out.println("학번\t이름\t학과\t학년\t전화번호");
		for(StudentVO stVO : stdList) {
			out.printf("%s\t", stVO.getNum());
			out.printf("%s\t", stVO.getName());
			out.printf("%s\t", stVO.getDept());
			out.printf("%s\t", stVO.getGrade());
			out.printf("%s\n", stVO.getTel());
		}
		out.close();
		ps.close();
	}//end PAFSS
	
	
}
