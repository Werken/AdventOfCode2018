import java.util.*;
import java.io.*;

public class Day4A {
	public static void main(String args[]) throws Exception {
		Scanner input = new Scanner(new File("Day4Input.txt"));
		ArrayList<String> arr = new ArrayList<String>();
		while (input.hasNext()) {
			String str = input.nextLine();
			arr.add(str);
		}
		Collections.sort(arr, new Comparator<String>() {
			public int compare(String string1, String string2) {
				return string1.compareTo(string2);
			}
		});
		ArrayList<String> guards = new ArrayList<String>();
		for (int i = 0; i < arr.size(); i++) {
			String array[] = arr.get(i).split(" ");
			StringBuilder sb = new StringBuilder(array[1]);
			sb.deleteCharAt(5);
			array[1] = sb.toString();
			String time[] = array[1].split(":");
			guards.add(time[0]);
			guards.add(time[1]);
			if (array[2].equals("Guard")) {
				StringBuilder id = new StringBuilder(array[3]);
				id.deleteCharAt(0);
				array[3] = id.toString();
				guards.add(array[3]);
			}
			else guards.add(array[2]);
		}
		ArrayList<Integer> sleep = new ArrayList<Integer>();
		for (int i = 0; i < guards.size(); i += 3) {
			sleep.add(Integer.parseInt(guards.get(i)));
			sleep.add(Integer.parseInt(guards.get(i + 1)));
			if (!guards.get(i + 2).equals("falls") && !guards.get(i + 2).equals("wakes")) sleep.add(Integer.parseInt(guards.get(i + 2)));
			else if (guards.get(i + 2).equals("falls")) sleep.add(-1);
			else sleep.add(-2);
		}
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<Integer> totals = new ArrayList<Integer>();
		int temp = 0, total = 0;
		for (int i = 0; i < sleep.size(); i += 3) {
			if (sleep.get(i + 2) > 0) {
				totals.add(total);
				totals.add(sleep.get(i + 2));
				total = 0;
				ids.add(sleep.get(i + 2));
			}
			else if (sleep.get(i + 2).equals(-1)) {
				ids.add(sleep.get(i + 1));
				temp = sleep.get(i + 1);
			}
			else if (sleep.get(i + 2).equals(-2)) {
				ids.add(sleep.get(i + 1));
				temp = sleep.get(i + 1) - temp;
				total += temp;
				temp = 0;
			}
		}
		totals.add(total);
		totals.remove(0);
		total = 0;
		int max = 0, maxID = 0;
		ArrayList<Integer> it = new ArrayList<Integer>();
		for (int i = 0; i < totals.size(); i++) {
			if (totals.get(i) > 59 && !it.contains(totals.get(i))) {
				it.add(totals.get(i));
				it.add(0);
			}
		}
		for (int i = 0; i < totals.size(); i += 2) {
			int tmp = it.get(it.indexOf(totals.get(i)) + 1);
			tmp += totals.get(i + 1);
			it.set(it.indexOf(totals.get(i)) + 1, tmp);
		}
		for (int i = 0; i < it.size(); i += 2) {
			if (it.get(i + 1) > max) {
				max = it.get(i + 1);
				maxID = it.get(i);
			}
		}
		ArrayList<Integer> times = new ArrayList<Integer>();
		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i).equals(maxID)) {
				while (ids.get(i + 1) < 100) {
					for (int j = ids.get(i + 1); j < ids.get(i + 2); j++) {
						times.add(j);
					}
					i += 2;
				}
			}
		}
		int[] data = new int[times.size()];
		for (int i = 0; i < times.size(); i++) {
			data[i] = times.get(i);
		}
		int freq = mode(data);
		System.out.println(freq);
		System.out.println("Result: " + maxID * freq);
	}

	public static int mode(int []array)
	{
	    HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
	    int max  = 1;
	    int temp = 0;

	    for(int i = 0; i < array.length; i++) {

	        if (hm.get(array[i]) != null) {

	            int count = hm.get(array[i]);
	            count++;
	            hm.put(array[i], count);

	            if(count > max) {
	                max  = count;
	                temp = array[i];
	            }
	        }

	        else
	            hm.put(array[i],1);
	    }
	    return temp;
	}
}