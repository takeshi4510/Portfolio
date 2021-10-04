package jp.co.tk.service;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.tk.entity.UserEntity;
import jp.co.tk.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	UserRepository repository;

	public Map<String, String> Auth(String name, String password) {
		//入力チェックの結果を入れるMapを作成
		Map<String, String> authMap = new HashMap<>();
		authMap.put("Judg", "normal");
		authMap.put("msg", "");

		//nameの空文字チェック
		if("".equals(name)) {
			authMap.put("msg", "Nameを入力してください");
			authMap.put("Judg", "error");
			return authMap;
		}
		//nameのnullチェック
		if(name.isEmpty()) {
			authMap.put("msg", "Nameを入力してください");
			authMap.put("Judg", "error");
			return authMap;
		}
		//passwordの空文字チェック
		if("".equals(password)) {
			authMap.put("msg", "Passwordを入力してください");
			authMap.put("Judg", "error");
			return authMap;
		}
		//passwordのnullチェック
		if(password.isEmpty()) {
			authMap.put("msg", "Passwordを入力してください");
			authMap.put("Judg", "error");
			return authMap;
		}
		//passwordの文字数チェック
		if(password.length() <= 5) {
			authMap.put("msg", "Passwordの入力数は6文字以上で入力してください");
			authMap.put("Judg", "error");
			return authMap;
		}
		//半角かチェック
		if(!password.matches("[a-zA-Z0-9]+")) {
			authMap.put("msg", "Passwordは半角英数字で入力してください");
			authMap.put("Judg", "error");
			return authMap;
		}
		return authMap;

	}
	//存在チェック
	public boolean login(String name, String password) {
		//入力された値が1件の場合チェックOK
		if(1 == repository.findbyUserAndPassword(name, password).size()) {
			return true;
		}
		return false;
	}

	//ログイン登録でDBに件数が一件でもある場合NG
	public boolean existenceCheck(String name) {
		//件数が一件でもある場合、チェックNG
		if(1 > repository.findbyUser(name).size()) {
			return true;
		}
		return false;
	}

	//認証チェックがOKの場合、登録処理を行う
	public UserEntity insert(HttpServletRequest request) {
		UserEntity entity = new UserEntity();
		entity.setName(request.getParameter("name"));
		entity.setPassword(request.getParameter("password"));
		return	repository.save(entity);
	}
}