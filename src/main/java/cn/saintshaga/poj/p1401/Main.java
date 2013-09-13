package cn.saintshaga.poj.p1401;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for(int i=0; i<n; i++) {
			int N = in.nextInt();
			int ans=0;
			while(N > 0) {
				N /= 5;
				ans += N;
			}
			System.out.println(ans);
		}

	}

}
