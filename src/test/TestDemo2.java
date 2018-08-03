package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class TestDemo2 {
	
	public static Logger logger = Logger.getLogger(TestDemo2.class);
	public static void main(String[] args) throws Exception {
		
		String path = "C:" + File.separator  +"Users"+ File.separator + "HYTX"+File.separator + "Desktop" + File.separator + "FileUploadDemo";
		System.err.println(path);
		// 创建目录
		File dir = new File(path);
		FileUtils.forceMkdir(dir);
	}
}
