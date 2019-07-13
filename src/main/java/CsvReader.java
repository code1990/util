import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**
 * Csv读取器实现类
 * @author hurunhua
 *
 */ 
public class CsvReader {
	private static final String CHARSET = "GBK";
	public static List<String[]> read(InputStream inputStream) throws IOException {
		return toArray(inputStream, "\t", 1);
	}

	public static List<String[]> read(File file) throws IOException {
		InputStream inputStream = null;
		List<String[]> result = null;
		
		try{
			inputStream = new FileInputStream(file);
			result = toArray(inputStream, "\t", 1);
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
		}
		
		return result;
	}
	
	public static List<String[]> readNoHead(File file)throws IOException{
		InputStream inputStream = null;
		List<String[]> result = null;
		
		try{
			inputStream = new FileInputStream(file);
			result = toArray(inputStream, ",", 0);
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
		}
		
		return result;
	}
	
	public static List<String[]> readNoHead(InputStream inputStream)throws IOException{
		List<String[]> result = null;
		try{
			result = toArray(inputStream, ",", 0);
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
		}
		
		return result;
	}
	
	 
	public static List<String[]> readFromZip(File file)
			throws IOException {
		ZipFile zipFile = new ZipFile(file, "GBK");
		Enumeration<ZipEntry> enumeration = zipFile.getEntries();
		InputStream inputStream = null;
		List<String[]> result = null;

		try{
			while(enumeration.hasMoreElements()){
				ZipEntry zipEntry = enumeration.nextElement();
				if(zipEntry.isDirectory()){
					continue;
				}
				
				inputStream = zipFile.getInputStream(zipEntry);
				result = toArray(inputStream, "\t", 1);
				break;
			}
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
			
			zipFile.close();
		}
		
		return result;
	}

 
	public static List<Map<String, String>> readWithHeader(InputStream inputStream)
			throws IOException {
		return toMap(inputStream, "\t", 1);
	}

	 
	public static List<Map<String, String>> readWithHeader(File file)
			throws IOException {
		InputStream inputStream = null;
		List<Map<String, String>> result = null;
		
		try{
			inputStream = new FileInputStream(file);
			result = toMap(inputStream, "\t", 1);
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
		}
		
		return result;
	}

	 
	public static List<Map<String, String>> readWithHeaderFromZip(File file)
			throws IOException {
		ZipFile zipFile = new ZipFile(file, "GBK");
		Enumeration<ZipEntry> enumeration = zipFile.getEntries();
		InputStream inputStream = null;
		List<Map<String, String>> result = null;

		try{
			while(enumeration.hasMoreElements()){
				ZipEntry zipEntry = enumeration.nextElement();
				if(zipEntry.isDirectory()){
					continue;
				}
				
				inputStream = zipFile.getInputStream(zipEntry);
				result = toMap(inputStream, "\t", 1);
				break;
			}
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
			
			zipFile.close();
		}
		
		return result;
	}
	
	public static InputStream getInputSteamFromZip(File file)
			throws IOException {
		ZipFile zipFile = new ZipFile(file, "GBK");
		Enumeration<ZipEntry> enumeration = zipFile.getEntries();
		InputStream inputStream = null;

		try{
			while(enumeration.hasMoreElements()){
				ZipEntry zipEntry = enumeration.nextElement();
				if(zipEntry.isDirectory()){
					continue;
				}
				
				inputStream = zipFile.getInputStream(zipEntry);
				
			}
		} finally {
			
		}
		
		return inputStream;
	}
	
	public static InputStream getInputSteamFromZip(ZipFile zipFile)
			throws IOException {
		InputStream ins=null;
		try{
			Enumeration<? extends ZipEntry> enumeration=zipFile.getEntries();
			while(enumeration.hasMoreElements()){
				ZipEntry zipEntry = enumeration.nextElement();
				if(zipEntry.isDirectory()){
					continue;
				}
				
				ins = zipFile.getInputStream(zipEntry);
			}
		} finally {
			
		}
		
		return ins;
	}
	
	
	private static List<String[]> toArray(InputStream inputStream, String split, int skipHeaderRows) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
		List<String[]> result = new ArrayList<String[]>();
		
		//跳过表头
		for(int i=0; i<skipHeaderRows; i++){
			br.readLine();
		}
		
		String temp = null;
		
		while((temp = br.readLine()) != null){
			String[] columns = temp.split(split);
			result.add(columns);
		}
				
		br.close();
		return result;
	}

	private static List<Map<String, String>> toMap(InputStream inputStream, String split,int skipHeaderRows) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		//跳过表头
		for(int i=0; i<skipHeaderRows; i++){
			br.readLine();
		}
		
		String temp = br.readLine();
		String[] header = temp.split(split);
		
		while((temp = br.readLine()) != null){
			String[] columns = temp.split(split);
			Map<String, String> row = new HashMap<String, String>();
			
			for(int i=0; i<header.length && i<columns.length; i++){
				row.put(header[i], columns[i]);
			}
			
			result.add(row);
		}
		
		br.close();
		return result;
	}

	 
	public static List<String[]> read(InputStream inputStream, String split, int skipHeaderRows)
			throws IOException {
		return toArray(inputStream, split, skipHeaderRows);
	}
	public static List<String> read(InputStream inputStream, int skipHeaderRows)
			throws IOException {
		return toString(inputStream, skipHeaderRows);
	}
	
	public static List<String> read(BufferedReader br, int skipHeaderRows,int readRowSize,int readIndex)
			throws IOException {
		return toString(br, skipHeaderRows, readRowSize,readIndex);
	}
	
	public static List<String> read2(InputStream inputStream, int skipHeaderRows)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream, CHARSET));
		List<String> list = new ArrayList<String>();

		// 跳过表头
		for (int i = 0; i < skipHeaderRows; i++) {
			br.readLine();
		}
		String temp = null;

		while ((temp = br.readLine()) != null) {
			String columns = temp;
			list.add(temp);
		}
		return list;
	}
	private static List<String> toString(InputStream inputStream,int skipHeaderRows) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream, CHARSET));
		List<String> list = new ArrayList<String>();

		// 跳过表头
		for (int i = 0; i < skipHeaderRows; i++) {
			br.readLine();
		}
		String temp = null;

		while ((temp = br.readLine()) != null) {
			String columns = temp;
			list.add(temp);
		}
		br.close();
		return list;

	}
	
	private static List<String> toString(BufferedReader br,int skipHeaderRows,int readRowSize,int readIndex) throws IOException{
		List<String> list = new ArrayList<String>();

		// 跳过表头
		if(readIndex ==0){//如果是第一次，才进去操作
			for (int i = 0; i < skipHeaderRows; i++) {
				br.readLine();
			}
		}
		
		String temp = null;

		while ((temp = br.readLine()) != null) {
			String columns = temp;
			list.add(temp);
		}
		br.close();
		return list;

	}
}
