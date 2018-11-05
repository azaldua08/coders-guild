package com.magenic.gamify;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(12));
	}
	
	public static int solution(long n) {
		long factorial = factorial(n);
		String strNum = Long.toString(factorial);
		System.out.println(n + "! = " + factorial);
		System.out.println(Math.log(factorial)-1);
		int count = 0;
		
		for (int j = strNum.length()-1; j >=0; j--) {
			if (strNum.charAt(j) == '0') count++;
			else break;
		}
		return count;
	}
	
	public static long factorial (long n) {
		long out = 1;
		for (int i = 1; i <= n; i++) {
			out*=i;
		}
		return out;
	}

}
