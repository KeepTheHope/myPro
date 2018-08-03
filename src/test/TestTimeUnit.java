package test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.ibm.icu.text.SimpleDateFormat;

public class TestTimeUnit {
	public static void main(String[] args) throws Exception {
		System.out.println("【START】"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date(System.currentTimeMillis())));
		// Thread.sleep(2 * 1000); // 需要自己根据毫秒来进行计算
		TimeUnit.SECONDS.sleep(2); // 休眠2秒
		System.out.println("【END】" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date(System.currentTimeMillis())));
		
//		================================================================
		
		long time = TimeUnit.SECONDS.convert(1, TimeUnit.HOURS) ;
		System.out.println("一小时转为秒：" + time);
		
//		================================================================
		
		long time1 = TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS) ;
		System.out.println("3天转为毫秒：" + time1);
		long threeTime = System.currentTimeMillis() + time1 ;	// 当前时间的毫秒数 + 三天后的毫秒数
		System.out.println("3天后的日期：" + new Date(threeTime));
		System.out.println("3天后的日期：" + new SimpleDateFormat("yyyy-MM-dd").format(new Date(threeTime)));
	
	}
}
