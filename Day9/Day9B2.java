import java.io.*;
import java.util.*;

public class Day9B2 {
	static int players;
	static int end;

	static class CircleDeque<T> extends ArrayDeque<T> {
		void rotate(int num) {
			if (num == 0) return;
			if (num > 0) {
				for (int i = 0; i < num; i++) {
					T t = this.removeLast();
					this.addFirst(t);
				}
			}
			else {
				for (int i = 0; i < Math.abs(num) - 1; i++) {
					T t = this.remove();
					this.addLast(t);
				}
			}
		}
	}

	static long game(int players, int end) {
		CircleDeque<Integer> circle = new CircleDeque<>();
		circle.addFirst(0);
		long[] scores = new long[players];
		for (int i = 1; i <= end; i++) {
			if (i % 23 == 0) {
				circle.rotate(-7);
				scores[i % players] += i + circle.pop();
			}
			else {
				circle.rotate(2);
				circle.addLast(i);
			}
		}
		return Arrays.stream(scores).max().getAsLong();
	}

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(new File("Day9InputT.txt"));
		int data[] = new int[2];
		while (input.hasNext()) {
			String str = input.nextLine();
			String array[] = str.split(" ");
			data[0] = Integer.parseInt(array[0]);
			data[1] = Integer.parseInt(array[6]);
		}
		//data[1] *= 100;
		players = data[0];
		end = data[1];
		long result = game(players, end);
		System.out.println("Result: " + result);
	}
}