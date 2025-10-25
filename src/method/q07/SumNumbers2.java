package method.q07;

public class SumNumbers2 {

	public static int calculateSum(int num1, double num2) {
		double sum = num1 + num2;
		return (int) sum;
	}

	public static void main(String[] args) {
		int num1 = 5;
		double num2 = 3.3;
		int sum = calculateSum(num1, num2);

		System.out.println("第一引数：" + num1 + "\n第二引数：" + num2 + "\n加算結果：" + sum);
	}

}
