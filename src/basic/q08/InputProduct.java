package basic.q08;

import java.util.Scanner;

public class InputProduct {

	public static void main(String[] args) {
		String productName = "";
		int price =0;
				
		Scanner sc = new Scanner(System.in);
		
		System.out.print("商品名を入力してください：\n");
		productName = sc.nextLine();
		System.out.print("価格を入力してください：\n");
		price = sc.nextInt();
		
		System.out.println("商品名は" + productName + "です。価格は" + price + "円です。");
		

	}

}
