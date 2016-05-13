package gov.gdgs.zs.controllers;

import java.util.HashMap;
import java.util.Map;

import gov.gdgs.zs.dao.HYHFDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HYHFController {
	@Resource
	private HYHFDao hyhfDao;
	 @RequestMapping(value="/api/hfjnqk", method = {RequestMethod.GET})  
	 public Map<String,Object> hyhfcx(HttpServletRequest request ) { 
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
					meta.put("pageTotal",hyhfDao.hyhf(pn,ps).get("pagetotal"));
					meta.put("total_number",hyhfDao.hyhf(pn,ps).get("total_number"));
					sb.put("page",meta);
					sb.put("data", hyhfDao.hyhf(pn,ps).get("data"));
				 }
			 }
		} catch (Exception e) {
			sb.put("data", hyhfDao.hyhf1().get("data"));
		}
		 return sb;

   }
	 @RequestMapping(value="/api/grhf", method = {RequestMethod.GET})  
	 public Map<String,Object> grhf(HttpServletRequest request ) { 
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
					meta.put("pageTotal",hyhfDao.grhf1(pn,ps).get("pagetotal"));
					meta.put("total_number1",hyhfDao.grhf1(pn,ps).get("total_number1"));
					sb.put("page",meta);
					sb.put("data", hyhfDao.grhf1(pn,ps).get("data"));
				 }
			 }
		} catch (Exception e) {
			sb.put("data", hyhfDao.grhf().get("data"));
		}
		 return sb;
		 
	 }
	 @RequestMapping("/api/grhf/{grhfxqTab:^[A-Za-z]+$}/{Id:^[0-9]*$}")
	 public Map<String,Object> grhf (@PathVariable(value="grhfxqTab") String xqTab, @PathVariable(value="Id") int id){
		 Map<String,Object> sb = new HashMap<>();
		 switch(xqTab){
		 case "xx" :
			 sb.put("xx", hyhfDao.xx(id));
			 break;
		     default:
		 
			 return sb;
		 }
		 return sb;
	 }
	 
	 
}