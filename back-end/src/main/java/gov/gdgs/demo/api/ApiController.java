package gov.gdgs.demo.api;

import gov.gdgs.demo.entity.Todo;
import gov.gdgs.demo.service.TodoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdky.rest.entity.ResponseMessage;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
	
	@Autowired
	TodoService todoService;
	
   
    public ApiController(){
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>  API 启动    >>>>>>>>>>>>>>>>>>>>>");
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getApiList() {

		Map<String, Object> obj = new HashMap<String,Object>();
		obj.put("project", "gd_zs_mis");
		obj.put("version", "1.0");
		obj.put("apis", "12");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
    
       
    @RequestMapping(value = "/todos", method = RequestMethod.GET)
	public  ResponseEntity<List<Todo>> getTodos(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize) {
		List<Todo> ls = todoService.getTodos();
		return new ResponseEntity<>(ls,HttpStatus.OK);
	}
	

	@RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseMessage> updateTodo(
			@PathVariable("id") String id,
			@RequestBody Map<String,Object> map) {
		
		todoService.updateTodo(map);
		return new ResponseEntity<>(ResponseMessage.success("更新成功"),HttpStatus.OK);

	}


	@RequestMapping(value = "/todos", method = RequestMethod.POST)
	public ResponseEntity<List<Todo>> addTodo(@RequestBody Map<String, Object> todo) {
		List<Todo> rs = todoService.addTodo(todo);
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<Todo>> removeTodo(
			@PathVariable("id") String id) {
		
		todoService.removeTodo(id);
		
		String q = "all";				
		List<Todo> ls = todoService.getTodos();
		
		return new ResponseEntity<>(ls,HttpStatus.OK);

	}
}
