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
public class FZYZCSWSDao {
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
       public Map<String,Object> fzyrycx1(int pn,int ps){
    	   StringBuffer sb= new StringBuffer();
    	   sb.append("SELECT  ");
    	   sb.append("  a.ID,a.XMING,a.SRI,b.MC AS xb,c.MC AS xl,a.ZYZGZSBH,a.ZZDW,d.MC AS zt");
    	   sb.append("  FROM zs_fzysws a,dm_xb b,dm_xl c,dm_ryzt d");
    	   sb.append("   WHERE a.XB_DM=b.ID AND a.XL_DM=c.ID AND a.RYZT_DM=d.ID  AND d.ID='1'");
    	   List<Map<String,Object>> ls= this.jdbcTemplate.queryForList(sb.toString());
    	   Pager<Map<String,Object>> pager =Pager.create(ls,ps);
           Map<String,Object> ob =new HashMap<>();
           ob.put("data", pager.getPagedList(pn));
           ob.put("total number", ls.size());
           ob.put("pagetotal" , (ls.size()+ps-1)/ ps);
           return ob;
       }
       public Map<String,Object> fzyrycx(){
      		StringBuffer sb = new StringBuffer();
      	   sb.append("SELECT  ");
   	       sb.append("  a.ID,a.XMING as xm,DATE_FORMAT(a.sri,'%Y-%m-%d') AS csrq, b.MC as xb, c.MC as xl, a.ZYZGZSBH, a.zzdw, case a.ryzt_DM when 1 then '有效'  when 2 then '无效' ELSE null end as dqzt");
   	       sb.append("  from zs_fzysws a,dm_xb b,dm_xl c ");
   	       sb.append("   where a.ryzt_dm = 1 and a.XB_DM =b.ID and a.XL_DM = c.ID  order by a.ID");
      		List<Map<String,Object>> ls = this.jdbcTemplate.queryForList(sb.toString());
      		List<Map<String,Object>> fl = new ArrayList<Map<String,Object>>();
      		for(Map<String, Object> rec : ls){
      			Map<String,Object> link = new HashMap<>();
      			link.put("herf_xxzl", "http://localhost:8080/api/fzyry/xxzl/"+rec.get("ID").toString());
      			link.put("herf_bgjl", "http://localhost:8080/api/fzyry/bgjl/"+rec.get("ID").toString());
      			link.put("herf_zxjl", "http://localhost:8080/api/fzyry/zxjl/"+rec.get("ID").toString());
      			link.put("herf_zjjl", "http://localhost:8080/api/fzyry/zjjl/"+rec.get("ID").toString());
      			link.put("herf_zfjl", "http://localhost:8080/api/fzyry/zfjl/"+rec.get("ID").toString());
      			rec.put("_links", link);
      			fl.add(rec);
      		}
      		Map<String,Object> ob = new HashMap<>();
      		ob.put("data", fl);
      		return ob;
      	}
       public Map<String,Object> xxzl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT a.ID,a.XMING,b.MC AS xb,a.SRI,a.SFZH,a.TXDZ,a.YZBM,a.DHHM,a.ZYZGZSBH, a.FZYHYBH,a.ZGZSQFRQ,a.FZYZCZSBH,");
    	   sb.append(" c.MC AS cs,d.MC AS mz,e.MC AS xl,f.MC AS zzmm,a.YDDH,g.MC AS zw,a.BYYX,a.BYSJ,a.RHSJ,a.FZYZCRQ,a.XPIAN");
    	   sb.append(" FROM zs_fzysws a,dm_xb b,dm_cs c,dm_mz d,dm_xl e,dm_zzmm f,dm_zw g");
    	   sb.append(" WHERE a.XB_DM=b.ID AND a.CS_DM=c.ID AND a.MZ_DM=d.ID ");
    	   sb.append("  AND a.XL_DM=e.ID AND a.ZZMM_DM=f.ID AND a.ZW_DM=g.ID AND a.RYZT_DM=1 AND a.id =?");	   
    	   String sql = " SELECT a.FZY_ID,a.QZNY,a.XXXX,a.ZMR FROM zs_fzyjl a WHERE a.FZY_ID=? ORDER BY a.QZNY";
   		Map<String,Object> m = this.jdbcTemplate.queryForMap(sb.toString(),new Object[]{id});
   		m.put("grjl", this.jdbcTemplate.queryForList(sql,new Object[]{id}));
   		return m;
       }
       public Map<String,Object> bgjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT a.FZY_ID,a.MC,a.JZHI,a.XZHI,a.GXSJ ");
    	   sb.append(" FROM zs_fzylsbgxxb a");
    	   sb.append(" WHERE a.FZY_ID=? ");
    	   List<Map<String,Object>> bg = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", bg);
      		return ob;
      		 } 
       
       public Map<String,Object> zxjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT a.FZY_ID,a.ZXYY,a.BDRQ,a.LRR ");
    	   sb.append(" FROM  zs_fzyzx a");
    	   sb.append(" WHERE a.FZY_ID=? ");
    	   List<Map<String,Object>> zx = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", zx);
      		return ob;
      		 } 
       public Map<String,Object> zjjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" SELECT a.FZY_ID,a.ZJYY,a.ZJYYRQ,a.TBRQ");
    	   sb.append(" FROM zs_fzyswszj a");
    	   sb.append(" WHERE a.FZY_ID=?");
    	   List<Map<String,Object>> zj = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", zj);
      		return ob;
      		 } 
       public Map<String,Object> zfjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" select distinct a.fzysws_ID,e.LCLXID,b.sjid,a.fZYSQ as zzsq,a.xdwyj as dwyj,DATE_FORMAT( a.tbrq,'%Y-%m-%d') AS bdrq, DATE_FORMAT( c.SPSJ,'%Y-%m-%d') AS spsj,g.DWMC as YDWMC ");
    	   sb.append("  from zs_zyswszfzy a, zs_spzx b, zs_spxx c, zs_splcbz d, zs_splc e,  zs_zysws f, zs_jg g");
    	   sb.append(" where a.zysws_id = f.id and f.JG_ID = g.ID and a.ID = b.sjid and c.SPID = b.ID  and c.ISPASS = 'Y'  and d.ID = c.LCBZID  and e.ID = d.LCID and a.fzysws_ID =?");
    	   List<Map<String,Object>> zf = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", zf);
      		return ob;
      		 } 
}
