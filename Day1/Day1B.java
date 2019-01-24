import java.io.File;
import java.util.*;

public class Day1B {
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Garrett\\Documents\\Advent_Code\\Day1\\Day1Input.txt");

		Scanner input = new Scanner(file);
		ArrayList<Integer> freq = new ArrayList<Integer>();
		boolean check = false;
		int total = 0, count = 0;
		freq.add(total);
		while(!check) {
			while (input.hasNextLine()) {
				String temp = input.nextLine();
				if (temp.charAt(0) == '+') {
					StringBuilder sb = new StringBuilder(temp);
					sb.deleteCharAt(0);
					String result = sb.toString();
					int num = Integer.parseInt(result);
					total += num;
					for (int i = 0; i < freq.size(); i++) {
						if (total == freq.get(i)) {
							System.out.println("Iterations: " + count + " Answer: " + total);
							check = true;
							return;
						}
					}
					freq.add(total);
					count++;
				}
				else {
					StringBuilder sb = new StringBuilder(temp);
					sb.deleteCharAt(0);
					String result = sb.toString();
					int num = Integer.parseInt(result);
					total -= num;
					for (int i = 0; i < freq.size(); i++) {
						if (total == freq.get(i)) {
							System.out.println("Iterations: " + count + " Answer: " + total);
							check = true;
							return;
						}
					}
					freq.add(total);
					count++;
				}
			}
			input = new Scanner(file);
		}
	}
}