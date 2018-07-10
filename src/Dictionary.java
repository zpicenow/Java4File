import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private static Map<String, String> dictionaryMap = new HashMap<>();
    private static boolean isHave = true;


//    public static void main(String[] args) throws IOException {
//
//        File file = new File("D:\\dictionary.txt");
//        if (file.exists() && file.isFile()) {
//            System.out.println("文件已存在");
//        } else {
//            if (file.createNewFile()) System.out.println("创建成功");
//
//        }
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        FileReader fileReader = new FileReader(file);
//        BufferedReader bufferedReaderForFile = new BufferedReader(fileReader);
//        String stringFromFile = null;
//        while ((stringFromFile = bufferedReaderForFile.readLine()) != null) {
//            String[] strings = stringFromFile.split("=");
//            dictionaryMap.put(strings[0], strings[1]);
//        }
//
//
//        PrintWriter printWriter = new PrintWriter(file);
//        String s = null;
//        boolean b = true;
//        System.out.println("=====================请选择===============\n1\t存单词解释；\n2\t查询单词\n3\t退出");
//
//            while ((s = bufferedReader.readLine()) != null) {
//
//                if (s.equals("3")) {
//
//                    b = false;break;
//                }
//                if (s .equals( "1")) {
//                    System.out.println("格式： /单词/=/解释/");
//                    if ((s = bufferedReader.readLine()) != null) {
//                        if ((s.indexOf('=') == -1) || (s.indexOf('=') == 0) || (s.indexOf('=') != s.lastIndexOf('='))) {
//                            System.out.println("格式错误");
//                            break;
//                        }
//                        DmapAdd(s);
//                    }
//                } else if (s.equals("2")) {
//                    isHave = false;
//                    System.out.println("请输入单词:");
//                    if ((s = bufferedReader.readLine()) != null) {
//                        String finalS = s;
//                        dictionaryMap.forEach((k, v) -> {
//                            if (k.equals(finalS)) {
//                                System.out.println("解释：" + v);
//                                isHave = true;
//                            }
//                        });
//                        if (!isHave) System.out.println("查无此词");
//
//                    }
//                }
//
//            }
//
//                dictionaryMap.forEach((k,v)->{
//                    printWriter.println(k + "=" + v);
//                });
//
//
//        bufferedReader.close();
//        bufferedReaderForFile.close();
//        printWriter.close();
//
//
//
//    }

    private static void DmapAdd(String s) {
        isHave = true;
        String[] strings = s.split("=");
        dictionaryMap.forEach((k, v) ->{
            if (k.equals(strings[0])) {
                dictionaryMap.put(k, strings[1]);
                isHave = false;
            }
        });
        if (isHave) {
            dictionaryMap.put(strings[0], strings[1]);
        }

    }
}
