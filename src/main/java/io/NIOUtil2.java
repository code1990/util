import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
/**
 * NIO读取百万级别文件
 * @author Chillax
 *
 */
public class NIOUtil2 {
    private static final String CHARSET = "GBK";

    public static List<String[]> read(File file) throws Exception{
        List<String[]> list = new ArrayList<String[]>();
        int bufferSize = 1000000;//一次读取的字节长度
        FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
        ByteBuffer readBuffer = ByteBuffer.allocate(bufferSize);
        byte[] lineByte = new byte[0];

        try {
            //temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，
            //并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题
            byte[] temp = new byte[0];
            while (fileChannel.read(readBuffer) != -1) {//fileChannel.read(readBuffer)：从文件管道读取内容到缓冲区(readBuffer)
                int rSize = readBuffer.position();//读取结束后的位置，相当于读取的长度
                byte[] bs = new byte[rSize];//用来存放读取的内容的数组
                readBuffer.rewind();//将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法
                readBuffer.get(bs);//相当于readBuffer.get(bs,0,bs.length())：从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[bs.length-1]的区域
                readBuffer.clear();

                int startNum = 0;
                int LF = 10;//换行符
                int CR = 13;//回车符
                boolean hasLF = false;//是否有换行符
                for(int i = 1; i < rSize; i++){
                    if(bs[i] == LF){
                        hasLF = true;
                        int tempNum = temp.length;
                        int lineNum = i - startNum;
                        lineByte = new byte[tempNum + lineNum];//数组大小已经去掉换行符

                        System.arraycopy(temp, 0, lineByte, 0, tempNum);//填充了lineByte[0]~lineByte[tempNum-1]
                        temp = new byte[0];
                        System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);//填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]

                        String line = new String(lineByte, 0, lineByte.length, CHARSET);//一行完整的字符串(过滤了换行和回车)
                        list.add(line.split("\t"));

                        //过滤回车符和换行符
                        if(i + 1 < rSize && bs[i + 1] == CR){
                            startNum = i + 2;
                        }else{
                            startNum = i + 1;
                        }

                    }
                }
                if(hasLF){
                    temp = new byte[bs.length - startNum];
                    System.arraycopy(bs, startNum, temp, 0, temp.length);
                }else{//兼容单次读取的内容不足一行的情况
                    byte[] toTemp = new byte[temp.length + bs.length];
                    System.arraycopy(temp, 0, toTemp, 0, temp.length);
                    System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                    temp = toTemp;
                }
            }
            if(temp.length > 0){//兼容文件最后一行没有换行的情况
                String line = new String(temp, 0, temp.length, CHARSET);
                list.add(line.split("\t"));
                if(!line.contains("\t")){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String args[]) throws Exception {

        int bufferSize = 1000000;//一次读取的字节长度
        File file = new File("C:\\Users\\issuser\\Desktop\\始发销售贡献\\销售贡献报表2019口径-OAS模板201901.txt");//读取的文件
//        Date startDate = new Date();
//        FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
//        ByteBuffer readBuffer = ByteBuffer.allocate(bufferSize);

//        readFileByLine(bufferSize, fileChannel, readBuffer);
//        Date endDate = new Date();

//        System.out.print(startDate+"|"+endDate);//测试执行时间
//        if(fileChannel.isOpen()){
//            fileChannel.close();
//        }
        long start = System.currentTimeMillis();
        System.out.println();
        List<String[]> list=read(file);
        System.out.println(start-System.currentTimeMillis());
//        for (int i = 0; i < list.size(); i++) {
////            System.out.println(list.get(i));
//        }
        System.out.println(list.get(list.size()-1).length);
        for (int i = 0; i < list.get(0).length; i++) {
            System.out.println(list.get(0)[i]);
        }

        String str = "C:\\Users\\issuser\\Desktop\\始发销售贡献.zip";
//        ZipFile zipFile =  zipFile = new ZipFile(str,"GBK");
//        List<File> list = upzipFile(new File(str),"C:\\Users\\issuser\\Desktop\\始发销售贡献");
        ZipFile zipFile = null;
        InputStream zis = null;
        try {
            zipFile = new ZipFile(str, "GBK");
            Enumeration<? extends ZipEntry> enumeration = zipFile.getEntries();
            //只有一个文件
            while (enumeration.hasMoreElements()) {
                ZipEntry zipEntry = enumeration.nextElement();
                if (zipEntry.isDirectory()) {
                    continue;
                }
                zis = zipFile.getInputStream(zipEntry);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        BufferedReader br = new BufferedReader(new InputStreamReader(zis, CHARSET));
        unZip("C:\\Users\\issuser\\Desktop\\始发销售贡献.zip");
//        System.out.println(new File("C:\\Users\\issuser\\Desktop\\始发销售贡献.zip").getParentFile().getName());
    }
    //解压指定zip文件
    public static void unZip(String str){//unZipfileName需要解压的zip文件名
        FileOutputStream fos;
        File file;
        InputStream inputStream;

        try{
            ZipFile     zipFile = new ZipFile(new File(str),"GBK");

            for(Enumeration entries = zipFile.getEntries(); entries.hasMoreElements();){
                ZipEntry entry = (ZipEntry)entries.nextElement();

                System.out.println(entry.getName());
//                System.out.println(file.getAbsolutePath());
                System.out.println(entry.isDirectory());
                String tmp = str.substring(0,str.lastIndexOf("."));
                if(!new File(tmp).exists()){
                    new File(tmp).mkdirs();
                }
                file = new File(tmp+File.separator+entry.getName());
                if(entry.isDirectory()){
                    file.mkdirs();
                }
                else{
                    //如果指定文件的目录不存在,则创建之.

                    File parent = new File(file.getParent());
                    if(!parent.exists()){
                        parent.mkdirs();
                    }

                    inputStream = zipFile.getInputStream(entry);

                    fos = new FileOutputStream(file);
                    int   readedBytes;
                    byte[]    buf= new byte[1024*1024*1024];
                    while(( readedBytes= inputStream.read(buf) ) > 0){
                        fos.write(buf , 0 , readedBytes );
                    }
                    fos.close();

                    inputStream.close();
                }
            }
            zipFile.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void readFileByLine(int bufferSize, FileChannel fileChannel,  ByteBuffer readBuffer) {
//        String enter = "\n";
        List<String> list = new ArrayList<String>();//存储读取的每行数据
        byte[] lineByte = new byte[0];

        try {
            //temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，
            //并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题
            byte[] temp = new byte[0];
            while (fileChannel.read(readBuffer) != -1) {//fileChannel.read(readBuffer)：从文件管道读取内容到缓冲区(readBuffer)
                int rSize = readBuffer.position();//读取结束后的位置，相当于读取的长度
                byte[] bs = new byte[rSize];//用来存放读取的内容的数组
                readBuffer.rewind();//将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法
                readBuffer.get(bs);//相当于readBuffer.get(bs,0,bs.length())：从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[bs.length-1]的区域
                readBuffer.clear();

                int startNum = 0;
                int LF = 10;//换行符
                int CR = 13;//回车符
                boolean hasLF = false;//是否有换行符
                for(int i = 0; i < rSize; i++){
                    if(bs[i] == LF){
                        hasLF = true;
                        int tempNum = temp.length;
                        int lineNum = i - startNum;
                        lineByte = new byte[tempNum + lineNum];//数组大小已经去掉换行符

                        System.arraycopy(temp, 0, lineByte, 0, tempNum);//填充了lineByte[0]~lineByte[tempNum-1]
                        temp = new byte[0];
                        System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);//填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]

                        String line = new String(lineByte, 0, lineByte.length, CHARSET);//一行完整的字符串(过滤了换行和回车)
//                        if(!line.contains("\t")){
//                            System.out.println(line);
//                        }
                        list.add(line);


                        //过滤回车符和换行符
                        if(i + 1 < rSize && bs[i + 1] == CR){
                            startNum = i + 2;
                        }else{
                            startNum = i + 1;
                        }

                    }
                }
                if(hasLF){
                    temp = new byte[bs.length - startNum];
                    System.arraycopy(bs, startNum, temp, 0, temp.length);
                }else{//兼容单次读取的内容不足一行的情况
                    byte[] toTemp = new byte[temp.length + bs.length];
                    System.arraycopy(temp, 0, toTemp, 0, temp.length);
                    System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                    temp = toTemp;
                }
            }
            if(temp.length > 0){//兼容文件最后一行没有换行的情况
                String line = new String(temp, 0, temp.length, CHARSET);
                list.add(line);
                if(!line.contains("\t")){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写到文件上
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
        List<File> _list = new ArrayList<File>() ;
        try {
            ZipFile zipFile = new ZipFile(file , "GBK") ;
            Enumeration<? extends ZipEntry> enumeration = zipFile.getEntries();
            for(Enumeration entries = zipFile.getEntries(); entries.hasMoreElements() ; ){
                ZipEntry entry = (ZipEntry)entries.nextElement() ;
                File _file = new File(descDir + File.separator + entry.getName()) ;
                System.out.println(descDir + File.separator + entry.getName());
                if( entry.isDirectory() ){
                    _file.mkdirs() ;
                }else{
                    File _parent = _file.getParentFile() ;
                    if( !_parent.exists() ){
                        _parent.mkdirs() ;
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
                    _list.add(_file) ;
                }
            }
        } catch (Exception e) {
        }
        return _list ;
    }
}