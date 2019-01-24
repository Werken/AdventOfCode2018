import java.io.*;
import java.util.*;

public class Day9A {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(new File("Day9Input.txt"));
		int data[] = new int[2];
		while (input.hasNext()) {
			String str = input.nextLine();
			String array[] = str.split(" ");
			data[0] = Integer.parseInt(array[0]);
			data[1] = Integer.parseInt(array[6]);
		}
		int counter = 0, current = 0;
		int scores[] = new int[data[0]];
		List<Integer> circle = new ArrayList<>();
		circle.add(counter);
		while (counter != data[1]) {
			for (int i = 0; i < data[0]; i++) {
				if ((counter + 1) % 23 == 0) {
					if (circle.indexOf(current) > 7) {
						scores[i] += (counter + 1 + circle.get(circle.indexOf(current) - 7));
						circle.remove(circle.get(circle.indexOf(current) - 7));
						current = circle.get(circle.indexOf(current) - 6);
					}
					else {
						int offset = Math.abs(circle.indexOf(current) - circle.size());
						if (circle.indexOf(current) + offset >= circle.size()) {
							scores[i] += (counter + 1 + circle.get(circle.indexOf(current) + offset - circle.size()));
							circle.remove(circle.get(circle.indexOf(current) + offset - circle.size()));
							current = circle.get(circle.indexOf(current) + offset - circle.size());
						}
						else {
							scores[i] += (counter + 1 + circle.get(circle.indexOf(current) + offset));
							circle.remove(circle.get(circle.indexOf(current) + offset));
							if (circle.indexOf(current) + offset > circle.size()) current = circle.get(circle.indexOf(current) + offset - circle.size());
							else current = circle.get(circle.indexOf(current) + offset);
						}
					}
					counter++;
				}
				else if (circle.size() == 1 || (circle.indexOf(current) + 2 == circle.size() && circle.size() != 1)) {
					counter++;
					circle.add(counter);
					current = counter;
				}
				else if (circle.indexOf(current) + 2 > circle.size()) {
					counter++;
					circle.add(circle.indexOf(current) + 2 - circle.size(), counter);
					current = counter;
				}
				else {
					counter++;
					circle.add(circle.indexOf(current) + 2, counter);
					current = counter;
				}
				if (counter == data[1]) break;
			}
		}
		int max = 0, maxID = 0;
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] > max) {
				max = scores[i];
				maxID = i + 1;
			}
		}
		System.out.println("Result: " + maxID + " " + max);
	}
}