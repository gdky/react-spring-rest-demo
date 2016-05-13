package gov.gdgs.zs.api;

import java.util.Map;

import javax.annotation.Resource;

import gov.gdgs.zs.configuration.Config;
import gov.gdgs.zs.service.TsjyService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * 投诉建议功能api
 * @author ming
 *
 */
@RestController
@RequestMapping(value = Config.URL_PROJECT)
public class TsjyController {

	@Resource
	private TsjyService tsjyService;
	
	@RequestMapping(value = "/tsjy", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addTsjy(@RequestBody Map<String, Object> node) {
		Map<String,Object> rs = tsjyService.addTsjy(node);
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
}
