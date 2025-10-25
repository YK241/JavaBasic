package method.q10;

import java.util.Arrays;

public class EvenNumber {
	public static int getEvenNumbers(int[] numbers) {
		int evenCount = 0;
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] % 2 == 0) {
				evenCount++;
			}
		}
		return evenCount;
	}

	public static void main(String[] args) {
		int[] numbers = { 3, 2, 5, 6, 7, 25, 10, 51, 88, 98 };
		int evenCount = getEvenNumbers(numbers);
		System.out.print(Arrays.toString(numbers) + "には、偶数が" + evenCount + "個あります。");

	}

}
