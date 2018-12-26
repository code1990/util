package io;

import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author code1990
 * @date
 * @descrption 文件工具类
 */
public class FileUtil {

    /**
     * 指定路径下面创建文件夹
     * @param list
     */
    public static void createDir(List<String> list){
        for(String str:list){
            createDir(str,false);
        }
    }

    /**
     * 创建文件夹
     * @param path 给定路径
     * @param flag 是否重复创建
     */
    public static void createDir(String path,boolean flag){
        File file = new File(path);
        if(file.exists() && true == flag){
            path = path + new SimpleDateFormat("yyyyMMdd").format(new Date());
            new File(path).mkdirs();
        }else{
            file.mkdirs();
        }
        System.out.println("ok");
    }

    public static void main(String[] args) {
        String str = "E:\\java1234";
        FileUtil.createDir(str,false);
        String[] strArray = new String[]{
                "javabase",
                "database",
                "webbase",
                "javaweb",
                "andriod",
                "yun",
                "blockchain"
        };
        for(String s:strArray){
            FileUtil.createDir(str+File.separator+s,false);
        }
    }

    @Test
    public void testCreateDir(){
        String str = "E:\\java1234";
        FileUtil.createDir(str,false);
    }

    public static List<String> readDir(String path){
        List<String> list = new ArrayList<>();
        File file = new File(path);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f:files){
                String str = f.getAbsolutePath();
                System.out.println(str);
                list.add(str);
            }
        }
        return list;
    }
}
