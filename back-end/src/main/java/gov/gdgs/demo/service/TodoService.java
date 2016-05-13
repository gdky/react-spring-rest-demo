package gov.gdgs.demo.service;

import gov.gdgs.demo.dao.TodoDao;
import gov.gdgs.demo.entity.Todo;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service
public class TodoService {
	
	@Resource
	TodoDao todoDao;
	
	public List<Todo> getTodos() {
		List<Todo> ls = todoDao.getTodos();
		return ls;
	}

	public List<Todo> addTodo(Map<String, Object> todo) {
		return null;
	}

	public void removeTodo(String id) {
		
	}

	

	public void updateTodo(Map<String, Object> map) {
		
	}
	
}
