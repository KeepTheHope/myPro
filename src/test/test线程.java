package test;

import java.util.concurrent.ThreadFactory;

class SimpleThreadFactory implements ThreadFactory {
	private static int count = 0 ;	// 进行一个计数的操作
	public Thread newThread(Runnable r) {
		return new Thread(r, "线程 - " + count++);
	}
}
public class test线程{
	public static void main(String[] args) throws Exception {
		
		Runnable run = new Runnable() {
			@Override
			public void run() {
				for (int x = 0 ; x < 10 ; x ++) {
					System.out.println(Thread.currentThread().getName() + "、x = " + x);
				}
				
			}
		};
		Thread t = new SimpleThreadFactory().newThread(run);
		
//		Thread t = new SimpleThreadFactory().newThread(()->{
//			for (int x = 0 ; x < 10 ; x ++) {
//				System.out.println(Thread.currentThread().getName() + "、x = " + x);
//			}
//		}) ;
		t.start();
	}
}


