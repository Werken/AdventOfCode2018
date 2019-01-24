import java.io.*;
import java.util.*;

public class Day11A {
	public static int getFuel(int SN, int x, int y) {
		int fuel = ((x + 10) * y + SN) * (x + 10);
		return ((fuel / 100) % 10) - 5;
	}
	public static int getTotal(int[][] cells, int x, int y, int k) {
		int total = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				total += cells[x + i][y + j];
			}
		}
		return total;
	}

	public static void main(String[] args) throws Exception {
		int serialNumber = 6303;
		int testSerialNumber = 42;
		int power = Integer.MIN_VALUE, x = Integer.MIN_VALUE, y = Integer.MIN_VALUE;
		int[][] cells = new int[300][300];
		for (int i = 0; i < 300; i++) {
			for (int j = 0; j < 300; j++) {
				cells[i][j] = getFuel(serialNumber, i, j);
			}
		}
		int[][] totals = new int[298][298];
		int largest = Integer.MIN_VALUE;
		for (int i = 0; i < 298; i++) {
			for (int j = 0; j < 298; j++) {
				int temp = getTotal(cells, i, j, 3);
				if (temp > largest) {
					largest = temp;
					x = i;
					y = j;
				}
			}
		}
		System.out.println("Result: " + x + " " + y);
	}
}