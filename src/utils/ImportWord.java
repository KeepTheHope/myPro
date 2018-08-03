package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ImportWord {
	/**
	 * 读取搜狗词库生insert语句
	 * @throws IOException 
	 */
	public static void genWord01() throws IOException{
		String dir = "D:/搜狗词库/";
		String newFile = "ok2.txt";
		Collection<File> listFiles = FileUtils.listFiles(new File(dir), FileFilterUtils.suffixFileFilter("txt"), DirectoryFileFilter.INSTANCE);
        if (listFiles==null) return;
        List<String> dictSql = new ArrayList<String>();
        List<String> wordSql = new ArrayList<String>();
        String entId = "ROBOT3";
        
        for (File file : listFiles) {
        	String dict_id = CommonTools.getRandomId();
        	String name = file.getName().replaceAll("\\.txt", "");
        	String parent_id = "58744133182784743603229321848069";
        	String en_name = "en_" + dict_id;
        	dictSql.add("INSERT INTO rbc_slot_dict (dict_id, name, parent_id, sort, en_name, type, ent_id)"
        			+ " VALUES ('" + dict_id + "', '" + name + "', '" + parent_id + "', null, '" + en_name + "', '0', '" + entId + "');");
        	
        	List<String> lines = FileUtils.readLines(new File(dir + "/" + file.getName()), "UTF-8");
        	for(String l : lines){
        		
        		wordSql.add("INSERT INTO rbc_slot_word (word_id, dict_id, name, alias, create_time, create_user_id, ent_id)"
            			+ " VALUES ('" + CommonTools.getRandomId8() + "', '" + dict_id + "', '" + l.trim() + "', null, '2018-05-21 15:41:32', 'admin', '" + entId + "');");
        	}
        }
        
        
       /*
        System.out.println("--------------------------------------------------");
        for(String s : dictSql){
        	System.out.println(s);
        }
        System.out.println("--------------------------------------------------");
        for(String s : wordSql){
        	System.out.println(s);
        }
        */
        
        
        
        List<String> ok = new ArrayList<String>();
        ok.add("--------------------------------------------");
        ok.addAll(dictSql);
        ok.add("--------------------------------------------");
        ok.addAll(wordSql);
        FileUtils.writeLines(new File(dir + "/" + newFile), ok);
        System.out.println("导出OK");
	}
	public static void main(String[] args) throws IOException {
		genWord01();
	}
	
	
//	/**
//	 * 从词库中获取指定类型的词
//	 * @throws Exception 
//	 */
//	public static void genWord02() throws Exception{
//		System.out.println("开始生成sql");
//		File f = new File("D:\\work\\workspace\\NLP\\ANSJ\\ansj_seg-master\\library\\default.dic");
//		File conf = new File("D:\\work\\workspace\\rbc_web\\doc\\词库\\导入词库");
//		String dir = "D:\\work\\workspace\\rbc_web\\doc\\词库\\文件";
//		FileUtils.deleteQuietly(new File(dir));
//		
//		 
//		 
//		
//		List<String> line = FileUtils.readLines(f);
//		String[] arr = null;
//		
//		String parentId = "5972952160";
//		String entId = "ROBOT3";
//		List<String> confline = FileUtils.readLines(conf);
//		List<String> sql = new ArrayList<String>();
//		String fileName = null;
//		String dict_id1 = null;
//		String dict_id2 = null;
//		String name = null;
//		String en_name = null;
//		String[] larr = null;
//		Set<String> set = new HashSet<String>();
//		for(String cline : confline){
//			if(cline.startsWith("#")){
//				if(sql.size() > 0){
//					//写文件
//					 FileUtils.writeLines(new File(dir + "/" + fileName), sql);
//					 System.out.println("写文件：" + fileName + "\t记录：" + sql.size());
//				}
//				
//				
//				//发现一个目录
//				//#动词
//				name = cline.replaceAll("#", "").trim();
//				sql.clear();
//				dict_id1 = CommonTools.getRandomId8();
//				en_name = "en_" + dict_id1;
//				sql.add("INSERT INTO rbc_slot_dict (dict_id, name, parent_id, sort, en_name, type, ent_id)"
//	        			+ " VALUES ('" + dict_id1 + "', '" + name + "', '" + parentId + "', null, '" + en_name + "', '0', '" + entId + "');");
//				
//				fileName = name + ".txt";
//				
//			
//			}else{
//				//发现一个子目录
//				//a 形容词
//				larr = cline.split(" ");
//				{
//					name = larr[1];
//					dict_id2 = CommonTools.getRandomId8();
//					en_name = "en_" + dict_id2;
//					sql.add("INSERT INTO rbc_slot_dict (dict_id, name, parent_id, sort, en_name, type, ent_id)"
//		        			+ " VALUES ('" + dict_id2 + "', '" + name + "', '" + dict_id1 + "', null, '" + en_name + "', '0', '" + entId + "');");
//				}
//				
//				
//				{
//					set.clear();
//					set.add(larr[0]);	
//					for(String s  : line){
//						//简单	a	6935
//						arr = s.split("\\t");
//						if(set.contains(arr[1])){
//							sql.add("INSERT INTO rbc_slot_word (word_id, dict_id, name, alias, create_time, create_user_id, ent_id)"
//			            			+ " VALUES ('" + CommonTools.getRandomId8() + "', '" + dict_id2 + "', '" + arr[0].trim() + "', null, '2018-05-21 15:41:32', 'admin', '" + entId + "');");
//						}
//					}
//				}
//				
//				
//			}
//			
//			
//		}
//		
//		if(sql.size() > 0){
//			//写文件
//			 FileUtils.writeLines(new File(dir + "/" + fileName), sql);
//			 System.out.println("写文件：" + fileName + "\t记录：" + sql.size());
//		}
//		
//		
//		System.out.println("完成");
//	}
//	
//	
//	
//	/**
//	 * 生成地市的词库
//	 * @throws Exception 
//	 */
//	public static void genCity02() throws Exception{
//		System.out.println("开始生成sql");
//		File city = new File("D:\\work\\workspace\\rbc_web\\doc\\词库\\地市.json");
//		String dir = "D:\\work\\workspace\\rbc_web\\doc\\词库\\地市词库";
//		FileUtils.deleteQuietly(new File(dir));
//		
//		String cityJson = FileUtils.readFileToString(city);
//		
//		String parentId = "11111";
//		String entId = "ROBOT3";
//		printJson(JSONObject.fromObject(cityJson), parentId, entId);
//		FileUtils.writeLines(new File(dir + "/city.sql"), citysql);
//		
//		System.out.println("完成");
//	}
//	
//	static List<String>  citysql = new ArrayList<String>();
//	private  static void printJson(JSONObject json, String parentId, String entId){
//		Set<String> cityset = json.keySet();
//		String dictId = null;
//		String enname = null;
//		String name = null;
//		for(String city : cityset){
//			//创建一级地市目录
//			{
//				dictId = CommonTools.getRandomId();
//				enname = "en_" + dictId;
//				name = city;
//				citysql.add("INSERT INTO rbc_slot_dict (dict_id, name, parent_id, sort, en_name, type, ent_id)"
//						+ " VALUES ('" + dictId + "', '" + name + "', '" + parentId + "', null, '" + enname + "', '0', '" + entId + "');");
//			}
//			
//			Object o = json.get(city);
//			if(o instanceof JSONObject){
//				printJson((JSONObject)o, dictId, entId);
//			}
//			if(o instanceof JSONArray){
//				for(Object o2 : (JSONArray)o){
//					String name2 = (String)o2;
//					if("其他".equals(name2)) continue;
//					citysql.add("INSERT INTO rbc_slot_word (word_id, dict_id, name, alias, create_time, create_user_id, ent_id)"
//	            			+ " VALUES ('" + CommonTools.getRandomId() + "', '" + dictId + "', '" + name2 + "', null, '2018-05-21 15:41:32', 'admin', '" + entId + "');");
//				}
//			}
//		}
//	}
//	
//	
//	
//	
//	
//	
//	/**
//	 * 从词库中获取同义词
//	 */
//	public static void genWord03(){
//		
//	}
//	
//	
//	
//	public static void main(String[] args) throws Exception {
//		//genWord02();
//		
//		genCity02();
//        
//	}
//	
	
	
}
