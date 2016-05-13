package gov.gdgs.zs.service;

import gov.gdgs.zs.configuration.Config;
import gov.gdgs.zs.dao.SWSDao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SwsService {
	@Resource
	private SWSDao swsDao;
	
	public Map<String, Object> swscx(int pn, int ps, String where) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (where != null) {
			try {
				where = java.net.URLDecoder.decode(where, "UTF-8");
				ObjectMapper mapper = new ObjectMapper();
				map = mapper.readValue(where,
						new TypeReference<Map<String, Object>>() {
						});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return swsDao.swscx(pn, ps, map);
	}
	
	public Map<String, Object> swsxx(String xqTab,String gid) {
		Map<String, Object> sb = new HashMap<>();
		Hashids hashids = new Hashids(Config.HASHID_SALT,Config.HASHID_LEN);
		int jgid = (int)hashids.decode(gid)[0];
		switch (xqTab) {
		case "swsxx":
			sb.put("data", swsDao.swsxx(jgid));
			break;
		case "zyryxx":
			sb.put("data", swsDao.zyryxx(jgid));
			break;
		case "cyryxx":
			sb.put("data", swsDao.cyryxx(jgid));
			break;
		case "czrylb":
			sb.put("data", swsDao.czrylb(jgid));
			break;
		case "swsbgxx":
			sb.put("data", swsDao.swsbgxx(jgid));
			break;
		case "njjl":
			sb.put("data", swsDao.njjl(jgid));
			break;
		}
		return sb;
	}

}
