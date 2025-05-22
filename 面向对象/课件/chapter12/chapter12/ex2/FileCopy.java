package ex2;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        int code = 123456;
        int data;

        try (FileInputStream fis = new FileInputStream("a.png");
                FileOutputStream fos = new FileOutputStream("b.png");) {
            while ((data = fis.read()) != -1) {
                fos.write(data ^ code);
            }
            fos.flush();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 