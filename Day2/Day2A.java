import java.util.*;
import java.io.*;

public class Day2A {
	public static void main(String args[]) throws Exception {
		Scanner input = new Scanner(new File("Day2Input.txt"));
		int doubles = 0;
		int triples = 0;
		int count = 0;
		boolean checkTwo = false;
		boolean checkThree = false;
		ArrayList<Character> arr = new ArrayList<Character>();
		while (input.hasNext()) {
			String str = input.nextLine();
			for (int i = 0; i < str.length(); i++) {
				if (!arr.contains(str.charAt(i))) {
					char tmp = str.charAt(i);
					arr.add(tmp);
					for (int j = i; j < str.length(); j++) {
						if (str.charAt(j) == tmp) count++;
					}
					if (count == 3) {
						if (!checkThree) {
							triples++;
							checkThree = true;
						}
					}
					else if (count == 2) {
						if (!checkTwo) {
							doubles++;
							checkTwo = true;
						}
					}
					count = 0;
				}
			}
			checkTwo = false;
			checkThree = false;
			arr = new ArrayList<Character>();
		}
		System.out.println("Twice: " + doubles + " Thrice: " + triples);
		System.out.println("Checksum: " + doubles * triples);
	}
}