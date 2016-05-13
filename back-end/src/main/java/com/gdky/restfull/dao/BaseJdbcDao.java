package com.gdky.restfull.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class BaseJdbcDao {

	protected JdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	/**
	 * 插入一条记录并返回主键ID
	 * 
	 * @param sqlStatement  sql语句
	 * @param obj           sql使用的变量
	 * @param idColumnName  要返回的id列名
	 * @return 
	 * @return
	 */
	public  Number insertAndGetKeyByJdbc(final String sqlStatement, final Object[] obj,
			final String[] idColumnName) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						sqlStatement, idColumnName);

				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey();
	}

}
