package io;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author issuser
 * @description 读取文本文件
 */
public class TxtUtil {

	/**
	 * 给定文件目录读取 获取所有的文件内容
	 * 
	 * @param filePath
	 *            给定文件路径
	 * @return
	 */
	public static List<String> readTxt(String filePath) {
		List<String> resultList = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			resultList = new ArrayList<String>();
			File file = new File(filePath);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String str = "";

			while ((str = bufferedReader.readLine()) != null) {
//				if (null != str && !"".equals(str)) {
					resultList.add(str);
//				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return resultList;
	}

	/**
	 * 把内容写入文本文件
	 * 
	 * @param filePath
	 *            需要保存的目标文件的路径
	 * @param content
	 *            需要保存的文件内容
	 */
	public static void writeTxt(String filePath, String content) {
		FileWriter fw = null;
		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file);
			fw.write(content);
			fw.flush();
			fw.close();
			System.out.println("ok");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 对于文本文件内容过滤
	 * 
	 * @param list
	 *            需要被过滤的对象
	 * @return list 返回一个list
	 */
	public static List<String> filter(List<String> list) {
		List<String> lessList = null;
		if (null != list && !list.isEmpty()) {
			lessList = new ArrayList<String>();
			Set<String> set = new HashSet<String>();
			for (String str : list) {
				// 如果可以被存放 说明不重复
				if (set.add(str)) {
					lessList.add(str);
				}
			}
		}
		return lessList;
	}

	/**
	 * 获取文本文件中重复的内容
	 * 
	 * @param list
	 *            需要查看重复的数据
	 * @return 返回重复的数据
	 */
	public static List<String> filterLess(List<String> list) {
		List<String> lessList = null;
		if (null != list && !list.isEmpty()) {
			lessList = new ArrayList<String>();
			Set<String> set = new HashSet<String>();
			for (String str : list) {
				// 如果不可以被存放 说明重复 需要作为返回的内容
				if (!set.add(str)) {
					lessList.add(str);
				}
			}
		}
		return lessList;
	}

	/**
	 * 给定文件名
	 * 
	 * @param fileName
	 *            需要保存的文件名
	 * @param list
	 *            需要保存的内容
	 */
	public static void save(String fileName, List<String> list) {
		if (null == fileName || "".equals(fileName)) {
			fileName = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
		}
		String defaultDir = System.getProperty("user.dir");
		try {
			StringBuilder sb = new StringBuilder(20000);
			for (String str : list) {
				sb.append(str + "\n");
			}
			String path = defaultDir + File.separator + fileName + ".txt";
			TxtUtil.writeTxt(path, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
    	List<String> list = readTxt("C:\\Users\\issuser\\Desktop\\0\\123.txt");
//    	List<String> list2 = readTxt("C:\\Users\\issuser\\Desktop\\始发销售贡献\\销售贡献报表2019口径-OAS模板201901.txt");
    	Map<String,String> map = new HashMap<String,String>();
    	StringBuilder sb =new StringBuilder(2000);
    	StringBuilder sb2 =new StringBuilder(2000);
    	for(int i =0;i<list.size();i++){
			String str = list.get(i);
			System.out.print("=SUM("+str+"6)\t");
			sb.append("=AVERAGE("+str+"6,"+str+"8)\t");
			sb2.append("=SUM("+str+"6,"+str+"8)\t");
		}
		System.out.println(sb.toString());
		System.out.println(sb2.toString());
//		int sum =0;
//        for(int i =0;i<list2.size();i++){
//            System.out.println(list2.get(i));
////            sum = sum+Integer.parseInt(list2.get(i));
////            System.out.println(sum);
//        }
//        System.out.println(sum);

	}

	public static void sqlToJava(List<String> list) {
		for (String tmp : list) {
			String str = "";
			String str1 = "";
			str = tmp.substring(tmp.indexOf("'") + 1, tmp.indexOf(",") - 1);
			str1 = str;
			while (true) {
				if (str.indexOf("_") != -1) {
					int length = str.indexOf("_");
					String upper = (str.charAt(length + 1) + "").toUpperCase();
					str = str.substring(0, length) + upper
							+ str.substring(length + 2, str.length());
				} else {
					break;
				}
			}
			System.out.println(tmp.replace(str1, str));
		}
	}

	public static String sqlToJava(String str) {
		while (true) {
			if (str.contains("_")) {
				int length = str.indexOf("_");
				String upper = (str.charAt(length + 1) + "").toUpperCase();
				str = str.substring(0, length) + upper
						+ str.substring(length + 2, str.length());
			} else {
				break;
			}
		}
		return str;
	}

	public static String getter(String str){

	    return "get"+str.substring(0,1).toUpperCase()+str.substring(1,str.length())+"();";
    }

    public static String setter(String str){

        return "set"+str.substring(0,1).toUpperCase()+str.substring(1,str.length())+"("+str+");";
    }
}
