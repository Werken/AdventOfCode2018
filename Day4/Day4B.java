import java.util.*;
import java.io.*;

public class Day4B {
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
		ArrayList<Integer> it = new ArrayList<Integer>();
		for (int i = 0; i < totals.size(); i++) {
			if (totals.get(i) > 59 && !it.contains(totals.get(i))) it.add(totals.get(i));
		}
		HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		List<Integer> values = new ArrayList<Integer>();
		ArrayList<Integer> times = new ArrayList<Integer>();
		int[] data, freq;
		ArrayList<Integer> occur = new ArrayList<Integer>();
		for (int i = 0; i < it.size(); i++) {
			for (int j = 0; j < ids.size(); j++) {
				if (ids.get(j).equals(it.get(i))) {
					j++;
					while (ids.get(j) < 100 || (j + 1) == ids.size() - 1) {
						for (int k = ids.get(j); k < ids.get(j + 1); k++) {
							times.add(k);
						}
						if ((j + 2) < ids.size()) j += 2;
						else break;
					}
				}
			}
			data = new int[times.size()];
			for (int j = 0; j < times.size(); j++) {
				data[j] = times.get(j);
			}
			freq = mode(data);
			occur.add(freq[0]);
			occur.add(freq[1]);
			occur.add(it.get(i));
			times = new ArrayList<Integer>();
		}
		int max = 0, maxID = 0, value = 0;
		for (int i = 0; i < occur.size(); i += 3) {
			if (occur.get(i + 1) >= max) {
				max = occur.get(i + 1);
				maxID = occur.get(i + 2);
				value = occur.get(i);
			}
		}
		System.out.println("Result: " + value * maxID);
	}

	public static int[] mode(int []array)
	{
	    HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
	    int max  = 1;
	    int temp = 0;
		int[] data;
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
	    data = new int[2];
	    data[0] = temp;
	    data[1] = max;
	    return data;
	}
}