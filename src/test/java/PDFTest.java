import com.spire.pdf.PdfDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class PDFTest {
    public static void main(String[] args) throws IOException {
        //加载PDF文件
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile("C:\\Users\\issuser\\Desktop\\196.pdf");

        //保存PDF的每一页到图片
        BufferedImage image;
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            image = doc.saveAsImage(i);
            File file = new File( String.format("ToImage-img-%d.png", i));
            ImageIO.write(image, "PNG", file);
        }

        doc.close();
    }


    @Test
    public void pdfTest() {
        try {
            // 是否排序
            boolean sort = false;
            // 开始提取页数
            int startPage = 1;
            // 结束提取页数
            int endPage = Integer.MAX_VALUE;
            String content = null;
            PrintWriter writer = null;
            //pdf文本路径
            String path = "E:\\github\\00000\\java1234\\【肖秀荣】三件套（高清电子版）\\三件套之三 2019肖秀荣1000题 上册 试题分册\\02.三件套之三 2019肖秀荣1000题 上册 试题分册【毛中特部分】（小白考研）.pdf";
            //输出txt文本路径
            String target = "E:\\github\\00000\\java1234\\【肖秀荣】三件套（高清电子版）\\三件套之三 2019肖秀荣1000题 上册 试题分册\\02.txt";
            PDDocument document = PDDocument.load(new File(path));
            PDFTextStripper pts = new PDFTextStripper();
            endPage = document.getNumberOfPages();
            System.out.println("Total Page: " + endPage);
            pts.setStartPage(startPage);
            pts.setEndPage(endPage);
            try {
                //content就是从pdf中解析出来的文本
                content = pts.getText(document);
                System.out.println(content);
                writer = new PrintWriter(new FileOutputStream(target));
                writer.write(content);// 写入文件内容
                writer.flush();
                writer.close();
            } catch (Exception e) {
                throw e;
            } finally {
                if (null != document)
                    document.close();
            }
            System.out.println("Get PDF Content ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
