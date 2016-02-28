import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.Scanner;

import javax.management.InvalidAttributeValueException;

public class GrowArray {

	static class GrowableArray {
		private int[] p;
		private int size;
		private int used;

		public GrowableArray() { // O(n)
			this.size = 1;
			this.p = new int[this.size]; // O(n)
			this.used = 0;
		}

		public GrowableArray(int size) { // O(n)
			this.size = size;
			this.p = new int[this.size]; // O(n)
			this.used = 0;
		}

		private void grow() { // O(n)
			this.size *= 2;
			// System.out.println("Growing array from " + this.used + " to " +
			// this.size);
			int[] temp = this.p;
			this.p = new int[this.size];
			for (int i = 0; i < temp.length; i++)
				p[i] = temp[i];
			// System.out.println("done");
		}

		private void shiftLeft(int start, int count) { // O(n)
			for (int i = start; i < count - 1;) {
				this.p[i] = this.p[++i];
			}
			this.p[count - 1] = 0;
		}

		private void shiftRight(int start, int count) { // O(n)
			for (int i = count; i > start;) {
				this.p[i] = this.p[--i];
			}
		}

		public void addBack(int v) { // O(log n)
			if (this.size == this.used)
				grow();
			this.p[this.used++] = v;
		}

		public void addFront(int v) { // O(n log n)
			if (this.size == this.used)
				grow();
			this.shiftRight(0, this.used);
			this.used++;
			this.p[0] = v;
		}

		public void removeFront() {
			shiftLeft(0, this.used--);
			// this.used--;
		}

		public void removeBack() {// O(1)
			this.p[--this.used] = 0;
		}

		public int size() { // O(1)
			return this.size;
		}

		public int used() { // O(1)
			return this.used;
		}

		public int get(int i) { // O(1)
			if (i < 0 || i >= this.used)
				throw new IllegalArgumentException("Index out of bounds: " + i);
			return this.p[i];
		}

		void set(int i, int v) { // O(1)
			if (i < 0 || i >= this.used)
				throw new IllegalArgumentException("Index out of bounds: " + i);
			this.p[i] = v;
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BetterGrowableArray testList = new BetterGrowableArray();
		int a;
		for (a = 0; a <= 15; a++) {
			if (a % 2 == 0) {
				testList.addBack(a);
			} else {
				testList.addFront(a);
			}
		}

		testList.removeBack();
		testList.removeBack();
		testList.removeFront();
		testList.removeBack();
		testList.removeFront();
		testList.removeFront();
		testList.removeFront();
		testList.removeBack();

		for (; a < 25; a++) {
			if (a % 2 == 0) {
				testList.addBack(a);
			} else {
				testList.addFront(a);
			}
		}

		BetterGrowableArray list = new BetterGrowableArray();
		final int n = 5;

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < n; i++)
			list.addBack(i + 1);
		// System.out.println("addBack took: " + ((System.currentTimeMillis() -
		// startTime) / 1000f) + " sec \n");
		for (int i = 0; i < list.used(); i++) {
			System.out.print(list.get(i) + " ");
			if ((i + 1) % 100 == 0)
				System.out.print("\n");
		}

		startTime = System.currentTimeMillis();
		for (int i = 0; i < n; i++)
			list.removeBack();
		System.out.println("\nremoveBack took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		// list = new BetterGrowableArray();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < n; i++)
			list.addFront(i + 1);
		System.out.println("addFront took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");
		for (int i = 0; i < list.used(); i++) {
			System.out.print(list.get(i) + " ");
			if ((i + 1) % 100 == 0)
				System.out.print("\n");
		}

		startTime = System.currentTimeMillis();
		for (int i = 0; i < n; i++)
			list.removeFront();
		System.out.println("\nremoveFront took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		//
		// final String ADD_FRONT = "ADD_FRONT";
		// final String ADD_BACK = "ADD_BACK";
		// final String REMOVE_FRONT = "REMOVE_FRONT";
		// final String REMOVE_BACK = "REMOVE_BACK";
		// final String OUTPUT = "OUTPUT";
		//
		// Scanner in = new Scanner(new FileReader("HW4a.txt"));
		// while(in.hasNextLine()){
		// String commandLine = in.nextLine();
		// if(commandLine != null && !commandLine.isEmpty()){
		// String[] splited = commandLine.split(" ");
		// switch (splited[0]) {
		// case ADD_FRONT:
		// break;
		// case ADD_BACK:
		// break;
		// case REMOVE_FRONT:
		// break;
		// case REMOVE_BACK:
		// break;
		// case OUTPUT:
		// break;
		// default:
		// throw new Exception("Invalid input");
		// }
		// }
		// else{
		// throw new Exception("Invalid input");
		// }
		// }

	}

}
