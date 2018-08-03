package test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public class Test文件 {

	static {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						TimeUnit.SECONDS.sleep(3);
						for (int x = 0; x < 10; x++) {
							System.out.println(x);
						}

					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(run).start();
	}

	public static void main(String[] args) throws Exception {

		File file = new File("C:/Users/HYTX/Desktop/123.txt");
		List<String> list = FileUtils.readLines(file, "utf8");
		for (String string : list) {
			 System.err.println(string);
		}
		FileUtils.forceMkdir(new File("C:/Users/HYTX/Desktop/5"));
		System.out.println(FileUtils.sizeOf(file));

	}

}