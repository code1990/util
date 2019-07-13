import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class NIOUtil3 {
    private static final String CHARSET = "GBK";

    public static List<String[]> read(File file) throws Exception {
        List<String[]> list = new ArrayList<String[]>();
        int bufferSize = 1000000;//一次读取的字节长度
        FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
        ByteBuffer readBuffer = ByteBuffer.allocate(bufferSize);
        byte[] lineByte = new byte[0];

        try {
            byte[] temp = new byte[0];
            while (fileChannel.read(readBuffer) != -1) {//从文件管道读取内容到缓冲区(readBuffer)
                int readSize = readBuffer.position();//读取结束后的位置，相当于读取的长度
                byte[] byteArray = new byte[readSize];//用来存放读取的内容的数组
                readBuffer.rewind();//将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法
                readBuffer.get(byteArray);//从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[byteArray.length-1]的区域
                readBuffer.clear();

                int startNum = 0;
                int LF = 10;//换行符
                int CR = 13;//回车符
                boolean hasLF = false;//是否有换行符
                for (int i = 0; i < readSize; i++) {
                    if (byteArray[i] == LF) {
                        hasLF = true;
                        int tempNum = temp.length;
                        int lineNum = i - startNum;
                        lineByte = new byte[tempNum + lineNum];//数组大小已经去掉换行符

                        System.arraycopy(temp, 0, lineByte, 0, tempNum);//填充了lineByte[0]~lineByte[tempNum-1]
                        temp = new byte[0];
                        System.arraycopy(byteArray, startNum, lineByte, tempNum, lineNum);//填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]

                        String line = new String(lineByte, 0, lineByte.length, CHARSET);//一行完整的字符串(过滤了换行和回车)
                        list.add(line.split("\t"));

                        //过滤回车符和换行符
                        if (i + 1 < readSize && byteArray[i + 1] == CR) {
                            startNum = i + 2;
                        } else {
                            startNum = i + 1;
                        }

                    }
                }
                if (hasLF) {
                    temp = new byte[byteArray.length - startNum];
                    System.arraycopy(byteArray, startNum, temp, 0, temp.length);
                } else {//兼容单次读取的内容不足一行的情况
                    byte[] toTemp = new byte[temp.length + byteArray.length];
                    System.arraycopy(temp, 0, toTemp, 0, temp.length);
                    System.arraycopy(byteArray, 0, toTemp, temp.length, byteArray.length);
                    temp = toTemp;
                }
            }
            if (temp.length > 0) {//兼容文件最后一行没有换行的情况
                String line = new String(temp, 0, temp.length, CHARSET);
                list.add(line.split("\t"));
                if (!line.contains("\t")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String args[]) throws Exception {

//        int bufferSize = 1000000;//一次读取的字节长度
//        File file = new File("C:\\Users\\issuser\\Desktop\\始发销售贡献\\201901销售贡献报表201901.txt");//读取的文件
//        List<String[]> list=read(file);
//        for(String[] s:list){
//            System.out.println(Arrays.toString(s)+s.toString());
//        }
//        String str = "C:\\Users\\issuser\\Desktop\\始发销售贡献.zip";
        List<File> list = unZip("C:\\Users\\issuser\\Desktop\\始发销售贡献.zip");
        for(File file :list){
            System.out.println(file.getAbsolutePath());
            List<String[]> tmpList = read(file);
            for (int i = 0; i <tmpList.size() ; i++) {
                if ((i+1)%50000==0){
                    System.out.println("--------------");
                }
//                System.out.println(tmpList.get(i));
            }
        }

    }
    public static List<File> unZip(String str) {//unZipfileName需要解压的zip文件名
        List<File> list = new ArrayList<File>();
        FileOutputStream fos;
        File file;
        InputStream inputStream;
        try {
            ZipFile zipFile = new ZipFile(new File(str), "GBK");

            for (Enumeration entries = zipFile.getEntries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String tmp = str.substring(0, str.lastIndexOf("."));
                if (!new File(tmp).exists()) {
                    new File(tmp).mkdirs();
                }
                file = new File(tmp + File.separator + entry.getName());
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    //如果指定文件的目录不存在,则创建之.
                    File parent = new File(file.getParent());
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    inputStream = zipFile.getInputStream(entry);
                    fos = new FileOutputStream(file);
                    int readBytes=0;
                    byte[] buf = new byte[1024 * 1024 * 1024];
                    while ((readBytes = inputStream.read(buf)) > 0) {
                        fos.write(buf, 0, readBytes);
                    }
                    fos.close();
                    inputStream.close();
                }
                list.add(file);
            }
            zipFile.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }


    public static void readFileByLine(int bufferSize, FileChannel fileChannel, ByteBuffer readBuffer) {
//        String enter = "\n";
        List<String> list = new ArrayList<String>();//存储读取的每行数据
        byte[] lineByte = new byte[0];

        try {
            //temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，
            //并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题
            byte[] temp = new byte[0];
            while (fileChannel.read(readBuffer) != -1) {//fileChannel.read(readBuffer)：从文件管道读取内容到缓冲区(readBuffer)
                int readSize = readBuffer.position();//读取结束后的位置，相当于读取的长度
                byte[] byteArray = new byte[readSize];//用来存放读取的内容的数组
                readBuffer.rewind();//将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法
                readBuffer.get(byteArray);//相当于readBuffer.get(byteArray,0,byteArray.length())：从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[byteArray.length-1]的区域
                readBuffer.clear();

                int startNum = 0;
                int LF = 10;//换行符
                int CR = 13;//回车符
                boolean hasLF = false;//是否有换行符
                for (int i = 0; i < readSize; i++) {
                    if (byteArray[i] == LF) {
                        hasLF = true;
                        int tempNum = temp.length;
                        int lineNum = i - startNum;
                        lineByte = new byte[tempNum + lineNum];//数组大小已经去掉换行符

                        System.arraycopy(temp, 0, lineByte, 0, tempNum);//填充了lineByte[0]~lineByte[tempNum-1]
                        temp = new byte[0];
                        System.arraycopy(byteArray, startNum, lineByte, tempNum, lineNum);//填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]

                        String line = new String(lineByte, 0, lineByte.length, CHARSET);//一行完整的字符串(过滤了换行和回车)
//                        if(!line.contains("\t")){
//                            System.out.println(line);
//                        }
                        list.add(line);


                        //过滤回车符和换行符
                        if (i + 1 < readSize && byteArray[i + 1] == CR) {
                            startNum = i + 2;
                        } else {
                            startNum = i + 1;
                        }

                    }
                }
                if (hasLF) {
                    temp = new byte[byteArray.length - startNum];
                    System.arraycopy(byteArray, startNum, temp, 0, temp.length);
                } else {//兼容单次读取的内容不足一行的情况
                    byte[] toTemp = new byte[temp.length + byteArray.length];
                    System.arraycopy(temp, 0, toTemp, 0, temp.length);
                    System.arraycopy(byteArray, 0, toTemp, temp.length, byteArray.length);
                    temp = toTemp;
                }
            }
            if (temp.length > 0) {//兼容文件最后一行没有换行的情况
                String line = new String(temp, 0, temp.length, CHARSET);
                list.add(line);
                if (!line.contains("\t")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写到文件上
     *
     * @param fcout
     * @param wBuffer
     * @param line
     */
    @SuppressWarnings("static-access")
    public static void writeFileByLine(FileChannel fcout, ByteBuffer wBuffer,
                                       String line) {
        try {
            fcout.write(wBuffer.wrap(line.getBytes("UTF-8")), fcout.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<File> upzipFile(File file, String descDir) {
        List<File> _list = new ArrayList<File>();
        try {
            ZipFile zipFile = new ZipFile(file, "GBK");
            Enumeration<? extends ZipEntry> enumeration = zipFile.getEntries();
            for (Enumeration entries = zipFile.getEntries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File _file = new File(descDir + File.separator + entry.getName());
                System.out.println(descDir + File.separator + entry.getName());
                if (entry.isDirectory()) {
                    _file.mkdirs();
                } else {
                    File _parent = _file.getParentFile();
                    if (!_parent.exists()) {
                        _parent.mkdirs();
                    }
//                    InputStream _in = zipFile.getInputStream(entry);
//                    OutputStream _out = new FileOutputStream(_file) ;
//                    int len = 0 ;
//                    while( (len = _in.read(_byte)) > 0){
//                        _out.write(_byte, 0, len);
//                    }
//                    _in.close();
//                    _out.flush();
//                    _out.close();
                    _list.add(_file);
                }
            }
        } catch (Exception e) {
        }
        return _list;
    }
}