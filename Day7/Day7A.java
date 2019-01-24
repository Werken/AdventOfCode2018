import java.io.*;
import java.util.*;

public class Day7A {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(new File("Day7Input.txt"));
		List<String> steps = new ArrayList<String>();
		List<String> instructions = new ArrayList<String>();
		HashMap<String, List<String>> children = new HashMap<String, List<String>>();
		HashMap<String, List<String>> parents = new HashMap<String, List<String>>();
		while (input.hasNext()) {
			String str = input.nextLine();
			String array[] = str.split(" ");
			if (!steps.contains(array[1])) steps.add(array[1]);
			if (!steps.contains(array[7])) steps.add(array[7]);
			instructions.add(array[1]);
			instructions.add(array[7]);
		}
		Collections.sort(steps);
		List<String> temp1 = new ArrayList<String>();
		List<String> temp2 = new ArrayList<String>();
		for (int i = 0; i < steps.size(); i++) {
			for (int j = 0; j < instructions.size(); j += 2) {
				if (instructions.get(j).equals(steps.get(i))) temp1.add(instructions.get(j + 1));
				if (instructions.get(j + 1).equals(steps.get(i))) temp2.add(instructions.get(j));
			}
			Collections.sort(temp1);
			Collections.sort(temp2);
			if (temp1.size() > 0) parents.put(steps.get(i), temp1);
			if (temp2.size() > 0) children.put(steps.get(i), temp2);
			temp1 = new ArrayList<String>();
			temp2 = new ArrayList<String>();
		}
		String end = "";
		List<String> start = new ArrayList<String>();
		for (int i = 0; i < steps.size(); i++) {
			if (!parents.containsKey(steps.get(i))) end = steps.get(i);
		}
		while (start.size() + parents.size() < steps.size()) {
			for (int i = 0; i < steps.size(); i++) {
				if (!children.containsKey(steps.get(i))) start.add(steps.get(i));
			}
		}
		Collections.sort(start);
		boolean check = true;
		List<String> completed = new ArrayList<String>();
		List<String> candidates = new ArrayList<String>();
		for (int j = 0; j < start.size(); j++) {
			completed.add(start.get(j));
			while (!completed.contains(end)) {
				for (Map.Entry<String, List<String>> e : children.entrySet()) {
					for (String e1 : e.getValue()) {
						if (!completed.contains(e1)) check = false;
					}
					if (check) {
						if (!completed.contains(e.getKey())) candidates.add(e.getKey());
					}
					check = true;
				}
				Collections.sort(candidates);
				if (candidates.size() != 0) completed.add(candidates.get(0));
				else break;
				candidates = new ArrayList<String>();
			}
		}
		System.out.print("Result: ");
		for (int i = 0; i < completed.size(); i++) System.out.print(completed.get(i));
		System.out.println();
	}
}