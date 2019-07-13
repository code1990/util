import org.junit.Test;

public class Demo7 {
    @Test
    public void test() {
        StringBuilder sb = new StringBuilder();
        String[] strArray = "鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪".split("、");
        String[] tArray = new String[]{
                "甲子",
                "乙丑",
                "丙寅",
                "丁卯",
                "戊辰",
                "己巳",
                "庚午",
                "辛未",
                "壬申",
                "癸酉",
                "甲戌",
                "乙亥",
                "丙子",
                "丁丑",
                "戊寅",
                "己卯",
                "庚辰",
                "辛巳",
                "壬午",
                "癸未",
                "甲申",
                "乙酉",
                "丙戌",
                "丁亥",
                "戊子",
                "己丑",
                "庚寅",
                "辛卯",
                "壬辰",
                "癸巳",
                "甲午",
                "乙未",
                "丙申",
                "丁酉",
                "戊戌",
                "己亥",
                "庚子",
                "辛丑",
                "壬寅",
                "癸卯",
                "甲辰",
                "乙巳",
                "丙午",
                "丁未",
                "戊申",
                "己酉",
                "庚戌",
                "辛亥",
                "壬子",
                "癸丑",
                "甲寅",
                "乙卯",
                "丙辰",
                "丁巳",
                "戊午",
                "己未",
                "庚申",
                "辛酉",
                "壬戌",
                "癸亥"};
        int a =0;
        int b=0;
//        System.out.println(strArray.length);
////        System.out.println(tArray.length);
        for(int i =1;i<=2100;i++){
            //已知公元1年为鸡年 且为辛酉年 获取2100年内所有的时段
            if(i ==1){
                for (int j = 0; j <strArray.length ; j++) {
                    if("鸡".equals(strArray[j])){
                        a = j;
                        break;
                    }
                }
                for (int j = 0; j <tArray.length ; j++) {
                    if("辛酉".equals(tArray[j])){
                        b = j;
                        break;
                    }
                }
            }else{
                if(a++==strArray.length-1){
                    a=0;
                }
                if(b++==tArray.length-1){
                    b=0;
                }
            }
            if((i+1)%100==0){
//                System.out.println(i);
                sb.append(tArray[b]+"年"+","+strArray[a]+"\n");
                System.out.print(sb.toString());
                System.out.println("--------------------");
                sb.setLength(0);
            }else{
                sb.append(tArray[b]+"年"+","+strArray[a]+"\n");
            }
        }
    }


//    @Test
//    public void test
}
