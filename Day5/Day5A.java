import java.util.*;
import java.io.*;

public class Day5A {
	public static void main(String args[]) throws Exception {
		Scanner input = new Scanner(new File("Day5Input.txt"));
		String str = input.nextLine();
		String tmp = str;
		boolean check = false;
		while (!check) {
			check = true;
			for (char i = 'a'; i <= 'z'; i++) {
				String letterLow = "" + i + Character.toUpperCase(i);
				String letterUp = "" + Character.toUpperCase(i) + i;
				str = str.replace(letterLow, "");
				str = str.replace(letterUp, "");
				if (str.length() != tmp.length()) {
					check = false;
					tmp = str;
				}
			}
		}
		System.out.println("Result: " + str.length());
	}
}