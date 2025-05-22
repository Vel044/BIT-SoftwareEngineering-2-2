package exe9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class SquareArea {

	public static void main(String[] args) {

		try (
				FileOutputStream fos = new FileOutputStream("a.txt");
				PrintWriter pw = new PrintWriter(fos);) {
			for (int r = 1; r <= 5; r++) {
				double area = Math.PI * r * r;

				pw.println(area);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String line;
		try {
			FileInputStream fis = new FileInputStream("a.txt");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				System.out.println(line); // System.out:PrintStream
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
