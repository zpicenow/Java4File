package com.zputil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimeNum {


    public static boolean isPrime(int x) {
        if (x==0||x==1 ) return false;
        int j = 2;
        while (x > j) {
            if (x%j==0) return false;
            j++;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\prime.txt");
        if (file.exists() && file.isFile()) {
            System.out.println("文件已存在");
        } else {
            if (file.createNewFile()) {
                System.out.println("创建成功");

            }
        }
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 2; i <= 10000; i++) {
            if (isPrime(i)) {
                printWriter.write(i + "\r\n");
            }
        }
        printWriter.close();
    }
}
