import java.io.*;
import java.util.*;

public class Day7B {
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
		String[] workers = new String[5];
		int[] times = new int[5];
		int counter = 0;
		for (int i = 0; i < workers.length; i++) {
			workers[i] = null;
			times[i] = -1;
		}
		int low = 27 + 60;
		for (int i = 0; i < start.size(); i++) {
			workers[i] = start.get(i);
			times[i] = steps.indexOf(start.get(i)) + 60;
			if (times[i] < low) low = times[i];
		}
		for (int i = 0; i < workers.length; i++) {
			if (times[i] == -1) times[i] = low;
		}
		List<String> queue = new ArrayList<String>();
		while (!completed.contains(end)) {
			for (int i = 0; i < times.length; i++) {
				if (counter == times[i] && workers[i] != null)
					completed.add(workers[i]);
			}
			for (int i = 0; i < completed.size(); i++) {
				if (queue.contains(completed.get(i))) queue.remove(completed.get(i));
			}
			for (Map.Entry<String, List<String>> e : children.entrySet()) {
				for (String e1 : e.getValue()) {
					if (!completed.contains(e1)) check = false;
				}
				if (check) {
					if (!completed.contains(e.getKey()) && !queue.contains(e.getKey())) candidates.add(e.getKey());
				}
				check = true;
			}
			Collections.sort(candidates, Collections.reverseOrder());
			int num = candidates.size();
			if (candidates.size() != 0) {
				for (int j = 0; j < workers.length; j++) {
					if (workers[j] == null && times[j] < counter) times[j] = counter;
					if (counter >= times[j] && num != 0 && !queue.contains(candidates.get(num - 1))) {
						workers[j] = candidates.get(num - 1);
						queue.add(workers[j]);
						candidates.remove(workers[j]);
						times[j] = counter + steps.indexOf(workers[j]) + 61;
						num--;
					}
				}
			}
			candidates = new ArrayList<String>();
			counter++;
		}
		System.out.println("Result: " + counter);
	}
}