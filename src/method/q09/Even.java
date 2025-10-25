package method.q09;

import java.util.Scanner;

public class Even {

	public static boolean checkEven(int num1) {
		if (num1 % 2 == 0) {
			return true;
		} else {
			return false;
		}
	};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("判定する数値を入力してください：\n");
		int num1 = sc.nextInt();

		if (checkEven(num1)) {
			System.out.println(num1 + "は偶数です。");
		} else {
			System.out.println(num1 + "は奇数です。");
		}
		;
	}
}
