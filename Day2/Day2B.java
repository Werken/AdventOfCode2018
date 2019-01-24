import java.util.*;
import java.io.*;
import java.lang.StringBuilder;

public class Day2B {
	public static void main(String args[]) throws Exception {
		Scanner input = new Scanner(new File("Day2Input.txt"));
		int count = 0;
		String result = "";
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<String> solve = new ArrayList<String>(2);
		while (input.hasNext()) {
			String str = input.nextLine();
			arr.add(str);
		}
		outerloop:
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 1; j < arr.size(); j++) {
				for (int k = 0; k < arr.get(i).length(); k++) {
					if (arr.get(i).charAt(k) != arr.get(j).charAt(k)) count++;
				}
				if (count == 1) {
					solve.add(arr.get(i));
					solve.add(arr.get(j));
					break outerloop;
				}
				count = 0;
			}
		}
		for (int i = 0; i < arr.get(0).length(); i++) {
			if (solve.get(0).charAt(i) != solve.get(1).charAt(i)) {
				StringBuilder sb = new StringBuilder(solve.get(0));
				sb.deleteCharAt(i);
				result = sb.toString();
			}
		}
		System.out.println("Result: " + result);
	}
}