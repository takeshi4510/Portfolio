package jp.co.tk.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tk.repository.UserRepository;

/**
 * Loginに関するService処理
 * @author Takeshi Nakasone
 *
 */
@Service
public class LoginService {
	@Autowired
	UserRepository repository;

	/**
	 *Login時の入力チェック
	 * @param name
	 * @param password
	 * @return
	 */
	public String validateLogin(
			HttpServletRequest request) {

		//空文字を変数msgに格納
		String msg = "";

		//Nameの空文字チェックとnullチェック
		if("".equals(request.getParameter("name")) || request.getParameter("name").isEmpty()) {
			msg = "Nameが未入力です！";
			return msg;
		}

		//Passwordの空文字チェックとnullチェック
		if("".equals(request.getParameter("password")) || request.getParameter("password").isEmpty()) {
			msg = "Passwordが未入力です！";
			return msg;
		}

		//存在チェック
		if(1 != repository.findbyUserAndPassword(request.getParameter("name"), request.getParameter("password")).size()) {
			msg = "NameまたはPasswordが間違っています";
			return msg;
		}

		//メッセージを変更
		return msg;
	}

	/**
	 * セッションに使用する値をユーザテーブルから取得
	 * @param name
	 * @param password
	 * @return
	 */
	public int selectId(
			String name,
			String password) {

		//Idを取得
		return repository.findById(name, password);
	}

	/**
	 * Idをもとにユーザ名を取得
	 * @param userId
	 * @return
	 */
	public String getUserName(Object userId) {

		//ユーザ名を取得
		return repository.findUserNameById(userId);
	}

}