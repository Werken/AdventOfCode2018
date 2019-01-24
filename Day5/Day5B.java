import java.util.*;
import java.io.*;

public class Day5B {
	public static void main(String args[]) throws Exception {
		Scanner input = new Scanner(new File("Day5Input.txt"));
		String str = input.nextLine();
		String tmp = str;
		String tmp2 = str;
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		boolean check = false;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (char k = 'a'; k <= 'z'; k++) {
			String letterLow = String.valueOf(k);
			String letterUp = String.valueOf(Character.toUpperCase(k));
			str = str.replace(letterLow, "");
			str = str.replace(letterUp, "");
			while (!check) {
				check = true;
				for (char i = 'a'; i <= 'z'; i++) {
					letterLow = "" + i + Character.toUpperCase(i);
					letterUp = "" + Character.toUpperCase(i) + i;
					str = str.replace(letterLow, "");
					str = str.replace(letterUp, "");
					if (str.length() != tmp2.length()) {
						check = false;
						tmp2 = str;
					}
				}
			}
			arr.add(str.length());
			str = tmp;
			check = false;
		}
		int min = tmp.length() + 1, ID = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) < min) {
				min = arr.get(i);
				ID = i;
			}
		}
		System.out.println("Result: " + alphabet[ID] + " " + min);
	}
}