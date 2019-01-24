import java.io.*;
import java.util.*;

public class Day8A {
	public static int count = 0;

	public static int getMeta(ArrayList<Integer> data) {
		int total = 0;
		int children, meta;
		children = data.get(count++);
		meta = data.get(count++);
		for (int i = children; i > 0; i--) {
			total += getMeta(data);
		}
		for (int i = meta; i > 0; i--) {
			total += data.get(count);
			count++;
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
		int sum = getMeta(data);
		System.out.println("Result: " + sum);
	}
}