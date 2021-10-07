package jp.co.tk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tk.entity.BbsHomeEntity;
import jp.co.tk.repository.BbsHomeRepository;

@Service
public class BbsHomeService {
	@Autowired
	BbsHomeRepository repository;

	public Map<String, String> check(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		map.put("check", "normal");
		map.put("msg", "");
		if("".equals(request.getParameter("title"))){
			map.put("check", "normal");
			map.put("msg", "titleを入力してください");
			return map;
		}

		if("".equals(request.getParameter("name"))) {
			map.put("check", "normal");
			map.put("msg", "nameを入力してください");
			return map;
		}


		if("".equals(request.getParameter("contents"))) {
			map.put("check", "normal");
			map.put("msg", "内容を入力してください");
			return map;
		}

		return map;

	}

	public BbsHomeEntity insert(HttpServletRequest request) {
		BbsHomeEntity entity = new BbsHomeEntity();
		entity.setTitle(request.getParameter("title"));
		entity.setName(request.getParameter("name"));
		entity.setContents(request.getParameter("contents"));
		return repository.save(entity);
	}

	public List<BbsHomeEntity> select() {
		return repository.findBySelectAll();


	}

}
