package method.q06;

public class Circle {

	public static double getCircleArea(double radius) {
		double TriangleArea = radius * radius * 3.14;
		return TriangleArea;
	}

	public static void main(String[] args) {
		double radius = 5.0;
		double TriangleArea = getCircleArea(radius);

		System.out.println("半径：" + radius + "\n円の面積：" + TriangleArea);
	}

}
