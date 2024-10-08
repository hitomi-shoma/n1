package com.example.nagoyameshi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.repository.UserRepository;

@Service
public class PasswordService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public PasswordService (UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
	    this.passwordEncoder = passwordEncoder;
	}
	//新しいパスワードと元のパスワードが同じかどうかをチェックする。パスワードが同じ場合はtrueを返す。
	public boolean isSamePassword(String password, String passwordConfirmation) {
	    return password.equals(passwordConfirmation);
	}
	//ユーザーオブジェクトのパスワードをハッシュ化して更新、保存。
	public void updatePassword(User user, String newPassword) {
	    user.setPassword(passwordEncoder.encode(newPassword));
	    userRepository.save(user);
	}
}
