package gov.gdgs.zs.service;

import java.util.List;
import java.util.Map;

import gov.gdgs.zs.dao.DMDao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class DMService {
	@Resource
	private DMDao dmDao;

	public List<Map<String, Object>> getDM_cs() {
		return dmDao.getDM_cs();
	}
}
