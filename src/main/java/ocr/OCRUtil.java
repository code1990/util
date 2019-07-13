package ocr;
import io.TxtUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class OCRUtil {
    public static void main(String[] args) {
//        String path = "E:\\github\\00000\\java1234\\【肖秀荣】三件套（高清电子版）\\三件套之三 2019肖秀荣1000题 上册 试题分册\\03.三件套之三 2019肖秀荣1000题 上册 试题分册【史纲部分】.pdf";
//        List<String> list = PDFUtil.toImages(path,null);
//        for (int i = 0; i < list.size(); i++) {
//            String str = list.get(i);
//            String content = BaiduOCR.ocrImage(str);
//            System.out.println(content);
//        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 46; i++) {
//            for (int j = 0; j <3 ; j++) {
            String str = "20190506shuoshiniluqumingdan.pdf" + i + ".png";
            sb.append(BaiduOCR.ocrImage("C:\\Users\\issuser\\Desktop\\0\\000\\" + str) + "\n-----\n");
            System.out.println(i);
//            }

        }
        System.out.println(sb.toString());
//        StringBuilder sb = new StringBuilder();
//        File[] files= new File("C:\\Users\\issuser\\Desktop\\11").listFiles();
//        for (int i = 0; i < files.length; i++) {
//            String str = files[i].getAbsolutePath();
//            sb.append(BaiduOCR.ocrImage(str)+"\n-----\n");
////            System.out.println(str);
//            System.out.println(i);
//        }
//        System.out.println(sb.toString());
    }

    @Test
    public void testOne() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 44; i++) {
            for (int j = 0; j < 2; j++) {
                String str = "单词补充.pdf" + i + ".png-" + j + ".png";
                sb.append(BaiduOCR.ocrImage("C:\\Users\\issuser\\Desktop\\bigdata\\111\\111\\" + str) + "\n-----\n");
                System.out.println(i);
            }
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testTwo() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 81; i++) {
            for (int j = 0; j < 2; j++) {
                String str = "5498.pdf" + i + ".png-" + j + ".png";
                sb.append(BaiduOCR.ocrImage("F:\\pdf\\英语\\0000\\222\\000\\" + str) + "\n-----\n");
                System.out.println(i);
            }
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testInfo() {
        String str = "C:\\Users\\issuser\\Desktop\\1";
        System.out.println("***************");
        for (File f : new File(str).listFiles()) {
//            System.out.println(f.listFiles().length);
            for (File ff : f.listFiles()) {
                System.out.println(ff.getAbsolutePath() + "=============================");
//                System.out.println(BaiduOCR.ocrImage(ff.getAbsolutePath()));
            }


        }
    }

    public static String[] getSortArray(String path) {
        String[] array = new String[80];
        File[] files = new File(path).listFiles();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (!f.getName().contains("-")) {
                int index = new Integer(f.getName().replace(".PNG", ""));
                array[index] = f.getAbsolutePath();
            } else {
                sb.append(f.getAbsolutePath() + ";");
            }
        }
        String[] tmp = sb.toString().split(";");
        for (int i = 50; i < tmp.length + 50; i++) {
            array[i] = tmp[i - 50];
        }
        return array;
    }

    @Test
    public void test1() {
        String path = "C:\\Users\\issuser\\Desktop\\1\\test01";
        String[] array = getSortArray(path);
        System.out.println("***************");
        for (String s : array) {
            if (null != s) {
                System.out.println(s);
                System.out.println(BaiduOCR.ocrImage(s));
            }

        }
    }

    @Test
    public void readBook() {
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\tmp\\book.txt");
        for (String s : list) {
            System.out.println(s);
            System.out.println(BaiduOCR.ocrImage(s));
        }
    }

    @Test
    public void readBook2() {
        String path =
                "C:\\Users\\issuser\\Desktop\\2020.png";
        System.out.println(BaiduOCR.ocrImage(path));
    }

}
