package io;

import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author issuser
 * @description 读取文本文件
 */
public class TxtUtil {

    /**
     *  给定文件目录读取 获取所有的文件内容
     * @param filePath 给定文件路径
     * @return
     */
    public static List<String> readTxt(String filePath) {
        List<String> resultList = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            resultList = new ArrayList<String>();
            File file = new File(filePath);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String str = "";

            while ((str = bufferedReader.readLine()) != null) {
                if (null != str && !"".equals(str)) {
                    resultList.add(str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return resultList;
    }

    /**
     * 把内容写入文本文件
     * @param filePath 需要保存的目标文件的路径
     * @param content 需要保存的文件内容
     */
    public static void writeTxt(String filePath, String content)  {
        FileWriter fw = null;
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file);
            fw.write(content);
            fw.flush();
            fw.close();
            System.out.println("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对于文本文件内容过滤
     * @param list 需要被过滤的对象
     * @return list 返回一个list
     */
    public static List<String> filter(List<String> list){
        List<String> lessList = null;
        if(null != list && !list.isEmpty()){
            lessList = new ArrayList<>();
            Set<String> set = new HashSet<String>();
            for (String str:list) {
                //如果可以被存放 说明不重复
                if(set.add(str)){
                    lessList.add(str);
                }
            }
        }
        return lessList;
    }

    /**
     * 获取文本文件中重复的内容
     * @param list 需要查看重复的数据
     * @return 返回重复的数据
     */
    public static List<String> filterLess(List<String> list){
        List<String> lessList = null;
        if(null != list && !list.isEmpty()){
            lessList = new ArrayList<>();
            Set<String> set = new HashSet<String>();
            for (String str:list) {
                //如果不可以被存放 说明重复 需要作为返回的内容
                if(!set.add(str)){
                    lessList.add(str);
                }
            }
        }
        return lessList;
    }

    /**
     * 给定文件名
     * @param fileName 需要保存的文件名
     * @param list 需要保存的内容
     */
    public static void save(String fileName,List<String> list){
        if(null == fileName || "".equals(fileName)){
            fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        }
        String defaultDir = System.getProperty("user.dir");
        try {
            StringBuilder sb = new StringBuilder(20000);
            for (String str:list){
                sb.append(str+"\n");
            }
            String path = defaultDir+File.separator+fileName+".txt";
            TxtUtil.writeTxt(path,sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
