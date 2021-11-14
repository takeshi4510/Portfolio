package jp.co.tk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tk.entity.BbsHomeEntity;
import jp.co.tk.repository.BbsHomeRepository;

/**
 * bbs画面　service処理
 * @author Takeshi Nakasone
 *
 */
@Service
public class BbsHomeService {
	@Autowired
	BbsHomeRepository service;

	/**
	 * BbsHome画面の入力チェック
	 * @param request
	 * @return
	 */
	public String validateBbsInput(
			HttpServletRequest request) {

		//空文字を変数msgに格納
		String msg = "";

		//タイトルの入力チェック
		if("".equals(request.getParameter("title"))){
			msg = "タイトルを入力してください";
			return msg;
		}

		//内容の入力チェック
		if("".equals(request.getParameter("contents"))) {
			msg = "内容を入力してください";
			return msg;
		}

		return msg;

	}

	/**
	 * BbsHomeの登録処理
	 * @param request
	 * @param id
	 * @return
	 */
	public BbsHomeEntity insertBbs(
			HttpServletRequest request,
			int id) {

		LocalDateTime nowDateTime = LocalDateTime.now();

		BbsHomeEntity entity = new BbsHomeEntity();
		entity.setUser_id(id);
		entity.setTitle(request.getParameter("title"));
		entity.setName(request.getParameter("name"));
		entity.setContents(request.getParameter("contents"));
		entity.setAdd_date(nowDateTime);
		return service.save(entity);
	}

	/**
	 * BbsTableのデータを全件取得処理
	 * @return
	 */
	public List<BbsHomeEntity> findByBbsAll() {
		return service.findAllDesc();
	}


	/**
	 * Bbs削除処理
	 * @param user_id
	 * @return
	 */
	public int removeBbs(String userId) {
		return service.remove(userId);
	}

	/**
	 * 編集処理
	 * @param request
	 * @return
	 */
	public BbsHomeEntity updateBbs(HttpServletRequest request) {

		LocalDateTime nowDateTime = LocalDateTime.now();

		Optional<BbsHomeEntity> optinal = service.edit(request.getParameter("bbsId"));
		BbsHomeEntity entity = optinal.get();

		entity.setTitle(request.getParameter("title"));
		entity.setContents(request.getParameter("contents"));
		entity.setUp_date(nowDateTime);

		return service.save(entity);
	}

}
