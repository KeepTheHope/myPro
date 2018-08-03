package test;

public class TestThreadLocal {
	public static void main(String[] args) throws Exception {
		new Thread(() -> {
			Message msg = new Message();
			msg.setMsg("AAAAAAAA");
			MessageSpace.setMessage(msg);
			MessageSend.send(); // 引用传递
		}, "发送者A").start();
		new Thread(() -> {
			Message msg = new Message();
			msg.setMsg("BBBBBBBB");
			MessageSpace.setMessage(msg);
			MessageSend.send(); // 引用传递
		}, "发送者B").start();
		new Thread(() -> {
			Message msg = new Message();
			msg.setMsg("CCCCCCC");
			MessageSpace.setMessage(msg);
			MessageSend.send(); // 引用传递
		}, "发送者C").start();
	}
}

class Message {
	private String msg = "www.baidu.com";

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}

class MessageSend { // 进行一个消息发送的类
	public static void send() {
		Message temp = MessageSpace.getMessage();
		System.out.println("【" + Thread.currentThread().getName() + " - 消息发送】" + temp.getMsg());
	}
}

class MessageSpace { // 定义一个消息的存储空间
	private static ThreadLocal<Message> threadLocal = new ThreadLocal<Message>();

	public static void setMessage(Message t) {
		threadLocal.set(t);
	}

	public static Message getMessage() {
		return threadLocal.get();
	}
}
