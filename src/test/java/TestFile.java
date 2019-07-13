import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestFile {
    /**
     * 文件名的正则表达式
     */
    private  static final String FILE_NAME_REDEX = "^oas_error_log.\\d{4}-\\d{1,2}-\\d{1,2}.txt$";
    private  static final String FILE_NAME_REDEX2 = "^oas_log.\\d{4}-\\d{1,2}-\\d{1,2}-\\d{1,2}.txt$";
    private  static final String FILE_NAME_REDEX3 = "^oas_error_log.\\d{4}-\\d{1,2}$";
    private  static final String FILE_NAME_REDEX4 = "^oas_log.\\d{4}-\\d{1,2}-\\d{1,2}$";
    /**
     * 目标的扫描文件夹
     */
    private static final String FTP_BASE_PATH = "D:/oas_ftp/";
    private static final String FTP_BASE_PATH2 = "D:\\oas_log\\oas\\";
    @Test
    public void testFile(){
        List<String> fileNames = new ArrayList<String>();
        if(new File(FTP_BASE_PATH2).exists() && new File(FTP_BASE_PATH2).isDirectory()){
            for(File file:new File(FTP_BASE_PATH2).listFiles()){
                boolean flag = Pattern.matches(FILE_NAME_REDEX, file.getName()) ||
                        Pattern.matches(FILE_NAME_REDEX2, file.getName()) ||
                        Pattern.matches(FILE_NAME_REDEX3, file.getName()) ||
                        Pattern.matches(FILE_NAME_REDEX4, file.getName()) ;
                if(flag){
                    fileNames.add(file.getAbsolutePath());
                }
            }
        }
        for (String f:fileNames ) {
            System.out.println(f);
        }
    }

    @Test
    public void testNIO(){
//        List<String> list = NIOReader.read("");
//        for (int i = 0; i <list.size() ; i++) {
//            System.out.println(list.get(i));
//        }

    }
}
