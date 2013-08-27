package cn.saintshaga.poj.p1400;

import org.junit.Test;

public class Test1400 {

	@Test
	public void test() {
		System.out.println(Main.infix2Postfix("a+b*c+(d*e+f)*g"));
		System.out.println(Main.postfix2Infix("abc*+de*f+g*+"));
	}

}
