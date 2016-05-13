package gov.gdgs.zs.dao;

import gov.gdgs.zs.configuration.Config;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gdky.restfull.dao.BaseJdbcDao;

@Repository
public class DMDao extends BaseJdbcDao {

	public List<Map<String, Object>> getDM_cs() {
		String sql = "select id,mc from "+Config.PROJECT_SCHEMA+"dm_cs t where t.PARENT_ID = 0 ";
		List<Map<String,Object>> ls = jdbcTemplate.queryForList(sql);
		return ls;
	}

}
