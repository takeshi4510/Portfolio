package jp.co.tk.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tk.entity.UserEntity;
import jp.co.tk.repository.UserRepository;

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
	public Map<String, String> Auth(String name, String password) {
		Map<String, String> authMap = new HashMap<>();
		authMap.put("Judg", "0");
		authMap.put("msg", "");

		//入力チェック用正規表現
		String str1 = ".+[a-z].+";
		String str2 = ".+[A-Z].+";
		String str3 = ".+[0-9].+";

		//Nameの空文字チェックとnullチェック
		if("".equals(name) || name.isEmpty()) {
			authMap.put("msg", "Nameが未入力です！");
			authMap.put("Judg", "1");
			return authMap;
		}

		//Nameの文字数チェック
		if(name.length() < 2) {
			authMap.put("msg", "Nameは2文字以上で作成してください！");
			authMap.put("Judg", "1");
			return authMap;
		}

		//Passwordの空文字チェックとnullチェック
		if("".equals(password) || password.isEmpty()) {
			authMap.put("msg", "Passwordが未入力です！");
			authMap.put("Judg", "1");
			return authMap;
		}

		//小文字のアルファベットが入ってるかチェック
		if(!password.matches(str1)) {
			authMap.put("msg", "半角英数字を組み合わせて作成してください");
			authMap.put("Judg", "1");
			return authMap;
		}

		//大文字のアルファベットが入ってるかチェック
		if(!password.matches(str2)) {
			authMap.put("msg", "半角英数字を組み合わせて作成してください");
			authMap.put("Judg", "1");
			return authMap;
		}

		//数字が入ってるかチェック
		if(!password.matches(str3)) {
			authMap.put("msg", "半角英数字を組み合わせて作成してください");
			authMap.put("Judg", "1");
			return authMap;
		}


		//Passwordの文字数チェック
		if(password.length() <= 5) {
			authMap.put("msg", "Passwordは6文字以上で作成してください！");
			authMap.put("Judg", "1");
			return authMap;
		}

		//DBに件数が一件でもある場合NG
		if(1 <= repository.findbyUser(name).size()) {
			authMap.put("msg", "すでに存在しているユーザです");
			authMap.put("Judg", "1");
			return authMap;
		}

		return authMap;
	}

	/**
	 * ユーザ新規登録処理
	 * @param request
	 * @return
	 */
	public UserEntity insert(HttpServletRequest request) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		UserEntity entity = new UserEntity();
		entity.setName(request.getParameter("name"));
		entity.setPassword(request.getParameter("password"));
		entity.setAdd_date(timestamp);
		return	repository.save(entity);
	}
}
