package com.callor.student.controller;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;
import com.callor.student.service.impl.StudentServiceImplV2;
import com.callor.utils.Line;

public class StudentControllerV2 {
	/* ControllerV1 에서는 ServiceV1을 사용할 때 단순히
	 * 기본 생성자를 호출하여 ServiceV1에서 stdList와 fileName을
	 * 직접 관리하도록 하였다.
	 * 만약 list변수와 파일을 다른 것으로 바꾸려면 어쩔 수 없이 ServiceV1코드를 
	 * 변경해야 한다. 하지만 ControllerV2에서 ServiceV2를 사용할 때
	 * stdList와 fileName을 생성자 매개변수로 전달하고 있다.
	 * 이러한 패턴은 Controller와 Service간 결합도를 낮춰
	 * 더 좋은 패턴의 코드가 된다.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//V2가 V1보다 더 좋은 코드이다. 이유는 응집도 높이고 결합도 낮추기 때문이다.
		List<StudentVO> stdList = new ArrayList<>();
		String fileName = "src/com/callor/student/student.txt";
		String printFile = "src/com/callor/student/print.txt";
		// java1.5이상에서 text파일에 내용을 기록하기 위해 단독으로 사용할 수 있는 클래스.
		PrintStream ps = new PrintStream(printFile);
		PrintStream psConsole = System.out;
		
		/* stdList와 원본 데이터 파일(student.txt)을 생성자에 주입하여
		 * student.txt 파일에서 데이터를 읽어 stdList를 준비해라는 이야기이다.
		 * 이것(ps)은 파일에 기록하는 것이다.
		 */
		StudentService stService = new StudentServiceImplV2(stdList, fileName, ps);
		stService.printStudent();
		System.out.println(Line.dLine);
		StudentService stService1 = new StudentServiceImplV2(stdList, fileName, psConsole);
		stService1.printStudent();
		// 콘솔에 출력하는 코드
		
		// 이 클래스로 인해 파일 데이터 출력에는 다양한방법이 있다는 것을 알 수 있다.
	}//end main
}//end class
