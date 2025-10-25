package method.q08;

public class SumLoop {

	public static int sumLoop(int num1, int num2) {
		int sum = 0;
		for (int i = num1; i <= num2; i++) {
			sum = sum + i;
		}
		return sum;
	}

	public static void main(String[] args) {
		int num1 = 1;
		int num2 = 100;
		int sum = sumLoop(num1, num2);

		System.out.println("最小値：" + num1 + "\n最大値：" + num2 + "\n加算結果：" + sum);
	}

}
