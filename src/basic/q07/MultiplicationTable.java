package basic.q07;

public class MultiplicationTable {

	public static void main(String[] args) {
		int mul = 0;
		
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9; j++) {
				mul = i*j;
				System.out.print(mul+"\t");
			}
			System.out.println();
		}
	}

}
