package gov.gdgs.zs.api;

import gov.gdgs.zs.configuration.Config;
import gov.gdgs.zs.service.DMService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/** 代码表有关api **/
@RestController
@RequestMapping(value = Config.URL_PROJECT)
public class DMController {
	
	@Resource
	private DMService dmService;

	
	/**
	 * 获取城市代码表
	 * @para
	 *
	 */
	@RequestMapping(value = "/dm/cs", method = RequestMethod.GET)
	public  ResponseEntity<List<Map<String,Object>>> getDM_cs(){
		List<Map<String,Object>> ls = dmService.getDM_cs();
		return new ResponseEntity<List<Map<String,Object>>>(ls, HttpStatus.OK);
	}
}
