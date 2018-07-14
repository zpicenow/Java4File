package com.zputil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyFile {
    private String fileName;
    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    private long fileSize;

   public static List<MyFile> fileList = new ArrayList<>();

    public static void isRepeat(String filePath) {

        File file = new File(filePath);
        if (!file.exists() || !file.isDirectory()) {
            System.out.println("输入格式错误");
            return;
        }
        File[] childFiles = file.listFiles();
        for (File child : childFiles) {
            if (child.isFile()) {

                fileList.add(new MyFile(child.getAbsolutePath()));
            } else if (child.isDirectory()) {
                isRepeat(child.getAbsolutePath());

            }

        }
    }

    public static void isR() {


        String s = null;

        for (int i = 0; i < fileList.size(); i++) {
            boolean b = true;
            s = fileList.get(i).fileName;
            for (int j = i; j < fileList.size(); j++) {
                if (fileList.get(j).fileName.equals(s)&& (fileList.get(i).fileSize == fileList.get(j).fileSize) && !(fileList.get(i).filePath.equals(fileList.get(j).filePath))) {
                    if (b) {
                        b = false;
                        System.out.print(s + "  in  " + fileList.get(i).filePath + " \n\t\t\t " + fileList.get(j).filePath);
                        fileList.remove(j);
                    } else {
                        System.out.print(" \n\t\t\t " + fileList.get(j).filePath);
                        fileList.remove(j);
                    }

                }
            }
            System.out.println();
        }

        if (s == null) {

            System.out.println("不存在重复文件");

        }

    }



    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String path = null;
        if ((path = bufferedReader.readLine()) != null) {
            MyFile.isRepeat(path);
            MyFile.isR();

        }

    }
    public MyFile(String pathname) {
        fileName = pathname.substring(pathname.lastIndexOf(File.separator) + 1, pathname.length());
        filePath = pathname.substring(0, pathname.lastIndexOf(File.separator));
        fileSize = new File(pathname).length();

    }

}
