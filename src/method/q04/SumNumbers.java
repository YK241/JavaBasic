package method.q04;

public class SumNumbers {

	public static int calculateSum(int num1, int num2) {
		int sum = num1 + num2;
		return sum;
	}

	public static void main(String[] args) {
		int num1 = 3;
		int num2 = 2;
		int sum = calculateSum(num1, num2);

		System.out.println("第一引数：" + num1 + "\n第二引数：" + num2 + "\n加算結果：" + sum);
	}

}
