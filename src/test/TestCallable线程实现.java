package test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TestCallable线程实现 {
	public static void main(String[] args) throws Exception {
		FutureTask<String> task = new FutureTask<>(new MyThread()) ;
		new Thread(task).start();	// FutureTask是Runnable和Future接口子类
		System.out.println(task.get()); // 获取线程体返回数据
	} 
}

class MyThread implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("*********** 线程执行 ************");
		return "Hello World";
	}
}