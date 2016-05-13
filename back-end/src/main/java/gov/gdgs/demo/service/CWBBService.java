package gov.gdgs.zs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gov.gdgs.zs.configuration.Config;
import gov.gdgs.zs.dao.CWBBDao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gdky.restfull.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.hashids.Hashids;

@Service
public class CWBBService {

	@Resource
	private CWBBDao cwbbDao;

	public Map<String, Object> zcmx(int page, int pageSize, String where) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (where != null) {
			try {
				where = java.net.URLDecoder.decode(where, "UTF-8");
				ObjectMapper mapper = new ObjectMapper();
				map = mapper.readValue(where,
						new TypeReference<Map<String, Object>>() {
						});
			} catch (Exception e) {
			}
		}
		Map<String, Object> rs = cwbbDao.zcmx(page, pageSize, map);
		Hashids hashids = new Hashids(Config.HASHID_SALT);
		ArrayList<Map<String, Object>> ls = (ArrayList<Map<String, Object>>) rs
				.get("data");
		for (Map<String, Object> item : ls) {
			String id = hashids.encode();
			System.out.println(id);
		}

		return rs;
	}

	public Map<String, Object> getLrb(int page, int pageSize,
			String where) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (where != null) {
			try {
				where = java.net.URLDecoder.decode(where, "UTF-8");
				ObjectMapper mapper = new ObjectMapper();
				map = mapper.readValue(where,
						new TypeReference<Map<String, Object>>() {
						});
			} catch (Exception e) {
			}
		}
		Map<String, Object> rs = cwbbDao.getLrb(page, pageSize, map);
		return rs;
	}

	public Map<String, Object> getLrbById(String id) {
		Map<String,Object> obj = cwbbDao.getLrbById(id);
		return obj;
	}

	public Map<String, Object> getZcfzb(int page, int pageSize, String where) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (where != null) {
			try {
				where = java.net.URLDecoder.decode(where, "UTF-8");
				ObjectMapper mapper = new ObjectMapper();
				map = mapper.readValue(where,
						new TypeReference<Map<String, Object>>() {
						});
			} catch (Exception e) {
			}
		}
		Map<String, Object> rs = cwbbDao.getZcfzb(page, pageSize, map);
		return rs;
	}

	public Map<String, Object> getZcfzbById(String id) {
		Map<String,Object> obj = cwbbDao.getZcfzbById(id);
		return obj;
	}

}
