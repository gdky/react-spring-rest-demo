package com.gdky.restfull.dao;


import gov.gdgs.zs.configuration.Config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hashids.Hashids;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gdky.restfull.entity.AsideMenu;
import com.gdky.restfull.entity.Role;
import com.gdky.restfull.entity.User;

@Repository
@Transactional
public class UserDao extends BaseJdbcDao{

	public List<User> getUser(String userName) {
		String sql = "select * from fw_users where username = ?";
		List<User> ls = this.jdbcTemplate.query(sql, new Object[]{userName}, new BeanPropertyRowMapper<User>(User.class));
		
		/*List<User> ls = this.jdbcTemplate.query(sql, new Object[]{userName}, new RowMapper<User>(){
			public User mapRow(ResultSet rs, int arg1) throws SQLException{
				User user = new User();
				user.setAccountEnabled((Integer)rs.getObject("ACCOUNT_ENABLED"));
				user.setAccountExpired((Integer)rs.getObject("ACCOUNT_EXPIRED"));
				user.setAccountLocked((Integer)rs.getObject("ACCOUNT_LOCKED"));
				user.setCredentialsExpired((Integer)rs.getObject("CREDENTIALS_EXPIRED"));
				user.setId((Integer)rs.getObject("ID"));
				user.setNames((String)rs.getObject("NAMES"));
				user.setPassword((String)rs.getObject("PASSWORD"));
				user.setPasswordHint((String)rs.getObject("PASSWORD_HINT"));
				user.setUsername((String)rs.getObject("USERNAME"));
				return user;
			}
		});*/
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
