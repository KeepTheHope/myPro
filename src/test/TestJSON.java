package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJSON {

	public static void main(String[] args) {
		String str = "{\"data\":{\"resultJson\":\"[{\\\"id\\\":\\\"2006211019\\\",\\\"name\\\":\\\"201807131531471034689006.wav\\\",\\\"type\\\":\\\"我没有时间，很忙\\\",\\\"updatetime\\\":\\\"2018-07-13 16:37:18\\\"},{\\\"id\\\":\\\"2408071152\\\",\\\"name\\\":\\\"\\\",\\\"type\\\":\\\"测试多音频\\\",\\\"updatetime\\\":\\\"2018-07-13 17:18:03\\\"},{\\\"id\\\":\\\"2548343348\\\",\\\"name\\\":\\\"201807131531474383133007.wav\\\",\\\"type\\\":\\\"怎么会这样\\\",\\\"updatetime\\\":\\\"2018-07-13 17:33:06\\\"},{\\\"id\\\":\\\"3027660941\\\",\\\"name\\\":\\\"去火车站或者登陆12306网站购买即可\\\",\\\"type\\\":\\\"如何购买火车票\\\",\\\"updatetime\\\":\\\"2018-07-13 17:32:34\\\"},{\\\"id\\\":\\\"3037503564\\\",\\\"name\\\":\\\"201807131531471172295088.wav\\\",\\\"type\\\":\\\"怎么打不通电话\\\",\\\"updatetime\\\":\\\"2018-07-13 16:39:46\\\"},{\\\"id\\\":\\\"4732829469\\\",\\\"name\\\":\\\"不知道\\\",\\\"type\\\":\\\"听不清怎么办\\\",\\\"updatetime\\\":\\\"2018-07-13 16:39:00\\\"},{\\\"id\\\":\\\"5421689515\\\",\\\"name\\\":\\\"中华人民共和国首都是北京市\\\",\\\"type\\\":\\\"北京首都\\\",\\\"updatetime\\\":\\\"2018-07-13 17:33:51\\\"}]\"},\"error\":\"\",\"name\":\"\",\"status\":\"1\"}";
		JSONObject obj = JSONObject.fromObject(str);
		String j = obj.getString("data");
		JSONObject s = JSONObject.fromObject(j);
		String st = s.getString("resultJson");
		JSONArray arr = JSONArray.fromObject(st);
		for (Object object : arr) {
			System.err.println(object);
		}
	}
}
