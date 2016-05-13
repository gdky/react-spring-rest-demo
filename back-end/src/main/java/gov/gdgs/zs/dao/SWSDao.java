package gov.gdgs.zs.dao;

import gov.gdgs.zs.configuration.Config;
import gov.gdgs.zs.untils.Condition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hashids.Hashids;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SWSDao extends BaseDao{

	/**
	 * 
	 * @return 事务所分页查询
	 * @throws Exception 
	 */
	public Map<String,Object> swscx(int pn,int ps,Map<String,Object> qury) {
		final String url=Config.URL_PROJECT;
		Condition condition = new Condition();
		condition.add("a.dwmc", Condition.FUZZY, qury.get("dwmc"));
		condition.add("a.zsbh", Condition.EQUAL, qury.get("zsbh"));
		condition.add("a.zczj", Condition.GREATER_EQUAL, qury.get("zczj"));
		condition.add("a.zczj", Condition.LESS_EQUAL, qury.get("zczj2"));
		condition.add("a.cs_dm", Condition.EQUAL, qury.get("cs"));
		condition.add("a.JGXZ_DM", Condition.EQUAL, qury.get("swsxz"));
		condition.add("b.zrs", Condition.GREATER_EQUAL, qury.get("zrs"));
		condition.add("b.zrs", Condition.LESS_EQUAL, qury.get("zrs2"));
		condition.add("b.zyrs", Condition.GREATER_EQUAL, qury.get("zyrs"));
		condition.add("b.zyrs", Condition.LESS_EQUAL, qury.get("zyrs2"));
		condition.add("a.clrq", Condition.GREATER_EQUAL, qury.get("clsj"));
		condition.add("a.clrq", Condition.LESS_EQUAL, qury.get("clsj2"));
		StringBuffer sb = new StringBuffer();
		sb.append("		SELECT 	SQL_CALC_FOUND_ROWS	 ");
		sb.append("		@rownum:=@rownum+1 AS 'key',");
		sb.append("		a.id,	");
		sb.append("		a.dwmc,		 ");
		sb.append("		a.zczj,		 ");
		sb.append("		a.fddbr,	 ");
		sb.append("		a.zsbh,	 ");
		sb.append("		d.mc AS swsxz,		"); 
		sb.append("		c.mc AS cs,		");
		sb.append("		b.zrs,		 ");
		sb.append("		b.zyrs,");
		sb.append("		DATE_FORMAT(a.clrq,'%Y-%m-%d') AS clsj");
		sb.append("		FROM		 ");
		sb.append("		zs_jg_new a,	zs_jg_njb b,dm_cs c,	"); 	 
		sb.append("		dm_jgxz d,(SELECT @rownum:=?) zs_jg");
		sb.append("		"+condition.getSql()+" ");
		sb.append("		and a.jgxz_dm = d.id ");
		sb.append("		AND a.cs_dm = c.id ");
		sb.append("		AND a.id = b.jg_id ");
		sb.append("		AND b.YXBZ = '1'");
		sb.append("		AND a.YXBZ = '1'");
		if(qury.containsKey("sorder")){
			Boolean asc = qury.get("sorder").toString().equals("ascend");
			switch (qury.get("sfield").toString()) {
			case "dwmc":
				if(asc){
					sb.append("		    order by convert( a.dwmc USING gbk) COLLATE gbk_chinese_ci ");
				}else{
					sb.append("		    order by convert( a.dwmc USING gbk) COLLATE gbk_chinese_ci desc");
				}
				break;
			case "zczj":
				if(asc){
					sb.append("		    order by a.zczj ");
				}else{
					sb.append("		    order by a.zczj desc");
				}
				break;
			case "fddbr":
				if(asc){
					sb.append("		    order by convert( a.fddbr USING gbk) COLLATE gbk_chinese_ci ");
				}else{
					sb.append("		    order by convert( a.fddbr USING gbk) COLLATE gbk_chinese_ci desc");
				}
				break;
			case "clsj":
				if(asc){
					sb.append("		    order by a.clrq ");
				}else{
					sb.append("		    order by a.clrq desc");
				}
				break;
			}
		}
		sb.append("		    LIMIT ?, ? ");
		ArrayList<Object> params = condition.getParams();
		params.add(0,(pn-1)*ps);
		params.add((pn-1)*ps);
		params.add(ps);
		List<Map<String,Object>> ls = this.jdbcTemplate.query(sb.toString(),params.toArray(),
				new RowMapper<Map<String,Object>>(){
			public Map<String,Object> mapRow(ResultSet rs, int arg1) throws SQLException{
				Hashids hashids = new Hashids(Config.HASHID_SALT,Config.HASHID_LEN);
				Map<String,Object> map = new HashMap<String,Object>();
				Map<String,Object> link = new HashMap<>();
				String id = hashids.encode(rs.getLong("id"));
				link.put("herf_sws", url+"/swsxx/"+id);
				link.put("herf_zyry", url+"/zyryxx/"+id);
				link.put("herf_cyry", url+"/cyryxx/"+id);
				link.put("herf_czrylb", url+"/czrylb/"+id);
				link.put("herf_swsbgxx", url+"/swsbgxx/"+id);
				link.put("herf_njjl", url+"/njjl/"+id);
				map.put("key", rs.getObject("key"));
				map.put("xh", rs.getObject("key"));
				map.put("_links", link);
				map.put("dwmc", rs.getObject("dwmc"));
				map.put("zczj", rs.getObject("zczj"));
				map.put("cs", rs.getObject("cs"));
				map.put("fddbr", rs.getObject("fddbr"));
				map.put("zsbh", rs.getObject("zsbh"));
				map.put("swsxz", rs.getObject("swsxz"));
				map.put("zrs", rs.getObject("zrs"));
				map.put("zyrs", rs.getObject("zyrs"));
				map.put("clsj", rs.getObject("clsj"));
				return map;
				}
			});
		int total = this.jdbcTemplate.queryForObject("SELECT FOUND_ROWS()", int.class);
		Map<String,Object> ob = new HashMap<>();
		ob.put("data", ls);
		Map<String, Object> meta = new HashMap<>();
		meta.put("pageNum", pn);
		meta.put("pageSize", ps);
		meta.put("pageTotal",total);
		meta.put("pageAll",(total + ps - 1) / ps);
		ob.put("page", meta);
		
		return ob;
	}
	
	/**
	 * 
	 * 
	 * @param id
	 * @return 事务所详细信息
	 */
	public Map<String,Object> swsxx(int id){
		StringBuffer sb = new StringBuffer();
		sb.append("select a.id,	");	 
		sb.append("		a.dwmc,c.mc as cs,	a.fddbr,e.bgdz as dzhi,e.sglzxsbwh as sjlzxsbwh,e.zcdz, ");
		sb.append("		date_format(e.sglzxsbsj,'%Y-%m-%d') as sglzxsbsj,date_format(e.zjpzsj,'%Y-%m-%d')"
				+ " as zjpzsj,e.yzbm,e.zjpzwh,e.czhm as czhen,e.dhhm as dhua,e.szyx, ");
		sb.append("		e.txyxm as txyxming,e.txyyx as xtyyx,e.txyyddh as xtyphone,a.zsbh,	a.zczj,e.jyfw,d.zrs, ");
		sb.append("		b.mc as swsxz,e.szyddh as szphone,e.gsyhbh as gsyhmcbh,e.dzyj,e.yhdw,date_format(e.yhsj,'%Y-%m-%d') as yhsj, ");
		sb.append("		e.gzbh,e.gzdw,e.gzry,date_format(e.gzsj,'%Y-%m-%d') as gzsj,e.yzbh,e.yzdw,e.yzry,date_format(e.yzsj,'%Y-%m-%d') as yzsj, ");
		sb.append("		e.tthyzcbh as tthybh,date_format(e.rhsj,'%Y-%m-%d') as rhsj,e.khyh as khh,e.khyhzh as khhzh,e.fj,e.swdjhm,e.qkjj as jbqk, ");
		sb.append("		e.swsnbglzd as glzd,e.dycgddh as gddh,e.bgcscqzm as bgcszczm	from		 ");
		sb.append("		 zs_jg_new a,	dm_jgxz b,dm_cs c,	 zs_jg_njb d,zs_jg_kzxx e ");
		sb.append("		WHERE		 a.JGXZ_DM = b.ID  ");
		sb.append("		AND a.CS_DM = c.ID  ");
		sb.append("		AND a.ID = d.JG_ID  ");
		sb.append("		and a.id = e.JG_ID ");
		sb.append("		and a.YXBZ = 1 ");
		sb.append("		and d.YXBZ = 1 ");
		sb.append("			and e.YXBZ = 1 ");
		sb.append("		and a.id = ?");
		String sql = "SELECT @rownum:=@rownum+1 AS 'key',b.* FROM zs_jg_new a,zs_jg_nbjgb b,(SELECT @rownum:=0) zs_jg WHERE a.id = b.jg_id AND a.id = ?";
		List<Map<String, Object>> tl = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
		Map<String,Object> ll =tl.get(0);
		ll.put("nbjgsz", this.jdbcTemplate.queryForList(sql,new Object[]{id}));
		return ll;
	}
	/**
	 * 
	 * @param id
	 * @return 执业人员信息
	 */
	public List<Map<String,Object>> zyryxx(int id){
		StringBuffer sb = new StringBuffer();
		sb.append("		select @rownum:=@rownum+1 as 'key',@rownum AS xh,c.xming, ");
		sb.append("		case a.czr_dm when 1 then \"是\"  when 2 then \"否\" else null end as czr,");
		sb.append("		case a.fqr_dm when 1 then \"是\"  when 2 then \"否\" else null end as fqr,");
		sb.append("	 case a.sz_dm when 1 then \"是\"  when 2 then \"否\" else null end as sz ");
		sb.append("		from ");
		sb.append("	zs_zysws a,zs_jg b ,");
		sb.append("		zs_ryjbxx c,(select @rownum:=0) zs_jg ");
		sb.append("		where");
		sb.append("		 b.id =?");
		sb.append("		and b.id=a.jg_id");
		sb.append("		 and a.ry_id = c.id");
		sb.append("		 and a.zyzt_dm = '1'");
		return this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
	}
	/**
	 * 
	 * @param id
	 * @return 从业人员信息
	 */
	public List<Map<String,Object>> cyryxx(int id){
		StringBuffer sb = new StringBuffer();
		sb.append("		select @rownum:=@rownum+1 as 'key', c.xming, ");
		sb.append("		 d.mc as xl, ");
		sb.append("		c.sfzh,");
		sb.append("		e.mc as zc,");
		sb.append("		a.id");
		sb.append("		from zs_cyry a,");
		sb.append("		zs_jg b");
		sb.append("		,zs_ryjbxx c,");
		sb.append("		dm_xl d,");
		sb.append("		dm_zw e,(select @rownum:=0) zs_jg");
		sb.append("		where b.id =?");
		sb.append("		and b.id=a.jg_id ");
		sb.append("		and a.ry_id = c.id ");
		sb.append("		and c.xl_dm = d.id ");
		sb.append("			and a.zw_dm = e.id");
		return this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
	}
	/**
	 * 
	 * @param id
	 * @return 出资人列表
	 */
	public List<Map<String,Object>> czrylb(int id){
		StringBuffer sb = new StringBuffer();
		sb.append("		select @rownum:=@rownum+1 as 'key',c.xming,");
		sb.append("		a.cze,");
		sb.append("		b.dwmc,");
		sb.append("		c.sfzh");
		sb.append("		from ");
		sb.append("		zs_zysws a,zs_jg b ,");
		sb.append("			zs_ryjbxx c,(select @rownum:=0) zs_jg ");
		sb.append("			where");
		sb.append("			 b.id =? ");
		sb.append("		and b.id=a.jg_id");
		sb.append("		and a.czr_dm = 1");
		sb.append("		 and a.ry_id = c.id");
		return this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
	}
	/**
	 * 
	 * @param id
	 * @return 事务所变更信息
	 */
	public List<Map<String,Object>> swsbgxx(int id){
		StringBuffer sb = new StringBuffer();
		sb.append("		select * from (select @rownum:=@rownum+1 as 'key',date_format(b.gxsj,'%Y-%m-%d') as xgxsj,b.* from zs_jg a,zs_jglsbgxxb b,(select @rownum:=0) zs_jg where a.id = ? and b.jgb_id = a.id ");
		sb.append("				union ");
		sb.append("				select @rownum:=@rownum+1 as 'key',date_format(b.gxsj,'%Y-%m-%d') as xgxsj,b.* from zs_jg a,zs_jgbgxxb b,zs_jgbgspb c where a.id = ? and b.jgbgspb_id = c.id and c.jg_id = a.id) as g where g.jgb_id = ? ");
		return this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id,id,id});
	}
	/**
	 * 
	 * @param id
	 * @return 年检记录
	 */
	public List<Map<String,Object>> njjl(int id){
		StringBuffer sb = new StringBuffer();
		sb.append("	select 		 ");
		sb.append("		@rownum:=@rownum+1 as 'key',a.id,");
		sb.append("		f.nd,	 ");
		sb.append("		a.dwmc,");
		sb.append("		c.spyj,");
		sb.append("		case f.ztdm when 1 then \"保存\"  when 2 then \"自检\" when 0 then \"退回\" when 3 then \"年检\" else null end as njzt,	");	 
		sb.append("		date_format( c.spsj,'%Y-%m-%d') as spsj ");
		sb.append("		from		");
		sb.append("		 zs_jg a,");
		sb.append("		 zs_spzx b,");
		sb.append("		 zs_spxx c,");
		sb.append("		 zs_splcbz d,");
		sb.append("		 zs_splc e,");
		sb.append("		zs_jgnj f,(select @rownum:=0) zs_jg");
		sb.append("		where		 ");
		sb.append("		a.id = ?");
		sb.append("			and b.zsjg_id = a.id ");
		sb.append("		and c.spid = b.id ");
		sb.append("		and c.ispass = 'y' ");
		sb.append("		 and d.id = c.lcbzid ");
		sb.append("		 and e.id = d.lcid");
		sb.append("		  and e.lclxid ='11'");
		sb.append("		and f.ztdm = 3");
		sb.append("		and f.id = b.sjid");
		sb.append("		and a.id = f.zsjg_id");
		sb.append("		order by f.nd ");
		return this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
	}

	
}
