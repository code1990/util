import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * excel工具类 所有的Excel表格按照文本文件形式读取 后台会统一接口处理
 * excel工具类 强调必须给定文件 给定流 无法判断文件的类型
 */
public class ExcelReader {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    private static final Pattern REGEX_NUMBER = Pattern.compile("([-,.0-9]*)");

    private static final String CHARSET = "GBK";

    public static List<String[]> read(File file) throws IOException {
        return toArray(file, 1);
    }

    public static List<String[]> readWithHead(File file) throws IOException {
        return toArray(file, 0);
    }

    public static List<String[]> readFromZip(File file) throws IOException {
        List<String[]> result = new ArrayList<>();
        List<File> fileList = ExcelReader.unZip(file.getAbsolutePath());
        for (File f : fileList) {
            List<String[]> list = toArray(f, 1);
            for (String[] array : list) {
                result.add(array);
            }
        }
        return result;
    }

    public static List<String[]> readWithHeadFromZip(File file) throws IOException {
        List<String[]> result = new ArrayList<>();
        List<File> fileList = ExcelReader.unZip(file.getAbsolutePath());
        for (File f : fileList) {
            List<String[]> list = toArray(f, 0);
            for (String[] array : list) {
                result.add(array);
            }
        }
        return result;
    }

    public static List<ExcelEntity> readExcel(File file) {

        // 根据后缀名称判断excel的版本
        String fileType = file.getName().split("\\.")[1];
        Workbook wb = null;
        if (XLS.equals(fileType)) {
            try {
                wb = new HSSFWorkbook(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (XLSX.equals(fileType)) {
            try {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 开始读取数据
        List<ExcelEntity> sheetList = new ArrayList<>();
        // 解析sheet
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            List<Object[]> dataList = new ArrayList<>();
            ExcelEntity entity = new ExcelEntity();
            entity.setSheetName(sheet.getSheetName());
            entity.setDataList(dataList);
            int readRowCount = sheet.getPhysicalNumberOfRows();
            // 解析sheet 的行
            for (int j = sheet.getFirstRowNum(); j < readRowCount; j++) {
                Row row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                if (row.getFirstCellNum() < 0) {
                    continue;
                }
                int readColumnCount = (int) row.getLastCellNum();
                List<String> rowValue = new LinkedList<String>();
                // 解析sheet 的列
                for (int k = 0; k < readColumnCount; k++) {
                    Cell cell = row.getCell(k);
                    //所有的Excel表格按照文本文件形式读取 后台会统一接口处理
                    //存在单元格为null 没有参数值 默认为""
                    if(cell==null){
                        rowValue.add("");
                    }else{
                        if(1!=cell.getCellType()){
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        String cellValue = cell.getStringCellValue();
                        //XLSX某些数字出现大量的科学计算法+小数
                        if(XLSX.equals(fileType)&&cellValue.contains(".") && cellValue.split("\\.")[1].length()>8){
                            cellValue= String.valueOf(new Double(cellValue));
                        }
                        rowValue.add(cellValue);
                    }
                }
                dataList.add(rowValue.toArray());
            }
            sheetList.add(entity);
        }
        return sheetList;
    }

    private static List<String[]> toArray(File file, int skipHeaderRows) throws IOException {
        List<String[]> result = new ArrayList<>();
        List<ExcelEntity> list = readExcel(file);
        for (ExcelEntity entity : list) {
            List<Object[]> dataList = entity.getDataList();
            for (int j = skipHeaderRows; j < dataList.size(); j++) {
                Object[] obj = dataList.get(j);
                String[] array = new String[obj.length];
                for (int i = 0; i < obj.length; i++) {
                    array[i] = obj[i].toString();
                }
                result.add(array);
            }
        }
        return result;
    }

    public static List<File> unZip(String str) {//unZipfileName需要解压的zip文件名
        List<File> list = new ArrayList<File>();
        FileOutputStream fos;
        File file;
        InputStream inputStream;
        try {
            ZipFile zipFile = new ZipFile(new File(str), CHARSET);

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
                    int readBytes = 0;
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

    public static String[] formatCSV(String str) {
        String[] array = str.split("\"");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            String temp = array[i];
            if (!",".equals(temp)) {

                Matcher isNum = REGEX_NUMBER.matcher(temp);
                if (!isNum.matches()) {
                    if (temp.startsWith(",")) {
                        temp = temp.substring(1, temp.length());
                    }
                    if (temp.endsWith(",")) {
                        temp = temp.substring(0, temp.length() - 1);
                    }
                    Collections.addAll(list, temp.split(","));
                } else {
                    /*,123.11,123.11,*/
                    if(temp.startsWith(",")||temp.endsWith(",")){
                        if (temp.startsWith(",")) {
                            temp = temp.substring(1, temp.length());
                        }
                        if (temp.endsWith(",")) {
                            temp = temp.substring(0, temp.length() - 1);
                        }
                        Collections.addAll(list, temp.split(","));
                    }else{
                        list.add(temp);
                    }
                }
            }
        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static class ExcelEntity {
        /**
         * sheet的名称
         */
        private String sheetName;
        /**
         * 表格标题
         */
        private String title;
        /**
         * 头部标题集合
         */
        private String[] headers;
        /**
         * 数据集合
         */
        private List<Object[]> dataList;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String[] getHeaders() {
            return headers;
        }

        public void setHeaders(String[] headers) {
            this.headers = headers;
        }

        public List<Object[]> getDataList() {
            return dataList;
        }

        public void setDataList(List<Object[]> dataList) {
            this.dataList = dataList;
        }

        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

        @Override
        public String toString() {
            return "ExcelEntity{" +
                    "sheetName='" + sheetName + '\'' +
                    ", title='" + title + '\'' +
                    ", headers=" + Arrays.toString(headers) +
                    ", dataList=" + dataList +
                    '}';
        }
    }
}


