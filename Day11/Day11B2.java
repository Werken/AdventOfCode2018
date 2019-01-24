import java.io.*;
import java.util.*;

public class Day11B2 {
	public static int getFuel(int SN, int x, int y) {
		int fuel = ((x + 10) * y + SN) * (x + 10);
		return ((fuel / 100) % 10) - 5;
	}

	public static void main(String[] args) throws Exception {
		long startTime = System.nanoTime();
		int serialNumber = 6303;
		int testSerialNumber = 42;
		int power = Integer.MIN_VALUE, x = Integer.MIN_VALUE, y = Integer.MIN_VALUE;
		int[][] cells = new int[301][301];
		for (int i = 1; i <= 300; i++) {
			for (int j = 1; j <= 300; j++) {
				cells[i][j] = getFuel(serialNumber, j, i) + cells[i - 1][j] + cells[i][j - 1] - cells[i - 1][j - 1];

			}
		}
		int largest = Integer.MIN_VALUE, size = 0;
		for (int k = 1; k <= 300; k++) {
			for (int i = k; i <= 300; i++) {
				for (int j = k; j <= 300; j++) {
					int total = cells[i][j] - cells[i - k][j] - cells[i][j - k] + cells[i - k][j - k];
					if (total > largest) {
						largest = total;
						x = j;
						y = i;
						size = k;
					}
				}
			}
		}
		System.out.println("Result: " + x + " " + y + " " + size);
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
	}
}