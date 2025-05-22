import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferWriting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub j
		File f = new File("BufferHello.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(f));
			out.write("hello");
			out.newLine();// BufferedWriternewline
			out.write("hello,buffer");
			out.newLine();
			out.write("I love the world");
			out.newLine();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
