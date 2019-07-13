import com.spire.pdf.PdfDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
/**
 * spire.pdf.free
 * 实现PDF文件的处理
 * http://www.e-iceblue.cn/spirepdfjava/pdf-to-image-in-java.html
 */
public class PDFUtil {

    @Test
    public void testInfo(){
        String path = "C:\\Users\\issuser\\Desktop\\0\\20190506shuoshiniluqumingdan.pdf";
        File f = new File(path);
        if(f.getName().endsWith(".pdf")){
            String absPath = f.getAbsolutePath();
            String name = f.getName();

            System.out.println(absPath);
            System.out.println(name);
//                    System.out.println(absPath.replaceAll(name,""));
            toImage(absPath,name);
        }
    }
    public static void main(String[] args) {
        String str = "F:\\pdf\\英语\\0000\\222";
        if(new File(str).isDirectory()){
            for(File f :new File(str).listFiles()){
//                if(f.getName().contains("-Linux公社（www.Linuxidc.com）")){
//                    f.renameTo(new File(str+"\\"+f.getName().replace("-Linux公社（www.Linuxidc.com）","")));
//                }
                if(f.getName().endsWith(".pdf")){
                    String absPath = f.getAbsolutePath();
                    String name = f.getName();

                    System.out.println(absPath);
                    System.out.println(name);
//                    System.out.println(absPath.replaceAll(name,""));
                    toImage(absPath,name);
                }

            }
        }
//        String str12 = "E:\\github\\00000\\java1234\\【肖秀荣】三件套（高清电子版）\\三件套之三 2019肖秀荣1000题 上册 试题分册\\04.三件套之三 2019肖秀荣1000题 上册 试题分册【思修部分】.pdf\n"
//      toImage("E:\\github\\00000\\java1234\\【肖秀荣】三件套（高清电子版）\\三件套之三 2019肖秀荣1000题 上册 试题分册\\04.三件套之三 2019肖秀荣1000题 上册 试题分册【思修部分】.pdf","04.三件套之三 2019肖秀荣1000题 上册 试题分册【思修部分】.pdf");
    }

    /**
     * 文件的路径默认放在项目的根路径下面
     * @param path
     */
    public static void toImage(String path,String name) {
        //加载PDF文件
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile(path);

        //保存PDF的每一页到图片
        BufferedImage image;
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            image = doc.saveAsImage(i);
            new File(path.replaceAll(name,"")+"\\000").mkdirs();
            File file = new File(path.replaceAll(name,"")+"\\000\\"+name+String.format("%d.png", i));
            try {
                ImageIO.write(image, "PNG", file);
                System.out.println(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        doc.close();

    }

    public static List<String> toImages(String path, String name) {
        List<String> list = new ArrayList<String>();
        //加载PDF文件
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile(path);

        //保存PDF的每一页到图片
        BufferedImage image;
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            image = doc.saveAsImage(i);
            File file = new File(name+String.format("%d.png", i));
            try {
                ImageIO.write(image, "PNG", file);
                list.add(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        doc.close();
        return list;
    }
}
