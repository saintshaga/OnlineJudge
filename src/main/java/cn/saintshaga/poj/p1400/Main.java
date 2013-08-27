package cn.saintshaga.poj.p1400;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	private static final char LEFT_PARENTHESE = '(';
	private static final char RIGHT_PARENTHESE = ')';
	
	private static final String SYMBOLS = "+-*/";
	
//	private static boolean canRemove(char[] chars, int left, int right, String operator) {
//		if(operator == null) {
//			return true;
//		}
//		if(left != 0 && SYMBOLS.contains(""+chars[left-1])) {
//			char leftChar = chars[left-1];
//			if(SYMBOLS.indexOf(leftChar) > SYMBOLS.indexOf(operator)) {
//				return false;
//			} else if (SYMBOLS.indexOf(leftChar) == SYMBOLS.indexOf(operator) && SYMBOLS.indexOf(leftChar)%2 == 1) {
//				return false;
//			}
//		}
//		if(right != chars.length-1 && SYMBOLS.contains(""+chars[right+1])){
//			char rightChar = chars[right+1];
//			if(SYMBOLS.indexOf(rightChar)/2 > SYMBOLS.indexOf(operator)/2) {
//				return false;
//			}
//		}
//		return true;
//	}
	
//	public static String parseExpression(String expression) {
//		Map<Integer, Integer> rightPare = new HashMap<Integer, Integer>();
//		Map<Integer, Boolean> canRemoveMap = new HashMap<Integer, Boolean>();
//		Stack<Integer> stack = new Stack<Integer>();
//		char[] chars = expression.toCharArray();
//		for(int i=0; i<chars.length; i++) {
//			if(LEFT_PARENTHESE == chars[i] || SYMBOLS.contains(""+chars[i])) {
//				stack.push(i);
//			} else if (RIGHT_PARENTHESE == chars[i]) {
//				String operator = null;
//				while(SYMBOLS.contains(""+chars[stack.peek()])) {
//					if(operator == null || 
//							SYMBOLS.indexOf(chars[stack.peek()])/2 < SYMBOLS.indexOf(operator)/2) {
//						operator = "" + chars[stack.peek()];
//					}
//					stack.pop();
//				}
//				rightPare.put(stack.peek(), i);
//				canRemoveMap.put(stack.peek(), canRemove(chars, stack.peek(), i, operator));
//				stack.pop();
//			}
//		}
//		for(int i=0; i<chars.length; i++) {
//			if(rightPare.get(i) != null && canRemoveMap.get(i) == true) {
//				chars[i] = ' ';
//				chars[rightPare.get(i)] = ' ';
//			}
//		}
//		
//		return new String(chars).replace(" ", "");
//	}
	
	private static boolean comparePriority(Character current, Character peek) {
		if(peek == LEFT_PARENTHESE) {
			return false;
		}
		if(SYMBOLS.indexOf(current)/2 <= SYMBOLS.indexOf(peek)/2) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String infix2Postfix(String infix) {
		Stack<Character> stack = new Stack<Character>();
		StringBuilder result = new StringBuilder();
		for(int i=0; i<infix.length(); i++) {
			char c = infix.charAt(i);
			if(c == LEFT_PARENTHESE) {
				stack.push(c);
			} else if (SYMBOLS.contains(""+c)) {
				while(!stack.isEmpty() && comparePriority(c, stack.peek())) {
					result.append(stack.pop());
				}
				stack.push(c);
			} else if (c == RIGHT_PARENTHESE) {
				while(stack.peek() != LEFT_PARENTHESE) {
					result.append(stack.pop());
				}
				stack.pop();
			} else {
				result.append(c);
			}
		}
		while(!stack.isEmpty()) {
			result.append(stack.pop());
		}
		return result.toString();
	}
	
	private static void addLeftPare(List<String> leftExp, char c) {
		if(SYMBOLS.indexOf(c)/2 > SYMBOLS.indexOf(leftExp.get(1))/2) {
			leftExp.set(0, "(" + leftExp.get(0) + ")");
		}
	}

	private static void addRightPare(List<String> rightExp, char c) {
		if(SYMBOLS.indexOf(c) > SYMBOLS.indexOf(rightExp.get(1)) || 
				(SYMBOLS.indexOf(c) == SYMBOLS.indexOf(rightExp.get(1)) && SYMBOLS.indexOf(c)%2 == 1)) {
			rightExp.set(0, "(" + rightExp.get(0) + ")");
		}
	}

	public static String postfix2Infix(String postfix) {
		Stack<List<String>> stack = new Stack<List<String>>();
		for(int i=0; i<postfix.length(); i++) {
			char c = postfix.charAt(i);
			if(SYMBOLS.contains(""+c)) {
				List<String> ansExp = new ArrayList<String>();
				List<String> rightExp = stack.pop();
				List<String> leftExp = stack.pop();
				if(rightExp.size() == 2) {
					addRightPare(rightExp,c);
				}
				if(leftExp.size() == 2) {
					addLeftPare(leftExp,c);
				}
				ansExp.add(leftExp.get(0) + c + rightExp.get(0));
				ansExp.add(""+c);
				stack.push(ansExp);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(""+c);
				stack.push(list);
			}
		}
		return stack.peek().get(0);
	}
	

	public static String parseExpression1(String expression) {
		String postfixExp = infix2Postfix(expression);
		return postfix2Infix(postfixExp);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		List<String> results = new ArrayList<String>(n);
		for(int i=0; i<n; i++) {
			String str = in.next().trim();
			results.add(parseExpression1(str));
		}
		for (String string : results) {
			System.out.println(string);
		}
	}

}
