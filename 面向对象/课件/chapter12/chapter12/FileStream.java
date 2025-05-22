import java.io.*;

public class FileStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {

			fis = new FileInputStream("C:\\Users\\57499\\Desktop\\java.png");
			fos = new FileOutputStream("C:\\Users\\57499\\Desktop\\aa.png");
			int temp = 0;
			while ((temp = fis.read()) != -1) {
				System.out.println(temp);
				fos.write(temp);
			}
			// 将数据从内存中写入磁盘
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
