package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.huayunworld.robot.conf.model.to.ImportExcelPatter;

  
public class ExcelUtil {  
    //默认单元格内容为数字时格式  
    private static DecimalFormat df = new DecimalFormat("0");  
    // 默认单元格格式化日期字符串   
    private static SimpleDateFormat sdf = new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss");   
    // 格式化数字  
    private static DecimalFormat nf = new DecimalFormat("0.00");    
    public static ArrayList<ArrayList<Object>> readExcel(File file){  
    	return readExcel(file, null);
    }
    
    public static ArrayList<ArrayList<Object>> readExcel(File file, String sheetName){  
        if(file == null){  
            return null;  
        }  
        if(file.getName().endsWith("xlsx")){  
            //处理ecxel2007  
            return readExcel2007(file, sheetName, -1);  
        }else{  
            //处理ecxel2003  
            return readExcel2003(file);  
        }  
    }
    
    
    
    /*  
     * @return 将返回结果存储在ArrayList内，存储结构与二位数组类似  
     * lists.get(0).get(0)表示过去Excel中0行0列单元格  
     */  
    public static ArrayList<ArrayList<Object>> readExcel2003(File file){  
        try{  
            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();  
            ArrayList<Object> colList;  
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));  
            HSSFSheet sheet = wb.getSheetAt(0);  
            HSSFRow row;  
            HSSFCell cell;  
            Object value;  
            for(int i = sheet.getFirstRowNum() , rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows() ; i++ ){  
                row = sheet.getRow(i);  
                colList = new ArrayList<Object>();  
                if(row == null){  
                    //当读取行为空时  
                    if(i != sheet.getPhysicalNumberOfRows()){//判断是否是最后一行  
                        rowList.add(colList);  
                    }  
                    continue;  
                }else{  
                    rowCount++;  
                }  
                for( int j = row.getFirstCellNum() ; j <= row.getLastCellNum() ;j++){  
                    cell = row.getCell(j);  
                    if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){  
                        //当该单元格为空  
                        if(j != row.getLastCellNum()){//判断是否是该行中最后一个单元格  
                            colList.add("");  
                        }  
                        continue;  
                    }  
                    switch(cell.getCellType()){  
                     case XSSFCell.CELL_TYPE_STRING:    
                            System.out.println(i + "行" + j + " 列 is String type");    
                            value = cell.getStringCellValue();    
                            break;    
                        case XSSFCell.CELL_TYPE_NUMERIC:    
                            if ("@".equals(cell.getCellStyle().getDataFormatString())) {    
                                value = df.format(cell.getNumericCellValue());    
                            } else if ("General".equals(cell.getCellStyle()    
                                    .getDataFormatString())) {    
                                value = nf.format(cell.getNumericCellValue());    
                            } else {    
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell    
                                        .getNumericCellValue()));    
                            }    
                            System.out.println(i + "行" + j    
                                    + " 列 is Number type ; DateFormt:"    
                                    + value.toString());   
                            break;    
                        case XSSFCell.CELL_TYPE_BOOLEAN:    
                            System.out.println(i + "行" + j + " 列 is Boolean type");    
                            value = Boolean.valueOf(cell.getBooleanCellValue());  
                            break;    
                        case XSSFCell.CELL_TYPE_BLANK:    
                            System.out.println(i + "行" + j + " 列 is Blank type");    
                            value = "";    
                            break;    
                        default:    
                            System.out.println(i + "行" + j + " 列 is default type");    
                            value = cell.toString();    
                    }// end switch  
                    colList.add(value);  
                }//end for j  
                rowList.add(colList);  
            }//end for i  
              
            return rowList;  
        }catch(Exception e){  
            return null;  
        }  
    }  
      
    public static ArrayList<ArrayList<Object>> readExcel2007(File file, String sheetName, int sheetIndex){  
        try{  
            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();  
            ArrayList<Object> colList;  
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));  
            XSSFSheet sheet = null;
            if(sheet == null && !StringUtils.isBlank(sheetName))  sheet = wb.getSheet(sheetName);
            if(sheet == null && sheetIndex != -1)  sheet = wb.getSheetAt(sheetIndex);
            
            
            //XSSFSheet sheet = wb.getSheetAt(0);  
            XSSFRow row;  
            XSSFCell cell;  
            Object value;  
            for(int i = sheet.getFirstRowNum() , rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows() ; i++ ){  
                row = sheet.getRow(i);  
                colList = new ArrayList<Object>();  
                if(row == null){  
                    //当读取行为空时  
                    if(i != sheet.getPhysicalNumberOfRows()){//判断是否是最后一行  
                        rowList.add(colList);  
                    }  
                    continue;  
                }else{  
                    rowCount++;  
                }  
                for( int j = row.getFirstCellNum() ; j <= row.getLastCellNum() ;j++){  
                    cell = row.getCell(j);  
                    if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){  
                        //当该单元格为空  
                        if(j != row.getLastCellNum()){//判断是否是该行中最后一个单元格  
                            colList.add("");  
                        }  
                        continue;  
                    }  
                    switch(cell.getCellType()){  
                     case XSSFCell.CELL_TYPE_STRING:    
                            System.out.println(i + "行" + j + " 列 is String type");    
                            value = cell.getStringCellValue();    
                            break;    
                        case XSSFCell.CELL_TYPE_NUMERIC:    
                            if ("@".equals(cell.getCellStyle().getDataFormatString())) {    
                                value = df.format(cell.getNumericCellValue());    
                            } else if ("General".equals(cell.getCellStyle()    
                                    .getDataFormatString())) {    
                                value = nf.format(cell.getNumericCellValue());    
                            } else {    
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell    
                                        .getNumericCellValue()));    
                            }    
                            System.out.println(i + "行" + j    
                                    + " 列 is Number type ; DateFormt:"    
                                    + value.toString());   
                            break;    
                        case XSSFCell.CELL_TYPE_BOOLEAN:    
                            System.out.println(i + "行" + j + " 列 is Boolean type");    
                            value = Boolean.valueOf(cell.getBooleanCellValue());  
                            break;    
                        case XSSFCell.CELL_TYPE_BLANK:    
                            System.out.println(i + "行" + j + " 列 is Blank type");    
                            value = "";    
                            break;    
                        default:    
                            System.out.println(i + "行" + j + " 列 is default type");    
                            value = cell.toString();    
                    }// end switch  
                    colList.add(value);  
                }//end for j  
                rowList.add(colList);  
            }//end for i  
              
            return rowList;  
        }catch(Exception e){  
            System.out.println("exception");  
            return null;  
        }  
    }  
    
      
    public static void writeExcel(ArrayList<ArrayList<Object>> result,String path){  
        if(result == null){  
            return;  
        }  
        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        for(int i = 0 ;i < result.size() ; i++){  
             HSSFRow row = sheet.createRow(i);  
            if(result.get(i) != null){  
                for(int j = 0; j < result.get(i).size() ; j ++){  
                    HSSFCell cell = row.createCell(j);  
                    cell.setCellValue(result.get(i).get(j).toString());  
                }  
            }  
        }  
        ByteArrayOutputStream os = new ByteArrayOutputStream();  
        try  
        {  
            wb.write(os);  
        } catch (IOException e){  
            e.printStackTrace();  
        }  
        byte[] content = os.toByteArray();  
        File file = new File(path);//Excel文件生成后存储的位置。  
        OutputStream fos  = null;  
        try  
        {  
            fos = new FileOutputStream(file);  
            fos.write(content);  
            os.close();  
            fos.close();  
        }catch (Exception e){  
            e.printStackTrace();  
        }             
    }  
    
    
    
    public static void writeMarkExcel(File file, List<ImportExcelPatter> data) throws Exception{  
        if(file == null || data == null || data.size() == 0){  
            return;  
        }  
        
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));  
        XSSFSheet sheet = wb.createSheet("标注-" + System.currentTimeMillis());
        
        int i = 0;
        int j = 0;
        XSSFRow row = sheet.createRow(i++);
        XSSFCell cell =null;
        
        //基础样式：边框
        CellStyle style1 = wb.createCellStyle();
        style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
