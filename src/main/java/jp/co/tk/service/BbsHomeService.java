package jp.co.tk.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tk.entity.BbsHomeEntity;
import jp.co.tk.repository.BbsHomeRepository;

@Service
public class BbsHomeService {
	@Autowired
	BbsHomeRepository BbsHomeRepository;

	/**
	 * BbsHome画面の入力チェック
	 * @param request
	 * @return
	 */
	public Map<String, String> check(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		//チェック
		map.put("check", "0");
		//エラーメッセージを格納
		map.put("msg", "");

		//タイトルの入力チェック
		if("".equals(request.getParameter("title"))){
			map.put("check", "1");
			map.put("msg", "タイトルを入力してください");
			return map;
		}

		//内容の入力チェック
		if("".equals(request.getParameter("contents"))) {
			map.put("check", "1");
			map.put("msg", "内容を入力してください");
			return map;
		}

		return map;

	}

	/*
	 * BbsHomeの登録処理
	 */
	public BbsHomeEntity insert(HttpServletRequest request, int id) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		BbsHomeEntity entity = new BbsHomeEntity();
		entity.setUser_id(id);
		entity.setTitle(request.getParameter("title"));
		entity.setName(request.getParameter("name"));
		entity.setContents(request.getParameter("contents"));
		entity.setAdd_date(timestamp);
		return BbsHomeRepository.save(entity);
	}

	public List<BbsHomeEntity> select() {
		return BbsHomeRepository.findBySelectAll();
	}

	public int remove(String user_id) {
		return BbsHomeRepository.remove(user_id);
	}

	/**
	 * 編集処理
	 * @param request
	 * @return
	 */
	public BbsHomeEntity edit(HttpServletRequest request) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Optional<BbsHomeEntity> optinal = BbsHomeRepository.edit(request.getParameter("bbs_id"));
		BbsHomeEntity entity = optinal.get();

		entity.setTitle(request.getParameter("title"));
		entity.setContents(request.getParameter("contents"));
		entity.setUp_date(timestamp);

		return BbsHomeRepository.save(entity);
	}

}
