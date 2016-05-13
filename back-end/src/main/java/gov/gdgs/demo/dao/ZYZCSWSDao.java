package gov.gdgs.zs.dao;

import gov.gdgs.zs.untils.Pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class ZYZCSWSDao {
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
       public Map<String,Object> zyrycx1(int pn,int ps){
    	   StringBuffer sb= new StringBuffer();
    	   sb.append("SELECT  ");
    	   sb.append("  a.ID,a.XMING,d.MC AS xb,a.DHHM,a.SFZH,e.MC,c.ZYZGZSBH,c.ZYZSBH,f.MC AS czr,f1.MC AS fqr,b.DWMC");
    	   sb.append("  FROM zs_ryjbxx a,zs_jg b,zs_zysws c,dm_xb d,dm_xl e,dm_fqrczrsz f,dm_fqrczrsz f1");
    	   sb.append("   WHERE c.JG_ID=b.ID AND a.ID=c.RY_ID AND a.XB_DM=d.ID  AND a.XL_DM=e.ID AND c.CZR_DM=f.ID AND c.FQR_DM=f1.ID AND c.ZYZT_DM=1");
    	   List<Map<String,Object>> ls= this.jdbcTemplate.queryForList(sb.toString());
    	   Pager<Map<String,Object>> pager =Pager.create(ls,ps);
           Map<String,Object> ob =new HashMap<>();
           ob.put("data", pager.getPagedList(pn));
           ob.put("total_number", ls.size());
           ob.put("pagetotal" , (ls.size()+ps-1)/ ps);
           return ob;
           
       }
       
       public Map<String,Object> zyrycx(){
   		StringBuffer sb = new StringBuffer();
   	   sb.append("SELECT  ");
	   sb.append("  a.ID,a.XMING,d.MC AS xb,a.DHHM,a.SFZH,e.MC,c.ZYZGZSBH,c.ZYZSBH,f.MC AS czr,f1.MC AS fqr,b.DWMC");
	   sb.append("  FROM zs_ryjbxx a,zs_jg b,zs_zysws c,dm_xb d,dm_xl e,dm_fqrczrsz f,dm_fqrczrsz f1");
	   sb.append("   WHERE c.JG_ID=b.ID AND a.ID=c.RY_ID AND a.XB_DM=d.ID  AND a.XL_DM=e.ID AND c.CZR_DM=f.ID AND c.FQR_DM=f1.ID");
   		List<Map<String,Object>> ls = this.jdbcTemplate.queryForList(sb.toString());
   		List<Map<String,Object>> fl = new ArrayList<Map<String,Object>>();
   		for(Map<String, Object> rec : ls){
   			Map<String,Object> link = new HashMap<>();
   			link.put("herf_xxzl", "http://localhost:8080/api/zyry/xxzl/"+rec.get("ID").toString());
   			link.put("herf_bgjl", "http://localhost:8080/api/zyry/bgjl/"+rec.get("ID").toString());
   			link.put("herf_zsjl", "http://localhost:8080/api/zyry/zsjl/"+rec.get("ID").toString());
   			link.put("herf_zjjl", "http://localhost:8080/api/zyry/zjjl/"+rec.get("ID").toString());
   			link.put("herf_zzjl", "http://localhost:8080/api/zyry/zzjl/"+rec.get("ID").toString());
   			link.put("herf_spgczt", "http://localhost:8080/api/zyry/spgczt/"+rec.get("ID").toString());
   			link.put("herf_njjl", "http://localhost:8080/api/zyry/njjl/"+rec.get("ID").toString());
   			rec.put("_links", link);
   			fl.add(rec);
   		}
   		Map<String,Object> ob = new HashMap<>();
   		ob.put("data", fl);
   		return ob;
   	}
       
       public Map<String,Object> xxzl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT a.ID,a.XMING,f.MC AS xb,date_format(a.SRI,'%Y-%m-%d') as SRI,a.SFZH,a.TXDZ,a.YZBM,a.DHHM,b.ZYZGZSBH,b.ZGZSQFRQ,b.ZYZSBH,b.GRHYBH,i1.MC AS czr,i2.MC AS fqr,");
    	   sb.append(" a.RYDAZT,c.MC AS cs,d.MC AS mz,e.MC AS xl,g.MC AS zzmm,a.YDDH,h.MC AS zw,a.BYYX,date_format(a.BYSJ,'%Y-%m-%d') as BYSJ,");
    	   sb.append(" date_format(b.SWDLYWKSSJ,'%Y-%m-%d') as SWDLYWKSSJ,date_format(b.ZYZCRQ,'%Y-%m-%d') as ZYZCRQ,date_format(b.RHSJ,'%Y-%m-%d') as RHSJ,CONCAT(b.CZE,'万') CZE,a.XPIAN");
    	   sb.append(" FROM zs_ryjbxx a,zs_zysws b,dm_cs c,dm_mz d,dm_xl e,dm_xb f,dm_zzmm g,dm_zw h,dm_fqrczrsz i1,dm_fqrczrsz i2");
    	   sb.append(" WHERE a.ID=b.RY_ID AND a.XB_DM=f.ID AND b.CZR_DM=i1.ID AND b.FQR_DM=i2.ID AND a.CS_DM=c.ID AND a.MZ_DM=d.ID");
    	   sb.append("  AND a.XL_DM=e.ID AND a.ZZMM_DM=g.ID AND b.ZW_DM=h.ID AND a.ID=?");   	   
    	   String sql = " SELECT * FROM zs_jl WHERE RY_ID=?";
   		Map<String,Object> m = this.jdbcTemplate.queryForMap(sb.toString(),new Object[]{id});
   		m.put("grjl", this.jdbcTemplate.queryForList(sql,new Object[]{id}));
   		return m;
       }
       public Map<String,Object> bgjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT c.ID,a.MC,a.JZHI,a.XZHI,date_format(a.GXSJ,'%Y-%m-%d') as GXSJ");
    	   sb.append(" FROM zs_zyswslsbgxxb a,zs_zysws b, zs_ryjbxx c");
    	   sb.append(" WHERE a.ZYSWS_ID=b.ID AND b.RY_ID=c.ID AND b.RY_ID=? ORDER BY a.GXSJ");
    	   List<Map<String,Object>> bg = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", bg);
      		return ob;
      		 } 
       public Map<String,Object> zsjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT c.ID,b.MC,a.ZYSWSYJ,a.YSWSYJ,date_format(a.TBRQ,'%Y-%m-%d') as TBRQ");
    	   sb.append(" FROM  zs_zyswssndz a,dm_spzt b,zs_ryjbxx c,zs_zysws d");
    	   sb.append(" WHERE a.SPZT_DM=b.ID AND c.ID=d.RY_ID  AND  a.RY_ID=d.ID AND c.ID=?");
    	   List<Map<String,Object>> zs = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", zs);
      		return ob;
      		 } 
       public Map<String,Object> zjjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT c.ID,b.MC,a.ZJYY,a.YSWSYJ,DATE_FORMAT(a.TBRQ,'%Y-%m-%d') AS TBRQ");
    	   sb.append(" FROM  zs_zyswszj a,dm_spzt b,zs_ryjbxx c,zs_zysws d");
    	   sb.append(" WHERE a.SPZT_DM=b.ID AND c.ID=d.RY_ID  AND  a.ZYSWS_ID=d.ID AND c.ID=?");
    	   List<Map<String,Object>> zj = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", zj);
      		return ob;
      		 } 
       
       public Map<String,Object> zzjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" select f.ID,e.LCLXID,b.sjid,a.ZYSQ as zzsq,a.xdwyj as dwyj,DATE_FORMAT( a.tbrq,'%Y-%m-%d') AS tbrq,DATE_FORMAT( c.SPSJ,'%Y-%m-%d') AS spsj");
    	   sb.append(" from (select * from zs_fzyzzy union select * from zs_cyryzzy )as a, zs_spzx b, zs_spxx c, zs_splcbz d, zs_splc e, zs_zysws f");
    	   sb.append(" where a.zysws_id = f.id  and a.ID = b.sjid and c.SPID = b.ID  and c.ISPASS = 'Y'  and d.ID = c.LCBZID  and e.ID = d.LCID and e.LCLXID in (13,44) and f.ID = ?");
    	   List<Map<String,Object>> zz = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", zz);
      		return ob;
      		 } 
       public Map<String,Object> spgczt(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT a.RY_ID,b.MC AS zt");
    	   sb.append(" FROM zs_zysws a,dm_ryspgczt b");
    	   sb.append(" WHERE a.RYSPGCZT_DM=b.ID AND ZYZT_DM=1 AND a.RY_ID=?");
    	   List<Map<String,Object>> sp = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", sp);
      		return ob;
      		 } 
       public Map<String,Object> njjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT c.ID,a.ND,b.DWMC,a.SWSFZRYJ,CASE a.ZTDM WHEN 1 THEN '保存'  WHEN 2 THEN '自检' WHEN 0 THEN '退回' WHEN 3 THEN '已年检' ELSE NULL END AS njzt,");
    	   sb.append(" DATE_FORMAT( f.SPSJ,'%Y-%m-%d') AS spsj");
    	   sb.append(" FROM  zs_zcswsnj a,zs_jg b,zs_ryjbxx c,zs_zysws d,zs_spzx e,zs_spxx f,zs_splcbz g,zs_splc h");
    	   sb.append(" WHERE a.ZSJG_ID=b.ID  AND c.id=d.RY_ID    AND d.id = a.sws_id AND f.SPID=e.ID AND f.ISPASS='Y'");
    	   sb.append("  AND g.ID=f.LCBZID AND h.ID=g.LCID AND h.LCLXID='12' AND a.ZTDM= 3 AND a.ID=e.SJID  AND c.ID=? ORDER BY a.ND");
    	   List<Map<String,Object>> nj = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", nj);
      		return ob;
      		 } 
       
}
