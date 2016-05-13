package gov.gdgs.zs.untils;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简陋的JDBC连接
 * @author Administrator
 *
 */
public class DbToDb {
	
	/**
	 * 事务所基本表(zs_jg)插入数据方法
	 */
	public  void insertnewDB(List<Map<String,Object>> ls) throws Exception {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/gdzs?"
		+ "user=root&password=my123&useUnicode=true&characterEncoding=UTF8"; 
		try {
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		
//		for(Map<String, Object> rec : ls){
//				
//				StringBuffer sb = new StringBuffer();
//				sb.append("insert into zs_jg(ID,DWMC,CS_DM,FDDBR,JGXZ_DM,ZCZJ,JGZT_DM,YXBZ) values('"+rec.get("id").toString()+"',");
//				sb.append("'"+rec.get("dwmc").toString()+"',");
//				sb.append("'"+rec.get("cs_dm").toString()+"',");
//				sb.append("'"+rec.get("fddbr").toString()+"',");
//				sb.append("'"+rec.get("jgxz_dm").toString()+"',");
//				sb.append("'"+rec.get("zczj").toString()+"',");
//				sb.append("'"+rec.get("JGZT_DM").toString()+"',");
//				sb.append("'0')");
//				stmt.executeUpdate(sb.toString());
//		}
		/**
		 * 更新无效事务所
		 */
		for(Map<String, Object> rec : ls){
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE zs_ry set YXBZ='0' where YID = '"+rec.get("id").toString()+"'");
//			+rec.get("xm").toString()+"',XB_DM='"
//					+rec.get("XB_DM").toString()+
					

			stmt.executeUpdate(sb.toString());
		}
		System.out.println("更新了~~~~~");
		} catch (SQLException e) {
		System.out.println("MySQL操作错误");
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		conn.close();
		}

		}
	/**
	 * 人员基本表(zs_ry)插入数据方法
	 */
	public  void insertnewRYDB(List<Map<String,Object>> ls, char q) throws Exception {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/gdzs?"
				+ "user=root&password=my123&useUnicode=true&characterEncoding=UTF8"; 
		try {
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			
		for(Map<String, Object> rec : ls){
				
				StringBuffer sb = new StringBuffer();
//				Boolean qq =rec.get("RYZT_DM");
				sb.append("insert into zs_ry(YID,XM,XB_DM,BIR,SFZH,CS_DM,MZ_DM,XL_DM,RYZT_DM,RYSF_DM,YXBZ) values('"+rec.get("id").toString()+"',");
				if(rec.get("xm")==null){
					sb.append("null,");
				}else{
					
					sb.append("'"+rec.get("xm").toString()+"',");
				}
				if(rec.get("XB_DM")==null){
					sb.append("null,");
				}else{
					
					sb.append("'"+rec.get("XB_DM").toString()+"',");
				}
				if(rec.get("SRI")==null){
					sb.append("null,");
				}else{
					
					sb.append("'"+rec.get("SRI").toString()+"',");
				}
				if(rec.get("SFZH")==null){
					sb.append("null,");
				}else{
					
					sb.append("'"+rec.get("SFZH").toString()+"',");
				}
				if(rec.get("CS_DM")==null){
					sb.append("null,");
				}else{
					
					sb.append("'"+rec.get("CS_DM").toString()+"',");
				}
				if(rec.get("MZ_DM")==null){
					sb.append("null,");
				}else{
					
					sb.append("'"+rec.get("MZ_DM").toString()+"',");
				}
				if(rec.get("XL_DM")==null){
					sb.append("null,");
				}else{
					
					sb.append("'"+rec.get("XL_DM").toString()+"',");
				}
				if(rec.get("RYZT_DM")==null){
					sb.append("null,");
				}else{
					
					sb.append("'"+rec.get("RYZT_DM").toString()+"',");
				}
				sb.append("'"+q+"',");
				sb.append("'1')");
				stmt.executeUpdate(sb.toString());
		}
		System.out.println("更新了~~~~~");
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
	}
	/**
	 * 查询
	 */
	@SuppressWarnings("unchecked")
	public  List<Map<String,Object>> querynewDB() throws Exception {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/gdzs?"
				+ "user=root&password=my123&useUnicode=true&characterEncoding=UTF8"; 
		try {
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select* from zs_jg");
			ResultSetMetaData md = rs.getMetaData();
			int num = md.getColumnCount();
			@SuppressWarnings("rawtypes")
			List listOfRows = new ArrayList();
			while (rs.next()) {
				@SuppressWarnings("rawtypes")
				Map mapOfColValues = new HashMap(num);
				for (int i = 1; i <= num; i++) {
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				}
				listOfRows.add(mapOfColValues);
			}
			return listOfRows;
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
		
	}
	public  List<Map<String,Object>> query11111() throws Exception {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/gdzs?"
				+ "user=root&password=my123&useUnicode=true&characterEncoding=UTF8"; 
		try {
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select* from zs_jg");
			ResultSetMetaData md = rs.getMetaData();
			int num = md.getColumnCount();
			@SuppressWarnings("rawtypes")
			List<Map<String,Object>> listOfRows = new ArrayList();
			while (rs.next()) {
				@SuppressWarnings("rawtypes")
				Map mapOfColValues = new HashMap(num);
				for (int i = 1; i <= num; i++) {
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				}
				listOfRows.add(mapOfColValues);
			}
			
			return listOfRows;
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
		
	}
	public  void insert11111(Map<String,Object> swxx,String id) throws Exception {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/gdzs?"
				+ "user=root&password=my123&useUnicode=true&characterEncoding=UTF8"; 
		try {
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			StringBuffer sb = new StringBuffer();
			sb.append("insert into zs_jg_kzxx(JG_ID,BGDZ,SGLZXSBWH,ZCDZ,SGLZXSBSJ,ZJPZSJ,YZBM,ZJPZWH,CZHM,DHHM,SZYX,TXYXM,TXYYX,TXYYDDH,ZSBH,JYFW,SZYDDH,GSYHBH,DZYJ,YHDW,YHSJ,GZBH,GZDW,GZRY,GZSJ,YZBH,YZDW,YZRY,YZSJ,TTHYZCBH,RHSJ,KHYH,KHYHZH,FJ,SWDJHM,QKJJ,SWSNBGLZD,DYCGDDH,BGCSCQZM,YXBZ,LRRQ) values('"
			+id+"',");
			if(swxx.get("dzhi")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("dzhi").toString()+"',");
			}
			if(swxx.get("sjlzxsbwh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("sjlzxsbwh").toString()+"',");
			}
			if(swxx.get("zcdz")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("zcdz").toString()+"',");
			}
			if(swxx.get("sglzxsbsj")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("sglzxsbsj").toString()+"',");
			}
			if(swxx.get("zjpzsj")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("zjpzsj").toString()+"',");
			}
			if(swxx.get("yzbm")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("yzbm").toString()+"',");
			}
			if(swxx.get("zjpzwh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("zjpzwh").toString()+"',");
			}
			if(swxx.get("czhen")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("czhen").toString()+"',");
			}
			if(swxx.get("dhua")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("dhua").toString()+"',");
			}
			if(swxx.get("szyx")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("szyx").toString()+"',");
			}
			if(swxx.get("txyxming")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("txyxming").toString()+"',");
			}
			if(swxx.get("xtyyx")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("xtyyx").toString()+"',");
			}
			if(swxx.get("xtyphone")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("xtyphone").toString()+"',");
			}
			if(swxx.get("zsbh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("zsbh").toString()+"',");
			}
			if(swxx.get("jyfw")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("jyfw").toString()+"',");
			}
			if(swxx.get("szphone")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("szphone").toString()+"',");
			}
			if(swxx.get("gsyhmcbh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("gsyhmcbh").toString()+"',");
			}
			if(swxx.get("dzyj")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("dzyj").toString()+"',");
			}
			if(swxx.get("yhdw")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("yhdw").toString()+"',");
			}
			if(swxx.get("yhsj")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("yhsj").toString()+"',");
			}
			if(swxx.get("gzbh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("gzbh").toString()+"',");
			}
			if(swxx.get("gzdw")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("gzdw").toString()+"',");
			}
			if(swxx.get("gzry")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("gzry").toString()+"',");
			}
			if(swxx.get("gzsj")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("gzsj").toString()+"',");
			}
			if(swxx.get("yzbh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("yzbh").toString()+"',");
			}
			if(swxx.get("yzdw")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("yzdw").toString()+"',");
			}
			if(swxx.get("yzry")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("yzry").toString()+"',");
			}
			if(swxx.get("yzsj")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("yzsj").toString()+"',");
			}
			if(swxx.get("tthybh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("tthybh").toString()+"',");
			}
			if(swxx.get("rhsj")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("rhsj").toString()+"',");
			}
			if(swxx.get("khh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("khh").toString()+"',");
			}
			if(swxx.get("khhzh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("khhzh").toString()+"',");
			}
			if(swxx.get("fj")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("fj").toString()+"',");
			}
			if(swxx.get("swdjhm")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("swdjhm").toString()+"',");
			}
			if(swxx.get("jbqk")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("jbqk").toString()+"',");
			}
			if(swxx.get("glzd")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("glzd").toString()+"',");
			}
			if(swxx.get("gddh")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("gddh").toString()+"',");
			}
			if(swxx.get("bgcszczm")==null){
				sb.append("null,");
			}else{
				
				sb.append("'"+swxx.get("bgcszczm").toString()+"',");
			}
			sb.append("'1',sysdate())");
			stmt.executeUpdate(sb.toString());
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
	}
	/**
	 * 删除重复身份证记录
	 * @return
	 * @throws Exception
	 */
	public  void dealwithRYDB() throws Exception {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/gdzs?"
				+ "user=root&password=my123&useUnicode=true&characterEncoding=UTF8"; 
		try {
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			
			
			
			
			ResultSet rs = stmt.executeQuery("select * from (select count(sfzh) as q,xm,sfzh,rysf_dm from zs_ry group by sfzh) as v where q>1 ");
			ResultSetMetaData md = rs.getMetaData();
			int num = md.getColumnCount();
			List<Map<String,Object>> listOfRows = new ArrayList<Map<String,Object>>();
			while (rs.next()) {
				Map mapOfColValues = new HashMap(num);
				for (int i = 1; i <= num; i++) {
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				}
				listOfRows.add(mapOfColValues);
			}
			for(Map<String, Object> rec : listOfRows){
				Statement stmt2 = conn.createStatement();
				Statement stmt3 = conn.createStatement();
				String sql ="select min(rysf_dm) as min from zs_ry a where a.sfzh ='"+rec.get("sfzh").toString()+"'" ;
			ResultSet rs2 = stmt2.executeQuery(sql);
        	rs2.next();
        	String value = rs2.getString("min"); 
        	switch(value){
        	case "1":
        			stmt3.executeUpdate("delete from zs_ry where sfzh ='"+rec.get("sfzh").toString()+"' and rysf_dm = '2'");
        			stmt3.executeUpdate("delete from zs_ry where sfzh ='"+rec.get("sfzh").toString()+"' and rysf_dm = '3'");
				
        		break;
        	case "2":
        			stmt3.executeUpdate("delete from zs_ry where sfzh ='"+rec.get("sfzh").toString()+"' and rysf_dm = '3'");
//        			stmt3.executeUpdate("delete from zs_ry where id  in (select * from(select min(d.id) from zs_ry d where d.sfzh='"+rec.get("sfzh").toString()+"') v)");
        		
        	case "3":
        		stmt3.executeUpdate("delete from zs_ry where id  in (select * from(select min(d.id) from zs_ry d where d.sfzh='"+rec.get("sfzh").toString()+"') v)");
        	
        	}
        	stmt2.close();
        	stmt3.close();
			
	}
			System.out.println("删除了~~~~~");
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
	}
	

}
