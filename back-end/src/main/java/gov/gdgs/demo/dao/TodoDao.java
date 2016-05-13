package gov.gdgs.demo.dao;

import gov.gdgs.demo.entity.Todo;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gdky.rest.dao.BaseJdbcDao;

@Repository
public class TodoDao extends BaseJdbcDao{

	public List<Todo> getTodos() {
		
		String sql = " select * from todo t ";		
		return  this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<Todo>(Todo.class));
	}
	
	
}
