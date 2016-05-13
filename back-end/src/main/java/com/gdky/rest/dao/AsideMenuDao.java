package com.gdky.restfull.dao;


import gov.gdgs.zs.configuration.Config;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gdky.restfull.configuration.Constants;
import com.gdky.restfull.entity.AsideMenu;

@Repository
@Transactional
public class AsideMenuDao extends BaseJdbcDao implements IAsideMenuDao {

	@Override
	public List<AsideMenu> getAsideMenu(String para,String l) {
		String sql = "select * from " + Config.PROJECT_SCHEMA 
				+ "fw_menu  where pid is not null"+para+" and lx = ? order by path,order_no";
		List<AsideMenu> ls = this.jdbcTemplate.query(sql,new String[]{l},new BeanPropertyRowMapper<AsideMenu>(AsideMenu.class));
		return ls;
	}

	@Override
	public void updateMenu(AsideMenu asideMenu) {
		String sql = "update fw_menu set pid=?,name=?,href=?,order_no=?,path=?,icon=?,visble=? where id=?";
		this.jdbcTemplate.update(
				sql,
				new Object[] { asideMenu.getPid(), asideMenu.getName(),
						asideMenu.getHref(), asideMenu.getOrderNo(),
						asideMenu.getPath(), asideMenu.getIcon(),
						asideMenu.getVisble(), asideMenu.getId() });
	}

	@Override
	public AsideMenu getMenuDetail(String id) {
		String sql = "select * from fw_menu where id = ?";
		AsideMenu rs = this.jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new BeanPropertyRowMapper<AsideMenu>(
						AsideMenu.class));
		return rs;
	}

	@Override
	public String getPathById(Integer id) {
		String sql = "select path from " + Config.PROJECT_SCHEMA
				+ "fw_menu where id = ?";
		String rs = jdbcTemplate.queryForObject(sql, new Object[] { id },
				String.class);
		return rs;
	}

	@Override
	public Number addMenu(AsideMenu item) {
		final StringBuffer sb = new StringBuffer("insert into "
				+ Config.PROJECT_SCHEMA + "fw_menu ");
		sb.append("(pid,name,path,visble,lx) ");
		sb.append("values (?,?,?,?,?)");
		Object[] arg = new Object[] { item.getPid(), item.getName(),
				item.getPath(), item.getVisble(),item.getLx() };

		Number rs = this.insertAndGetKeyByJdbc(sb.toString(), arg,
				new String[] { "id" });
		return rs;

	}

	@Override
	public int removeMenu(AsideMenu menu) {
		String sql = "delete from " + Config.PROJECT_SCHEMA
				+ "fw_menu where path like concat(?,'-',lpad(?,3,0),'%') or id = ?";
		int rs = this.jdbcTemplate.update(sql, new Object[] { menu.getPath(),
				menu.getId(), menu.getId() });
		return rs;
	}

}
