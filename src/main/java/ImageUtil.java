import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 */
public class ImageUtil {

    @Test
    public void test(){
        String path = "F:\\pdf\\英语\\0000\\222\\000";
        File file = new File(path);
        if (file.exists() && file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (File tmp:listFiles){
                String filePath = tmp.getAbsolutePath();
                try {
                    splitImage( filePath,9,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public  void splitImage() throws IOException {

        //String originalImg = "C:\\img\\split\\a380_1280x1024.jpg";
        String filePath = "C:\\Users\\issuser\\Desktop\\tmp\\2020.png";
        // 读入大图
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);

        // 分割成4*4(16)个小图
        int rows = 20;
        int cols = 1;
        int chunks = rows * cols;

        // 计算每个小图的宽度和高度
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;

        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //设置小图的大小和类型
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                //写入图像内容
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0,
                        chunkWidth, chunkHeight,
                        chunkWidth * y, chunkHeight * x,
                        chunkWidth * y + chunkWidth,
                        chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }

        // 输出小图
        for (int i = 0; i < imgs.length; i++) {
            //ImageIO.write(imgs[i], "jpg", new File("C:\\img\\split\\img" + i + ".jpg"));
            String path = filePath.substring(0,filePath.lastIndexOf("\\"));
            System.out.println(path);
            String name = filePath.substring(filePath.lastIndexOf("\\")+1,filePath.length());
            System.out.println(name);
            System.out.println(path +"\\"+ name+"-"+i + ".png");
            ImageIO.write(imgs[i], "png", new File(path +"\\"+ name+"-"+i + ".png"));
        }
        new File(filePath).delete();
//        System.out.println("完成分割！");
    }

    public static void splitImage(String filePath,int rows,int cols) throws IOException {

        //String originalImg = "C:\\img\\split\\a380_1280x1024.jpg";
//        String originalImg = "C:\\Users\\issuser\\Desktop\\QQ截图20190614092347.png";
        // 读入大图
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);

        // 分割成4*4(16)个小图
//        int rows = 1;
//        int cols = 3;
        int chunks = rows * cols;

        // 计算每个小图的宽度和高度
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;

        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //设置小图的大小和类型
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                //写入图像内容
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0,
                        chunkWidth, chunkHeight,
                        chunkWidth * y, chunkHeight * x,
                        chunkWidth * y + chunkWidth,
                        chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }

        // 输出小图
        for (int i = 0; i < imgs.length; i++) {
            String path = filePath.substring(0,filePath.lastIndexOf("\\"));
            String name = filePath.substring(filePath.lastIndexOf("\\")+1,filePath.length());
            ImageIO.write(imgs[i], "png", new File(path +"\\"+ name+"-"+i + ".png"));
        }
//        file.deleteOnExit();
//        System.out.println("完成分割！");
    }

    @Test
    public void testInfo(){
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

    }
}
