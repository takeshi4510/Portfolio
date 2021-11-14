package jp.co.tk.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tk.entity.UserEntity;
import jp.co.tk.repository.UserRepository;

/**
 * LoginRegistrationに関するService処理
 * @author Takeshi Nakasone
 *
 */
@Service
public class LoginRegistrationService {
	@Autowired
	UserRepository repository;

	/**
	 *Login登録時の入力チェック
	 * @param name
	 * @param password
	 * @return
	 */
	public String validateResistration(
			String name,
			String password) {

		//空文字を変数msgに格納
		String msg = "";

		//入力チェック用正規表現
		String str1 = ".*[a-z]+.";
		String str2 = ".*[A-Z]+.";
		String str3 = ".*[0-9]+.";

		//Nameの空文字チェックとnullチェック
		if("".equals(name) || name.isEmpty()) {
			msg = "Nameが未入力です！";
			return msg;
		}

		//Nameの文字数チェック
		if(name.length() < 2) {
			msg = "Nameは2文字以上で作成してください！";
			return msg;
		}

		//Passwordの空文字チェックとnullチェック
		if("".equals(password) || password.isEmpty()) {
			msg = "Passwordが未入力です！";
			return msg;
		}

		//Passwordの正規表現チェック
		boolean bResult = (password.matches(str1) && password.matches(str2) && password.matches(str3));
		if (bResult == false) {
			msg = "半角英数字を組み合わせて作成してください";
			return msg;
		}

		//Passwordの文字数チェック
		if(password.length() <= 3) {
			msg = "Passwordは6文字以上で作成してください！";
			return msg;
		}

		//DBに件数が一件でもある場合NG
		if(1 <= repository.findbyUser(name).size()) {
			msg = "すでに存在しているユーザです";
			return msg;
		}

		//メッセ―ジを返す
		return msg;
	}


	/**
	 * ログインユーザ新規登録処理
	 * @param request
	 * @return
	 */
	public UserEntity insertLoginResistration(
			HttpServletRequest request) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		UserEntity entity = new UserEntity();
		entity.setName(request.getParameter("name"));
		entity.setPassword(request.getParameter("password"));
		entity.setAdd_date(timestamp);

		//ログインユーザ登録
		return repository.save(entity);
	}
}
