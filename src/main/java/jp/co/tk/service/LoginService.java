package jp.co.tk.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public Map<String, String> Auth(String name, String password) {
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
}