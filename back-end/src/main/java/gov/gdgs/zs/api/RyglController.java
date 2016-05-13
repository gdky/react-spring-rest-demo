package gov.gdgs.zs.api;

import java.util.Map;

import javax.annotation.Resource;

import gov.gdgs.zs.configuration.Config;
import gov.gdgs.zs.service.RyglService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Config.URL_PROJECT)
public class RyglController {
	@Resource
	private RyglService ryglService;
	
	@RequestMapping(value = "/rys", method = RequestMethod.GET )
	public ResponseEntity<Map<String, Object>> zyrycx(
			@RequestParam(value = "pagenum", required = true) int pn,
			@RequestParam(value = "pagesize", required = true) int ps,
			@RequestParam(value="where", required=false) String where)  {
		
		return new ResponseEntity<>(ryglService.rycx(pn, ps, where),HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/ryxx/{zyryxqTab:^[A-Za-z]+$}/{zyryId}", method = RequestMethod.GET )
	public ResponseEntity<Map<String, Object>> swsxx(
			@PathVariable(value = "zyryxqTab") String xqTab,
			@PathVariable(value = "zyryId") String gid) {
		return new ResponseEntity<>(ryglService.kzxx(xqTab, gid),HttpStatus.OK);
		
	}
		
}
