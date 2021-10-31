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
import jp.co.tk.entity.BbsThreadEntity;
import jp.co.tk.repository.BbsHomeRepository;
import jp.co.tk.repository.BbsThreadRepository;

@Service
public class BbsThreadService {
	@Autowired
	BbsHomeRepository BbsHomeRepository;
	@Autowired
	BbsThreadRepository BbsThreadRepository;
	@Autowired
	LoginService LoginService;

	/*
	 * 入力チェック
	 */
	public Map<String, String> check(HttpServletRequest request) {
		Map<String, String> authMap = new HashMap<>();
		//チェック
		authMap.put("check", "0");
		//エラーメッセージを格納
		authMap.put("msg", "");

		//ネームの入力チェック
		if("".equals(request.getParameter("name"))) {
			authMap.put("check", "1");
			authMap.put("msg", "ネームを入力してください");
			return authMap;
		}

		//内容の入力チェック
		if("".equals(request.getParameter("contents"))) {
			authMap.put("check", "1");
			authMap.put("msg", "内容を入力してください");
			return authMap;
		}


		return authMap;
	}


	/*
	 *
	 */
	public List<BbsHomeEntity> select(Integer id) {
		return BbsHomeRepository.findbyId(id);
	}

	//BbsThread画面の登録処理
	public BbsThreadEntity insert(HttpServletRequest request, int bbs_id, int user_id) {
		BbsThreadEntity entity = new BbsThreadEntity();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		entity.setBbs_id(bbs_id);
		entity.setUser_id(user_id);
		entity.setName(LoginService.id(request.getSession().getAttribute("session_id")));
		entity.setContents(request.getParameter("contents"));
		entity.setAdd_date(timestamp);
		return BbsThreadRepository.save(entity);
	}

	//
	public List<BbsThreadEntity> threadSelect(int id) {
		return BbsThreadRepository.threadFingId(id);
	}

	//
	public int remove(String thread_id) {
		return BbsThreadRepository.delete(thread_id);
	}


	/**
	 * bbsthreadの編集処理
	 * @param request
	 */
	public BbsThreadEntity edit(HttpServletRequest request) {
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Optional<BbsThreadEntity> optinal = BbsThreadRepository.edit(request.getParameter("thread_id"));
		BbsThreadEntity entity = optinal.get();

		entity.setContents(request.getParameter("contents"));

		return BbsThreadRepository.save(entity);

	}

}
