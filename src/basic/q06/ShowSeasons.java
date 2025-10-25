package basic.q06;

import java.util.Scanner;

public class ShowSeasons {

	public static void main(String[] args) {
		
		String season = "";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("月を入力してください\n");
		int month = sc.nextInt();
		
		if(month == 1 || month == 2|| month == 12) {
			season = "月は冬です。";
		}else if(month >= 3 && month <= 5 ) {
			season = "月は春です。";
		}else if(month >= 6 && month <= 8 ) {
			season = "月は夏です。";
		}else if(month >= 9 && month <= 11 ) {
			season = "月は秋です。";
		}else {
			season = "月は存在しないです。";
		}
		
		
		System.out.println(month + season);

	}

}
