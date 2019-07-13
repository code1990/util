package spider.word;
import io.TxtUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuanYu {
    private static Pattern NUMBER_PATTERN = Pattern.compile("^\\d{6}");
    public static void main(String[] args) {
        Object[] list22 = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\短语.txt").toArray();
        int i =0;

        while (i<list22.length){
            String str = list22[i].toString();
            StringBuilder sb = new StringBuilder();
//            sb.append(list22[i].toString()+"\t");
//            sb.append(list22[i+1].toString()+"\t");
//            System.out.println(sb.toString());
//            if(!Pattern.matches("^([^\\u4e00-\\u9fa5])*", str)){
////                System.out.println(i+1);
////                System.out.println(str);
////                str = str +"\nabc";
////                break;
//            }
            if(str.contains("例]")){
                str = str.split("例]")[0];
            }
            System.out.println(str);
//            System.out.println(str);
//            i+=1;
//            if(str.endsWith(".")){
//                str = "";
//            }
//            if(!str.trim().equals("")){
//                System.out.println(str);
//            }

            i++;
//            System.out.println(i);
        }
    }

    @Test
    public void getword(){
        Object[] list22 = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\bakup\\new11.txt").toArray();
        for (int i =0;i<list22.length;i++ ) {
            String str = list22[i].toString();
            if(!str.trim().equals("") && !str.contains("----")){
                String tmp = str.trim().split(" ")[0];
                System.out.println(tmp);
//                System.out.println(str.replaceAll(tmp,"").trim());
            }



        }
    }

    @Test
    public void getInfo(){
        File[] files = new File("C:\\Users\\issuser\\Desktop\\导入测试").listFiles();
        for (File f:files){
            String filename = f.getName();
            System.out.println(f.getAbsoluteFile().toString().lastIndexOf("\\"));
//            if(filename.contains("边际贡献月报")){
//                f.renameTo(new File(f.getAbsoluteFile()+""));
//            }

            // 判断前6位为合法年月
            Matcher m = NUMBER_PATTERN.matcher(filename);
            if (!m.find()) {
                String errorMessage = "您选择文件前6位年月不合法!";
                System.out.println(filename+"\t"+errorMessage);
            }
        }

    }




    @Test
    public void testInfo5(){
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\123456.txt");
        for(String str:list){
            if(!str.trim().equals("")){
                StringBuilder sb = new StringBuilder();
                String[] ss = str.split("\t");
                sb.append(ss[0]+"\t");
                for(int i =0;i<ss.length;i++){
                    if(ss[i].contains("人评价")){
                        sb.append(ss[i-1]+"\t");
                        sb.append(ss[i].replace("(","").replace(")","").replace("人评价","")+"\t");
                        break;
                    }
                }
                System.out.println(sb.toString());

//                System.out.println(str);
            }
        }
    }
}
