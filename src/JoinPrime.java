import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Queue;

import com.zputil.*;

public class JoinPrime {
    private static Queue<Integer> queue = new ArrayDeque<>();


    public static void main(String[] args) throws Exception {
        int num = 0;
        File file = new File("D:\\prime.txt");
        if (file.exists() && file.isFile()) {
            System.out.println("找到该文件");

        } else {
            throw new Exception("找不到文件");
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String ss = null;
        while ((ss = bufferedReader.readLine()) != null) {
            if (isJoinPrime(ss)) {

                System.out.print(ss + " : ");

                while (queue.size() != 0) {

                    if (queue.element() == -1) {
                        System.out.print("/");
                        queue.poll();
                    }
                    System.out.print(queue.poll() + " ");

                }
                System.out.println();
                num++;
            }
        }
        System.out.println("组合素数的个数为：" + num);

    }




    private static boolean isJoinPrime(String ss) {
        boolean b = false;
        switch (ss.length()) {
            case 1:
                break;
            case 2:
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 1)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(1, 2)))) && (ss.indexOf('0') != 1)) {

                    queue.add(Integer.parseInt(ss.substring(0, 1)));
                    queue.add(Integer.parseInt(ss.substring(1, 2)));

                    b = true;

                }
                break;
            case 3:
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 1)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(1, 2))))
                        && (PrimeNum.isPrime(Integer.parseInt(ss.substring(2, 3))))&& ((ss.indexOf('0')) == -1)) {
                    queue.add(Integer.parseInt(ss.substring(0, 1)));
                    queue.add(Integer.parseInt(ss.substring(1, 2)));
                    queue.add(Integer.parseInt(ss.substring(2, 3)));
                    if (b) queue.add(-1);
                    b = true;

                }
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 1)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(1, 3))))
                        && (ss.indexOf('0') != 1)) {
                    if (b) queue.add(-1);
                    queue.add(Integer.parseInt(ss.substring(0, 1)));
                    queue.add(Integer.parseInt(ss.substring(1, 3)));
                    b = true;
                }
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 2)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(2, 3))))
                        && (ss.indexOf('0') != 2)) {
                    if (b) queue.add(-1);
                    queue.add(Integer.parseInt(ss.substring(0, 2)));
                    queue.add(Integer.parseInt(ss.substring(2, 3)));
                    b = true;
                }
                break;
            case 4:
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 1)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(1, 2))))
                        && (PrimeNum.isPrime(Integer.parseInt(ss.substring(2, 3))))&& (PrimeNum.isPrime(Integer.parseInt(ss.substring(3, 4))))&& ((ss.indexOf('0')) == -1)) {
                    queue.add(Integer.parseInt(ss.substring(0, 1)));
                    queue.add(Integer.parseInt(ss.substring(1, 2)));
                    queue.add(Integer.parseInt(ss.substring(2, 3)));
                    queue.add(Integer.parseInt(ss.substring(3, 4)));
                    b = true;

                }
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 2)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(2, 3))))
                        && (PrimeNum.isPrime(Integer.parseInt(ss.substring(3, 4))))&& (ss.indexOf('0') != 2)&& (ss.indexOf('0') != 3)) {
                    if (b) queue.add(-1);
                    queue.add(Integer.parseInt(ss.substring(0, 2)));
                    queue.add(Integer.parseInt(ss.substring(2, 3)));
                    queue.add(Integer.parseInt(ss.substring(3, 4)));
                    b = true;
                }
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 1)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(1, 3))))
                        && (PrimeNum.isPrime(Integer.parseInt(ss.substring(3, 4))))&& (ss.indexOf('0') != 1)&& (ss.indexOf('0') != 3)) {
                    if (b) queue.add(-1);
                    queue.add(Integer.parseInt(ss.substring(0, 1)));
                    queue.add(Integer.parseInt(ss.substring(1, 3)));
                    queue.add(Integer.parseInt(ss.substring(3, 4)));
                    b = true;
                }
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 1)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(1, 2))))
                        && (PrimeNum.isPrime(Integer.parseInt(ss.substring(2, 4))))&& (ss.indexOf('0') != 2)&& (ss.indexOf('0') != 1)) {
                    if (b) queue.add(-1);
                    queue.add(Integer.parseInt(ss.substring(0, 1)));
                    queue.add(Integer.parseInt(ss.substring(1, 2)));
                    queue.add(Integer.parseInt(ss.substring(2, 4)));
                    b = true;
                }
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 3)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(3, 4))))
                        && (ss.indexOf('0') != 3)) {
                    if (b) queue.add(-1);
                    queue.add(Integer.parseInt(ss.substring(0, 3)));
                    queue.add(Integer.parseInt(ss.substring(3, 4)));
                    b = true;
                }
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 1)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(1, 4))))
                        && (ss.indexOf('0') != 1)) {
                    if (b) queue.add(-1);
                    queue.add(Integer.parseInt(ss.substring(0, 1)));
                    queue.add(Integer.parseInt(ss.substring(1, 4)));
                    b = true;
                }
                if ((PrimeNum.isPrime(Integer.parseInt(ss.substring(0, 2)))) && (PrimeNum.isPrime(Integer.parseInt(ss.substring(2, 4))))
                        && (ss.indexOf('0') != 2)) {
                    if (b) queue.add(-1);
                    queue.add(Integer.parseInt(ss.substring(0, 2)));
                    queue.add(Integer.parseInt(ss.substring(2, 4)));
                    b = true;
                }
                break;

        }
        return b;
    }
}
