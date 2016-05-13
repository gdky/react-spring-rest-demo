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
public class QtcyryDao {
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
       public Map<String,Object> cyrycx1(int pn,int ps){
    	   StringBuffer sb= new StringBuffer();
    	   sb.append(" select a.ID,b.XMING,date_format(b.SRI,'%Y-%m-%d') as SRI,b.SFZH,d.MC as xb,e.MC as xl,a.XZSNGZGW,a.ZGXLZYMC,c.DWMC");
    	   sb.append(" from zs_cyry a,zs_ryjbxx b,zs_jg c,dm_xb d,dm_xl e");
    	   sb.append(" where a.RY_ID = b.ID and a.JG_ID = c.ID and b.xb_dm = d.ID and b.XL_DM = e.ID and c.id <>'-2'");
    	   List<Map<String,Object>> ls= this.jdbcTemplate.queryForList(sb.toString());
    	   Pager<Map<String,Object>> pager =Pager.create(ls,ps);
           Map<String,Object> ob =new HashMap<>();
           ob.put("data", pager.getPagedList(pn));
           ob.put("total number", ls.size());
           ob.put("pagetotal" , (ls.size()+ps-1)/ ps);
           return ob;
           
       }
       public Map<String,Object> cyrycx(){
      		StringBuffer sb = new StringBuffer();
      		 sb.append(" select a.ID,b.XMING,date_format(b.SRI,'%Y-%m-%d') as SRI,b.SFZH,d.MC as xb,e.MC as xl,a.XZSNGZGW,a.ZGXLZYMC,c.DWMC");
      	   sb.append(" from zs_cyry a,zs_ryjbxx b,zs_jg c,dm_xb d,dm_xl e");
      	   sb.append(" where a.RY_ID = b.ID and a.JG_ID = c.ID and b.xb_dm = d.ID and b.XL_DM = e.ID and c.id <>'-2'");
      		List<Map<String,Object>> ls = this.jdbcTemplate.queryForList(sb.toString());
      		List<Map<String,Object>> fl = new ArrayList<Map<String,Object>>();
      		for(Map<String, Object> rec : ls){
      			Map<String,Object> link = new HashMap<>();
      			link.put("herf_xxzl", "http://localhost:8080/api/cyry/xxzl/"+rec.get("ID").toString());
      			link.put("herf_bgjl", "http://localhost:8080/api/cyry/bgjl/"+rec.get("ID").toString());
      			rec.put("_links", link);
      			fl.add(rec);
      		}
      		Map<String,Object> ob = new HashMap<>();
      		ob.put("data", fl);
      		return ob;
      	}
       public Map<String,Object> xxzl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" select a.ID,b.XMING, f.MC as cs, d.MC as xb, g.MC as mz, date_format(b.SRI,'%Y-%m-%d') as SRI, e.MC as xl, b.SFZH,");
    	   sb.append(" h.MC as zzmm, b.TXDZ,b.YDDH,b.YZBM,i.MC as zw,b.DHHM,b.BYYX,a.XZSNGZGW,date_format(b.BYSJ,'%Y-%m-%d') as BYSJ,");
    	   sb.append(" date_format(a.LRSJ,'%Y-%m-%d') as LRSJ,date_format(a.SWDLYWKSSJ,'%Y-%m-%d') as SWDLYWKSSJ,a.ZGXLZYMC,date_format(a.ZGXLFZJGJSJ,'%Y-%m-%d') as ZGXLFZJGJSJ,b.RYDAZT");
    	   sb.append(" from zs_cyry a,zs_ryjbxx b,zs_jg c,dm_xb d,dm_xl e,dm_cs f,dm_mz g,dm_zzmm h,dm_zw i");
    	   sb.append(" where a.RY_ID = b.ID and a.JG_ID = c.ID and b.xb_dm = d.ID and b.XL_DM = e.ID ");
    	   sb.append(" and b.CS_DM = f.ID and b.MZ_DM = g.ID and b.ZZMM_DM = h.ID and a.ZW_DM = i.ID and c.id <> '-2' and a.ID = ?");   	   
    	   String sql = " SELECT c.ID,a.QZNY,a.XXXX,a.ZMR FROM zs_jl a,zs_cyry c WHERE a.ry_id = c.ry_id  AND c.ID = ?  ORDER BY a.QZNY";
   		Map<String,Object> m = this.jdbcTemplate.queryForMap(sb.toString(),new Object[]{id});
   		m.put("grjl", this.jdbcTemplate.queryForList(sql,new Object[]{id}));
   		return m;
       }
       public Map<String,Object> bgjl(int id){
    	   StringBuffer sb = new StringBuffer();
    	   sb.append(" select a.cyry_id,a.MC,a.JZHI,a.XZHI,date_format(a.GXSJ,'%Y-%m-%d') as GXSJ");
    	   sb.append(" from zs_cyrylsbgxxb a");
    	   sb.append(" where a.cyry_id = ? ");
    	   List<Map<String,Object>> bg = this.jdbcTemplate.queryForList(sb.toString(),new Object[]{id});
    	   Map<String,Object> ob = new HashMap<>();
   		    ob.put("Data", bg);
      		return ob;
      		 }

}
