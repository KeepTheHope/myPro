package test;

import java.util.concurrent.atomic.AtomicLong;

public class Test原子操作 {
	
	public static void main(String[] args) {
		AtomicLong num = new AtomicLong(100) ;	// 设置为原子性操作
		System.out.println("数减自增：" + num.decrementAndGet());
		System.out.println("数据自增：" + num.incrementAndGet());
		
		
		AtomicLong num1 = new AtomicLong(100) ;	// 设置为原子性操作
		// 如果现在要进行修改的内容是100，即：原始的原子类型里面为100，则使用333替换num的内容
		System.out.println(num1.compareAndSet(100, 333));	// 比较的值等于100，返回true
		System.out.println(num1);
	}

}
