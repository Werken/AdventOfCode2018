import java.util.*;
import java.io.*;

public class Day6A {
	static void print(int[][] board, int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static<K> void incrementValue(HashMap<K, Integer> map, K key) {
		Integer count = map.containsKey(key) ? map.get(key) : 0;
		map.put(key, count + 1);
	}
	public static<K, V> K getKey(HashMap<K, V> map, V value) {
		for (K key : map.keySet()) {
			if (value.equals(map.get(key))) {
				return key;
			}
		}
		return null;
	}
	public static void main(String args[]) throws Exception {
		Scanner input = new Scanner(new File("Day6Input.txt"));
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		while (input.hasNext()) {
			String str = input.nextLine();
			String array[] = str.split(", ");
			x.add(Integer.parseInt(array[0]));
			y.add(Integer.parseInt(array[1]));
		}
		int col = Collections.max(x);
		int row = Collections.max(y);
		row += 1;
		col += 1;
		int[][] board = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = 0;
			}
		}
		for (int i = 1; i <= x.size(); i++) {
			board[y.get(i - 1)][x.get(i - 1)] = i;
		}
		int tmp = Integer.MAX_VALUE, man, manID = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				for (int k = 0; k < x.size(); k++) {
					man = Math.abs(x.get(k) - j) + Math.abs(y.get(k) - i);
					if (man < tmp) {
						tmp = man;
						manID = board[y.get(k)][x.get(k)];
					}
					else if (man == tmp) manID = 0;
				}
				board[i][j] = manID;
				tmp = Integer.MAX_VALUE;
			}
		}
		ArrayList<Integer> ignore = new ArrayList<Integer>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if ((i == 0 || i == row - 1) && !ignore.contains(board[i][j])) ignore.add(board[i][j]);
				else if ((i != 0 && i != row - 1 && (j == 0 || j == col - 1))
					&& !ignore.contains(board[i][j])) ignore.add(board[i][j]);
			}
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (!ignore.contains(board[i][j])) incrementValue(map, board[i][j]);
			}
		}
		int area = Collections.max(map.values());
		System.out.println("Result: " + getKey(map, area) + " " + area);
	}
}