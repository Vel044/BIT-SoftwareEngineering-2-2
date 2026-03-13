import java.io.File;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean flag=false;
	   File path=new File("c:\\javatest");
	   if (!path.exists()) path.mkdirs();
	  // System.out.println(path.getPath());
	   //System.out.println(path.getAbsolutePath());
       File f=new File(path.getPath()+File.separator+"Hello.txt");
           if (f.exists()) f.delete();
        else {
        	
				try {
					flag=f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println(flag);
        }
	}

}
