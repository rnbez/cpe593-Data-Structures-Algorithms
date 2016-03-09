import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

	public static int[] run(int[] x, int[] y) {
		int[] zeros = new int[x.length];
		int[] res;
		int count = x.length;

		for (int i = 0; i < y.length; i++) {
			for (int j = 0; j < x.length; j++) {
				if (zeros[j] == 1)
					if (x[j] == y[i])
						break;
					else
						continue; 
				
				if (x[j] % y[i] == 0) {
					for (int l = j; l < x.length; l += y[i]) {
						if (zeros[l] == 1)
							continue;
						zeros[l] = 1;
						count--;
					}
					break;
				}
			}
		}

		res = new int[count];
		int res_index = 0;
		for (int i = 0; i < zeros.length; i++) {
			if (zeros[i] == 0) {
				res[res_index++] = x[i];
			}
		}
		return res;
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Max Memory: " + (Runtime.getRuntime().maxMemory()/(Math.pow(2, 20))) + " MB");
		
		Scanner s = new Scanner(new FileReader("HW.dat"));
		final String pattern = "Cases:";
		int cases = 0;
		String firstLine = s.nextLine();
		if (firstLine.contains(pattern)) {
			cases = Integer.parseInt(firstLine.replace(pattern, "").trim());
		}

		for (int i = 0; i < cases; i++) {
			int start = s.nextInt();
			int end = s.nextInt();
			int[] arr = new int[end - start + 1];
			for (int j = 0; j < arr.length; j++)
				arr[j] = start++;

			int n = s.nextInt();
			int[] multi = new int[n];
			for (int j = 0; j < multi.length; j++)
				multi[j] = s.nextInt();

			long startTime = System.currentTimeMillis();
			int[] res = run(arr, multi);
			long sum = 0;
			for (int j = 0; j < res.length; j++)
				sum += res[j];
			
			System.out.println(res.length + " " + sum + " " + (System.currentTimeMillis() - startTime) );
		}

		s.close();
	}
}
