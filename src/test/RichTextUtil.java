package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RichTextUtil {
	public static Map<String, Object> edit(String str) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> textList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		String[] arr = str.split("<p>");
		Integer totalCount = arr.length - 1;
		String valueStr = "";
		String content = "";
		for (int x = 0; x < arr.length; x++) {
			valueStr = arr[x].replaceAll("</p>", "");
			content = sb.append(valueStr).toString();
		}
		System.out.println(content);
		List<String> imgList = getSrcList(content).get("imgSrcList");
		List<String> videoList = getSrcList(content).get("videoSrcList");
		List<String> audioList = getSrcList(content).get("audioSrcList");
		map.put("count", totalCount);
		map.put("textList", textList);
		map.put("imgList", imgList);
		map.put("videoList", videoList);
		map.put("audioList", audioList);
		return map;
	}

	/**
	 * 匹配富文本标签获取各个标签的数据信息
	 * @param content ：需要处理的标签字符串
	 * @return
	 */
	public static Map<String, List<String>> getSrcList(String str) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> imgList = new ArrayList<String>();
		List<String> videoList = new ArrayList<String>();
		List<String> audioList = new ArrayList<String>();
		Matcher mImg = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)").matcher(str);
		Matcher mVideo = Pattern.compile("<(video|VIDEO)(.*?)(/>|></video>|>)").matcher(str);
		Matcher mAudio = Pattern.compile("<(audio|AUDIO)(.*?)(/>|></audio>|>)").matcher(str);
		// 开始匹配content中的<img />标签
		boolean resultmg = mImg.find();
		if (resultmg) {
			while (resultmg) {
				// 获取到匹配的<img />标签中的内容
				String str_img = mImg.group(2);
				imgList.add(str_img);
				// 匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
				resultmg = mImg.find();
			}
		}
		// 开始匹配content中的<video />标签
		boolean resultVideo = mVideo.find();
		if (resultVideo) {
			while (resultVideo) {
				// 获取到匹配的<video />标签中的内容
				String str_video = mVideo.group(2);
				videoList.add(str_video);
				// 匹配content中是否存在下一个<video/>标签，有则继续以上步骤匹配<video/>标签中的src
				resultVideo = mVideo.find();
			}
		}
		// 开始匹配content中的<audio />标签
		boolean resultAudio = mAudio.find();
		if (resultAudio) {
			while (resultAudio) {
				// 获取到匹配的<audio />标签中的内容
				String str_audio = mAudio.group(2);
				audioList.add(str_audio);
				// 匹配content中是否存在下一个<audio/>标签，有则继续以上步骤匹配<audio/>标签中的src
				resultAudio = mAudio.find();
			}
		}
		map.put("imgSrcList", imgList);
		map.put("videoSrcList", videoList);
		map.put("audioSrcList", audioList);
		return map;
	}

	public static void main(String[] args) {
		String str2 = "<p>对外答案01</p>"
				+ "<p><img src=\"/rbc_web/uploadVideo/20180510094521/171124.jpg\" width=\"50%\" height=\"50%\"></p>"
				+ "<p><img src=\"/rbc_web/uploadVideo/20180510094524/618671.jpg\" width=\"50%\" height=\"50%\"></p>"
				+ "<p>对外答案02</p>"
				+ "<p><audio src=\"/rbc_web/uploadVideo/20180510094546/701288.ogg\" controls=\"controls\"></audio></p>"
				+ "<p><audio src=\"/rbc_web/uploadVideo/20180510094543/370779.ogg\" controls=\"controls\"></audio></p>"
				+ "<p><video src=\"/rbc_web/uploadVideo/20180510094540/782704.mp4\" controls=\"controls\" width=\"50%\" height=\"50%\"></video></p>"
				+ "<p><video src=\"/rbc_web/uploadVideo/20180510094537/620441.mp4\" controls=\"controls\" width=\"50%\" height=\"50%\"></video></p>";
//		String str2 = "<p>是分抵达</p><p><br></p><p><br></p><p><img src=\"/rbc_web/uploadVideo/20180510152936/658132.jpg\" width=\"50%\" height=\"50%\"></p><p><video src=\"/rbc_web/uploadVideo/20180510152940/488896.mp4\" width=\"50%\" height=\"50%\" controls=\"controls\"></video></p>";
		System.err.println(RichTextUtil.edit(str2));

	}
}
