package gov.gdgs.zs.service;

import java.util.HashMap;
import java.util.Map;

import gov.gdgs.zs.configuration.Config;
import gov.gdgs.zs.dao.RyglDao;

import javax.annotation.Resource;

import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RyglService {
	@Resource
	private RyglDao ryglDao;
	
	public Map<String, Object> rycx(int pn, int ps, String where) {
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
		return ryglDao.rycx(pn,ps,map);
	}
	
	public Map<String, Object> kzxx(String xqTab,String gid) {
		Map<String, Object> sb = new HashMap<>();
		Hashids hashids = new Hashids(Config.HASHID_SALT,Config.HASHID_LEN);
		int ryid = (int)hashids.decode(gid)[0];
		switch (xqTab) {
		case "zyryxx":
			sb.put("data", ryglDao.zyryxx(ryid));
			break;
		case "zyrybgjl":
			sb.put("data", ryglDao.zyrybgjl(ryid));
			break;
		case "zyryzsjl":
			sb.put("data", ryglDao.zyryzsjl(ryid));
			break;
		case "zyryzjjl":
			sb.put("data", ryglDao.zyryzjjl(ryid));
			break;
		case "zyryzzjl":
			sb.put("data", ryglDao.zyryzzjl(ryid));
			break;
		case "zyryspzt":
			sb.put("data", ryglDao.zyryspzt(ryid));
			break;
		case "zyrynjjl":
			sb.put("data", ryglDao.zyrynjjl(ryid));
			break;
		case "fzyryxx":
			sb.put("data", ryglDao.fzyryxx(ryid));
			break;
		case "fzyrybgjl":
			sb.put("data", ryglDao.fzyrybgjl(ryid));
			break;
		case "fzyryzxjl":
			sb.put("data", ryglDao.fzyryzxjl(ryid));
			break;
		case "fzyryzjjl":
			sb.put("data", ryglDao.fzyryzjjl(ryid));
			break;
		case "fzyryzfjl":
			sb.put("data", ryglDao.fzyryzfjl(ryid));
			break;
		case "cyryxx":
			sb.put("data", ryglDao.cyryxx(ryid));
			break;
		case "cyrybgjl":
			sb.put("data", ryglDao.cyrybgjl(ryid));
			break;
		}
		return sb;
		}
	
}
