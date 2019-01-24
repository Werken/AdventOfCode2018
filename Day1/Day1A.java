import java.io.File;
import java.util.Scanner;

public class Day1A {
	public static void main(String[] args) throws Exception {
		File file = new File("Day1Input.txt");

		Scanner input = new Scanner(file);

		int total = 0;

		while (input.hasNextLine()) {
			String temp = input.nextLine();
			if (temp.charAt(0) == '+') {
				StringBuilder sb = new StringBuilder(temp);
				sb.deleteCharAt(0);
				String result = sb.toString();
				int num = Integer.parseInt(result);
				total += num;
			}
			else {
				StringBuilder sb = new StringBuilder(temp);
				sb.deleteCharAt(0);
				String result = sb.toString();
				int num = Integer.parseInt(result);
				total -= num;
			}
		}
		System.out.println("Answer: " + total);
	}
}