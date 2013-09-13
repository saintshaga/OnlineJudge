package cn.saintshaga.poj.p2479;

import java.util.Scanner;

public class Main {
	
	public static int maxSum(int[] a) {
		int n = a.length;
		int[] b = new int[n];
		for(int i=0; i<n; i++) {
			b[n-i-1] = a[i];
		}
		int[] da = maxSumi(a);
		int[] db = maxSumi(b);
		int max = Integer.MIN_VALUE;
		for(int i=1; i<n; i++) {
			int current = da[i-1] + db[n-i-1];
			if(max < current) {
				max = current;
			}
		}
		return max;
	}

	private static int[] maxSumi(int[] a) {
		int n = a.length;
		int[] c = new int[n];
		int[] d = new int[n];
		int max = Integer.MIN_VALUE;
		c[0] = a[0];
		d[0] = c[0];
		for(int i=1; i<n; i++) {
			c[i] = Math.max(a[i], a[i] + c[i-1]);
			if(max < c[i]) {
				max = c[i];
			}
			d[i] = max;
		}
		return d;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n = in.nextInt();
			int[] a = new int[n];
			for (int i=0; i<n; i++) {
				a[i] = in.nextInt();
			}
			System.out.println(maxSum(a));
		}
	}

}
