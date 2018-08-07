package test;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class TestMapReduce {
	public static void main(String[] args) {
		List<Order> list = new ArrayList<Order>();
		list.add(new Order("手机", 2999.9, 100));
		list.add(new Order("电脑", 4999.9, 200));
		list.add(new Order("奥迪", 99999.9, 50));
		list.add(new Order("鼠标", 99.9, 200));
		list.add(new Order("键盘", 999.9, 200));
		DoubleSummaryStatistics summ = list.stream().mapToDouble((obj) -> obj.getPrice() * obj.getAmount())
				.summaryStatistics();
		Double sumPrice = list.stream().map((obj) -> obj.getPrice() * obj.getAmount()).reduce((sum,x)->sum + x).get();
		System.err.println("总消费：" + sumPrice);
		System.out.println("总量:" + summ.getCount());
		System.out.println("平均值:" + summ.getAverage());
		System.out.println("最大:" + summ.getMax());
		System.out.println("最小:" + summ.getMin());

	}

}

class Order {
	private String title;
	private Double price;
	private Integer amount;

	public Order(String title, Double price, Integer amount) {
		super();
		this.title = title;
		this.price = price;
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
