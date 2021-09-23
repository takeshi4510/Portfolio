package jp.co.tk.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean Auth(String name, String password) {

		if(name == null) {
			return false;
		}
		return true;
	}
}
