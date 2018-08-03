package test;

import java.util.List;

public class Emp {
	private String name;
	private int age;
	private String job;
	private List<String> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Emp [name=" + name + ", age=" + age + ", job=" + job + ", data=" + data + "]";
	}

}
