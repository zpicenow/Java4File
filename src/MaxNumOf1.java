import java.io.*;
import java.lang.reflect.Array;

public class MaxNumOf1 {
    public static void main(String[] args) throws IOException {
        File fileTxt = new File("D:\\a.txt");
        if (fileTxt.exists() && fileTxt.isFile()) {
            System.out.println("文件已存在");
        } else {
            if (fileTxt.createNewFile()) {
                System.out.println("创建成功");
            } else {
                System.out.println("创建a.txt失败");
            }
        }
        PrintWriter printWriter = new PrintWriter(fileTxt);
        String aTxtString = "11001101\r\n10110101\r\n01010101\r\n11001000\r\n01010101\r\n11001101\r\n00011000\r\n11110000";
        printWriter.write(aTxtString);
        printWriter.close();


        FileReader fileReader = new FileReader(fileTxt);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        char[][] aTxtChars = new char[8][];

        String s = null;
        for (int i = 0; i < 8; i++) {
            if ((s = bufferedReader.readLine()) != null) {
                aTxtChars[i] = s.toCharArray();
            }
        }

        int max = 0;
            for (int i = 0; i < aTxtChars.length; i++){
                int temp = 0;
                for (int j = 0; j < aTxtChars[i].length; j++){
                    if(aTxtChars[i][j] == '1'){
                        temp ++;
                    }
                    else {
                        if(temp > max){
                            max = temp;
                        }
                        temp = 0;
                    }
                }
            }

            //纵向搜索
            for (int i = 0; i < 8; i++){
                int temp = 0;
                for (int j = 0; j < 8; j++){
                    if(aTxtChars[j][i] == '1'){
                        temp ++;
                    }
                    else {
                        if(temp > max){
                            max = temp;
                        }
                        temp = 0;
                    }
                }
            }

            //斜线搜索
            for (int i = 0; i < 8; i++){
                int temp = 0;
                if(max > 8 - i){
                    break;
                }
                if(i == 0){
                    for (int j = 0; j < 8; j ++){
                        if(aTxtChars[i+j][i+j] == '1'){
                            temp++;
                        }
                        else {
                            if(temp > max){
                                max = temp;
                            }
                            temp = 0;
                        }
                    }
                }else{
                    for (int j = 0; j < 8 - i; j ++){
                        if(aTxtChars[i+j][j] == '1'){
                            temp++;
                        }
                        else {
                            if(temp > max){
                                max = temp;
                            }
                            temp = 0;
                        }
                    }
                    for (int j = 0; j < 8 - i; j ++){
                        if(aTxtChars[j][i+j] == '1'){
                            temp++;
                        }
                        else {
                            if(temp > max){
                                max = temp;
                            }
                            temp = 0;
                        }
                    }
                }
            }

            System.out.println(max);
        }
}
