package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	/**
	 * 将date转换为自定义格式的时间字符串 
	 */
	public static String formatDate(Date date,String formatString){
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		return format.format(date);
	}
	/**
	 * 将date转换为默认格式的时间字符串  yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDate(Date date){
		return formatDate(date,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 将date转换为自定义格式的时间
	 */
	public static Date format(Date date,String formatString){
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		String dataStr = format.format(date);
		Date dataF = null;
		try {
			dataF = format.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataF;
	}
	/**
	 * 将String转换为自定义格式的时间
	 */
	public static Date format(String date,String formatString){
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		Date dataF = null;
		try {
			dataF = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataF;
	}
	/**
	 * 将String转换为自定义格式的时间字符串
	 */
	public static String formatString(String date,String formatString){
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		Date dataF = null;
		String dataStr = null;
		try {
			dataF = format.parse(date);
			dataStr = format.format(dataF);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataStr;
	}
	/**
	 * 获取当天的开始结束时间
	 */
	public static List<String> getToday(){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		String endTime = DateUtil.formatDate( ca.getTime(), "yyyy-MM-dd HH:mm:ss");
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		String startTime = DateUtil.formatDate(ca.getTime(), "yyyy-MM-dd HH:mm:ss");
		List<String> list = new ArrayList<String>();
		list.add(startTime);
		list.add(endTime);
		return list;
	}
	/**
	 * 将时间毫秒值 转化为分秒值 （00'00"）
	 */
	public static String changeToMSvalue(Integer time){
		//显示时间（将毫秒值转换为分秒值）
		int second = (time/1000)%60;//秒
		int millis = (time/1000)/60;//分
		String s = second+"";
		String m=millis+"";
		if(second<=9) s="0"+second;
		if(millis<=9) m="0"+millis;
		String timeStr = m+"′"+s+"″";
		return timeStr;
	}
	
	/**
	 * 获取当前月份的上一个月
	 */
	
   public static Date getLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
   }
   
   
   
   /**
    * 获取两个日期中间的所有月份
    */
   public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException{
	    ArrayList<String> result = new ArrayList<String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//格式化为年月

	    Calendar min = Calendar.getInstance();
	    Calendar max = Calendar.getInstance();

	    min.setTime(sdf.parse(minDate));
	    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

	    max.setTime(sdf.parse(maxDate));
	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

	    Calendar curr = min;
	    while (curr.before(max)) {
	     result.add(sdf.format(curr.getTime()));
	     curr.add(Calendar.MONTH, 1);
	    }

	    return result;
	  }
   
   
	/**
	 * 获取每个月的天数
	 * @throws ParseException 
	 */
	public static List<String> getEveryDay(String dateString) throws ParseException {
		SimpleDateFormat format=null;
		if(dateString.length()==4){
			List<String> listday=new ArrayList<String>();
			format = new SimpleDateFormat("yyyy");
			for(int i=0;i<12;i++){
					listday.add(String.valueOf(i+1));
			}
			return listday;
		}
		else{
			List<String> listday=new ArrayList<String>();
			format = new SimpleDateFormat("yyyyMM");
			Date date = format.parse(dateString);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			for(int i=0;i<calendar.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
				listday.add(String.valueOf(i+1));
			}
			return listday;
		}
	}
}
