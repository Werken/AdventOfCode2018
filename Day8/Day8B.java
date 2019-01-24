import java.io.*;
import java.util.*;

public class Day8B {
	public static int count = 0;

	public static int getRoot(ArrayList<Integer> data) {
		int total = 0;
		int children, meta;
		children = data.get(count++);
		meta = data.get(count++);
		int[] nodes = new int[children];
		for (int i = children; i > 0; i--) {
			nodes[children - i] = getRoot(data);
		}
		if (children == 0) {
			for (int i = meta; i > 0; i--) {
				total += data.get(count);
				count++;
			}
		}
		else {
			for (int i = meta; i > 0; i--) {
				if (data.get(count) > children) {
					count++;
				}
				else {
					total += nodes[data.get(count) - 1];
					count++;
				}
			}
		}
		return total;
	}

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(new File("Day8Input.txt"));
		ArrayList<Integer> data = new ArrayList<>();
		while (input.hasNext()) {
			String str = input.nextLine();
			String array[] = str.split(" ");
			for (int i = 0; i < array.length; i++) data.add(Integer.parseInt(array[i]));
		}
		int sum = getRoot(data);
		System.out.println("Result: " + sum);
	}
}