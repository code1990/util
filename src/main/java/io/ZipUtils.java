//import org.apache.tools.zip.ZipFile;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
//public class ZipUtils {
//    private static String basePath;
//
//    /**
//     * 解压文件
//     *
//     * @param unzip
//     * @throws IOException
//     */
//    public static void unzip(String unzip) throws IOException {
//        File file = new File(unzip);
//        basePath = file.getParent();
//        FileInputStream fis = new FileInputStream(file);
//        ZipInputStream zis = new ZipInputStream(fis);
////        unzip(zis);
//    }
//
//    private static void unzip(ZipInputStream zis) throws IOException {
//        ZipFile zipFile = new ZipFile(new File("C:\\Users\\issuser\\Desktop\\始发销售贡献.zip"),"GBK");
//        ZipEntry entry = zis.getNextEntry();
//        if (entry != null) {
//            File file = new File(basePath + File.separator + entry.getName());
//            if (file.isDirectory()) {
//                // 可能存在空文件夹
//                if (!file.exists())
//                    file.mkdirs();
//                unzip(zis);
//            } else {
//                File parentFile = file.getParentFile();
//                if (parentFile != null && !parentFile.exists())
//                    parentFile.mkdirs();
//                FileOutputStream fos = new FileOutputStream(file);// 输出流创建文件时必须保证父路径存在
//                int len = 0;
//                byte[] buf = new byte[1024];
//                while ((len = zis.read(buf)) != -1) {
//                    fos.write(buf, 0, len);
//                }
//                fos.flush();
//                fos.close();
//                zis.closeEntry();
//                unzip(zis);
//            }
//        }
//    }
//    private static void unzip() throws IOException {
//        ZipFile zipFile = new ZipFile(new File("C:\\Users\\issuser\\Desktop\\始发销售贡献.zip"),"GBK");
////        ZipEntry entry = zis.getNextEntry();
////        if (entry != null) {
//            File file = new File(basePath + File.separator + entry.getName());
//            if (file.isDirectory()) {
//                // 可能存在空文件夹
//                if (!file.exists())
//                    file.mkdirs();
//                unzip(zis);
//            } else {
//                File parentFile = file.getParentFile();
//                if (parentFile != null && !parentFile.exists())
//                    parentFile.mkdirs();
//                FileOutputStream fos = new FileOutputStream(file);// 输出流创建文件时必须保证父路径存在
//                int len = 0;
//                byte[] buf = new byte[1024];
//                while ((len = zis.read(buf)) != -1) {
//                    fos.write(buf, 0, len);
//                }
//                fos.flush();
//                fos.close();
//                zis.closeEntry();
//                unzip(zis);
//            }
//        }
//    }
//}