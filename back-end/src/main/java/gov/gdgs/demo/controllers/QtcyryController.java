package gov.gdgs.zs.controllers;

import java.util.HashMap;
import java.util.Map;

import gov.gdgs.zs.dao.QtcyryDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QtcyryController {
	@Resource
	private QtcyryDao qtcyryDao;
	 @RequestMapping(value="/api/cyry", method = {RequestMethod.GET})  
	 public Map<String,Object> cyrycx(HttpServletRequest request ) { 
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
					meta.put("pageTotal",qtcyryDao.cyrycx1(pn,ps).get("pagetotal"));
					meta.put("total number",qtcyryDao.cyrycx1(pn,ps).get("total number"));
					sb.put("Page",meta);
					sb.put("Data", qtcyryDao.cyrycx1(pn,ps).get("data"));
				 }
			 }
		} catch (Exception e) {
			sb.put("Data", qtcyryDao.cyrycx().get("data"));
		}
		 return sb;
		 
	 }
	 @RequestMapping("/api/cyry/{cyryxqTab:^[A-Za-z]+$}/{ryId:^[0-9]*$}")
	 public Map<String,Object> cyryxx (@PathVariable(value="cyryxqTab") String xqTab, @PathVariable(value="ryId") int id){
		 Map<String,Object> sb = new HashMap<>();
		 switch(xqTab){
		 case "xxzl" :
			 sb.put("xxzl", qtcyryDao.xxzl(id));
			 break;
		 case "bgjl" :
			 sb.put("bgjl",qtcyryDao.bgjl(id));
			 break;
		
		 default:
			 return sb;
		 } 
		 return sb;
	 }
	 

}
