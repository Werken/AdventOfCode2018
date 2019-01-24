import java.io.*;
import java.util.*;
import java.lang.*;

public class Day12 {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(new File("Day12Input.txt"));
		int part1 = 20;
		int part2 = 200;
		long billion = 50000000000L;
		int count = 0;
		String state = "";
		List<String> dead = new ArrayList<>();
		List<String> live = new ArrayList<>();
		while (input.hasNext()) {
			String str = input.nextLine();
			if (count == 0) {
				String splitter[] = str.split(" ");
				state = "...." + splitter[2] + "....";
				count++;
			}
			else if (str.equals("")) {
				continue;
			}
			else {
				String line[] = str.split(" => ");
				String pattern = line[0];
				String plant = line[1];
				if (plant.equals(".")) dead.add(pattern);
				else live.add(pattern);
			}
		}
		int generation = 0, lastPlant = Integer.MIN_VALUE;
		String temp = state;
		while (generation < part2) {
			if (lastPlant == state.length() - 4) {
				StringBuilder sb = new StringBuilder(state);
				sb.append("....");
				state = sb.toString();
				temp = state;
			}
			for (int i = 2; i < state.length() - 2; i++) {
				String s = state.substring(i - 2, i + 3);
				if (live.contains(s)) {
					StringBuilder sb = new StringBuilder(temp);
					sb.setCharAt(i, '#');
					temp = sb.toString();
					if (i > lastPlant) lastPlant = i;
				}
				else if (dead.contains(s)) {
					StringBuilder sb = new StringBuilder(temp);
					sb.setCharAt(i, '.');
					temp = sb.toString();
				}
			}
			state = temp;
			generation++;
		}
		int total = 0;
		for (int i = 2; i < state.length(); i++) {
			StringBuilder sb = new StringBuilder(state);
			if (sb.charAt(i) == '#') total += (i - 4);
		}
		//System.out.println(total); // Part 1, switch end condition in while loop to get part 1 answer
		System.out.println(total + (billion - 200) * 46); // Part 2
	}
}