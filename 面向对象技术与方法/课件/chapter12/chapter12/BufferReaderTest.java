import java.io.*;

public class BufferReaderTest {

	public static void main(String[] args) {
		String line;
		File f = new File("HelloDate.java");
		if (!f.exists()) {
			System.err.println("cannt read the file");
		} else {
			try {
				BufferedReader in = new BufferedReader(new FileReader(f));
				line = in.readLine();
				while (line != null) {
					System.out.println(line);
					line = in.readLine();
				}
				in.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
