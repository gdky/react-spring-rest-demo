package gov.gdgs.zs.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

import gov.gdgs.zs.configuration.Config;
import gov.gdgs.zs.dao.CWBBDao;
import gov.gdgs.zs.service.CWBBService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = Config.URL_PROJECT)
public class CWBBController {

	@Resource
	private CWBBService cwbbService;

	@RequestMapping(value = "/zcmx", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> zcmx(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "where", required = false) String where) {
		Map<String, Object> obj = cwbbService.zcmx(page, pageSize, where);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cwbb/lrb", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getLrb(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "where", required = false) String where) {
		Map<String,Object> obj = cwbbService.getLrb(page,pageSize,where);
		return new ResponseEntity<>(obj,HttpStatus.OK);

	}
	@RequestMapping(value = "/cwbb/lrb/{id}", method = RequestMethod.GET)
	public  ResponseEntity<Map<String,Object>> getLrbById(@PathVariable("id") String id){ 
		
		Map<String,Object> obj = cwbbService.getLrbById(id);
		return new ResponseEntity<>(obj,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cwbb/zcfzb", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getZcfzb(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "where", required = false) String where) {
		Map<String,Object> obj = cwbbService.getZcfzb(page,pageSize,where);
		return new ResponseEntity<>(obj,HttpStatus.OK);

	}
	
	@RequestMapping(value = "/cwbb/zcfzb/{id}", method = RequestMethod.GET)
	public  ResponseEntity<Map<String,Object>> getZcfzbById(@PathVariable("id") String id){ 
		
		Map<String,Object> obj = cwbbService.getZcfzbById(id);
		return new ResponseEntity<>(obj,HttpStatus.OK);
	}


	

}
