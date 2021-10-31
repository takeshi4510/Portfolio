package jp.co.tk.service;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tk.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	UserRepository UserRepository;

	/**
	 *Login時の入力チェック
	 * @param name
	 * @param password
	 * @return
	 */
	public Map<String, String> Auth(HttpServletRequest request) {
		Map<String, String> authMap = new HashMap<>();
		authMap.put("Judg", "0");
		authMap.put("msg", "");

		//Nameの空文字チェックとnullチェック
		if("".equals(request.getParameter("name")) || request.getParameter("name").isEmpty()) {
			authMap.put("msg", "Nameが未入力です！");
			authMap.put("Judg", "1");
			System.out.println("NG");
			return authMap;
		}

		//Passwordの空文字チェックとnullチェック
		if("".equals(request.getParameter("password")) || request.getParameter("password").isEmpty()) {
			authMap.put("msg", "Passwordが未入力です！");
			authMap.put("Judg", "1");
			return authMap;
		}

		//存在チェック
		if(1 != UserRepository.findbyUserAndPassword(request.getParameter("name"), request.getParameter("password")).size()) {
			authMap.put("msg", "NameまたはPasswordが間違っています");
			authMap.put("Judg", "1");
			return authMap;
		}

		return authMap;
	}

	//セッションに使用する値をユーザテーブルから取得
	public int selectId(String name, String password) {
		return UserRepository.selectId(name, password);
	}

	public String id(Object user_id) {

		return UserRepository.user_name(user_id);
	}

}