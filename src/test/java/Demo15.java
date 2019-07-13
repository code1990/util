import org.junit.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Demo15 {

    @Test
    public void testStream() throws Exception {
        FileInputStream in = new FileInputStream(new File(""));
        in.read();//方法：从输入流中读取数据的下一个字节。
        in.read("".getBytes());//从输入流中读入一定长度的字节,并以整数的形式返回字节数。
        in.mark(1);//方法：在输入流的当前位置放置一个标记。
        in.reset();//方法：将输入指针返回到当前所做的标记处。
        in.skip(1L);//方法：跳过输入流上的n个字节并返回实际跳过的字节数。
        in.markSupported();//方法：如果当前流支持markO/reset);//操作就返回true。
        in.close();//方法关闭此输入流并释放与该流关联的所有系统资源。
        FileOutputStream os = new FileOutputStream(new File(""));
        os.write(1);//方法：将指定的字节写入此输出流。
        os.write("".getBytes());//方法：将b个字节从指定的byte数组写入此输出流。
        os.write("".getBytes(), 1, 1);//方法：将指定byte数组中从偏移量off开始的len个字节写入此输出流。
        os.flush();//方法：彻底完成输出并清空缓存区。
        os.close();//方法：关闭输出流。

        /*字符流 弥补了字节流读取的不足*/
        FileWriter fw = new FileWriter(new File(""));
        FileReader fr = new FileReader(new File(""));


    }

    @Test
    public void testBuffer() throws Exception {
        /*缓冲流是高级流,对于低级流的封装 弥补性能的不足*/
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("")));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("")));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("")));
        BufferedReader br = new BufferedReader(new FileReader(new File("")));
        br.read();//方法：读取单个字符。
        br.readLine();//方法：读取一个文本行,并将其返回为字符串。若无数据可读,则返回null。
        bw.write("", 1, 1);//方法：写入字符串的某一部分。
        bw.flush();//方法：刷新该流的缓存。
        bw.newLine();//方法：写入一个行分隔符。

        /*DataInputStream类与DataOutputStream类）允许应用程序以与机器无关的方式从底层输入流中读取基本Java数据类型。*/
        DataInputStream dis = new DataInputStream(new FileInputStream(new File("")));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File("")));
        //3种写入字符串的方法
        dos.writeBytes("");
        dos.writeChars("");
        dos.writeUTF("");
        //返回字符串
        dis.readUTF();
    }

    @Test
    public void testFile() {
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts"));//构造方法1
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\", "hosts"));//构造方法2
        System.out.println(new File(new File("C:\\Windows\\System32\\drivers\\etc\\"), "hosts"));//构造方法3
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").getName());//获取文件的名称
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").canRead()); //判断文件是否为可读的
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").canWrite());//判断文件是否可被写入
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").exists());//判断文件是否存在
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").length()); //获取文件的长度(以字节为单位);//
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").getAbsolutePath());//获取文件的绝对路径
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").getParent());//获取文件的父路径
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").isFile()); //判断文件是否存在
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").isDirectory()); //判断文件是否为一个目录
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").isHidden()); //判断文件是否为隐藏文件
        System.out.println(new File("C:\\Windows\\System32\\drivers\\etc\\hosts").lastModified());//获取文件最后修改时间

    }

    @Test
    public void testZip() throws Exception {
        /*压缩文件*/
        new ZipOutputStream(new FileOutputStream(new File(""))).putNextEntry(new ZipEntry(""));//开始写一个新的ZipEntry,并将流内的位置移至此entry所指数据的开头
        new ZipOutputStream(new FileOutputStream(new File(""))).write("str".getBytes(), 1, 2);//将字节数组写入当前ZIP条目数据
        new ZipOutputStream(new FileOutputStream(new File(""))).finish();//完成写入ZIP输出流的内容,无须关闭它所配合的OutputStream
        new ZipOutputStream(new FileOutputStream(new File(""))).setComment("str");//可设置此ZIP文件的注释文字
        /*解压缩ZIP文件*/
        new ZipInputStream(new FileInputStream(new File(""))).read("str".getBytes(), 1, 2);//读取目标b数组内off偏移量的位置,长度是len字节
        new ZipInputStream(new FileInputStream(new File(""))).available();//判断是否已读完目前entry所指定的数据。已读完返回(),否则返回1
        new ZipInputStream(new FileInputStream(new File(""))).closeEntry();//关闭当前ZIP条目并定位流以读取下一个条目跳过
        new ZipInputStream(new FileInputStream(new File(""))).skip(1L);//当前ZIP条目中指定的字节数
        new ZipInputStream(new FileInputStream(new File(""))).getNextEntry();//读取下一个ZipEntry,并将流内的位置移至该entry所指数据的开头
        //new ZipInputStream(new FileInputStream(new File(""))).createZipEntry("");//以指定的name参数新建一个ZipEntry对象
        File file = new File("hello.zip");

        ZipInputStream zin;
        try {
            ZipFile zipFile = new ZipFile(file);
            zin = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = zin.getNextEntry();
            while ((entry = zin.getNextEntry()) != null && !entry.isDirectory()) {
                File tmp = new File("C:\\" + entry.getName());
                if (!tmp.exists()) {
                    OutputStream os = new FileOutputStream(tmp);
                    InputStream in = zipFile.getInputStream(entry);
                    int count = 0;
                    while ((count = in.read()) != -1) {
                        os.write(count);
                    }
                    os.close();
                    in.close();
                }
                zin.closeEntry();
                System.out.println(entry.getName() + "解压成功");
            }
            zin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
