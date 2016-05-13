package com.gdky.restfull.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdky.restfull.dao.IAsideMenuDao;
import com.gdky.restfull.entity.AsideMenu;

@Service
public class CommonService implements ICommonService {

	@Resource
	private IAsideMenuDao asideMenuDao;

	@Override
	public List<AsideMenu> getAsideMenu(String q, String l) {
		String para = " and visble = 1 ";

		if (q!=null && q.equals("all")){
			para = " ";
		}
		return asideMenuDao.getAsideMenu(para,l);
	}


	@Override
	public void updateMenu(AsideMenu asideMenu) {
		asideMenuDao.updateMenu(asideMenu);

	}

	@Override
	public AsideMenu getMenuDetail(String id) {
		return asideMenuDao.getMenuDetail(id);
	}

	@Override
	public Map<String, Object> addMenu(Map<String, Object> node) {
		String path = asideMenuDao.getPathById((Integer) node.get("pid"));

		path = path == null ? String.format("%03d", (Integer) node.get("pid"))
				: path + "-" + String.format("%03d", (Integer) node.get("pid"));

		AsideMenu item = new AsideMenu();
		item.setPid((Integer) node.get("pid"));
		// 0 代表前面补充0
		// 3 代表长度为3
		// d 代表参数为正数型
		item.setPath(path);
		item.setName((String) node.get("name"));
		item.setVisble((Integer) node.get("visble"));
		item.setLx((String) node.get("lx"));
		Number rs =  asideMenuDao.addMenu(item);
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("id", rs);
		map.put("path", path);
		return map;

	}

	@Override
	public int removeMenu(String id) {
		AsideMenu menu = asideMenuDao.getMenuDetail(id);
		return asideMenuDao.removeMenu(menu);
	}

	

}
