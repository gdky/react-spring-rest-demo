package gov.gdgs.zs.controllers;

import java.util.HashMap;
import java.util.Map;

import gov.gdgs.zs.dao.FZYZCSWSDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FZYZCSWSController {
	 @Resource
	 private FZYZCSWSDao fzyzcswsDao;
	 @RequestMapping(value="/api/fzyry", method = {RequestMethod.GET})  
	 public Map<String,Object> fzyrycx(HttpServletRequest request ) { 
		 Map<String,Object> sb = new HashMap<>();
		 try {
			 if(request.getParameterValues("pagenum")[0]!=null&&!request.getParameterValues("pagenum")[0].equals("0")){
				 if(request.getParameterValues("pagesize")[0]!=null&&!request.getParameterValues("pagesize")[0].equals("0")){
					Map<String,Object> qury = new HashMap<>();
					qury.put("pn", request.getParameterValues("pagenum")[0]);
					qury.put("ps", request.getParameterValues("pagesize")[0]);
					int pn =Integer.parseInt(qury.get("pn").toString());
					int ps =Integer.parseInt(qury.get("ps").toString());
					Map<String,Object> meta = new HashMap<>();
					meta.put("pageNum",pn);
					meta.put("pageSize",ps);
					meta.put("pageTotal",fzyzcswsDao.fzyrycx1(pn,ps).get("pagetotal"));
					meta.put("total number",fzyzcswsDao.fzyrycx1(pn,ps).get("total number"));
					sb.put("Page",meta);
					sb.put("Data", fzyzcswsDao.fzyrycx1(pn,ps).get("data"));
				 }
			 }
		} catch (Exception e) {
			sb.put("Data", fzyzcswsDao.fzyrycx().get("data"));
		}
		 return sb;
		 
	 }
	 @RequestMapping("/api/fzyry/{fzyryxqTab:^[A-Za-z]+$}/{ryId:^[0-9]*$}")
	 public Map<String,Object> fzyryxx (@PathVariable(value="fzyryxqTab") String xqTab, @PathVariable(value="ryId") int id){
		 Map<String,Object> sb = new HashMap<>();
		 switch(xqTab){
		 case "xxzl" :
			 sb.put("xxzl", fzyzcswsDao.xxzl(id));
			 break;
		 case "bgjl" :
			 sb.put("bgjl",fzyzcswsDao.bgjl(id));
			 break;
		 case "zxjl" :
			 sb.put("zxjl",fzyzcswsDao.zxjl(id) );
			 break;
		 case "zjjl" :
			 sb.put("zjjl",fzyzcswsDao.zjjl(id) );
			 break;
		 case "zfjl" :
			 sb.put("zfjl", fzyzcswsDao.zfjl(id));
			 break;
		 default:
			 return sb;
		 } 
		 return sb;
	 }
	 
}



