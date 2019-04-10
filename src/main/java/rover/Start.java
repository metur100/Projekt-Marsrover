package rover;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Start {

	static Random r = new Random();
	static LinkedHashMap<int[], String> mars;

	public static void init() {
		mars = new LinkedHashMap<>();
		int x = 80;
		int y = 20;
		int rx = x / 2;
		int ry = y / 2;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int[] p = new int[] { i, j };
				if (r.nextDouble() < 0.25 && !(rx == i && ry == j))
					mars.put(p, "#");
			}
		}
		mars.put(new int[] { rx, ry }, "n");
	}

	public static int[] maximum(Set<int[]> set) {
		int[] x = new int[2];
		for (int[] e : set) {
			if (e[0] > x[0])
				x[0] = e[0];
			if (e[1] > x[1])
				x[1] = e[1];
		}
		return x;
	}

	public static String get(Map<int[], String> kloetze, int[] p) {
		Set<Entry<int[], String>> entrySet = kloetze.entrySet();
		for (Entry<int[], String> entry : entrySet) {
			if (entry.getKey()[0] == p[0] && entry.getKey()[1] == p[1])
				return entry.getValue();
		}
		return null;
	}

	public static void out() {
		// Set<int[]> keySet = mars.keySet();
		// for (int[] e : keySet) {
		// if (e[0] == 39 && e[1] == 10)
		// System.err.println(mars.get(e) + " " + e.hashCode());
		// }

		int[] max = maximum(mars.keySet());
		for (int j = 0; j < max[1]; j++) {
			for (int i = 0; i < max[0]; i++) {
				// System.out.println(i + "," + j + ": " + get(mars, new int[] { i, j }));

				if (get(mars, new int[] { i, j }) == null) {
					System.out.print(" ");
					continue;
				}
				if (get(mars, new int[] { i, j }).equals("#"))
					System.out.print("#");
				else if (get(mars, new int[] { i, j }).equals("n"))
					System.out.print("^");
				else if (get(mars, new int[] { i, j }).equals("s"))
					System.out.print("V");
				else if (get(mars, new int[] { i, j }).equals("e"))
					System.out.print(">");
				if (get(mars, new int[] { i, j }).equals("w"))
					System.out.print("<");

			}
			System.out.println();
		}
		for (int i = 0; i < max[0]; i++) {
			System.out.print("=");
		}
		System.out.println();
	}

	public static void main(String[] args) {

		if (args.length > 1) {
			long seed = Long.parseLong(args[1]);
			r.setSeed(seed);
			// System.out.println("Seed: " + seed);
		}
		init();
		String pg = args[0];
		out();
		for (int i = 0; i < pg.length(); i++) {
			make(pg.charAt(i));
			out();
		}
	}

	public static void make(char c) {
		if (c == 'f') {
			int[] p = findeRover();
			if (get(mars, p).equals("n"))
				p[1]--;
			else if (get(mars, p).equals("s"))
				p[1]++;
			else if (get(mars, p).equals("e"))
				p[0]++;
			else if (get(mars, p).equals("w"))
				p[0]--;
		} else if (c == 'b') {
			int[] p = findeRover();
			if (get(mars, p).equals("s"))
				p[1]--;
			else if (get(mars, p).equals("n"))
				p[1]++;
			else if (get(mars, p).equals("w"))
				p[0]++;
			else if (get(mars, p).equals("e"))
				p[0]--;
		} else if (c == 'l') {
			int[] p = findeRover();
			if (get(mars, p).equals("n"))
				mars.put(p, "w");
			else if (get(mars, p).equals("s"))
				mars.put(p, "e");
			else if (get(mars, p).equals("e"))
				mars.put(p, "n");
			else if (get(mars, p).equals("w"))
				mars.put(p, "s");
		} else if (c == 'r') {
			int[] p = findeRover();
			if (get(mars, p).equals("w"))
				mars.put(p, "n");
			else if (get(mars, p).equals("e"))
				mars.put(p, "s");
			else if (get(mars, p).equals("n"))
				mars.put(p, "e");
			else if (get(mars, p).equals("s"))
				mars.put(p, "w");
		}

	}

	private static int[] findeRover() {
		Set<Entry<int[], String>> entrySet = mars.entrySet();
		for (Entry<int[], String> entry : entrySet) {
			if (entry.getValue() != null && !entry.getValue().equals("#"))
				return entry.getKey();
		}
		throw new IllegalStateException("Rover missing in action");
	}

}
