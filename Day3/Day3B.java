import java.util.*;
import java.io.*;

public class Day3B {
	public static void main(String args[]) throws Exception {
		Scanner input = new Scanner(new File("Day3Input.txt"));
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		int width = 0, height = 0, store1 = 0, store2 = 0, count = 0;
		while (input.hasNext()) {
			String str = input.nextLine();
			String array[] = str.split(" ");
			String array2[] = array[2].split(",");
			String array3[] = array[3].split("x");
			StringBuilder id = new StringBuilder(array[0]);
			id.deleteCharAt(0);
			array[0] = id.toString();
			arr2.add(Integer.parseInt(array[0]));
			StringBuilder sb = new StringBuilder(array2[1]);
			sb.deleteCharAt(array2[1].length()-1);
			array2[1] = sb.toString();
			arr.add(Integer.parseInt(array2[0]));
			arr.add(Integer.parseInt(array2[1]));
			arr.add(Integer.parseInt(array3[0]));
			arr.add(Integer.parseInt(array3[1]));
			height = Integer.parseInt(array2[0]) + Integer.parseInt(array3[0]);
			width = Integer.parseInt(array2[1]) + Integer.parseInt(array3[1]);
			if (width > store2) store2 = width;
			if (height > store1) store1 = height;
		}
		int[][] fabric = new int[store1][store2];
		boolean[][] hasBeen = new boolean[store1][store2];
		for (int i = 0; i < store1; i++) {
			for (int j = 0; j < store2; j++) {
				fabric[i][j] = 0;
				hasBeen[i][j] = false;
			}
		}
		for (int i = 0; i < arr.size(); i += 4) {
			int y = arr.get(i);
			int x = arr.get(i + 1);
			int y2 = arr.get(i + 2);
			int x2 = arr.get(i + 3);
			for (int j = y; j < y + y2; j++) {
				for (int k = x; k < x2 + x; k++) {
					if (fabric[j][k] != 0) hasBeen[j][k] = true;
					fabric[j][k] = arr2.get(i / 4);
				}
			}
		}
		int idCount = 0;
		outerloop:
		for (int i = 0; i < arr.size(); i += 4) {
			for (int j = 0; j < store1; j++) {
				for (int k = 0; k < store2; k++) {
					if (fabric[j][k] == arr2.get(i / 4) && hasBeen[j][k] == false) idCount++;
					if (idCount == (arr.get(i + 2) * arr.get(i + 3))) {
						System.out.println("Result: " + arr2.get(i / 4));
						break outerloop;
					}
				}
			}
			idCount = 0;
		}
	}
}
