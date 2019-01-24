import java.util.*;
import java.io.*;

public class Day6B {
	static void print(int[][] board, int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
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
		int man = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				for (int k = 0; k < x.size(); k++) {
					man += Math.abs(x.get(k) - j) + Math.abs(y.get(k) - i);
				}
				if (man < 10000) board[i][j] = x.size() + 1;
				man = 0;
			}
		}
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == x.size() + 1) count++;
			}
		}
		System.out.println("Result: " + count);
	}
}