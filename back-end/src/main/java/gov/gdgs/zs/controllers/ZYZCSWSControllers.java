package gov.gdgs.zs.controllers;

import java.util.HashMap;
import java.util.Map;

import gov.gdgs.zs.dao.ZYZCSWSDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ZYZCSWSControllers {
	 @Resource
	 private ZYZCSWSDao zyzcswsDao;
	 @RequestMapping(value="/api/zyry", method = {RequestMethod.GET})  
	 public Map<String,Object> zyrycx(HttpServletRequest request ) { 
		 Map<String,Object> sb = new HashMap<>();
		 try {
			 if(request.getParameterValues("pagenum")[0]!=null&&!request.getParameterValues("pagenum")[0].equals("0")){
				 if(request.getParameterValues("pagesize")[0]!=null&&!request.getParameterValues("pagesize")[0].equals("0")){
					Map<String,Object> qury = new HashMap<>();
					qury.put("pn", request.getParameterValues("pagenum")[0]);
					qury.put("ps", request.getParameterValues("pagesize")[0]);
//					Object y =request.getParameterValues("qury");
// 					String q =java.net.URLDecoder.decode(request.getParameterValues("qury")[0].toString(),"UTF-8");
//					qury.put("qury", q);
					int pn =Integer.parseInt(qury.get("pn").toString());
					int ps =Integer.parseInt(qury.get("ps").toString());
					Map<String,Object> meta = new HashMap<>();
					meta.put("pageNum",pn);
					meta.put("pageSize",ps);
					meta.put("pageTotal",zyzcswsDao.zyrycx1(pn,ps).get("pagetotal"));
					meta.put("total number",zyzcswsDao.zyrycx1(pn,ps).get("total number"));
					sb.put("Page",meta);
					sb.put("Data", zyzcswsDao.zyrycx1(pn,ps).get("data"));
				 }
			 }
		} catch (Exception e) {
			sb.put("Data", zyzcswsDao.zyrycx().get("data"));
		}
		 return sb;
		 
	 }
	 @RequestMapping("/api/zyry/{zyryxqTab:^[A-Za-z]+$}/{ryId:^[0-9]*$}")
	 public Map<String,Object> zyryxx (@PathVariable(value="zyryxqTab") String xqTab, @PathVariable(value="ryId") int id){
		 Map<String,Object> sb = new HashMap<>();
		 switch(xqTab){
		 case "xxzl" :
			 sb.put("xxzl", zyzcswsDao.xxzl(id));
			 break;
		 case "bgjl" :
			 sb.put("bgjl", zyzcswsDao.bgjl(id));
			 break;
		 case "zsjl" :
			 sb.put("zsjl", zyzcswsDao.zsjl(id));
			 break;
		 case "zjjl" :
			 sb.put("zjjl", zyzcswsDao.zjjl(id));
			 break;
		 case "zzjl" :
			 sb.put("zzjl", zyzcswsDao.zzjl(id));
			 break;
		 case "spgczt" :
			 sb.put("spgczt", zyzcswsDao.spgczt(id));
			 break;
		 case "njjl" :
			 sb.put("njjl", zyzcswsDao.njjl(id));
			 break;
		 default:
			 return sb;
		 } 
		 return sb;
	 }
	 
}
