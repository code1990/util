import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//import org.junit.Test;
public class TestClass {
    private static final String FILE_NAME_REDEX = "^\\([0-9]\\)$";

    public static void main(String[] args) {
        Object[] list22 = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\1233.txt").toArray();
        for (int i = 0; i < list22.length; i++) {
            String str = list22[i].toString();
            if (str.contains(")")) {
                int tmp = str.lastIndexOf(")");
                String tmpStr = str.substring(0, tmp + 1);
                if (Pattern.matches(FILE_NAME_REDEX, tmpStr)) {
//                    System.out.println(list22[(i+1)]);
//                    System.out.println(list22[(i+2)]);
//                    System.out.println(list22[(i+3)]);
//                    System.out.println(list22[(i+4)]);
                    list22[i + 1] = "A." + list22[(i + 1)];
                    list22[i + 2] = "B." + list22[(i + 2)];
                    list22[i + 3] = "C." + list22[(i + 3)];
                    list22[i + 4] = "D." + list22[(i + 4)];
//                    System.out.println(list22[(i+1)]);
//                    System.out.println(list22[(i+2)]);
//                    System.out.println(list22[(i+3)]);
//                    System.out.println(list22[(i+4)]);
                }
            }

//            System.out.println(str.replace(".","\t"));
//            if(str.split("\\.").length!=2){
//                System.out.println(str);
//            }
////            System.out.println(getStr(str));
////            System.out.println();
        }
        for (Object str : list22) {
            System.out.println(str);
        }
    }

    @org.junit.Test
    public void testInfo() {
        File file = new File("F:\\线性代数：汤神");
        for (File f : file.listFiles()) {
//            if(f.getName().endsWith(".rar")){
            String str = f.getName().replaceAll("微信公众号：考研微课 (考研资料每日更新·免费提供)", "").replaceAll("【微信公众号：考研微课】", "").replaceAll("—20考研数学基础课程线性代数", "");
            System.out.println(str);
//            f.renameTo(new File(str));
//                System.out.println(
//                        str.replaceAll("【微信公众号：考研微课 (考研资料每日更新·免费提供)】",""));
////                        );
//            }

        }
    }

    @org.junit.Test
    public void testInfo2() {
        List<String> list1 = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\bakup\\2019062401.txt");
        List<String> list2 = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\bakup\\2019062402.txt");
        System.out.println(list1.size());
        System.out.println(list2.size());
        for (int i = 0; i < list1.size(); i++) {
            String f = list1.get(i);
            String str = "";
            if (!list2.get(i).trim().equals("")) {
                str = "      记忆技巧:" + list2.get(i);
            }
            System.out.println("短语:" + f + str);
        }
    }

    @org.junit.Test
    public void testInfo3() {
        int x =1;
        List<String> tmpList = null;
        if(x==1){
            tmpList =  new ArrayList<String>();
        }
        List<String> logList = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            logList.add(""+i);
        }
         new ArrayList<String>();//new ArrayList<String>();
        tmpList.addAll(logList);
        System.out.println(tmpList.size());
//        response.setResultList(tmpList);
    }
}