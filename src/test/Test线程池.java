package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 	Java通过Executors提供四种线程池，分别为：
	newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
	线程池作用就是限制系统中执行线程的数量。
		根据系统的环境情况，可以自动或手动设置线程数量，达到运行的最佳效果；少了浪费了系统资源，多了造成系统拥挤效率不高。用线程池控制线程数量，其他线程排 队等候。一个任务执行完毕，再从队列的中取最前面的任务开始执行。若队列中没有等待进程，线程池的这一资源处于等待。当一个新任务需要运行时，如果线程池 中有等待的工作线程，就可以开始运行了；否则进入等待队列。
	为什么要用线程池:
		1.减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。
		2.可以根据系统的承受能力，调整线程池中工作线线程的数目，防止因为消耗过多的内存，而把服务器累趴下(每个线程需要大约1MB内存，线程开的越多，消耗的内存也就越大，最后死机)。
		Java里面线程池的顶级接口是Executor，但是严格意义上讲Executor并不是一个线程池，而只是一个执行线程的工具。真正的线程池接口是ExecutorService。
 */

public class Test线程池 {

	public Logger logger = Logger.getLogger(this.getClass());

	public static void main(String[] args) throws Exception {
		
		{
			// 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			for (int i = 0; i < 10; i++) {
				final int index = i;
				try {
					Thread.sleep(index * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						System.out.println(index);
						System.err.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
					}
				});
			}
			
		}
		

		// ==================================================================================================

		// 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
		
		
		{	
			ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
			for (int i = 0; i < 10; i++) {
				final int index = i;
				fixedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							System.out.println(index);
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
		
//====================================================================================================
		
		{
			//创建一个定长线程池，支持定时及周期性任务执行
			ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
			// 表示延迟3秒执行。
			scheduledThreadPool.schedule(new Runnable() {
				@Override
				public void run() {
					System.out.println("延迟 3 秒执行");
				}
			}, 3, TimeUnit.SECONDS);
			
			//表示延迟1秒后每3秒执行一次。
			scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					System.err.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
					System.out.println("延迟1秒，每3秒执行一次");
					System.err.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
				}
			}, 1, 3, TimeUnit.SECONDS);
			
		}
		
//====================================================================================================
		{
//			创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行结果依次输出，相当于顺序执行各个任务	
			
			ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
			for (int i = 0; i < 10; i++) {
				final int index = i;
				singleThreadExecutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							System.out.println(index);
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
	}
}
