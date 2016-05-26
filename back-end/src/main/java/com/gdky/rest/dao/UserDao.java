package com.gdky.rest.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gdky.rest.entity.Role;
import com.gdky.rest.entity.User;


@Repository
@Transactional
public class UserDao extends BaseJdbcDao{

	public List<User> getUser(String userName) {
		String sql = "select * from fw_users where username = ?";
		List<User> ls = this.jdbcTemplate.query(sql, new Object[]{userName}, new BeanPropertyRowMapper<User>(User.class));
		return ls;
	}

	public List<Role> getRolesByUser(String userName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select r.* from fw_users u, fw_user_role ur ,fw_role  r ");
		sb.append(" where u.ID = ur.USER_ID ");
		sb.append(" and ur.ROLE_ID = r.ID ");
		sb.append(" and u.USERNAME =? ");
		List<Role> ls = this.jdbcTemplate.query(sb.toString(), new Object[]{userName}, new BeanPropertyRowMapper<Role>(Role.class));
		return ls;
	}

}
