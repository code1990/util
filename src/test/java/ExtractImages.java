import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExtractImages {

    public static void main(String[] args) throws IOException {

        //创建PdfDocument对象
        PdfDocument doc = new PdfDocument();

        //加载PDF文档
        doc.loadFromFile("C:\\Users\\issuser\\Desktop\\196.pdf");

        //定义一个int型变量
        int index = 0;

        System.out.println(doc.getPages().getCount());
        //遍历PDF中的页
        for (PdfPageBase page : (Iterable< PdfPageBase >) doc.getPages()) {

            //使用extractImages方法获取指定页上图片
            for (BufferedImage image : page.extractImages()) {

                //指定输出文件路径及名称
                File output = new File("C:\\Users\\issuser\\Desktop\\img\\" + String.format("Image_%d.png", index++));

                //将图片保存为PNG格式文件
                ImageIO.write(image, "PNG", output);
            }
        }
    }
}