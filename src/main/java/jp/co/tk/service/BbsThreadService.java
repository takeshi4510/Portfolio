package jp.co.tk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tk.entity.BbsHomeEntity;
import jp.co.tk.entity.BbsThreadEntity;
import jp.co.tk.repository.BbsHomeRepository;
import jp.co.tk.repository.BbsThreadRepository;

/**
 * BbsThreadに関するservice処理
 * @author Takeshi Nakasone
 *
 */
@Service
public class BbsThreadService {
	@Autowired
	BbsHomeRepository bbsHomeRepository;
	@Autowired
	BbsThreadRepository repository;
	@Autowired
	LoginService service;

	/*
	 * 入力チェック
	 */
	public String validateThreadInputs(
			HttpServletRequest request) {

		//空文字を変数msgにセット
		String msg = "";

		//内容の入力チェック
		if("".equals(request.getParameter("contents"))) {
			//エラーメッセージを変数msgに格納
			msg = "内容を入力してください";

			//メッセージを返す
			return msg;
		}

		//メッセージを返す
		return msg;
	}


	/**
	 * bbsTableからIdを元に値を取得処理
	 * @param id
	 * @return
	 */
	public List<BbsHomeEntity> findByBbs(Integer id) {


		//取得した値を返す
		return bbsHomeRepository.findByBbsId(id);
	}


	/**
	 * BbsThread画面の登録処理
	 * @param request
	 * @param bbsId
	 * @param userId
	 * @return
	 */
	public BbsThreadEntity insert(
			HttpServletRequest request,
			int bbsId,
			int userId) {

		//BbsThreadEntityクラスをnewする
		BbsThreadEntity entity = new BbsThreadEntity();

		LocalDateTime nowDateTime = LocalDateTime.now();

		//取得した値をentityにセット
		entity.setBbs_id(bbsId);
		entity.setUser_id(userId);
		entity.setName(service.getUserName(request.getSession().getAttribute("sessionId")));
		entity.setContents(request.getParameter("contents"));
		entity.setAdd_date(nowDateTime);

		//取得したデータを登録
		return repository.save(entity);
	}

	/**
	 * スレッドTable取得処理
	 * @param id
	 * @return
	 */
	public List<BbsThreadEntity> threadSelect(
			int id) {

		//IdをもとにスレッドTableから値を取得し返す
		return repository.findByBbsId(id);
	}

	/**
	 * スレッド画面のデータ削除処理
	 * @param thread_id
	 * @return
	 */
	public void removeThread(
			String thread_id) {

		Optional<BbsThreadEntity> entity = repository.findById(Integer.parseInt(thread_id));

		//スレッド削除
		repository.delete(entity.get());
	}


	/**
	 * スレッド画面の編集処理
	 * @param request
	 */
	public BbsThreadEntity updateThreadEdit(
			HttpServletRequest request) {

		Optional<BbsThreadEntity> optinal = repository.editBbsThread(request.getParameter("threadId"));

		BbsThreadEntity entity = optinal.get();

		entity.setContents(request.getParameter("contents"));

		//編集した値を更新
		return repository.save(entity);

	}

}
