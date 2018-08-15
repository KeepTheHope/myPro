package test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 对ETL数据进行数据核查，核查方向有：数据断点、未计算、运算错误
 * @author zhanglongge
 *
 */
public class CheckToETLData {

	/*public static void main(String[] args) {
		
		String hisdate="20180523";
		getMinus(hisdate);
		getEtlNotRun();
	}
	
	public static void startEtlData(String hisdate){
		getMinus(hisdate);
		getEtlNotRun();
	}*/
	
	/**
	 * 判断归档表中的数据是否为负值
	 * @param hisdate
	 *//*
	public static void getMinus(String hisdate){
		deleteLackPoint(hisdate);
		int type=0;
		int dataglag=1;
		StringBuffer sql=new StringBuffer();
		sql.append("select b.companyid as companyid,b.scompanyid as scompanyid,a.meterid as meterid,a.measurecode as measurecode,a.hisdate as hisdate,");
		for(int i=1;i<96;i++){
			sql.append("a.point"+i+",");
		}
		sql.append("a.point96 from t_daq_result_arc_curve a left join (select m.meterid as meterid,m.companyid as companyid,p.servicecompany as scompanyid");
		sql.append(" from t_daq_bas_meter m left join t_bas_power_company p on m.companyid=p.companyid) b on a.meterid=b.meterid where a.hisdate=? ");
		sql.append(" and a.measurecode in('Pw','Pwr','Qv','Qvr')");
		List<Map<String,Object>> listAll=CrudMethod.selectInfoListMaps(sql.toString(),new Object[]{hisdate});
		List<Map<String,Object>> listMinus=new ArrayList<Map<String,Object>>();
		if(listAll.size()>0){
			for(int i=0;i<listAll.size();i++){
				Map<String,Object> mapnull=new HashMap<String,Object>();
				Map<String,Object> map=listAll.get(i);
				Iterator iterAcquireMap = map.entrySet().iterator();
				StringBuffer keystr=new StringBuffer();
				while(iterAcquireMap.hasNext()){
					Map.Entry entryAcquire = (Map.Entry) iterAcquireMap.next();  
					String keyAcquire = (String)entryAcquire.getKey();  
					if(keyAcquire.toLowerCase().contains("point")){
						BigDecimal valueCount = (BigDecimal)map.get(keyAcquire);
						if(valueCount==null){
							continue;
						}
						if(valueCount.compareTo(new BigDecimal(0))==-1){
							keystr.append(keyAcquire+",");
						}
					}
				}
				if(keystr.toString().length()>0){
					keystr.delete(keystr.lastIndexOf(","), keystr.length());
					mapnull.put("points", keystr.toString());
					mapnull.put("meterid", map.get("METERID"));
					mapnull.put("measurecode", map.get("MEASURECODE"));
					mapnull.put("hisdate", map.get("HISDATE"));
					mapnull.put("companyid", map.get("companyid"));
					mapnull.put("scompanyid", map.get("scompanyid"));
					listMinus.add(mapnull);
				}else{
					continue;
				}
			}
		}
		if(listMinus.size()>0){
			insertResult(listMinus,dataglag,type);
		}
	}*/
	
	/**
	 * 将结果保存到数据表中
	 * @param list 负值
	 * @param relevanceTable 关联表
	 * @return
	 */
	public static int insertResult(List<Map<String,Object>> list,int dataglag,int type){
		int intoNum=0;
		StringBuffer sql=new StringBuffer();
		List<String[]> lists = new ArrayList<String[]>();
		String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
		sql.append("insert into t_check_result_running(meterid,measurecode,hisdate,inspecttime,points,datatype,dataflag,companyid,servicecompany) values(?,?,?,?,?,?,?,?,?)");
		String[] strList=new String[9];
		for(int i=0;i<list.size();i++){
			if(list.get(i).get("meterid")!=null){
				strList[0]=list.get(i).get("meterid").toString();
			}
			if(list.get(i).get("measurecode")!=null){
				strList[1]=list.get(i).get("measurecode").toString();
			}
			if(list.get(i).get("hisdate")!=null){
				strList[2]=list.get(i).get("hisdate").toString();
			}
			//strList[2]=hisdate.toString();
			strList[3]=date.toString();
			if(list.get(i).get("points")!=null){
				strList[4]=list.get(i).get("points").toString();
			}else{
				strList[4]=null;
			}
			strList[5]=""+type+"";
			strList[6]=""+dataglag+"";
			if(list.get(i).get("companyid")!=null){
				strList[7]=list.get(i).get("companyid").toString();
			}
			if(list.get(i).get("scompanyid")!=null){
				strList[8]=list.get(i).get("scompanyid").toString();
			}
			lists.add(strList);
			strList=new String[9];
			if(i!=0 && (i%5000==0 || list.size() ==i+1)){
				 try {
					intoNum =intoNum + CrudMethod.InsertListPlaceInt(sql.toString(), lists);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			//批量插入SQL语句；
				 lists = new ArrayList<String[]>();
			 }
		}
		return intoNum;
	}

	/**
	 * 获取ETL未执行的数据的信息
	 */
	public static void getEtlNotRun(){
		int type=1;
		int dataglag=1;
		String sql="SELECT METERID,MEASURECODE,HISDATE FROM t_daq_result_tmp_curve WHERE CHECKFLAG=0;";
		insertResult(CrudMethod.selectInfoListMap(sql),dataglag,type);
	}
	
	/**
	 * 删除结果表中的数据
	 * @param relevanceTable
	 * @param hisdate
	 * @return
	 */
	/*public static int deleteLackPoint(String hisdate){
		String sql="delete from t_check_result_running where hisdate=? and dataflag=1";
		return CrudMethod.deleteTable(sql, new Object[]{hisdate});
	}*/
}