//        style1.setTopBorderColor(IndexedColors.BLACK.index);
//        style1.setBottomBorderColor(IndexedColors.BLACK.index);
//        style1.setLeftBorderColor(IndexedColors.BLACK.index);
//        style1.setRightBorderColor(IndexedColors.BLACK.index);
        
        
        
        //异常的样式，背景标红
        CellStyle style2 = wb.createCellStyle();
        style2.cloneStyleFrom(style1);
        style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  //填充单元格
//        style2.setFillForegroundColor(IndexedColors.RED.index);    //填红色
        
        //意图的样式：有换行
        CellStyle style3 = wb.createCellStyle();	
        style3.cloneStyleFrom(style1);
        style3.setWrapText(true);
        
        //意图的样式：有颜色，有换行
        CellStyle style4 = wb.createCellStyle();	
        style4.cloneStyleFrom(style2);
        style4.setWrapText(true);
        
        
        //标题的样式
        CellStyle style5 = wb.createCellStyle();	
        style5.cloneStyleFrom(style1);
        style5.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  
//        style5.setFillBackgroundColor(IndexedColors.BLUE_GREY.index);
//        style5.setFillForegroundColor(IndexedColors.WHITE.index);
        
    	
        //序号、分类、话术标题、机器人话术、意图
        cell = row.createCell(j++); cell.setCellValue("序号");  cell.setCellStyle(style5);
        cell = row.createCell(j++); cell.setCellValue("分类");  cell.setCellStyle(style5);
        cell = row.createCell(j++); cell.setCellValue("话术标题");  cell.setCellStyle(style5);
        cell = row.createCell(j++); cell.setCellValue("机器人话术");  cell.setCellStyle(style5);
        cell = row.createCell(j++); cell.setCellValue("意图");  cell.setCellStyle(style5);
        cell = row.createCell(j++); cell.setCellValue("问题");  cell.setCellStyle(style5);
        
        for(ImportExcelPatter p : data){
        	Map<String, String> map = p.getErrorInfo();
        	if(map == null) map = new HashMap<String, String>();
        	j = 0;
        	row = sheet.createRow(i++);
        	cell = row.createCell(j++); cell.setCellValue(p.getXuHao());cell.setCellStyle(map.containsKey("xuHao") ? style2 : style1);
        	cell = row.createCell(j++); cell.setCellValue(p.getFenLei());cell.setCellStyle(map.containsKey("fenLei") ? style2 : style1);
        	cell = row.createCell(j++); cell.setCellValue(p.getBiaoTi());cell.setCellStyle(map.containsKey("biaoTi") ? style2 : style1);
        	cell = row.createCell(j++); cell.setCellValue(p.getHuaShu());cell.setCellStyle(map.containsKey("huaShu") ? style2 : style1);
        	cell = row.createCell(j++); cell.setCellValue(p.getYiTu());cell.setCellStyle(map.containsKey("yiTu") ? style4 : style3);
        	
        	String err = "";
        	for(String key : map.keySet()){
        		if("fenLei".equals(key)) err += "分类：" + map.get(key) + "\r";
        		if("biaoTi".equals(key)) err += "话术标题：" + map.get(key) + "\r";
        		if("huaShu".equals(key)) err += "机器人话术：" + map.get(key) + "\r";
        		if("yiTu".equals(key)) err += "意图：" + map.get(key) + "\r";
        	}
        	cell = row.createCell(j++); cell.setCellValue(err);cell.setCellStyle(style1);
        }
        
        
        for(int m=0;m<6;m++){
        	sheet.autoSizeColumn((short)m);
        }
        
        
        
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();  
        try  
        {  
            wb.write(os);  
        } catch (IOException e){  
            e.printStackTrace();  
        }  
        byte[] content = os.toByteArray();  
        OutputStream fos  = null;  
        try  
        {  
            fos = new FileOutputStream(file);  
            fos.write(content);  
            os.close();  
            fos.close();  
        }catch (Exception e){  
            e.printStackTrace();  
        }             
    }  
      
    public static DecimalFormat getDf() {  
        return df;  
    }  
    public static void setDf(DecimalFormat df) {  
        ExcelUtil.df = df;  
    }  
    public static SimpleDateFormat getSdf() {  
        return sdf;  
    }  
    public static void setSdf(SimpleDateFormat sdf) {  
        ExcelUtil.sdf = sdf;  
    }  
    public static DecimalFormat getNf() {  
        return nf;  
    }  
    public static void setNf(DecimalFormat nf) {  
        ExcelUtil.nf = nf;  
    }  
      
      
      
}  