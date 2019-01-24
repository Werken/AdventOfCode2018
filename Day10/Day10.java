import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Day10 {
	class Point {
		private int x;
		private int y;
		private final int vx;
		private final int vy;

		public Point(String data) {
			String parts[] = data.split("<");
			String partsCont[] = parts[1].substring(0, parts[1].indexOf('>')).split(",");
			x = Integer.parseInt(partsCont[0].trim());
			y = Integer.parseInt(partsCont[1].trim());
			String vParts[] = parts[2].substring(0, parts[2].length() - 1).split(",");
			vx = Integer.parseInt(vParts[0].trim());
			vy = Integer.parseInt(vParts[1].trim());
		}

		public void tick() {
			x += vx;
			y += vy;
		}
		public void reverseTick() {
			x -= vx;
			y -= vy;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public boolean atCoords(int x, int y) {
			return this.x == x && this.y == y;
		}
	}

	private void main() throws Exception {
		List<String> input = Files.readAllLines(Paths.get("Day10Input.txt"));
		if (input == null || input.isEmpty()) {
			System.out.println("Input data set is empty");
			return;
		}
		List<Point> points = new ArrayList<>();
		for (String line : input) {
			points.add(new Point(line));
		}
		int minX = Integer.MIN_VALUE, maxX = Integer.MAX_VALUE, minY = Integer.MIN_VALUE, maxY = Integer.MAX_VALUE;
		int xDiff = Integer.MAX_VALUE, yDiff = Integer.MAX_VALUE;
		int seconds = 0;
		boolean first = true;

		do {
			if (first) first = false;
			else {
				xDiff = maxX - minX;
				yDiff = maxY - minY;
			}
			minX = Integer.MAX_VALUE;
			maxX = Integer.MIN_VALUE;
			minY = Integer.MAX_VALUE;
			maxY = Integer.MIN_VALUE;
			for (Point point : points) {
				point.tick();
				if (point.getX() < minX) minX = point.getX();
				if (point.getX() > maxX) maxX = point.getX();
				if (point.getY() < minY) minY = point.getY();
				if (point.getY() > maxY) maxY = point.getY();
			}
			seconds++;
		} while ((maxX - minX) < xDiff && (maxY - minY) < yDiff);
		minX = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		minY = Integer.MAX_VALUE;
		maxY = Integer.MIN_VALUE;
		for (Point point : points) {
			if (point.getX() < minX) minX = point.getX();
			if (point.getX() > maxX) maxX = point.getX();
			if (point.getY() < minY) minY = point.getY();
			if (point.getY() > maxY) maxY = point.getY();
			point.reverseTick();
		}
		seconds--;
		for (int y = minY; y <= maxY; y++) {
			for (int x = minX; x <= maxX; x++) {
				boolean found = false;
				for (Point point : points) {
					if (point.atCoords(x, y)) {
						found = true;
						break;
					}
				}
				if (found) System.out.print("#");
				else System.out.print(".");
			}
			System.out.println("");
		}
		System.out.println(String.format("Seconds: %d", seconds));
	}

	public static void main(String[] args) throws Exception {
		new Day10().main();
	}

}