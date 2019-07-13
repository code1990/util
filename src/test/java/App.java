import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private static final String CHINA = "(\\u4e00-\\u9fa5)";
    private static final String NUMBER = "(0-9)";
    private static final String CHAR = "(A-Za-z)";
    private static final String FILE_NAME_REDEX = "^\\([0-9]\\)$";

    public static void main(String[] args) {
        List<String> list22 = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\1233.txt");
//        System.out.println(tmp.split("\\.")[0]);
        for (String str : list22) {
            if (str.contains(")")) {
                int tmp = str.lastIndexOf(")");
                String tmpStr = str.substring(0, tmp + 1);
//                System.out.println(tmpStr);
                if (Pattern.matches(FILE_NAME_REDEX, tmpStr)) {
                    System.out.println(str);
                }
            }

//            System.out.println(str.replace(".","\t"));
//            if(str.split("\\.").length!=2){
//                System.out.println(str);
//            }
////            System.out.println(getStr(str));
////            System.out.println();
        }
//        System.out.println();
//        int k =1;
//        int x=0;
//        for(int j =0;j<list22.size();j++){
//            String str = list22.get(j);
//            x= map.get(k);
//            if(j<x){
//                System.out.println("Unit"+k+"\t"+str);
//            }else {
//                k++;
//                x = map.get(k);
//                System.out.println("Unit"+k+"\t"+str);
//            }
//        }
//        int i =0;
//        while (i<list22.size()){
////            System.out.println(list22.get(i));
//            StringBuilder sb = new StringBuilder();
//            sb.append(list22.get(i)+"\t");
//            sb.append(list22.get(i+1)+"\t");
//            sb.append(list22.get(i+2)+"\t");
//            sb.append(list22.get(i+3)+"\t");
//            sb.append(list22.get(i+4)+"\t");
//            i +=5;
//            System.out.println(sb.toString());
//        }
    }

    public static String getStr(String tmp) {
        if (tmp.contains(".")) {
            return tmp;
        }
        for (int i = 0; i < tmp.toCharArray().length; i++) {
            char c = tmp.toCharArray()[i];
            if (!"※".equals(c + "") && isChina(c)) {
                return tmp.substring(0, i) + "." + tmp.substring(i, tmp.length());
            }
        }
        return tmp;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isChina(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    @Test
    public void testInfo() {
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\123abcd.txt");
        List<String> list2 = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\1234abcd.txt");
        for (String str : list2) {
            for (String s : list) {
                if (s.contains(str)) {
                    System.out.println(s);
                }
            }

        }
    }

    @Test
    public void testInfo123() {
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\tmp.txt");
        int[] array = new int[30];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (String s : list) {
            for (int i : array) {
                if (s.startsWith(i + ".")) {
                    if (s.split("\\.").length == 3) {
                        sb3.append(s + "\n");
                    }
                    if (s.split("\\.").length == 2) {
                        sb2.append(s + "\n");
                    }
                }

            }
            if (s.contains("章")) {
                sb1.append(s + "\n");
            }

        }
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
        System.out.println(sb3.toString());
    }

    @Test
    public void testInfo555() {
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\1233.txt");
        List<String> list2 = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\12333.txt");
        for (String str : list2) {
            String key = str.split("\t")[0].trim().toLowerCase();
            boolean flag = false;
            for (String ss : list) {
                String kk = ss.split("\t")[0].trim().toLowerCase();
                if (key.equals(kk)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(str);
            }
        }
    }

    @Test
    public void testInfo111() {
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\tmp.txt");
        String[] array = "处理月\t承运年月\t出票年月\t承运日期\t出票日期\t提前出票分组\t始发责任区代码\t始发责任区名称\t销售责任区代码\t始发大区代码\t始发大区名称\t销售责任区名称\t销售单位属性\t销售大区代码\t销售大区名称\t考核责任区代码\t考核责任区名称\t销售大区属性\t考核单位属性\t考核大区代码\t考核大区名称\t考核大区属性\t海外站点单位\t本异地始发标识\t出票渠道\t渠道细分\t渠道种类\t代理人代号\t代理人名称\t代理人所属国家代码\t代理人所属国家\t代理人所在城市代码\t代理人所在城市名称\t直销/分销\t捆绑代理名称\t统签标识\t统签协议\t统签协议名称\t票面航程\t票面航程始发机场代码\t票面航程国际/国内标识\t直达/中转标识\t航线类型\t航线分区\t航线单位归属\t航线大区单位归属\t航段类型\t航段始发城市名称\t航段到达城市名称\t航段始发机场代码\t航段到达机场代码\t航段起止机场代码\t实际承运航班号\t运输子舱位\t销售子舱位\t实际运价基础\t运价折扣率分组\t运输座位等级\t销售座位等级\t高端舱位标识\t长航线标识\t航线代号\t航线机场代码全程\t航线管辖责任区\t国内/国际票\t本航/外航票\t票证种类\t数据来源渠道\t销售货币代码\t订座数据来源\t政府采购标识\t政府采购编码\t团散标识\t数据类型\t机型\t机号\t实际承运单位\tEMD类型代码\tEMD种类代码\tEMD用途代码\t旅客人数\t免票旅客\t分摊收入\t净收入\t额外手续费\t客标准手续费\t燃油费\t航空保险费（人民币）\t动态兑换免票还原收入".split("\t");
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < list.size(); ) {
            String str1 = list.get(i);
            String str2 = list.get(i + 1);
//            String str3 = list.get(i+2);
//            System.out.println(str2);
//            System.out.println(str3);
            map.put(str1.trim(), str2.trim());
            i += 3;
        }
        StringBuilder sb = new StringBuilder();
        for (String ss : array) {
            System.out.println(ss);
            sb.append(map.get(ss) + ",");
//                System.out.println(sb.toString());
        }
        System.out.println(sb.toString());


    }

    @Test
    public void testInfo1234(){
        String path = "F:\\4.尚硅谷全套JAVA教程--JavaEE阶段";
        for(File f:new File(path).listFiles()){
            String str = f.getName().replaceAll("尚硅谷","").replace("视频","").replace("教程","");
            System.out.println(str);
//            new File("I:\\000000000000000000000000000javaEE\\"+str).mkdirs();
        }
    }
}

