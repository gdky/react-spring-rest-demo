package gov.gdgs.zs.api;

import gov.gdgs.zs.configuration.Config;
import gov.gdgs.zs.service.SwsService;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdky.restfull.configuration.Constants;

@RestController
@RequestMapping(value = Constants.URI_API_PREFIX + Config.URI_API_ZS)
public class SWSCXController {
	@Resource
	private SwsService swsService;

	@RequestMapping(value = "/jgs", method = { RequestMethod.GET })
	public ResponseEntity<Map<String, Object>> swscx(
			@RequestParam(value = "pagenum", required = true) int pn,
			@RequestParam(value = "pagesize", required = true) int ps,
			@RequestParam(value="where", required=false) String where)  {
		
		return new ResponseEntity<>(swsService.swscx(pn, ps, where),HttpStatus.OK);

	}

	@RequestMapping(value="/{swsxqTab:^[A-Za-z]+$}/{swjgId}", method = { RequestMethod.GET} )
	public ResponseEntity<Map<String, Object>> swsxx(
			@PathVariable(value = "swsxqTab") String xqTab,
			@PathVariable(value = "swjgId") String gid) {
		return new ResponseEntity<>(swsService.swsxx(xqTab, gid),HttpStatus.OK);
	}
}