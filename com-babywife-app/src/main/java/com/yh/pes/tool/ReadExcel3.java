package com.yh.pes.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel3 {

    public static void main(String[] args) throws IOException {
    	String filePath = "C:\\Users\\yangh\\Desktop\\1111.xlsx";
        String[] columns = {"name","type","mark"};
        String className = null;
        
        Workbook  wb = readExcel(filePath);
        
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {//循环输出每一个sheet
        	Sheet sheet = wb.getSheetAt(i);
        	className = sheet.getSheetName();
        	List<Map<String,String>> list = readExcelData(filePath,columns,wb,sheet);
            //遍历解析出来的list
            StringBuffer sb = new StringBuffer();
        	sb.append("public class "+className+"{\n\n");
            sb.append(appendPrivate(list));
            sb.append(getSet(list));
            sb.append("}");
            File  file1 = new File("C:\\Users\\yangh\\Desktop\\"+className+".java");
            FileOutputStream  fop = new FileOutputStream(file1);
            if (!file1.exists()) {
                file1.createNewFile();
            }
            byte[] contentInBytes = sb.toString().getBytes();
             
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            System.out.println("生成完成!");
		}
        
        

    }
    public static String forString(List<Map<String,String>> list) {
    	StringBuffer sb = new StringBuffer();
    	
		return null;
    	
    }
    /**
     * 打印私有属性
     * @param types
     * @param name
     * @param mark
     */
    public static String appendPrivate(List<Map<String,String>> list){
        StringBuffer sb = new StringBuffer();
        for (Map<String,String> map : list) {
        	String name = null;
        	String namet = null;
        	String type = null;
        	String mark = null;
            for (Entry<String,String> entry : map.entrySet()) {
                //System.out.print(entry.getKey()+":"+entry.getValue()+",");
            	
            	if("name".equals(entry.getKey())) {
            		name = entry.getValue();
            		namet = Underline2Camel.underline2Camel(name, true);
            	}
                if("type".equals(entry.getKey())) {
                	type = entry.getValue();
            	}
                if("mark".equals(entry.getKey())) {
                	mark = entry.getValue();
                }
               
            }
            sb.append(" /**\n");
            sb.append("   "+mark+"\n");
            sb.append(" **/\n");
            sb.append(" @XStreamAlias(\""+name+"\")"+"\n");
            sb.append(" private "+type+" "+namet+";\n\n");
            System.out.println();
        }
                
        return sb.toString();
    }
    /**
     * 打印get.set方法
     * @param types
     * @param name
     * @param mark
     * @return
     */
    public static String getSet(List<Map<String,String>> list){
                StringBuffer sb = new StringBuffer();
                
                for (Map<String,String> map : list) {
                	String name = null;
                	String namet = null;
                	String type = null;
                	String mark = null;
                	String attribute = null;
                    for (Entry<String,String> entry : map.entrySet()) {
                        //System.out.print(entry.getKey()+":"+entry.getValue()+",");
                    	
                    	if("name".equals(entry.getKey())) {
                    		name = entry.getValue();
                    		namet = Underline2Camel.underline2Camel(name, true);
                    		attribute = namet;
                            String c = String.valueOf(attribute.charAt(0)); 
                            c = c.toUpperCase(); 
                            attribute = c + attribute.substring(1);
                    	}
                        if("type".equals(entry.getKey())) {
                        	type = entry.getValue();
                    	}
                        if("mark".equals(entry.getKey())) {
                        	mark = entry.getValue();
                        }
                       
                    }
                    sb.append("/**\n");
                    sb.append(" * 设置"+mark+"\n");
                    sb.append(" * @param "+namet+" "+mark+"\n");
                    sb.append(" */\n");
                    sb.append(" public void set"+attribute+"("+type+" "+namet+"){\n");
                    sb.append("     this."+namet+" = "+namet+";\n");
                    sb.append(" }\n\n");
                    sb.append(" /**\n");
                    sb.append(" *获得"+mark+"\n");
                    sb.append(" * @return "+namet+" "+mark+"\n");
                    sb.append(" */\n");
                    sb.append(" public "+type+" get"+attribute+"(){\n");
                    sb.append("     return "+namet+";\n");
                    sb.append(" }\n\n");
             
                }
                
        return sb.toString();
    }
    /**
     * 获取excel数据
     * @param filePath
     * @return
     */
    public static List<Map<String, String>> readExcelData(String filePath,String[] columns,Workbook wb,Sheet sheet) {
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        wb = readExcel(filePath);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
            //ssheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }
        return list;
    	
    }
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC:{
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            }
            case Cell.CELL_TYPE_FORMULA:{
                //判断cell是否为日期格式
                if(DateUtil.isCellDateFormatted(cell)){
                    //转换为日期格式YYYY-mm-dd
                    cellValue = cell.getDateCellValue();
                }else{
                    //数字
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            case Cell.CELL_TYPE_STRING:{
                cellValue = cell.getRichStringCellValue().getString();
                break;
            }
            default:
                cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

}