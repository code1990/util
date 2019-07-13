import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
/**
 * 替代CSVReader 解决 基础数据维护--考核模块数据--BO数据维护--销售贡献  上传较大txt或者zip文件的数据读取问题
 * @author issuser
 *
 */
public class NIOReader {
    public static void main(String[] args) {
        List<String> list = NIOReader.read("C:\\Users\\issuser\\Desktop\\bakup\\导入测试\\201901销售贡献.txt");
        for (int i =0;i<list.size();i++){
            String a = list.get(i);
            if(a.contains("B1402") && a.contains("中国南方航空股份有限公司湖北公司航空路大客户组") && a.contains("665")){
                System.out.println(a);
            }

//            String sss= "";
//            String str1 = a.substring(0,a.indexOf(" "));
//            String str2 = a.substring(a.indexOf(" "),a.length());
////            System.out.println(str);
//            System.out.println(str1+"\t"+sss+"\t"+str2);
        }
//        System.out.println("1\ta art.一(个);每一(个);(同类事物中)任一个".indexOf(" "));
//        int x=0;
//        StringBuilder sb = new StringBuilder().append("");
//        for (int i =0;i<list.size();i++){
//            String ss = list.get(i);
//            String[] str = ss.split("\t");
//            for (int j=0;j < str.length;j++){
//                String s = str[j];
//                StringBuilder sb2 = new StringBuilder().append("");
//                if(i ==1){
//                    if (s.trim().contains(",")){
//                        sb.append(j+"\t");
//                    }
//
//                }else{
//
//                    if (s.trim().contains(",")){
//                        sb2.append(j+"\t");
//                    }
//                }
//                if(!sb2.toString().equals(sb.toString())){
//                    System.out.println(sb2.toString());
//                }
//
//            }
//
//
//        }
    }
	private static final String CHARSET = "GBK";
	
	public static List<String> read(String  path) {
        List<String> list = new ArrayList<String>();
        int bufferSize = 1000000;//一次读取的字节长度
        FileChannel fileChannel = null;
        try{
        	fileChannel = new RandomAccessFile(new File(path), "r").getChannel();
        }catch(Exception e){
        	e.printStackTrace();
        }
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
        return list;
    }

	public static List<String> readZip(String fileName) {
		List<String> list = new ArrayList<String>();
		List<File> fileList= unZip(fileName);
		//每一份文件的表头没有被删除  包含在内
		for(File file :fileList){
			System.out.println();
			List<String> tmpList = read(file.getAbsolutePath());
			list.addAll(0, tmpList);
		}
		//上传的文件保存在D:\OAS_Dev\oas_domestic_service\target\classes\201901.txt
		//文件解压以后先删除所有解压后的txt文件 然后删除父类文件夹  zip源文件导入成功后删除
		File parentFile = null;
		for(int i =0;i<fileList.size();i++){
			File file  = fileList.get(i);
			
			if(i==fileList.size()-1){
				parentFile=file.getParentFile();
			}
			file.deleteOnExit();
		}
		parentFile.deleteOnExit();
		return list;
	}
	
	//解压指定zip文件  获取解压后的文件路径
    public static List<File> unZip(String str) {//unZipfileName需要解压的zip文件名
        List<File> list = new ArrayList<File>();
        FileOutputStream fos;
        File file;
        InputStream inputStream;
        try {
            ZipFile zipFile = new ZipFile(new File(str), "GBK");

            for (Enumeration<ZipEntry> entries = zipFile.getEntries(); entries.hasMoreElements(); ) {
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
}
