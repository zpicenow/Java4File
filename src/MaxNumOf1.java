//import java.io.*;
//import java.lang.reflect.Array;
//
//public class MaxNumOf1 {
//    public static void main(String[] args) throws IOException {
//        File fileTxt = new File("D:\\a.txt");
//        if (fileTxt.exists() && fileTxt.isFile()) {
//            System.out.println("文件已存在");
//        } else {
//            if (fileTxt.createNewFile()) {
//                System.out.println("创建成功");
//            } else {
//                System.out.println("创建a.txt失败");
//            }
//        }
//        PrintWriter printWriter = new PrintWriter(fileTxt);
//        String aTxtString = "11001101\r\n10110101\r\n01010101\r\n11001000\r\n01010101\r\n11001101\r\n00011000\r\n11110000";
//        printWriter.write(aTxtString);
//        printWriter.close();
//
//
//        FileReader fileReader = new FileReader(fileTxt);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//        char[][] aTxtChars = new char[8][];
//
//        String s = null;
//        for (int i = 0; i < 8; i++) {
//            if ((s = bufferedReader.readLine()) != null) {
//                aTxtChars[i] = s.toCharArray();
//            }
//        }
//
//        int num = 0;
//        int maxnum = 0;
//        for (char[] cc : aTxtChars) {
//            num = 0;
//            for (char c : cc) {
//
//                if (c == '1') num++;
//            }
//            maxnum = maxnum > num ? maxnum : num;
//
//        }
//
//        for (int i = 0; i < 8; i++) {
//
//            num = 0;
//            for (int j = 0; j < 8; j++) {
//                if(aTxtChars[j][i]=='1') num++;
//            }
//            maxnum = maxnum > num ? maxnum : num;
//
//        }
//
//        for (int i = 0; i < 8; i++) {
//            num = 0;
//            for (int j = 0; j < 8; j++) {
//                if (i == j && aTxtChars[i][j] == '1') {
//
//                    num++;
//                }
//            }
//            maxnum = maxnum > num ? maxnum : num;
//
//        }
//
//
//        System.out.println(maxnum);
//    }
//}
