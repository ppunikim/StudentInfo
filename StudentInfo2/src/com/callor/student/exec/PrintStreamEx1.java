package com.callor.student.exec;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.callor.utils.Line;

public class PrintStreamEx1 {
	public static void main(String[] args) throws FileNotFoundException {
		
		PrintStream ps = System.out;
		ps.println("뿌니는 귀여워");
		ps.println("ppuni");
		// System.out.print와 같은 역할을 하지만
		// 파일을 만드는 역할도 해서 try-catch를 해줘야 한다.
		
		PrintStream ps1 = new PrintStream("data.txt");	//프로젝트에서 f5를 누르면
		ps1.println("뿌니는 귀여워");					//README파일에 붙어있다. 
		ps1.println("ppuni");							//경로 지정을 해주지 않아서 그렇다.
		
		int length = 50;
		ps.println(Line.dLine(length));
		ps.println("7단 구구단");
		ps.println(Line.sLine(length));
		for(int i = 2; i < 10; i ++) {
			ps.printf("%d x %d = %d\n",7, i, i*7 );
		}
		ps.println(Line.dLine(length));
		
		
		ps1.close();		//끝낸다는 의미여서 제일 마지막에 붙여줘야 한다.
	}//end main
}//end class
