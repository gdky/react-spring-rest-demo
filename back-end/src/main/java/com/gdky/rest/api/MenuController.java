package com.gdky.restfull.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdky.restfull.configuration.Constants;
import com.gdky.restfull.entity.AsideMenu;
import com.gdky.restfull.entity.ResponseMessage;
import com.gdky.restfull.service.ICommonService;

@RestController
@RequestMapping(value = Constants.URI_API_FRAMEWORK_PRIFIX)
public class MenuController {
	private static final Logger log = LoggerFactory
            .getLogger(MenuController.class);

	@Resource
	private ICommonService commonService;

	/**
	 * 定义模块菜单类api
	 * q= all 时取全部模块，包括隐藏的
	 * l = A 管理端类型， l=B 客户端类型
	 * @return
	 */
	@RequestMapping(value = "/asidemenu", method = RequestMethod.GET)
	public  ResponseEntity<List<AsideMenu>> getAsideMenu(
			@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "l", required = true) String l) {
		List<AsideMenu> ls = commonService.getAsideMenu(q,l);
		return new ResponseEntity<>(ls,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/asidemenu/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseMessage> updateMenu(@PathVariable("id") String id,
			@RequestBody AsideMenu asideMenu) {
		
		commonService.updateMenu(asideMenu);
		return new ResponseEntity<>(ResponseMessage.success("更新成功"),HttpStatus.OK);

	}
	
	@RequestMapping(value = "/asidemenu/{id}",method = RequestMethod.GET)
	public ResponseEntity<AsideMenu> getMenuDetail(@PathVariable("id") String id){
		AsideMenu rs = this.commonService.getMenuDetail(id);
		return  new ResponseEntity<>(rs, HttpStatus.OK);
	}

	@RequestMapping(value = "/asidemenu", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addMenu(@RequestBody Map<String, Object> node) {
		Map<String,Object> rs = commonService.addMenu(node);
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/asidemenu/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<AsideMenu>> removeMenu(
			@PathVariable("id") String id,
			@RequestParam(value = "l", required = true) String l) {
		
		commonService.removeMenu(id);
		
		String q = "all";				
		List<AsideMenu> ls = commonService.getAsideMenu(q,l);
		
		return new ResponseEntity<>(ls,HttpStatus.OK);

	}
	
}
