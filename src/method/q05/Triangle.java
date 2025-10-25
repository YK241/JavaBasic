package method.q05;

public class Triangle {

	public static int getTriangleArea(int num1, int num2) {
		int TriangleArea = num1 * num2 / 2;
		return TriangleArea;
	}

	public static void main(String[] args) {
		int num1 = 8;
		int num2 = 5;
		int TriangleArea = getTriangleArea(num1, num2);

		System.out.println("底辺：" + num1 + "\n高さ：" + num2 + "\n三角形の面積：" + TriangleArea);
	}

}
